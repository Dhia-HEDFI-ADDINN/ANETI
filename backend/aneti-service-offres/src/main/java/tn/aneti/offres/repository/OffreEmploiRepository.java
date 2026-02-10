package tn.aneti.offres.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.aneti.offres.entity.OffreEmploi;
import java.util.UUID;

@Repository
public interface OffreEmploiRepository extends JpaRepository<OffreEmploi, UUID> {
    Page<OffreEmploi> findByEntrepriseId(UUID entrepriseId, Pageable pageable);
    Page<OffreEmploi> findByStatut(OffreEmploi.StatutOffre statut, Pageable pageable);

    @Query("SELECT o FROM OffreEmploi o WHERE " +
           "(:titre IS NULL OR LOWER(o.titre) LIKE LOWER(CONCAT('%', :titre, '%'))) AND " +
           "(:secteur IS NULL OR o.secteurActivite = :secteur) AND " +
           "(:gouvernorat IS NULL OR o.gouvernorat = :gouvernorat) AND " +
           "(:typeContrat IS NULL OR o.typeContrat = :typeContrat) AND " +
           "o.statut = 'PUBLIEE' AND o.active = true")
    Page<OffreEmploi> rechercherPubliques(
            @Param("titre") String titre, @Param("secteur") String secteur,
            @Param("gouvernorat") String gouvernorat, @Param("typeContrat") String typeContrat,
            Pageable pageable);
}
