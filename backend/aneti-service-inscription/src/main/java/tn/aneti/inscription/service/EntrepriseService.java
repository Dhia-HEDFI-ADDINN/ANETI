package tn.aneti.inscription.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.aneti.common.exception.*;
import tn.aneti.inscription.dto.EntrepriseDTO;
import tn.aneti.inscription.entity.Entreprise;
import tn.aneti.inscription.mapper.EntrepriseMapper;
import tn.aneti.inscription.repository.EntrepriseRepository;
import java.time.LocalDate;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class EntrepriseService {

    private final EntrepriseRepository repository;
    private final EntrepriseMapper mapper;

    public EntrepriseDTO creer(EntrepriseDTO dto) {
        if (dto.getMatriculeFiscal() != null && repository.existsByMatriculeFiscal(dto.getMatriculeFiscal())) {
            throw new DuplicateResourceException("Une entreprise avec ce matricule fiscal existe déjà");
        }
        if (repository.existsByEmail(dto.getEmail())) {
            throw new DuplicateResourceException("Une entreprise avec cet email existe déjà");
        }
        Entreprise entity = mapper.toEntity(dto);
        entity.setStatutInscription(Entreprise.StatutInscription.BROUILLON);
        entity.setDateInscription(LocalDate.now());
        entity = repository.save(entity);
        log.info("Entreprise créée: {}", entity.getId());
        return mapper.toDto(entity);
    }

    @Transactional(readOnly = true)
    public EntrepriseDTO getById(UUID id) {
        return repository.findById(id)
                .map(mapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("Entreprise", "id", id));
    }

    public EntrepriseDTO mettreAJour(UUID id, EntrepriseDTO dto) {
        Entreprise entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Entreprise", "id", id));
        mapper.updateEntityFromDto(dto, entity);
        entity = repository.save(entity);
        log.info("Entreprise mise à jour: {}", id);
        return mapper.toDto(entity);
    }

    public EntrepriseDTO soumettre(UUID id) {
        Entreprise entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Entreprise", "id", id));
        if (entity.getStatutInscription() != Entreprise.StatutInscription.BROUILLON) {
            throw new BusinessException("Seule une inscription en brouillon peut être soumise");
        }
        entity.setStatutInscription(Entreprise.StatutInscription.SOUMIS);
        entity = repository.save(entity);
        log.info("Inscription entreprise soumise: {}", id);
        return mapper.toDto(entity);
    }

    public EntrepriseDTO valider(UUID id) {
        Entreprise entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Entreprise", "id", id));
        if (entity.getStatutInscription() != Entreprise.StatutInscription.SOUMIS) {
            throw new BusinessException("Cette inscription ne peut pas être validée dans son état actuel");
        }
        entity.setStatutInscription(Entreprise.StatutInscription.VALIDE);
        entity = repository.save(entity);
        log.info("Inscription entreprise validée: {}", id);
        return mapper.toDto(entity);
    }

    public EntrepriseDTO rejeter(UUID id, String motif) {
        Entreprise entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Entreprise", "id", id));
        entity.setStatutInscription(Entreprise.StatutInscription.REJETE);
        entity.setMotifRejet(motif);
        entity = repository.save(entity);
        log.info("Inscription entreprise rejetée: {} - Motif: {}", id, motif);
        return mapper.toDto(entity);
    }

    @Transactional(readOnly = true)
    public Page<EntrepriseDTO> rechercher(String raisonSociale, String secteur, String gouvernorat,
                                           Entreprise.StatutInscription statut, Pageable pageable) {
        return repository.rechercher(raisonSociale, secteur, gouvernorat, statut, pageable)
                .map(mapper::toDto);
    }
}
