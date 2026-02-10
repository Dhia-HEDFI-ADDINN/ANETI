package tn.aneti.profilage.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.aneti.common.exception.*;
import tn.aneti.profilage.dto.*;
import tn.aneti.profilage.entity.*;
import tn.aneti.profilage.repository.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class SessionProfilageService {

    private final SessionProfilageRepository sessionRepository;
    private final QuestionnaireRepository questionnaireRepository;
    private final SegmentationEngine segmentationEngine;

    public SessionProfilageDTO demarrerSession(UUID chercheurEmploiId, UUID questionnaireId, UUID conseillerId) {
        Questionnaire questionnaire = questionnaireRepository.findById(questionnaireId)
                .orElseThrow(() -> new ResourceNotFoundException("Questionnaire", "id", questionnaireId));

        SessionProfilage session = SessionProfilage.builder()
                .chercheurEmploiId(chercheurEmploiId)
                .conseillerId(conseillerId)
                .questionnaire(questionnaire)
                .statut(SessionProfilage.StatutSession.EN_COURS)
                .dateDebut(LocalDateTime.now())
                .build();

        session = sessionRepository.save(session);
        log.info("Session de profilage démarrée pour CE: {}", chercheurEmploiId);
        return toDto(session);
    }

    public SessionProfilageDTO soumettreReponses(UUID sessionId, List<ReponseDTO> reponseDTOs) {
        SessionProfilage session = sessionRepository.findById(sessionId)
                .orElseThrow(() -> new ResourceNotFoundException("Session profilage", "id", sessionId));

        if (session.getStatut() != SessionProfilage.StatutSession.EN_COURS) {
            throw new BusinessException("La session n'est plus en cours de saisie");
        }

        session.getReponses().clear();
        for (ReponseDTO dto : reponseDTOs) {
            Reponse reponse = Reponse.builder()
                    .session(session)
                    .questionId(dto.getQuestionId())
                    .optionId(dto.getOptionId())
                    .valeurTexte(dto.getValeurTexte())
                    .valeurNumerique(dto.getValeurNumerique())
                    .score(dto.getScore())
                    .build();
            session.getReponses().add(reponse);
        }

        Double score = segmentationEngine.calculerScore(session.getReponses());
        SessionProfilage.Segment segment = segmentationEngine.calculerSegment(score, session.getReponses());

        session.setScoreGlobal(score);
        session.setSegmentPropose(segment);
        session.setStatut(SessionProfilage.StatutSession.SOUMIS);
        session.setDateFin(LocalDateTime.now());

        session = sessionRepository.save(session);
        log.info("Réponses soumises pour session {} - Score: {} - Segment proposé: {}", sessionId, score, segment);
        return toDto(session);
    }

    public SessionProfilageDTO validerSegment(UUID sessionId, String segmentValide, String commentaire) {
        SessionProfilage session = sessionRepository.findById(sessionId)
                .orElseThrow(() -> new ResourceNotFoundException("Session profilage", "id", sessionId));

        session.setSegmentValide(SessionProfilage.Segment.valueOf(segmentValide));
        session.setCommentaireConseiller(commentaire);
        session.setStatut(SessionProfilage.StatutSession.VALIDE);
        session.setDateValidation(LocalDateTime.now());

        session = sessionRepository.save(session);
        log.info("Segment validé pour session {}: {}", sessionId, segmentValide);
        return toDto(session);
    }

    @Transactional(readOnly = true)
    public SessionProfilageDTO getById(UUID id) {
        return sessionRepository.findById(id)
                .map(this::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("Session profilage", "id", id));
    }

    @Transactional(readOnly = true)
    public List<SessionProfilageDTO> getByChercheur(UUID chercheurEmploiId) {
        return sessionRepository.findByChercheurEmploiId(chercheurEmploiId).stream()
                .map(this::toDto).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Page<SessionProfilageDTO> getByConseiller(UUID conseillerId, Pageable pageable) {
        return sessionRepository.findByConseillerId(conseillerId, pageable).map(this::toDto);
    }

    private SessionProfilageDTO toDto(SessionProfilage session) {
        List<ReponseDTO> reponses = session.getReponses().stream()
                .map(r -> ReponseDTO.builder()
                        .id(r.getId())
                        .questionId(r.getQuestionId())
                        .optionId(r.getOptionId())
                        .valeurTexte(r.getValeurTexte())
                        .valeurNumerique(r.getValeurNumerique())
                        .score(r.getScore())
                        .build())
                .collect(Collectors.toList());

        return SessionProfilageDTO.builder()
                .id(session.getId())
                .chercheurEmploiId(session.getChercheurEmploiId())
                .conseillerId(session.getConseillerId())
                .questionnaireId(session.getQuestionnaire().getId())
                .statut(session.getStatut().name())
                .scoreGlobal(session.getScoreGlobal())
                .segmentPropose(session.getSegmentPropose() != null ? session.getSegmentPropose().name() : null)
                .segmentValide(session.getSegmentValide() != null ? session.getSegmentValide().name() : null)
                .commentaireConseiller(session.getCommentaireConseiller())
                .dateDebut(session.getDateDebut())
                .dateFin(session.getDateFin())
                .dateValidation(session.getDateValidation())
                .reponses(reponses)
                .build();
    }
}
