/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.tychecash.explorer.service.model;

import java.math.BigDecimal;
import net.tychecash.explorer.service.util.BlockUtil;

/**
 *
 * @author jithin
 */
public class TransactionInVO {
    
    private String amountValue;
    
    private BigDecimal amountIn;
    
    private String keyImage;
    
    private String type;
    
    private String keyOffsets;

    public String getAmountValue() {
        return amountValue;
    }

    public void setAmountValue(String amountValue) {
        this.amountValue = BlockUtil.insertCharAt(amountValue, '.', 8);
    }

    public BigDecimal getAmountIn() {
        return amountIn;
    }

    public void setAmountIn(BigDecimal amountIn) {
        this.amountIn = amountIn;
    }

    public String getKeyImage() {
        return keyImage;
    }

    public void setKeyImage(String keyImage) {
        this.keyImage = keyImage;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getKeyOffsets() {
        return keyOffsets;
    }

    public void setKeyOffsets(String keyOffsets) {
        this.keyOffsets = keyOffsets;
    }
    
}
