package net.tychecash.explorer.scheduler.jobs;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import net.tychecash.explorer.common.model.LastBlockInfo;
import net.tychecash.explorer.common.service.LastBlockInfoService;
import net.tychecash.explorer.scheduler.model.BlockDetails;
import net.tychecash.explorer.scheduler.service.JobService;
import net.tychecash.explorer.service.model.response.BlockResponse;
import net.tychecash.explorer.service.service.TycheExploreService;

import org.quartz.InterruptableJob;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;
import org.quartz.UnableToInterruptJobException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class MasterJob extends QuartzJobBean implements InterruptableJob {

    private volatile boolean toStopFlag = true;

    @Autowired
    JobService jobService;

    @Autowired
    TycheExploreService tycheExploreService;

    @Autowired
    LastBlockInfoService lastBlockInfoService;

    @Autowired
    private SimpMessagingTemplate template;

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        JobKey key = jobExecutionContext.getJobDetail().getKey();
        System.out.println("Master Job started with key :" + key.getName() + ", Group :" + key.getGroup() + " , Thread Name :" + Thread.currentThread().getName());

        System.out.println("======================================");
        System.out.println("Accessing annotation example: " + jobService.getAllJobs());
        List<Map<String, Object>> list = jobService.getAllJobs();
        System.out.println("Job list :" + list);
        System.out.println("======================================");

        //*********** For retrieving stored key-value pairs ***********/
        JobDataMap dataMap = jobExecutionContext.getMergedJobDataMap();
        String myValue = dataMap.getString("myKey");
        System.out.println("Value:" + myValue);

        List<BlockDetails> blockDetailsList = new ArrayList<>();

        BlockResponse blockResponse = tycheExploreService.getLastBlockResponse();
        String height = blockResponse.getResult().getBlock_header().getHeight().toString();
        String hash = blockResponse.getResult().getBlock_header().getHash();
        String foundDate = blockResponse.getResult().getBlock_header().getTimestamp();
        BlockDetails blockDetails = new BlockDetails(height, hash, foundDate);

        LastBlockInfo lastBlockInfo = lastBlockInfoService.findLastBlock();
        height = lastBlockInfo.getBlockResponse().getResult().getBlock_header().getHeight().toString();
        hash = lastBlockInfo.getBlockResponse().getResult().getBlock_header().getHash();
        foundDate = lastBlockInfo.getBlockResponse().getResult().getBlock_header().getTimestamp();
        BlockDetails lastBlockDetails = new BlockDetails(height, hash, foundDate);

        blockDetailsList.add(blockDetails);
        blockDetailsList.add(lastBlockDetails);

        template.convertAndSend("/topic/recentblock", blockDetailsList);

        System.out.println("Thread: " + Thread.currentThread().getName() + " stopped.");
    }

    @Override
    public void interrupt() throws UnableToInterruptJobException {
        System.out.println("Stopping thread... ");
        toStopFlag = false;
    }

}
