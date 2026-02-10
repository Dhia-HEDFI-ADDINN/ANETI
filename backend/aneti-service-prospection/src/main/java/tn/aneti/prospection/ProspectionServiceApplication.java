package tn.aneti.prospection;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication(scanBasePackages = {"tn.aneti.prospection", "tn.aneti.common"})
@EnableDiscoveryClient
public class ProspectionServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProspectionServiceApplication.class, args);
    }
}
