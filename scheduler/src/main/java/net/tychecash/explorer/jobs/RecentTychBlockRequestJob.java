/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.tychecash.explorer.jobs;

import java.util.Date;
import net.tychecash.explorer.constants.JobNamesEnum;
import net.tychecash.explorer.model.Block;

import net.tychecash.explorer.model.response.BlockResponse;
import net.tychecash.explorer.service.BlockService;
import net.tychecash.explorer.service.JobService;
import net.tychecash.explorer.service.TycheExploreService;
import org.quartz.InterruptableJob;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;
import org.quartz.UnableToInterruptJobException;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        JobKey key = jobExecutionContext.getJobDetail().getKey();
        System.out.println("RecentTychBlockRequestJob started with key :" + key.getName() + ", Group :" + key.getGroup() + " , Thread Name :" + Thread.currentThread().getName() + " ,Time now :" + new Date());

        if(!jobService.isJobRunning(JobNamesEnum.BLOCKCHAIN_DOWNLOAD_JOB.getType())){
            
        }
        BlockResponse blockResponse = tycheExploreService.getLastBlockResponse();
        
        Block block = new Block();
        block.setBlockResponse(blockResponse);
        //blockService.createBlock(block);
        
        System.out.println("Block Response "+blockResponse.toString());

        System.out.println("Thread: " + Thread.currentThread().getName() + " stopped.");
    }

    @Override
    public void interrupt() throws UnableToInterruptJobException {
        System.out.println("Stopping thread... ");
        toStopFlag = false;
    }
}
