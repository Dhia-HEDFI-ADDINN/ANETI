package tn.aneti.offres.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.aneti.common.exception.*;
import tn.aneti.offres.dto.OffreEmploiDTO;
import tn.aneti.offres.entity.OffreEmploi;
import tn.aneti.offres.repository.OffreEmploiRepository;
import java.time.LocalDate;
import java.util.UUID;

@Service @RequiredArgsConstructor @Slf4j @Transactional
public class OffreEmploiService {

    private final OffreEmploiRepository repository;

    public OffreEmploiDTO creer(OffreEmploiDTO dto) {
        OffreEmploi offre = OffreEmploi.builder()
                .entrepriseId(dto.getEntrepriseId()).titre(dto.getTitre()).titreAr(dto.getTitreAr())
                .description(dto.getDescription()).descriptionAr(dto.getDescriptionAr())
                .typeContrat(dto.getTypeContrat()).secteurActivite(dto.getSecteurActivite())
                .metier(dto.getMetier()).codeRtmc(dto.getCodeRtmc())
                .niveauEtudeRequis(dto.getNiveauEtudeRequis()).experienceRequise(dto.getExperienceRequise())
                .competencesRequises(dto.getCompetencesRequises()).languesRequises(dto.getLanguesRequises())
                .gouvernorat(dto.getGouvernorat()).delegation(dto.getDelegation()).lieuTravail(dto.getLieuTravail())
                .salaireMin(dto.getSalaireMin()).salaireMax(dto.getSalaireMax())
                .nombrePostes(dto.getNombrePostes()).dateExpiration(dto.getDateExpiration())
                .statut(OffreEmploi.StatutOffre.BROUILLON).nombreCandidatures(0)
                .build();
        offre = repository.save(offre);
        log.info("Offre d'emploi créée: {}", offre.getId());
        return toDto(offre);
    }

    @Transactional(readOnly = true)
    public OffreEmploiDTO getById(UUID id) {
        return repository.findById(id).map(this::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("Offre d'emploi", "id", id));
    }

    public OffreEmploiDTO publier(UUID id) {
        OffreEmploi offre = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Offre d'emploi", "id", id));
        offre.setStatut(OffreEmploi.StatutOffre.PUBLIEE);
        offre.setDatePublication(LocalDate.now());
        offre = repository.save(offre);
        log.info("Offre publiée: {}", id);
        return toDto(offre);
    }

    public OffreEmploiDTO suspendre(UUID id) {
        OffreEmploi offre = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Offre d'emploi", "id", id));
        offre.setStatut(OffreEmploi.StatutOffre.SUSPENDUE);
        offre = repository.save(offre);
        return toDto(offre);
    }

    public OffreEmploiDTO pourvoir(UUID id) {
        OffreEmploi offre = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Offre d'emploi", "id", id));
        offre.setStatut(OffreEmploi.StatutOffre.POURVUE);
        offre = repository.save(offre);
        return toDto(offre);
    }

    @Transactional(readOnly = true)
    public Page<OffreEmploiDTO> rechercherPubliques(String titre, String secteur, String gouvernorat, String typeContrat, Pageable pageable) {
        return repository.rechercherPubliques(titre, secteur, gouvernorat, typeContrat, pageable).map(this::toDto);
    }

    @Transactional(readOnly = true)
    public Page<OffreEmploiDTO> getByEntreprise(UUID entrepriseId, Pageable pageable) {
        return repository.findByEntrepriseId(entrepriseId, pageable).map(this::toDto);
    }

    private OffreEmploiDTO toDto(OffreEmploi o) {
        return OffreEmploiDTO.builder()
                .id(o.getId()).entrepriseId(o.getEntrepriseId()).titre(o.getTitre()).titreAr(o.getTitreAr())
                .description(o.getDescription()).descriptionAr(o.getDescriptionAr()).typeContrat(o.getTypeContrat())
                .secteurActivite(o.getSecteurActivite()).metier(o.getMetier()).codeRtmc(o.getCodeRtmc())
                .niveauEtudeRequis(o.getNiveauEtudeRequis()).experienceRequise(o.getExperienceRequise())
                .competencesRequises(o.getCompetencesRequises()).languesRequises(o.getLanguesRequises())
                .gouvernorat(o.getGouvernorat()).delegation(o.getDelegation()).lieuTravail(o.getLieuTravail())
                .salaireMin(o.getSalaireMin()).salaireMax(o.getSalaireMax()).nombrePostes(o.getNombrePostes())
                .datePublication(o.getDatePublication()).dateExpiration(o.getDateExpiration())
                .statut(o.getStatut().name()).betiId(o.getBetiId()).conseillerId(o.getConseillerId())
                .nombreCandidatures(o.getNombreCandidatures())
                .build();
    }
}
