package tn.aneti.recherche.dto;

import lombok.*;
import java.util.List;
import java.util.Map;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class SearchRequest {

    private String query;
    private String index;
    private List<String> fields;
    private Map<String, Object> filters;
    private Integer page;
    private Integer size;
    private String sortBy;
    private String sortDirection;
}
