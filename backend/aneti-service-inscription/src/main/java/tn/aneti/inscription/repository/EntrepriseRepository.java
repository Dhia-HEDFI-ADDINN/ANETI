package tn.aneti.inscription.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.aneti.inscription.entity.Entreprise;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface EntrepriseRepository extends JpaRepository<Entreprise, UUID> {
    Optional<Entreprise> findByMatriculeFiscal(String matriculeFiscal);
    Optional<Entreprise> findByEmail(String email);
    boolean existsByMatriculeFiscal(String matriculeFiscal);
    boolean existsByEmail(String email);
    Page<Entreprise> findByStatutInscription(Entreprise.StatutInscription statut, Pageable pageable);
    Page<Entreprise> findByGouvernorat(String gouvernorat, Pageable pageable);

    @Query("SELECT e FROM Entreprise e WHERE " +
           "(:raisonSociale IS NULL OR LOWER(e.raisonSociale) LIKE LOWER(CONCAT('%', :raisonSociale, '%'))) AND " +
           "(:secteur IS NULL OR e.secteurActivite = :secteur) AND " +
           "(:gouvernorat IS NULL OR e.gouvernorat = :gouvernorat) AND " +
           "(:statut IS NULL OR e.statutInscription = :statut) AND " +
           "e.active = true")
    Page<Entreprise> rechercher(
            @Param("raisonSociale") String raisonSociale,
            @Param("secteur") String secteur,
            @Param("gouvernorat") String gouvernorat,
            @Param("statut") Entreprise.StatutInscription statut,
            Pageable pageable);
}
