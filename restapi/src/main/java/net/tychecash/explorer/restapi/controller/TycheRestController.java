/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.tychecash.explorer.restapi.controller;

import net.tychecash.explorer.service.model.ResponseVO;
import net.tychecash.explorer.service.model.response.BlockResponse;
import net.tychecash.explorer.service.service.TycheExploreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


/**
 *
 * @author jithin
 */
@RestController
public class TycheRestController {

    @Autowired
    private TycheExploreService tycheExploreService;

    @ResponseBody
    @RequestMapping(value = "/getGraphData")
    public ResponseVO getGraphData() {
        ResponseVO responseVO = tycheExploreService.getBlockSamples(30);
        return responseVO;
    }

    @ResponseBody
    @RequestMapping(value = "/getBlockBySearch")
    public BlockResponse getBlockBySearch(@RequestParam("query") String query) {
        try {
            Integer height = Integer.parseInt(query);
            BlockResponse blockResponse = tycheExploreService.getBlockResponseByHeight(height);
            return blockResponse;
        } catch (NumberFormatException exception) {
            BlockResponse blockResponse = tycheExploreService.getBlockResponseByHash(query);
            return blockResponse;
        }
    }

    @ResponseBody
    @RequestMapping(value = "/getRecentBlocks")
    public ResponseVO getRecentBlocks(@RequestParam("filterslength") Integer filterslength,
            @RequestParam("pagenum") Integer pagenum, @RequestParam("pagesize") Integer pagesize) {
        BlockResponse lastBlockResponse = tycheExploreService.getLastBlockResponse();
        Integer lastBlockHeight = lastBlockResponse.getResult().getBlock_header().getHeight();
        ResponseVO responseVO = tycheExploreService.getLastNBlockResponseFromHeight(lastBlockHeight, pagenum, pagesize);
        return responseVO;
    }

    @ResponseBody
    @RequestMapping(value = "/getTotalPages")
    public Integer getTotalPages() {
        BlockResponse lastBlockResponse = tycheExploreService.getLastBlockResponse();
        Integer lastBlockHeight = lastBlockResponse.getResult().getBlock_header().getHeight();
        return lastBlockHeight;

    }
}
