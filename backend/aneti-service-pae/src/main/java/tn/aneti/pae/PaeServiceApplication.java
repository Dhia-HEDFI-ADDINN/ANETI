package tn.aneti.pae;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication(scanBasePackages = {"tn.aneti.pae", "tn.aneti.common"})
@EnableDiscoveryClient
public class PaeServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(PaeServiceApplication.class, args);
    }
}
