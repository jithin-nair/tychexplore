package net.tychecash.explorer.web.controller;

import net.tychecash.explorer.service.model.response.block.BlockResponse;
import net.tychecash.explorer.service.model.response.block.tx.BlockTransactionResponse;
import net.tychecash.explorer.service.model.response.tx.TransactionResponse;
import net.tychecash.explorer.service.service.TycheExploreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author jithin
 */
@Controller
public class TycheWebController {

    @Autowired
    private TycheExploreService tycheExploreService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String showHome(Model model) {
        return "index";
    }
    
    @RequestMapping(value = "/api", method = RequestMethod.GET)
    public String showAPI(Model model) {
        return "api";
    }

    @RequestMapping(value = "/block/{hash}", method = RequestMethod.GET)
    public ModelAndView getBlock(ModelAndView modelAndView, @PathVariable("hash") String hash) {
        try {
            if(hash.length()>64){
                throw new RuntimeException();
            }
            BlockResponse blockResponse = tycheExploreService.getBlockResponseByHash(hash);
            modelAndView.addObject("bHeight", blockResponse.getResult().getBlock_header().getHeight());
            modelAndView.addObject("bHash", blockResponse.getResult().getBlock_header().getHash());
            modelAndView.addObject("bFound", blockResponse.getResult().getBlock_header().getTimestamp());
            modelAndView.addObject("bDifficulty", blockResponse.getResult().getBlock_header().getDifficulty());
            modelAndView.addObject("bReward", blockResponse.getResult().getBlock_header().getReward());
            modelAndView.addObject("bStatus", (blockResponse.getResult().getBlock_header().getOrphan_status().equalsIgnoreCase("true")) ? "Orphaned" : "Not Orphaned");
            modelAndView.addObject("bPrevious", blockResponse.getResult().getBlock_header().getPrev_hash());
            modelAndView.setViewName("search");
        } catch (RuntimeException ex) {
            modelAndView.addObject("message", "Nothing in the blockchain has been found that matches the search"
                    + "<br>Note: There might be some delay for latest details to be found");
            modelAndView.setViewName("invalid");
        }
        return modelAndView;
    }
    
    @RequestMapping(value = "/block/tx/{hash}", method = RequestMethod.GET)
    public ModelAndView getBlockTransaction(ModelAndView modelAndView, @PathVariable("hash") String hash) {
        try {
            if(hash.length()>64){
                throw new RuntimeException();
            }
            BlockResponse blockResponse = tycheExploreService.getBlockResponseByHash(hash);
            BlockTransactionResponse blockTransactionResponse = tycheExploreService.getBlockTransactionResponseByHash(hash);
            modelAndView.addObject("bHeight", blockResponse.getResult().getBlock_header().getHeight());
            modelAndView.addObject("bHash", blockResponse.getResult().getBlock_header().getHash());
            modelAndView.addObject("bFound", blockResponse.getResult().getBlock_header().getTimestamp());
            modelAndView.addObject("bDifficulty", blockResponse.getResult().getBlock_header().getDifficulty());
            modelAndView.addObject("bReward", blockResponse.getResult().getBlock_header().getReward());
            modelAndView.addObject("bStatus", (blockResponse.getResult().getBlock_header().getOrphan_status().equalsIgnoreCase("true")) ? "Orphaned" : "Not Orphaned");
            modelAndView.addObject("bPrevious", blockResponse.getResult().getBlock_header().getPrev_hash());
            modelAndView.addObject("blockTransactionResponse", blockTransactionResponse);
            modelAndView.addObject("transactionVOs", tycheExploreService.getTransactionsByBlockTransactionResponse(blockTransactionResponse));
            modelAndView.setViewName("block_tx");
        } catch (RuntimeException ex) {
            modelAndView.addObject("message", "Nothing in the blockchain has been found that matches the search"
                    + "<br>Note: There might be some delay for latest details to be found");
            modelAndView.setViewName("invalid");
        }
        return modelAndView;
    }
    
    @RequestMapping(value = "/tx/{hash}", method = RequestMethod.GET)
    public ModelAndView getTransaction(ModelAndView modelAndView, @PathVariable("hash") String hash) {
        try {
            if(hash.length()>64){
                throw new RuntimeException();
            }
            TransactionResponse transactionResponse = tycheExploreService.getTransactionResponseByHash(hash);
            modelAndView.addObject("transactionResponse", transactionResponse);
            modelAndView.addObject("transactionInVOs", tycheExploreService.getTransactionsVinByTransactionResponse(transactionResponse));
            modelAndView.addObject("transactionOutVOs", tycheExploreService.getTransactionsVoutByTransactionResponse(transactionResponse));
            modelAndView.setViewName("tx");
        } catch (RuntimeException ex) {
            modelAndView.addObject("message", "Nothing in the blockchain has been found that matches the search"
                    + "<br>Note: There might be some delay for latest details to be found");
            modelAndView.setViewName("invalid");
        }
        return modelAndView;
    }
}
