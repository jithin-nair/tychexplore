/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.tychecash.explorer.scheduler.jobs;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.introspect.VisibilityChecker;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.tychecash.explorer.scheduler.model.Logs;
import net.tychecash.explorer.common.model.Block;
import net.tychecash.explorer.common.model.LastBlockInfo;

import net.tychecash.explorer.common.service.BlockService;
import net.tychecash.explorer.scheduler.service.JobService;
import net.tychecash.explorer.common.service.LastBlockInfoService;
import net.tychecash.explorer.service.model.response.block.BlockResponse;
import net.tychecash.explorer.service.service.TycheExploreService;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.InterruptableJob;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;
import org.quartz.UnableToInterruptJobException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 *
 * @author jithin
 */
@DisallowConcurrentExecution
public class BlockChainDownloadJob extends QuartzJobBean implements InterruptableJob {

    private volatile boolean toStopFlag = true;

    @Autowired
    JobService jobService;

    @Autowired
    TycheExploreService tycheExploreService;

    @Autowired
    BlockService blockService;
    
    @Autowired
    LastBlockInfoService lastBlockInfoService;
    
    @Autowired
    private SimpMessagingTemplate template;

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        JobKey key = jobExecutionContext.getJobDetail().getKey();
        System.out.println("RecentTychBlockRequestJob started with key :" + key.getName() + ", Group :" + key.getGroup() + " , Thread Name :" + Thread.currentThread().getName() + " ,Time now :" + new Date());

        Integer currentBlockHeight = tycheExploreService.getBlockCount().getHeight();
        Block lastBlock = null;
        BigDecimal alreadyGeneratedCoins = new BigDecimal(0);
        MathContext mc = new MathContext(20);
        for (int i = 0; i < currentBlockHeight; i++) {
            BlockResponse blockResponse = tycheExploreService.getBlockResponseByHeight(i);
            
            net.tychecash.explorer.common.model.response.BlockResponse response = null;
            ObjectMapper mapper = new ObjectMapper();
            mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
            mapper.setVisibilityChecker(VisibilityChecker.Std.defaultInstance().withFieldVisibility(JsonAutoDetect.Visibility.ANY));
            try {
                String pureJson = mapper.writeValueAsString(blockResponse);
                response = mapper.readValue(pureJson, net.tychecash.explorer.common.model.response.BlockResponse.class);
            } catch (JsonProcessingException ex) {
                Logger.getLogger(BlockChainDownloadJob.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(BlockChainDownloadJob.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            Block block = new Block();
            block.setBlockResponse(response);
            blockService.createBlock(block);
            
            String val = block.getBlockResponse().getResult().getBlock_header().getReward();
            BigDecimal value = BigDecimal.valueOf(Double.parseDouble(val));
            alreadyGeneratedCoins = alreadyGeneratedCoins.add(value, mc);
            lastBlock = block;

            System.out.println("Block Response " + blockResponse);
            template.convertAndSend("/topic/logs", new Logs(blockResponse.toString()));
            currentBlockHeight = tycheExploreService.getBlockCount().getHeight();
        }
        LastBlockInfo lastBlockInfo = new LastBlockInfo();
        lastBlockInfo.setBlockResponse(lastBlock.getBlockResponse());
        lastBlockInfo.setAlreadyGeneratedCoins(alreadyGeneratedCoins);
        lastBlockInfoService.createBlock(lastBlockInfo);
        
        System.out.println("Thread: " + Thread.currentThread().getName() + " stopped.");
    }

    @Override
    public void interrupt() throws UnableToInterruptJobException {
        System.out.println("Stopping thread... ");
        toStopFlag = false;
    }
}
