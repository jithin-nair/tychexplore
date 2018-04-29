package net.tychecash.explorer.jobs;

import java.util.List;
import java.util.Map;
import net.tychecash.explorer.model.BlockDetails;
import net.tychecash.explorer.model.response.BlockResponse;
import net.tychecash.explorer.service.JobService;
import net.tychecash.explorer.service.TycheExploreService;

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

        BlockResponse blockResponse = tycheExploreService.getLastBlockResponse();
        String height = blockResponse.getResult().getBlock_header().getHeight().toString();
        String hash = blockResponse.getResult().getBlock_header().getHash();
        String foundDate = blockResponse.getResult().getBlock_header().getTimestamp();

        template.convertAndSend("/topic/recentblock", new BlockDetails(height, hash, foundDate));

        System.out.println("Thread: " + Thread.currentThread().getName() + " stopped.");
    }

    @Override
    public void interrupt() throws UnableToInterruptJobException {
        System.out.println("Stopping thread... ");
        toStopFlag = false;
    }

}
