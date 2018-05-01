package net.tychecash.explorer.common.model;

import net.tychecash.explorer.common.model.response.BlockResponse;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "blocks")
public class Block {

    @Id
    private String id;

    private BlockResponse blockResponse;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BlockResponse getBlockResponse() {
        return blockResponse;
    }

    public void setBlockResponse(BlockResponse blockResponse) {
        this.blockResponse = blockResponse;
    }



}
