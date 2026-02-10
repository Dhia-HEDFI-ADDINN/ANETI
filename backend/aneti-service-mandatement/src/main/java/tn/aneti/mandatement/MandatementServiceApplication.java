package tn.aneti.mandatement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication(scanBasePackages = {"tn.aneti.mandatement", "tn.aneti.common"})
@EnableDiscoveryClient
public class MandatementServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(MandatementServiceApplication.class, args);
    }
}
