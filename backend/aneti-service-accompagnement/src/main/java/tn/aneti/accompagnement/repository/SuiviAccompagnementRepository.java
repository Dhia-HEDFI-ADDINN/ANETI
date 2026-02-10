package tn.aneti.accompagnement.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.aneti.accompagnement.entity.SuiviAccompagnement;
import java.util.UUID;

@Repository
public interface SuiviAccompagnementRepository extends JpaRepository<SuiviAccompagnement, UUID> {
    Page<SuiviAccompagnement> findByChercheurEmploiIdOrderByDateSuiviDesc(UUID chercheurEmploiId, Pageable pageable);
    Page<SuiviAccompagnement> findByConseillerIdOrderByDateSuiviDesc(UUID conseillerId, Pageable pageable);
}
