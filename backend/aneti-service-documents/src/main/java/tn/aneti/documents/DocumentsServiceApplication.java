package tn.aneti.documents;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication(scanBasePackages = {"tn.aneti.documents", "tn.aneti.common"})
@EnableDiscoveryClient
public class DocumentsServiceApplication {
    public static void main(String[] args) { SpringApplication.run(DocumentsServiceApplication.class, args); }
}
