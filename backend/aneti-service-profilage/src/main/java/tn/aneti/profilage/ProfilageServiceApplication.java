package tn.aneti.profilage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication(scanBasePackages = {"tn.aneti.profilage", "tn.aneti.common"})
@EnableDiscoveryClient
public class ProfilageServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProfilageServiceApplication.class, args);
    }
}
