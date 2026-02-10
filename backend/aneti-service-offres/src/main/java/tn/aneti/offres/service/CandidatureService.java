package tn.aneti.offres.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.aneti.common.exception.*;
import tn.aneti.offres.dto.CandidatureDTO;
import tn.aneti.offres.entity.Candidature;
import tn.aneti.offres.repository.CandidatureRepository;
import java.time.LocalDateTime;
import java.util.UUID;

@Service @RequiredArgsConstructor @Slf4j @Transactional
public class CandidatureService {

    private final CandidatureRepository repository;

    public CandidatureDTO postuler(CandidatureDTO dto) {
        if (repository.existsByOffreIdAndChercheurEmploiId(dto.getOffreId(), dto.getChercheurEmploiId())) {
            throw new DuplicateResourceException("Vous avez déjà postulé à cette offre");
        }
        Candidature candidature = Candidature.builder()
                .offreId(dto.getOffreId()).chercheurEmploiId(dto.getChercheurEmploiId())
                .lettreMotivation(dto.getLettreMotivation()).cvDocumentId(dto.getCvDocumentId())
                .dateCandidature(LocalDateTime.now()).statut(Candidature.StatutCandidature.SOUMISE)
                .build();
        candidature = repository.save(candidature);
        log.info("Candidature soumise: offre={}, CE={}", dto.getOffreId(), dto.getChercheurEmploiId());
        return toDto(candidature);
    }

    @Transactional(readOnly = true)
    public Page<CandidatureDTO> getByOffre(UUID offreId, Pageable pageable) {
        return repository.findByOffreId(offreId, pageable).map(this::toDto);
    }

    @Transactional(readOnly = true)
    public Page<CandidatureDTO> getByChercheur(UUID chercheurEmploiId, Pageable pageable) {
        return repository.findByChercheurEmploiId(chercheurEmploiId, pageable).map(this::toDto);
    }

    public CandidatureDTO changerStatut(UUID id, String statut, String commentaire) {
        Candidature c = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Candidature", "id", id));
        c.setStatut(Candidature.StatutCandidature.valueOf(statut));
        if (commentaire != null) c.setCommentaireConseiller(commentaire);
        c = repository.save(c);
        return toDto(c);
    }

    private CandidatureDTO toDto(Candidature c) {
        return CandidatureDTO.builder()
                .id(c.getId()).offreId(c.getOffreId()).chercheurEmploiId(c.getChercheurEmploiId())
                .lettreMotivation(c.getLettreMotivation()).cvDocumentId(c.getCvDocumentId())
                .scoreMatching(c.getScoreMatching()).dateCandidature(c.getDateCandidature())
                .statut(c.getStatut().name()).commentaireConseiller(c.getCommentaireConseiller())
                .commentaireEntreprise(c.getCommentaireEntreprise())
                .dateEntretien(c.getDateEntretien()).resultatEntretien(c.getResultatEntretien())
                .build();
    }
}
