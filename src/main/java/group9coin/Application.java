package group9coin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

    @Autowired
    private Group9CoinRestClient restClient;

    @Autowired
    private BlockGenerator blockGenerator;

    public static void main(final String[] args) {
        SpringApplication.run(Application.class);
    }

    @Bean
    public CommandLineRunner run() {
        return args -> {
            findAndPost5Blocks();
        };
    }

    private void findAndPost5Blocks() {
        int count = 0;
        while (count < 5) {
            restClient.postBlock(blockGenerator.findNextBlock());
            count++;
        }
    }





}