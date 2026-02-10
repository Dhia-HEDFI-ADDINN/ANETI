package tn.aneti.profilage.service;

import org.springframework.stereotype.Component;
import tn.aneti.profilage.entity.SessionProfilage;
import tn.aneti.profilage.entity.Reponse;
import java.util.List;

@Component
public class SegmentationEngine {

    public SessionProfilage.Segment calculerSegment(Double scoreGlobal, List<Reponse> reponses) {
        if (scoreGlobal == null || reponses.isEmpty()) {
            return SessionProfilage.Segment.GUIDE;
        }
        if (scoreGlobal >= 80) {
            return SessionProfilage.Segment.AUTONOME;
        } else if (scoreGlobal >= 60) {
            return SessionProfilage.Segment.GUIDE;
        } else if (scoreGlobal >= 40) {
            return SessionProfilage.Segment.RENFORCE;
        } else {
            return SessionProfilage.Segment.GLOBAL;
        }
    }

    public Double calculerScore(List<Reponse> reponses) {
        if (reponses == null || reponses.isEmpty()) {
            return 0.0;
        }
        return reponses.stream()
                .filter(r -> r.getScore() != null)
                .mapToDouble(Reponse::getScore)
                .average()
                .orElse(0.0);
    }
}
