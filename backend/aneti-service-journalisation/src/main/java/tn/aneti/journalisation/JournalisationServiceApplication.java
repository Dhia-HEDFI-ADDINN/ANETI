package tn.aneti.journalisation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication(scanBasePackages = {"tn.aneti.journalisation", "tn.aneti.common"})
@EnableDiscoveryClient
public class JournalisationServiceApplication {
    public static void main(String[] args) { SpringApplication.run(JournalisationServiceApplication.class, args); }
}
