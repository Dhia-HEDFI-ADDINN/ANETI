package tn.aneti.accompagnement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication(scanBasePackages = {"tn.aneti.accompagnement", "tn.aneti.common"})
@EnableDiscoveryClient
public class AccompagnementServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(AccompagnementServiceApplication.class, args);
    }
}
