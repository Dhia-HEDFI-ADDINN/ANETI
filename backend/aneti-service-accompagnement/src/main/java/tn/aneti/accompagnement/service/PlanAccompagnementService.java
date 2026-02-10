package tn.aneti.accompagnement.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.aneti.common.exception.*;
import tn.aneti.accompagnement.dto.*;
import tn.aneti.accompagnement.entity.*;
import tn.aneti.accompagnement.repository.*;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service @RequiredArgsConstructor @Slf4j @Transactional
public class PlanAccompagnementService {

    private final PlanAccompagnementRepository repository;

    public PlanAccompagnementDTO creer(PlanAccompagnementDTO dto) {
        PlanAccompagnement plan = PlanAccompagnement.builder()
                .chercheurEmploiId(dto.getChercheurEmploiId())
                .conseillerId(dto.getConseillerId())
                .segment(dto.getSegment())
                .objectifPrincipal(dto.getObjectifPrincipal())
                .dateDebut(dto.getDateDebut())
                .dateFinPrevue(dto.getDateFinPrevue())
                .statut(PlanAccompagnement.StatutPlan.BROUILLON)
                .build();

        if (dto.getActions() != null) {
            for (ActionAccompagnementDTO actionDto : dto.getActions()) {
                ActionAccompagnement action = ActionAccompagnement.builder()
                        .plan(plan)
                        .typeAction(actionDto.getTypeAction())
                        .description(actionDto.getDescription())
                        .dateDebut(actionDto.getDateDebut())
                        .dateFinPrevue(actionDto.getDateFinPrevue())
                        .statut(ActionAccompagnement.StatutAction.PLANIFIEE)
                        .priorite(actionDto.getPriorite())
                        .build();
                plan.getActions().add(action);
            }
        }

        plan = repository.save(plan);
        log.info("Plan d'accompagnement créé: {}", plan.getId());
        return toDto(plan);
    }

    @Transactional(readOnly = true)
    public PlanAccompagnementDTO getById(UUID id) {
        return repository.findById(id).map(this::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("Plan d'accompagnement", "id", id));
    }

    @Transactional(readOnly = true)
    public List<PlanAccompagnementDTO> getByChercheur(UUID chercheurEmploiId) {
        return repository.findByChercheurEmploiId(chercheurEmploiId).stream().map(this::toDto).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Page<PlanAccompagnementDTO> getByConseiller(UUID conseillerId, Pageable pageable) {
        return repository.findByConseillerId(conseillerId, pageable).map(this::toDto);
    }

    public PlanAccompagnementDTO valider(UUID id) {
        PlanAccompagnement plan = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Plan d'accompagnement", "id", id));
        plan.setStatut(PlanAccompagnement.StatutPlan.VALIDE);
        plan = repository.save(plan);
        log.info("Plan d'accompagnement validé: {}", id);
        return toDto(plan);
    }

    public PlanAccompagnementDTO demarrer(UUID id) {
        PlanAccompagnement plan = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Plan d'accompagnement", "id", id));
        plan.setStatut(PlanAccompagnement.StatutPlan.EN_COURS);
        plan = repository.save(plan);
        return toDto(plan);
    }

    public PlanAccompagnementDTO terminer(UUID id, String bilan) {
        PlanAccompagnement plan = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Plan d'accompagnement", "id", id));
        plan.setStatut(PlanAccompagnement.StatutPlan.TERMINE);
        plan.setBilan(bilan);
        plan.setDateFinEffective(java.time.LocalDate.now());
        plan = repository.save(plan);
        return toDto(plan);
    }

    private PlanAccompagnementDTO toDto(PlanAccompagnement plan) {
        List<ActionAccompagnementDTO> actions = plan.getActions().stream()
                .map(a -> ActionAccompagnementDTO.builder()
                        .id(a.getId()).typeAction(a.getTypeAction()).description(a.getDescription())
                        .dateDebut(a.getDateDebut()).dateFinPrevue(a.getDateFinPrevue())
                        .dateRealisation(a.getDateRealisation()).statut(a.getStatut().name())
                        .resultat(a.getResultat()).commentaire(a.getCommentaire()).priorite(a.getPriorite())
                        .build())
                .collect(Collectors.toList());

        return PlanAccompagnementDTO.builder()
                .id(plan.getId()).chercheurEmploiId(plan.getChercheurEmploiId())
                .conseillerId(plan.getConseillerId()).segment(plan.getSegment())
                .objectifPrincipal(plan.getObjectifPrincipal()).dateDebut(plan.getDateDebut())
                .dateFinPrevue(plan.getDateFinPrevue()).dateFinEffective(plan.getDateFinEffective())
                .statut(plan.getStatut().name()).bilan(plan.getBilan()).actions(actions)
                .build();
    }
}
