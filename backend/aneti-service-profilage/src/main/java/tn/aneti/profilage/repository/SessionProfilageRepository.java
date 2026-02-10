package tn.aneti.profilage.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.aneti.profilage.entity.SessionProfilage;
import java.util.List;
import java.util.UUID;

@Repository
public interface SessionProfilageRepository extends JpaRepository<SessionProfilage, UUID> {
    List<SessionProfilage> findByChercheurEmploiId(UUID chercheurEmploiId);
    Page<SessionProfilage> findByConseillerId(UUID conseillerId, Pageable pageable);
    Page<SessionProfilage> findByStatut(SessionProfilage.StatutSession statut, Pageable pageable);
    long countBySegmentValide(SessionProfilage.Segment segment);
}
