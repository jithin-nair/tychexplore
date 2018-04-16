package net.tychecash.explorer.jobs;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.tychecash.explorer.service.JobService;

import org.quartz.InterruptableJob;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;
import org.quartz.UnableToInterruptJobException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class MasterJob extends QuartzJobBean implements InterruptableJob {

    private volatile boolean toStopFlag = true;

    @Autowired
    JobService jobService;

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
        
        jobService.scheduleOneTimeJob("BlockChainDownloadJob", BlockChainDownloadJob.class, new Date());

        //*********** For retrieving stored object, It will try to deserialize the bytes Object. ***********/
        /*
		SchedulerContext schedulerContext = null;
        try {
            schedulerContext = jobExecutionContext.getScheduler().getContext();
        } catch (SchedulerException e1) {
            e1.printStackTrace();
        }
        YourClass yourClassObject = (YourClass) schedulerContext.get("storedObjectKey");
         */
        while (toStopFlag) {
            try {
                System.out.println("Test Job Running... Thread Name :" + Thread.currentThread().getName());
                Thread.sleep(2000);
                if(!jobService.isJobRunning("BlockChainDownloadJob")&&jobService.getJobState("BlockChainDownloadJob").equalsIgnoreCase("COMPLETE")){
                    //jobService.scheduleCronJob("RecentTychBlockRequestJob", RecentTychBlockRequestJob.class, new Date(), "0/2 0/1 * 1/1 * ? *");
                    try {
                        interrupt();
                    } catch (UnableToInterruptJobException ex) {
                        Logger.getLogger(MasterJob.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
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
