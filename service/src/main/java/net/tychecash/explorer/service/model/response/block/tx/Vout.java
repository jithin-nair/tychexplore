
package net.tychecash.explorer.service.model.response.block.tx;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.math.BigInteger;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "amount",
    "target"
})
public class Vout {

    @JsonProperty("amount")
    private BigInteger amount;
    @JsonProperty("target")
    private Target target;

    @JsonProperty("amount")
    public BigInteger getAmount() {
        return amount;
    }

    @JsonProperty("amount")
    public void setAmount(BigInteger amount) {
        this.amount = amount;
    }

    @JsonProperty("target")
    public Target getTarget() {
        return target;
    }

    @JsonProperty("target")
    public void setTarget(Target target) {
        this.target = target;
    }

}
