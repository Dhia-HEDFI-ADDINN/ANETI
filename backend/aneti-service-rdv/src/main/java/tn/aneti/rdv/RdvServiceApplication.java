package tn.aneti.rdv;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication(scanBasePackages = {"tn.aneti.rdv", "tn.aneti.common"})
@EnableDiscoveryClient
public class RdvServiceApplication {
    public static void main(String[] args) { SpringApplication.run(RdvServiceApplication.class, args); }
}
