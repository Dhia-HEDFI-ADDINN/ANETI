package tn.aneti.pae.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.aneti.pae.entity.ContratPAE;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ContratPAERepository extends JpaRepository<ContratPAE, UUID> {
    Page<ContratPAE> findByChercheurEmploiId(UUID chercheurEmploiId, Pageable pageable);
    Page<ContratPAE> findByEntrepriseId(UUID entrepriseId, Pageable pageable);
    Page<ContratPAE> findByStatut(ContratPAE.StatutContrat statut, Pageable pageable);
    Page<ContratPAE> findByTypeContrat(ContratPAE.TypeContratPAE type, Pageable pageable);
    Optional<ContratPAE> findByNumeroContrat(String numeroContrat);
    long countByTypeContratAndStatut(ContratPAE.TypeContratPAE type, ContratPAE.StatutContrat statut);
}
