package tn.aneti.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("inscription-service", r -> r
                        .path("/api/v1/inscriptions/**")
                        .uri("lb://aneti-service-inscription"))
                .route("profilage-service", r -> r
                        .path("/api/v1/profilage/**", "/api/v1/segmentation/**")
                        .uri("lb://aneti-service-profilage"))
                .route("accompagnement-service", r -> r
                        .path("/api/v1/accompagnement/**", "/api/v1/plans-action/**")
                        .uri("lb://aneti-service-accompagnement"))
                .route("offres-service", r -> r
                        .path("/api/v1/offres/**", "/api/v1/candidatures/**")
                        .uri("lb://aneti-service-offres"))
                .route("pae-service", r -> r
                        .path("/api/v1/pae/**", "/api/v1/civp/**", "/api/v1/contrats-programme/**")
                        .uri("lb://aneti-service-pae"))
                .route("rdv-service", r -> r
                        .path("/api/v1/rendez-vous/**")
                        .uri("lb://aneti-service-rdv"))
                .route("notification-service", r -> r
                        .path("/api/v1/notifications/**")
                        .uri("lb://aneti-service-notification"))
                .route("journalisation-service", r -> r
                        .path("/api/v1/audit/**", "/api/v1/logs/**")
                        .uri("lb://aneti-service-journalisation"))
                .route("documents-service", r -> r
                        .path("/api/v1/documents/**")
                        .uri("lb://aneti-service-documents"))
                .route("administration-service", r -> r
                        .path("/api/v1/admin/**", "/api/v1/utilisateurs/**")
                        .uri("lb://aneti-service-administration"))
                .route("referentiel-service", r -> r
                        .path("/api/v1/referentiels/**")
                        .uri("lb://aneti-service-referentiel"))
                .route("matching-service", r -> r
                        .path("/api/v1/matching/**")
                        .uri("lb://aneti-service-matching"))
                .route("mandatement-service", r -> r
                        .path("/api/v1/mandatement/**")
                        .uri("lb://aneti-service-mandatement"))
                .route("prospection-service", r -> r
                        .path("/api/v1/prospection/**")
                        .uri("lb://aneti-service-prospection"))
                .route("evenements-service", r -> r
                        .path("/api/v1/evenements/**")
                        .uri("lb://aneti-service-evenements"))
                .route("reclamations-service", r -> r
                        .path("/api/v1/reclamations/**")
                        .uri("lb://aneti-service-reclamations"))
                .route("reporting-service", r -> r
                        .path("/api/v1/reporting/**", "/api/v1/tableaux-bord/**")
                        .uri("lb://aneti-service-reporting"))
                .route("recherche-service", r -> r
                        .path("/api/v1/recherche/**")
                        .uri("lb://aneti-service-recherche"))
                .build();
    }
}
