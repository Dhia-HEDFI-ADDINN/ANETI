package tn.aneti.referentiel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication(scanBasePackages = {"tn.aneti.referentiel", "tn.aneti.common"})
@EnableDiscoveryClient
public class ReferentielServiceApplication {
    public static void main(String[] args) { SpringApplication.run(ReferentielServiceApplication.class, args); }
}
