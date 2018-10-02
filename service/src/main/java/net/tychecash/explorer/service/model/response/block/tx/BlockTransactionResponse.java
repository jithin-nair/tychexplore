
package net.tychecash.explorer.service.model.response.block.tx;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "major_version",
    "miner_tx",
    "minor_version",
    "nonce",
    "prev_id",
    "timestamp",
    "tx_hashes"
})
public class BlockTransactionResponse {

    @JsonProperty("major_version")
    private Integer majorVersion;
    @JsonProperty("miner_tx")
    private MinerTx minerTx;
    @JsonProperty("minor_version")
    private Integer minorVersion;
    @JsonProperty("nonce")
    private String nonce;
    @JsonProperty("prev_id")
    private String prevId;
    @JsonProperty("timestamp")
    private Integer timestamp;
    @JsonProperty("tx_hashes")
    private List<String> txHashes = null;

    @JsonProperty("major_version")
    public Integer getMajorVersion() {
        return majorVersion;
    }

    @JsonProperty("major_version")
    public void setMajorVersion(Integer majorVersion) {
        this.majorVersion = majorVersion;
    }

    @JsonProperty("miner_tx")
    public MinerTx getMinerTx() {
        return minerTx;
    }

    @JsonProperty("miner_tx")
    public void setMinerTx(MinerTx minerTx) {
        this.minerTx = minerTx;
    }

    @JsonProperty("minor_version")
    public Integer getMinorVersion() {
        return minorVersion;
    }

    @JsonProperty("minor_version")
    public void setMinorVersion(Integer minorVersion) {
        this.minorVersion = minorVersion;
    }

    @JsonProperty("nonce")
    public String getNonce() {
        return nonce;
    }

    @JsonProperty("nonce")
    public void setNonce(String nonce) {
        this.nonce = nonce;
    }

    @JsonProperty("prev_id")
    public String getPrevId() {
        return prevId;
    }

    @JsonProperty("prev_id")
    public void setPrevId(String prevId) {
        this.prevId = prevId;
    }

    @JsonProperty("timestamp")
    public Integer getTimestamp() {
        return timestamp;
    }

    @JsonProperty("timestamp")
    public void setTimestamp(Integer timestamp) {
        this.timestamp = timestamp;
    }

    @JsonProperty("tx_hashes")
    public List<String> getTxHashes() {
        return txHashes;
    }

    @JsonProperty("tx_hashes")
    public void setTxHashes(List<String> txHashes) {
        this.txHashes = txHashes;
    }
    
}
