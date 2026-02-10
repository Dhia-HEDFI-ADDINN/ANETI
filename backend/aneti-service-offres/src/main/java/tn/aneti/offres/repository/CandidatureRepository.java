package tn.aneti.offres.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.aneti.offres.entity.Candidature;
import java.util.UUID;

@Repository
public interface CandidatureRepository extends JpaRepository<Candidature, UUID> {
    Page<Candidature> findByOffreId(UUID offreId, Pageable pageable);
    Page<Candidature> findByChercheurEmploiId(UUID chercheurEmploiId, Pageable pageable);
    boolean existsByOffreIdAndChercheurEmploiId(UUID offreId, UUID chercheurEmploiId);
    long countByOffreId(UUID offreId);
}
