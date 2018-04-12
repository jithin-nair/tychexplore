/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.tychecash.explorer.model.response;

/**
 *
 * @author jithin
 */
public class Result {

    private String status;

    private BlockHeader block_header;

    private Integer count;

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

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "Result [status = " + status + ", block_header = " + block_header + "]";
    }
}
