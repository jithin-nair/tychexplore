/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.tychecash.explorer.restapi.service.impl;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import net.tychecash.explorer.common.model.LastBlockInfo;
import net.tychecash.explorer.common.service.LastBlockInfoService;
import net.tychecash.explorer.common.util.NumberToWordsUtil;
import net.tychecash.explorer.restapi.service.RestApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author jithin
 */
@Service
public class RestApiServiceImpl implements RestApiService{

    @Autowired
    LastBlockInfoService lastBlockInfoService;
    
    @Override
    public String getTotalMinedCoins() {
        LastBlockInfo lastBlockInfo = lastBlockInfoService.findLastBlock();
        BigDecimal generatedCoins = lastBlockInfo.getAlreadyGeneratedCoins();
        String totalCoins = generatedCoins.toString();
        return totalCoins;
    }

    @Override
    public String getTotalMinedCoinsInWords() {
        LastBlockInfo lastBlockInfo = lastBlockInfoService.findLastBlock();
        BigDecimal generatedCoins = lastBlockInfo.getAlreadyGeneratedCoins();
        long longVal = generatedCoins.round(new MathContext(generatedCoins.precision() - generatedCoins.scale(), RoundingMode.CEILING)).longValueExact();
        String totalCoins = NumberToWordsUtil.convert(longVal);
        return totalCoins;
    }
    
}
