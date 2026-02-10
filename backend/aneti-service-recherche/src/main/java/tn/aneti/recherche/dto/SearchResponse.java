package tn.aneti.recherche.dto;

import lombok.*;
import java.util.List;
import java.util.Map;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class SearchResponse {

    private String query;
    private Long totalHits;
    private Integer page;
    private Integer size;
    private Integer totalPages;
    private Long executionTimeMs;
    private List<SearchHit> hits;

    @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
    public static class SearchHit {
        private String id;
        private String index;
        private Double score;
        private Map<String, Object> source;
        private Map<String, List<String>> highlights;
    }
}
