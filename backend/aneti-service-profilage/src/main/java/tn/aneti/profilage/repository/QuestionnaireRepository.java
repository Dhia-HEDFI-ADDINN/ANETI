package tn.aneti.profilage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.aneti.profilage.entity.Questionnaire;
import java.util.List;
import java.util.UUID;

@Repository
public interface QuestionnaireRepository extends JpaRepository<Questionnaire, UUID> {
    List<Questionnaire> findByStatut(Questionnaire.StatutQuestionnaire statut);
}
