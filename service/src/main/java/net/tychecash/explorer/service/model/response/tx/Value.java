
package net.tychecash.explorer.service.model.response.tx;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "amount",
    "k_image",
    "key_offsets"
})
public class Value {

    @JsonProperty("amount")
    private Integer amount;
    @JsonProperty("k_image")
    private String kImage;
    @JsonProperty("key_offsets")
    private List<Integer> keyOffsets = null;

    @JsonProperty("amount")
    public Integer getAmount() {
        return amount;
    }

    @JsonProperty("amount")
    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    @JsonProperty("k_image")
    public String getKImage() {
        return kImage;
    }

    @JsonProperty("k_image")
    public void setKImage(String kImage) {
        this.kImage = kImage;
    }

    @JsonProperty("key_offsets")
    public List<Integer> getKeyOffsets() {
        return keyOffsets;
    }

    @JsonProperty("key_offsets")
    public void setKeyOffsets(List<Integer> keyOffsets) {
        this.keyOffsets = keyOffsets;
    }

}
