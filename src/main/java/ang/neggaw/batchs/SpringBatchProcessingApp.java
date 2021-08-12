package ang.neggaw.batchs;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableBatchProcessing
@SpringBootApplication
public class SpringBatchProcessingApp {
    public static void main(String[] args) {
        SpringApplication.run(SpringBatchProcessingApp.class, args);
    }
}
