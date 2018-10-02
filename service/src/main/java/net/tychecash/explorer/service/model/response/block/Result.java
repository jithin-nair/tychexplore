/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.tychecash.explorer.service.model.response.block;

/**
 *
 * @author jithin
 */
public class Result {

    private String status;

    private BlockHeader block_header;
    
    @com.fasterxml.jackson.annotation.JsonRawValue
    private String json_response;

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

    @Override
    public String toString() {
        return "Result [status = " + status + ", block_header = " + block_header + "]";
    }
}
