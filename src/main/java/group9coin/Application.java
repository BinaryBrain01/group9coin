package group9coin;

import group9coin.db.BlockRepository;
import group9coin.db.DbBlock;
import group9coin.domain.Block;
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

    @Autowired
    private BlockRepository repository;

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
            restClient.postBlock(nextBlock);
            repository.save(new DbBlock("" + nextBlock.getHeader().getBlockNumber(), nextBlock.getHash())); //TODO JD: fix type
            count++;
        }
    }


}