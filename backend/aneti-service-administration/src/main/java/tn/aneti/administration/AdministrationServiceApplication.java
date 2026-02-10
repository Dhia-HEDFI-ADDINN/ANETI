package tn.aneti.administration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication(scanBasePackages = {"tn.aneti.administration", "tn.aneti.common"})
@EnableDiscoveryClient
public class AdministrationServiceApplication {
    public static void main(String[] args) { SpringApplication.run(AdministrationServiceApplication.class, args); }
}
