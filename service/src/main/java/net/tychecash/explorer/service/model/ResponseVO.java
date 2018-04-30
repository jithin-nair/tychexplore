package net.tychecash.explorer.service.model;

import java.util.List;
import net.tychecash.explorer.service.model.response.BlockHeader;

public class ResponseVO {

    private String id;

    private List<BlockHeader> blockHeaders;

    private String jsonrpc;

    private String status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<BlockHeader> getBlockHeaders() {
        return blockHeaders;
    }

    public void setBlockHeaders(List<BlockHeader> blockHeaders) {
        this.blockHeaders = blockHeaders;
    }

    public String getJsonrpc() {
        return jsonrpc;
    }

    public void setJsonrpc(String jsonrpc) {
        this.jsonrpc = jsonrpc;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
