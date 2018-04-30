/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.tychecash.explorer.common.model;

import java.math.BigDecimal;
import net.tychecash.explorer.common.model.response.BlockResponse;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author Jithin
 */
@Document(collection = "lastblockinfo")
public class LastBlockInfo {

    @Id
    private String id;

    private BlockResponse blockResponse;

    private BigDecimal alreadyGeneratedCoins;

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

    public BigDecimal getAlreadyGeneratedCoins() {
        return alreadyGeneratedCoins;
    }

    public void setAlreadyGeneratedCoins(BigDecimal alreadyGeneratedCoins) {
        this.alreadyGeneratedCoins = alreadyGeneratedCoins;
    }
}
