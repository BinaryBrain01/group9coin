package group9coin.db;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Block")
public class DbBlock implements Serializable {
    private static final long serialVersionUID = -3009157732242241606L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "blocknumber")
    private String blockNumber;

    @Column(name = "hash")
    private String hash;

    protected DbBlock() {
    }

    public DbBlock(final String blockNumber, final String hash) {
        this.blockNumber = blockNumber;
        this.hash = hash;
    }

    @Override
    public String toString() {
        return String.format("Block[id=%d, blockNumber='%s', hash='%s']", id, blockNumber, hash);
    }
}
