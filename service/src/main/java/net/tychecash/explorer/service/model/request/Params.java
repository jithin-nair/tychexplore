/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.tychecash.explorer.service.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/**
 *
 * @author jithin
 */
public class Params {

    private Integer height;

    private String hash;
    
    @JsonProperty("txs_hashes")
    private List<String> txHashes;

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public List<String> getTxHashes() {
        return txHashes;
    }

    public void setTxHashes(List<String> txHashes) {
        this.txHashes = txHashes;
    }

    @Override
    public String toString() {
        return "Params [height = " + height + ", hash = " + hash + "]";
    }
}
