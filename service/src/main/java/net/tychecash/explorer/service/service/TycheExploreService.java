/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.tychecash.explorer.service.service;

import net.tychecash.explorer.service.model.CountVO;
import net.tychecash.explorer.service.model.ResponseVO;
import net.tychecash.explorer.service.model.response.block.BlockResponse;
import net.tychecash.explorer.service.model.response.block.tx.BlockTransactionResponse;
import net.tychecash.explorer.service.model.response.tx.TransactionResponse;

/**
 *
 * @author jithin
 */
public interface TycheExploreService {

    public BlockResponse getBlockResponseByHeight(Integer height);

    public BlockResponse getBlockResponseByHash(String hash);
    
    public BlockTransactionResponse getBlockTransactionResponseByHash(String hash);
    
    public TransactionResponse getTransactionResponseByHash(String tx_hash);

    public BlockResponse getFirstBlockResponse();

    public BlockResponse getLastBlockResponse();

    public ResponseVO getLastNBlockResponseFromHeight(Integer height, Integer pageNumber, Integer size);

    public ResponseVO getBlockSamples(Integer samplingRate);

    public CountVO getBlockCount();
}
