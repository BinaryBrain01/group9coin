package group9coin;

import group9coin.domain.Block;
import group9coin.persistence.BlockDbClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

    @Autowired
    private BlockGenerator blockGenerator;

    @Autowired
    private BlockDbClient databaseClient;

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
        while (count < 1) {
            final Block nextBlock = blockGenerator.findNextBlock();
            blockGenerator.postBlock(nextBlock);
            databaseClient.saveBlock(nextBlock);
            count++;
        }
    }


}