package tn.aneti.inscription.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.aneti.inscription.entity.ChercheurEmploi;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ChercheurEmploiRepository extends JpaRepository<ChercheurEmploi, UUID> {
    Optional<ChercheurEmploi> findByCin(String cin);
    Optional<ChercheurEmploi> findByEmail(String email);
    boolean existsByCin(String cin);
    boolean existsByEmail(String email);
    Page<ChercheurEmploi> findByStatutInscription(ChercheurEmploi.StatutInscription statut, Pageable pageable);
    Page<ChercheurEmploi> findByBetiId(UUID betiId, Pageable pageable);
    Page<ChercheurEmploi> findByConseillerId(UUID conseillerId, Pageable pageable);

    @Query("SELECT c FROM ChercheurEmploi c WHERE " +
           "(:nom IS NULL OR LOWER(c.nom) LIKE LOWER(CONCAT('%', :nom, '%'))) AND " +
           "(:prenom IS NULL OR LOWER(c.prenom) LIKE LOWER(CONCAT('%', :prenom, '%'))) AND " +
           "(:gouvernorat IS NULL OR c.gouvernorat = :gouvernorat) AND " +
           "(:statut IS NULL OR c.statutInscription = :statut) AND " +
           "c.active = true")
    Page<ChercheurEmploi> rechercher(
            @Param("nom") String nom,
            @Param("prenom") String prenom,
            @Param("gouvernorat") String gouvernorat,
            @Param("statut") ChercheurEmploi.StatutInscription statut,
            Pageable pageable);

    long countByStatutInscription(ChercheurEmploi.StatutInscription statut);
}
