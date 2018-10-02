/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.tychecash.explorer.service.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import net.tychecash.explorer.service.config.TycheExploreConfig;
import net.tychecash.explorer.service.model.CountVO;
import net.tychecash.explorer.service.model.ResponseVO;
import net.tychecash.explorer.service.model.request.BlockRequest;
import net.tychecash.explorer.service.model.request.Params;
import net.tychecash.explorer.service.model.response.block.BlockHeader;
import net.tychecash.explorer.service.model.response.block.BlockResponse;
import net.tychecash.explorer.service.model.response.block.tx.BlockTransactionResponse;
import net.tychecash.explorer.service.model.response.tx.TransactionResponse;

import net.tychecash.explorer.service.service.TycheExploreService;
import net.tychecash.explorer.service.util.BlockUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Service;

/**
 *
 * @author jithin
 */
@Service
public class TycheExploreServiceImpl implements TycheExploreService {

    @Autowired
    TycheExploreConfig tycheExploreConfig;

    @Override
    public BlockResponse getBlockResponseByHeight(Integer height) throws RuntimeException {
        BlockRequest blockRequest = new BlockRequest();
        blockRequest.setId("self");
        blockRequest.setJsonrpc("2.0");
        blockRequest.setMethod("getblockheaderbyheight");
        Params params = new Params();
        params.setHeight(height);
        blockRequest.setParams(params);
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        String uri = tycheExploreConfig.getJsonRpcServerUrl();
        BlockResponse blockResponse = restTemplate.postForObject(uri, blockRequest, BlockResponse.class);
        return blockResponse;
        // Tools | Templates.
    }

    @Override
    public BlockResponse getBlockResponseByHash(String hash) throws RuntimeException {
        BlockRequest blockRequest = new BlockRequest();
        blockRequest.setId("self");
        blockRequest.setJsonrpc("2.0");
        blockRequest.setMethod("getblockheaderbyhash");
        Params params = new Params();
        params.setHash(hash);
        blockRequest.setParams(params);
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        String uri = tycheExploreConfig.getJsonRpcServerUrl();
        BlockResponse blockResponse = restTemplate.postForObject(uri, blockRequest, BlockResponse.class);
        return blockResponse;

    }

