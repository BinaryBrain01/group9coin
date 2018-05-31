package group9coin.persistence;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Block")
public class BlockDbEntity implements Serializable {
    private static final long serialVersionUID = -3009157732242241606L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "blocknumber")
    private Integer blockNumber;

    @Column(name = "hash")
    private String hash;

    protected BlockDbEntity() {
    }

    private BlockDbEntity(final BlockDbEntity.Builder builder) {
        blockNumber = builder.blockNumber;
        hash = builder.hash;
    }

    @Override
    public String toString() {
        return String.format("Block[id=%d, blockNumber='%s', hash='%s']", id, blockNumber, hash);
    }

    public static class Builder {
        private Integer blockNumber;
        private String hash;

        public BlockDbEntity.Builder blockNumber(final Integer blockNumber) {
            this.blockNumber = blockNumber;
            return this;
        }

        public BlockDbEntity.Builder hash(final String hash) {
            this.hash = hash;
            return this;
        }

        public BlockDbEntity build() {
            return new BlockDbEntity(this);
        }

    }
}
