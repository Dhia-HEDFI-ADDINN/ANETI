package tn.aneti.rdv.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.aneti.rdv.entity.RendezVous;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface RendezVousRepository extends JpaRepository<RendezVous, UUID> {
    Page<RendezVous> findByConseillerId(UUID conseillerId, Pageable pageable);
    Page<RendezVous> findByChercheurEmploiId(UUID chercheurEmploiId, Pageable pageable);
    @Query("SELECT r FROM RendezVous r WHERE r.conseillerId = :conseillerId AND r.dateDebut BETWEEN :debut AND :fin")
    List<RendezVous> findByConseillerIdAndDateRange(@Param("conseillerId") UUID conseillerId, @Param("debut") LocalDateTime debut, @Param("fin") LocalDateTime fin);
    Page<RendezVous> findByStatut(RendezVous.StatutRdv statut, Pageable pageable);
}