    @Override
    public BlockTransactionResponse getBlockTransactionResponseByHash(String hash) throws RuntimeException {
        BlockRequest blockRequest = new BlockRequest();
        blockRequest.setId("self");
        blockRequest.setJsonrpc("2.0");
        blockRequest.setMethod("f_block_json");
        Params params = new Params();
        params.setHash(hash);
        blockRequest.setParams(params);
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
        String uri = tycheExploreConfig.getJsonRpcServerUrl();
        BlockResponse blockResponse = restTemplate.postForObject(uri, blockRequest, BlockResponse.class);
        ObjectMapper mapper = new ObjectMapper();
        String jsonResponse = blockResponse.getResult().getJson_response();
        BlockTransactionResponse blockTransactionResponse = null;
        try {
            blockTransactionResponse = mapper.readValue(jsonResponse, BlockTransactionResponse.class);
        } catch (IOException ex) {
            Logger.getLogger(TycheExploreServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException();
        }
        return blockTransactionResponse;

    }

    @Override
    public TransactionResponse getTransactionResponseByHash(String tx_hash) throws RuntimeException {
        BlockRequest blockRequest = new BlockRequest();
        blockRequest.setId("self");
        blockRequest.setJsonrpc("2.0");
        blockRequest.setMethod("f_transaction_json");
        Params params = new Params();
        params.setHash(tx_hash);
        blockRequest.setParams(params);
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        String uri = tycheExploreConfig.getJsonRpcServerUrl();
        TransactionResponse blockResponse = restTemplate.postForObject(uri, blockRequest, TransactionResponse.class);
        return blockResponse;

    }

    @Override
    public BlockResponse getFirstBlockResponse() throws RuntimeException {
        return getBlockResponseByHeight(0);
    }

    @Override
    public BlockResponse getLastBlockResponse() throws RuntimeException {
        BlockRequest blockRequest = new BlockRequest();
        blockRequest.setId("self");
        blockRequest.setJsonrpc("2.0");
        blockRequest.setMethod("getlastblockheader");
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        String uri = tycheExploreConfig.getJsonRpcServerUrl();
        BlockResponse blockResponse = restTemplate.postForObject(uri, blockRequest, BlockResponse.class);
        return blockResponse;
    }

    @Override
    public ResponseVO getLastNBlockResponseFromHeight(Integer height, Integer pageNumber, Integer size) throws RuntimeException {
        ResponseVO responseVO = new ResponseVO();
        List<BlockResponse> blockResponses = new ArrayList<BlockResponse>();
        String uri = tycheExploreConfig.getJsonRpcServerUrl();
        BlockRequest blockRequest = new BlockRequest();
        blockRequest.setId("self");
        blockRequest.setJsonrpc("2.0");
        blockRequest.setMethod("getblockheaderbyheight");
        Params params = new Params();
        for (int i = 0; i < size; i++) {
            Integer curHeight = (height - (size * pageNumber)) - i;
            if (curHeight > -1) {
                params.setHeight(curHeight);
                blockRequest.setParams(params);
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
                BlockResponse blockResponse = restTemplate.postForObject(uri, blockRequest, BlockResponse.class);
                blockResponses.add(blockResponse);
            }
        }
        List<BlockHeader> blockHeaders = new ArrayList<>();
        for (BlockResponse blockResponse : blockResponses) {
            if (blockResponse.getResult().getStatus().equalsIgnoreCase("OK")) {
                blockHeaders.add(blockResponse.getResult().getBlock_header());
            }
        }
        responseVO.setId(blockRequest.getId());
        responseVO.setJsonrpc(blockRequest.getJsonrpc());
        responseVO.setBlockHeaders(blockHeaders);
        responseVO.setStatus("SUCCESS");
        return responseVO;
    }

    @Override
    public ResponseVO getBlockSamples(Integer samplingRate) throws RuntimeException {
        ResponseVO responseVO = new ResponseVO();
        BlockResponse lastBlockResponse = getLastBlockResponse();
        Integer lastBlockHeight = lastBlockResponse.getResult().getBlock_header().getHeight();
        List<Integer> blockSamples = BlockUtil.getAllBlockSamples(samplingRate, lastBlockHeight);
        List<BlockResponse> blockResponses = new ArrayList<BlockResponse>();
        String uri = tycheExploreConfig.getJsonRpcServerUrl();
        BlockRequest blockRequest = new BlockRequest();
        blockRequest.setId("self");
        blockRequest.setJsonrpc("2.0");
        blockRequest.setMethod("getblockheaderbyheight");
        Params params = new Params();
        for (Integer blockHeight : blockSamples) {
            if (blockHeight > 0) {
                params.setHeight(blockHeight);
                blockRequest.setParams(params);
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
                BlockResponse blockResponse = restTemplate.postForObject(uri, blockRequest, BlockResponse.class);
                blockResponses.add(blockResponse);
            }
        }
        List<BlockHeader> blockHeaders = new ArrayList<>();
        for (BlockResponse blockResponse : blockResponses) {
            if (blockResponse.getResult().getStatus().equalsIgnoreCase("OK")) {
                blockHeaders.add(blockResponse.getResult().getBlock_header());
            }
        }
        responseVO.setId(blockRequest.getId());
        responseVO.setJsonrpc(blockRequest.getJsonrpc());
        responseVO.setBlockHeaders(blockHeaders);
        responseVO.setStatus("SUCCESS");
        return responseVO;
    }

    @Override
    public CountVO getBlockCount() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        String uri = tycheExploreConfig.getHttpRpcServerUrl();
        String result = restTemplate.getForObject(uri + "getheight", String.class);
        Gson gson = new Gson();
        CountVO countVO = gson.fromJson(result, CountVO.class);
        return countVO;
    }

}
