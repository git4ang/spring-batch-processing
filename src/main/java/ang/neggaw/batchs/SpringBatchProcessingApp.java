package ang.neggaw.batchs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@Import(SpringBatchReaderConfig.class)
@SpringBootApplication
public class SpringBatchProcessingApp {
    public static void main(String[] args) {
        SpringApplication.run(SpringBatchProcessingApp.class, args);
    }
}
