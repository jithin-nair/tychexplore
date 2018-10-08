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
public class TransactionVO {
    
    private String amountValue;
    
    private BigDecimal amountOut;
    
    private String key;
    
    private String type;

    public String getAmountValue() {
        return amountValue;
    }

    public void setAmountValue(String amountValue) {
        this.amountValue = BlockUtil.insertCharAt(amountValue, '.', 8);
    }

    public BigDecimal getAmountOut() {
        return amountOut;
    }

    public void setAmountOut(BigDecimal amountOut) {
        this.amountOut = amountOut;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    
    
}
