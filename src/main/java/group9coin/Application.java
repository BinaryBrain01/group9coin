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
    private WebServiceClient webServiceClient;

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
            final Block previousBlock = webServiceClient.getBlockAtEndOfLongestChain();
            System.out.println("previous hash: " + previousBlock.getHash());
            System.out.println("previous blockNr: " + previousBlock.getHeader().getBlockNumber());
            final Integer difficulty = webServiceClient.getDifficulty().getValue();
            System.out.println("Difficulty: " + difficulty);

            final Block nextBlock = blockGenerator.findNextBlock(previousBlock, difficulty);
            webServiceClient.postBlock(nextBlock);
            databaseClient.saveBlock(nextBlock);
            count++;
        }
    }


}