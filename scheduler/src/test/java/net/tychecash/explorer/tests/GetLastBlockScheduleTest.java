/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.tychecash.explorer.tests;

import net.tychecash.explorer.config.SchedulerConfiguration;
import org.quartz.Scheduler;
import static org.quartz.JobBuilder.*;
import static org.quartz.TriggerBuilder.*;
import static org.quartz.SimpleScheduleBuilder.*;
import net.tychecash.explorer.jobs.JSONRPCBlockRequestJob;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.quartz.JobDetail;
import org.quartz.Trigger;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 * @author jithin
 */
@SuppressWarnings("unused")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { SchedulerConfiguration.class })

public class GetLastBlockScheduleTest {

    @Test
    protected void test(Scheduler scheduler) throws Exception {
        // define the job and tie it to our MyJob class
        JobDetail job = newJob(JSONRPCBlockRequestJob.class).withIdentity("job1", "group1").build();

        // Trigger the job to run now, and then repeat every 40 seconds
        Trigger trigger = newTrigger().withIdentity("trigger1", "group1").startNow()
                .withSchedule(simpleSchedule().withIntervalInSeconds(40).repeatForever()).build();

        // Tell quartz to schedule the job using our trigger
        scheduler.scheduleJob(job, trigger);
    }

}
