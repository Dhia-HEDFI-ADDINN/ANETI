package tn.aneti.accompagnement.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.aneti.accompagnement.entity.PlanAccompagnement;
import java.util.List;
import java.util.UUID;

@Repository
public interface PlanAccompagnementRepository extends JpaRepository<PlanAccompagnement, UUID> {
    List<PlanAccompagnement> findByChercheurEmploiId(UUID chercheurEmploiId);
    Page<PlanAccompagnement> findByConseillerId(UUID conseillerId, Pageable pageable);
    Page<PlanAccompagnement> findByStatut(PlanAccompagnement.StatutPlan statut, Pageable pageable);
    long countByConseillerIdAndStatut(UUID conseillerId, PlanAccompagnement.StatutPlan statut);
}
