package tn.aneti.inscription;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication(scanBasePackages = {"tn.aneti.inscription", "tn.aneti.common"})
@EnableDiscoveryClient
@EnableJpaAuditing
public class InscriptionServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(InscriptionServiceApplication.class, args);
    }
}
