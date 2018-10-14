/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.tychecash.explorer.service.model.response.block;

import java.util.List;

/**
 *
 * @author jithin
 */
public class Result {

    private String status;

    private BlockHeader block_header;
    
    @com.fasterxml.jackson.annotation.JsonRawValue
    private String json_response;
    
    @com.fasterxml.jackson.annotation.JsonRawValue
    private String tx_as_json;
    
    private List<String> missed_tx;
    
    private List<String> txs_as_hex;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BlockHeader getBlock_header() {
        return block_header;
    }

    public void setBlock_header(BlockHeader block_header) {
        this.block_header = block_header;
    }

    public String getJson_response() {
        return json_response;
    }

    public void setJson_response(String json_response) {
        this.json_response = json_response;
    }

    public String getTx_as_json() {
        return tx_as_json;
    }

    public void setTx_as_json(String tx_as_json) {
        this.tx_as_json = tx_as_json;
    }

    public List<String> getMissed_tx() {
        return missed_tx;
    }

    public void setMissed_tx(List<String> missed_tx) {
        this.missed_tx = missed_tx;
    }

    public List<String> getTxs_as_hex() {
        return txs_as_hex;
    }

    public void setTxs_as_hex(List<String> txs_as_hex) {
        this.txs_as_hex = txs_as_hex;
    }

    @Override
    public String toString() {
        return "Result [status = " + status + ", block_header = " + block_header + "]";
    }
}
