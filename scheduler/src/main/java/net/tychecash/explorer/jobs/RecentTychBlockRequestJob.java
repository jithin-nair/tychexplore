/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.tychecash.explorer.jobs;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Date;
import net.tychecash.explorer.constants.JobNamesEnum;
import net.tychecash.explorer.modal.Greeting;
import net.tychecash.explorer.model.Block;
import net.tychecash.explorer.model.LastBlockInfo;

import net.tychecash.explorer.model.response.BlockResponse;
import net.tychecash.explorer.service.BlockService;
import net.tychecash.explorer.service.JobService;
import net.tychecash.explorer.service.LastBlockInfoService;
import net.tychecash.explorer.service.TycheExploreService;
import org.quartz.InterruptableJob;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;
import org.quartz.UnableToInterruptJobException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 *
 * @author jithin
 */
public class RecentTychBlockRequestJob extends QuartzJobBean implements InterruptableJob {

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

        if (!jobService.isJobRunning(JobNamesEnum.BLOCKCHAIN_DOWNLOAD_JOB.getType())) {
            Integer lastBlockHeight = blockService.findLastBlock().getBlockResponse().getResult().getBlock_header().getHeight();
            Integer topBlockHeight = tycheExploreService.getLastBlockResponse().getResult().getBlock_header().getHeight();
            LastBlockInfo lastBlockInfo = lastBlockInfoService.findLastBlock();
            BigDecimal alreadyGeneratedCoins = lastBlockInfo.getAlreadyGeneratedCoins();
            MathContext mc = new MathContext(20);
            if (lastBlockHeight < topBlockHeight) {
                BlockResponse blockResponse = tycheExploreService.getBlockResponseByHeight(lastBlockHeight+1);

                Block block = new Block();
                block.setBlockResponse(blockResponse);
                blockService.createBlock(block);

                String val = block.getBlockResponse().getResult().getBlock_header().getReward();
                BigDecimal value = BigDecimal.valueOf(Double.parseDouble(val));
                alreadyGeneratedCoins = alreadyGeneratedCoins.add(value, mc);

                System.out.println("Block Response " + blockResponse);
                template.convertAndSend("/topic/greetings", new Greeting(blockResponse.toString()));
                lastBlockInfo.setBlockResponse(block.getBlockResponse());
                lastBlockInfo.setAlreadyGeneratedCoins(alreadyGeneratedCoins);
                lastBlockInfoService.updateBlock(lastBlockInfo);
            }
        }

        System.out.println("Thread: " + Thread.currentThread().getName() + " stopped.");
    }

    @Override
    public void interrupt() throws UnableToInterruptJobException {
        System.out.println("Stopping thread... ");
        toStopFlag = false;
    }
}
