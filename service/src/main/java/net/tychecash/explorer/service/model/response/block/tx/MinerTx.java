
package net.tychecash.explorer.service.model.response.block.tx;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import net.tychecash.explorer.service.model.response.tx.Vout;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "extra",
    "unlock_time",
    "version",
    "vin",
    "vout"
})
public class MinerTx {

    @JsonProperty("extra")
    private String extra;
    @JsonProperty("unlock_time")
    private Integer unlockTime;
    @JsonProperty("version")
    private Integer version;
    @JsonProperty("vin")
    private List<Vin> vin = null;
    @JsonProperty("vout")
    private List<Vout> vout = null;

    @JsonProperty("extra")
    public String getExtra() {
        return extra;
    }

    @JsonProperty("extra")
    public void setExtra(String extra) {
        this.extra = extra;
    }

    @JsonProperty("unlock_time")
    public Integer getUnlockTime() {
        return unlockTime;
    }

    @JsonProperty("unlock_time")
    public void setUnlockTime(Integer unlockTime) {
        this.unlockTime = unlockTime;
    }

    @JsonProperty("version")
    public Integer getVersion() {
        return version;
    }

    @JsonProperty("version")
    public void setVersion(Integer version) {
        this.version = version;
    }

    @JsonProperty("vin")
    public List<Vin> getVin() {
        return vin;
    }

    @JsonProperty("vin")
    public void setVin(List<Vin> vin) {
        this.vin = vin;
    }

    @JsonProperty("vout")
    public List<Vout> getVout() {
        return vout;
    }

    @JsonProperty("vout")
    public void setVout(List<Vout> vout) {
        this.vout = vout;
    }

}
