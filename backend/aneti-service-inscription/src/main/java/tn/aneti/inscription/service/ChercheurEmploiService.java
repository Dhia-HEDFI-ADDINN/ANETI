package tn.aneti.inscription.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.aneti.common.exception.*;
import tn.aneti.inscription.dto.ChercheurEmploiDTO;
import tn.aneti.inscription.entity.ChercheurEmploi;
import tn.aneti.inscription.mapper.ChercheurEmploiMapper;
import tn.aneti.inscription.repository.ChercheurEmploiRepository;
import java.time.LocalDate;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class ChercheurEmploiService {

    private final ChercheurEmploiRepository repository;
    private final ChercheurEmploiMapper mapper;

    public ChercheurEmploiDTO creer(ChercheurEmploiDTO dto) {
        if (dto.getCin() != null && repository.existsByCin(dto.getCin())) {
            throw new DuplicateResourceException("Un chercheur d'emploi avec ce CIN existe déjà: " + dto.getCin());
        }
        if (repository.existsByEmail(dto.getEmail())) {
            throw new DuplicateResourceException("Un chercheur d'emploi avec cet email existe déjà: " + dto.getEmail());
        }
        ChercheurEmploi entity = mapper.toEntity(dto);
        entity.setStatutInscription(ChercheurEmploi.StatutInscription.BROUILLON);
        entity.setDateInscription(LocalDate.now());
        entity = repository.save(entity);
        log.info("Chercheur d'emploi créé: {}", entity.getId());
        return mapper.toDto(entity);
    }

    @Transactional(readOnly = true)
    public ChercheurEmploiDTO getById(UUID id) {
        return repository.findById(id)
                .map(mapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("Chercheur d'emploi", "id", id));
    }

    @Transactional(readOnly = true)
    public ChercheurEmploiDTO getByCin(String cin) {
        return repository.findByCin(cin)
                .map(mapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("Chercheur d'emploi", "CIN", cin));
    }

    public ChercheurEmploiDTO mettreAJour(UUID id, ChercheurEmploiDTO dto) {
        ChercheurEmploi entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Chercheur d'emploi", "id", id));
        mapper.updateEntityFromDto(dto, entity);
        entity = repository.save(entity);
        log.info("Chercheur d'emploi mis à jour: {}", id);
        return mapper.toDto(entity);
    }

    public ChercheurEmploiDTO soumettre(UUID id) {
        ChercheurEmploi entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Chercheur d'emploi", "id", id));
        if (entity.getStatutInscription() != ChercheurEmploi.StatutInscription.BROUILLON) {
            throw new BusinessException("Seule une inscription en brouillon peut être soumise");
        }
        entity.setStatutInscription(ChercheurEmploi.StatutInscription.SOUMIS);
        entity = repository.save(entity);
        log.info("Inscription soumise pour validation: {}", id);
        return mapper.toDto(entity);
    }

    public ChercheurEmploiDTO valider(UUID id) {
        ChercheurEmploi entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Chercheur d'emploi", "id", id));
        if (entity.getStatutInscription() != ChercheurEmploi.StatutInscription.SOUMIS &&
            entity.getStatutInscription() != ChercheurEmploi.StatutInscription.EN_COURS_VALIDATION) {
            throw new BusinessException("Cette inscription ne peut pas être validée dans son état actuel");
        }
        entity.setStatutInscription(ChercheurEmploi.StatutInscription.VALIDE);
        entity = repository.save(entity);
        log.info("Inscription validée: {}", id);
        return mapper.toDto(entity);
    }

    public ChercheurEmploiDTO rejeter(UUID id, String motif) {
        ChercheurEmploi entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Chercheur d'emploi", "id", id));
        entity.setStatutInscription(ChercheurEmploi.StatutInscription.REJETE);
        entity.setMotifRejet(motif);
        entity = repository.save(entity);
        log.info("Inscription rejetée: {} - Motif: {}", id, motif);
        return mapper.toDto(entity);
    }

    public ChercheurEmploiDTO affecterConseiller(UUID id, UUID conseillerId) {
        ChercheurEmploi entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Chercheur d'emploi", "id", id));
        entity.setConseillerId(conseillerId);
        entity = repository.save(entity);
        log.info("Chercheur d'emploi {} affecté au conseiller {}", id, conseillerId);
        return mapper.toDto(entity);
    }

    @Transactional(readOnly = true)
    public Page<ChercheurEmploiDTO> rechercher(String nom, String prenom, String gouvernorat,
                                                ChercheurEmploi.StatutInscription statut, Pageable pageable) {
        return repository.rechercher(nom, prenom, gouvernorat, statut, pageable)
                .map(mapper::toDto);
    }

    @Transactional(readOnly = true)
    public Page<ChercheurEmploiDTO> getByConseiller(UUID conseillerId, Pageable pageable) {
        return repository.findByConseillerId(conseillerId, pageable).map(mapper::toDto);
    }

    @Transactional(readOnly = true)
    public Page<ChercheurEmploiDTO> getByBeti(UUID betiId, Pageable pageable) {
        return repository.findByBetiId(betiId, pageable).map(mapper::toDto);
    }
}
