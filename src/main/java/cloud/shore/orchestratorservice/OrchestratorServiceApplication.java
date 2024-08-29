package cloud.shore.orchestratorservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
//@EnableEurekaClient
@EnableFeignClients // Enable Feign Clients for inter-service communication
@EnableDiscoveryClient
public class OrchestratorServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrchestratorServiceApplication.class, args);
    }
}

