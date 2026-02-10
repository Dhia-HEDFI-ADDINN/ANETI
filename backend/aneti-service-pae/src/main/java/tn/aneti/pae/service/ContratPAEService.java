package tn.aneti.pae.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.aneti.common.exception.*;
import tn.aneti.pae.dto.ContratPAEDTO;
import tn.aneti.pae.entity.ContratPAE;
import tn.aneti.pae.repository.ContratPAERepository;
import java.time.LocalDate;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

@Service @RequiredArgsConstructor @Slf4j @Transactional
public class ContratPAEService {

    private final ContratPAERepository repository;
    private static final AtomicLong counter = new AtomicLong(System.currentTimeMillis());

    public ContratPAEDTO creer(ContratPAEDTO dto) {
        ContratPAE contrat = ContratPAE.builder()
                .chercheurEmploiId(dto.getChercheurEmploiId())
                .entrepriseId(dto.getEntrepriseId())
                .conseillerId(dto.getConseillerId())
                .betiId(dto.getBetiId())
                .typeContrat(ContratPAE.TypeContratPAE.valueOf(dto.getTypeContrat()))
                .numeroContrat(genererNumeroContrat(dto.getTypeContrat()))
                .dateDebut(dto.getDateDebut())
                .dateFin(dto.getDateFin())
                .dureeMois(dto.getDureeMois())
                .montantIndemnite(dto.getMontantIndemnite())
                .posteOccupe(dto.getPosteOccupe())
                .specialite(dto.getSpecialite())
                .lieuStage(dto.getLieuStage())
                .statut(ContratPAE.StatutContrat.BROUILLON)
                .renouvele(false)
                .contratParentId(dto.getContratParentId())
                .build();
        contrat = repository.save(contrat);
        log.info("Contrat PAE créé: {} - Type: {}", contrat.getNumeroContrat(), dto.getTypeContrat());
        return toDto(contrat);
    }

    @Transactional(readOnly = true)
    public ContratPAEDTO getById(UUID id) {
        return repository.findById(id).map(this::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("Contrat PAE", "id", id));
    }

    public ContratPAEDTO soumettre(UUID id) {
        ContratPAE c = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Contrat PAE", "id", id));
        if (c.getStatut() != ContratPAE.StatutContrat.BROUILLON) {
            throw new BusinessException("Seul un contrat en brouillon peut être soumis");
        }
        c.setStatut(ContratPAE.StatutContrat.SOUMIS);
        c = repository.save(c);
        return toDto(c);
    }

    public ContratPAEDTO valider(UUID id) {
        ContratPAE c = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Contrat PAE", "id", id));
        c.setStatut(ContratPAE.StatutContrat.VALIDE);
        c.setDateValidation(LocalDate.now());
        c = repository.save(c);
        log.info("Contrat PAE validé: {}", c.getNumeroContrat());
        return toDto(c);
    }

    public ContratPAEDTO activer(UUID id) {
        ContratPAE c = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Contrat PAE", "id", id));
        c.setStatut(ContratPAE.StatutContrat.ACTIF);
        c = repository.save(c);
        return toDto(c);
    }

    public ContratPAEDTO resilier(UUID id, String motif) {
        ContratPAE c = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Contrat PAE", "id", id));
        c.setStatut(ContratPAE.StatutContrat.RESILIE);
        c.setDateResiliation(LocalDate.now());
        c.setMotifResiliation(motif);
        c = repository.save(c);
        return toDto(c);
    }

    public ContratPAEDTO renouveler(UUID id, ContratPAEDTO dto) {
        ContratPAE original = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Contrat PAE", "id", id));
        original.setStatut(ContratPAE.StatutContrat.RENOUVELE);
        original.setRenouvele(true);
        repository.save(original);

        dto.setContratParentId(id);
        return creer(dto);
    }

    @Transactional(readOnly = true)
    public Page<ContratPAEDTO> getByChercheur(UUID chercheurId, Pageable pageable) {
        return repository.findByChercheurEmploiId(chercheurId, pageable).map(this::toDto);
    }

    @Transactional(readOnly = true)
    public Page<ContratPAEDTO> getByEntreprise(UUID entrepriseId, Pageable pageable) {
        return repository.findByEntrepriseId(entrepriseId, pageable).map(this::toDto);
    }

    @Transactional(readOnly = true)
    public Page<ContratPAEDTO> getByType(String type, Pageable pageable) {
        return repository.findByTypeContrat(ContratPAE.TypeContratPAE.valueOf(type), pageable).map(this::toDto);
    }

    private String genererNumeroContrat(String type) {
        return type + "-" + LocalDate.now().getYear() + "-" + String.format("%06d", counter.incrementAndGet() % 1000000);
    }

    private ContratPAEDTO toDto(ContratPAE c) {
        return ContratPAEDTO.builder()
                .id(c.getId()).chercheurEmploiId(c.getChercheurEmploiId())
                .entrepriseId(c.getEntrepriseId()).conseillerId(c.getConseillerId())
                .betiId(c.getBetiId()).typeContrat(c.getTypeContrat().name())
                .numeroContrat(c.getNumeroContrat()).dateDebut(c.getDateDebut())
                .dateFin(c.getDateFin()).dureeMois(c.getDureeMois())
                .montantIndemnite(c.getMontantIndemnite()).posteOccupe(c.getPosteOccupe())
                .specialite(c.getSpecialite()).lieuStage(c.getLieuStage())
                .statut(c.getStatut().name()).motifRejet(c.getMotifRejet())
                .dateValidation(c.getDateValidation()).dateResiliation(c.getDateResiliation())
                .motifResiliation(c.getMotifResiliation()).renouvele(c.getRenouvele())
                .contratParentId(c.getContratParentId())
                .build();
    }
}
