package group9coin.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Header {
    private Integer blockNumber;
    private String previousHash;
    private String contentHash;
    private String nonce;

    public Header(){

    }

    public Header(Integer blockNumber, String previousHash, String contentHash, String nonce) {
        this.blockNumber = blockNumber;
        this.previousHash = previousHash;
        this.contentHash = contentHash;
        this.nonce = nonce;
    }

    public Integer getBlockNumber() {
        return blockNumber;
    }

    public void setBlockNumber(Integer blockNumber) {
        this.blockNumber = blockNumber;
    }

    public String getPreviousHash() {
        return previousHash;
    }

    public void setPreviousHash(String previousHash) {
        this.previousHash = previousHash;
    }

    public String getContentHash() {
        return contentHash;
    }

    public void setContentHash(String contentHash) {
        this.contentHash = contentHash;
    }

    public String getNonce() {
        return nonce;
    }

    public void setNonce(String nonce) {
        this.nonce = nonce;
    }
}
