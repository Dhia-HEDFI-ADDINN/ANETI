package tn.aneti.rdv.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.aneti.common.exception.*;
import tn.aneti.rdv.dto.RendezVousDTO;
import tn.aneti.rdv.entity.RendezVous;
import tn.aneti.rdv.repository.RendezVousRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service @RequiredArgsConstructor @Slf4j @Transactional
public class RendezVousService {
    private final RendezVousRepository repository;

    public RendezVousDTO creer(RendezVousDTO dto) {
        RendezVous rdv = RendezVous.builder()
                .conseillerId(dto.getConseillerId()).chercheurEmploiId(dto.getChercheurEmploiId())
                .entrepriseId(dto.getEntrepriseId()).titre(dto.getTitre()).description(dto.getDescription())
                .dateDebut(dto.getDateDebut()).dateFin(dto.getDateFin())
                .typeRdv(RendezVous.TypeRdv.valueOf(dto.getTypeRdv()))
                .canal(dto.getCanal() != null ? RendezVous.Canal.valueOf(dto.getCanal()) : RendezVous.Canal.PRESENTIEL)
                .lieu(dto.getLieu()).lienVisio(dto.getLienVisio()).statut(RendezVous.StatutRdv.PLANIFIE)
                .build();
        rdv = repository.save(rdv);
        log.info("RDV créé: {}", rdv.getId());
        return toDto(rdv);
    }

    @Transactional(readOnly = true)
    public RendezVousDTO getById(UUID id) {
        return repository.findById(id).map(this::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("Rendez-vous", "id", id));
    }

    public RendezVousDTO changerStatut(UUID id, String statut) {
        RendezVous rdv = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Rendez-vous", "id", id));
        rdv.setStatut(RendezVous.StatutRdv.valueOf(statut));
        rdv = repository.save(rdv);
        return toDto(rdv);
    }

    public RendezVousDTO annuler(UUID id, String motif) {
        RendezVous rdv = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Rendez-vous", "id", id));
        rdv.setStatut(RendezVous.StatutRdv.ANNULE);
        rdv.setMotifAnnulation(motif);
        rdv = repository.save(rdv);
        return toDto(rdv);
    }

    @Transactional(readOnly = true)
    public Page<RendezVousDTO> getByConseiller(UUID conseillerId, Pageable pageable) {
        return repository.findByConseillerId(conseillerId, pageable).map(this::toDto);
    }

    @Transactional(readOnly = true)
    public Page<RendezVousDTO> getByChercheur(UUID chercheurId, Pageable pageable) {
        return repository.findByChercheurEmploiId(chercheurId, pageable).map(this::toDto);
    }

    @Transactional(readOnly = true)
    public List<RendezVousDTO> getAgenda(UUID conseillerId, LocalDateTime debut, LocalDateTime fin) {
        return repository.findByConseillerIdAndDateRange(conseillerId, debut, fin).stream()
                .map(this::toDto).collect(Collectors.toList());
    }

    private RendezVousDTO toDto(RendezVous r) {
        return RendezVousDTO.builder().id(r.getId()).conseillerId(r.getConseillerId())
                .chercheurEmploiId(r.getChercheurEmploiId()).entrepriseId(r.getEntrepriseId())
                .titre(r.getTitre()).description(r.getDescription()).dateDebut(r.getDateDebut())
                .dateFin(r.getDateFin()).typeRdv(r.getTypeRdv().name())
                .canal(r.getCanal() != null ? r.getCanal().name() : null)
                .lieu(r.getLieu()).lienVisio(r.getLienVisio()).statut(r.getStatut().name())
                .compteRendu(r.getCompteRendu()).motifAnnulation(r.getMotifAnnulation()).build();
    }
}
