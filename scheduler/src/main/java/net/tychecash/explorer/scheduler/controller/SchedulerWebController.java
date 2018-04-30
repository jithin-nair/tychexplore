/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.tychecash.explorer.scheduler.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.tychecash.explorer.scheduler.constants.JobNamesEnum;
import net.tychecash.explorer.scheduler.jobs.BlockChainDownloadJob;
import net.tychecash.explorer.scheduler.jobs.MasterJob;
import net.tychecash.explorer.scheduler.jobs.RecentTychBlockRequestJob;
import net.tychecash.explorer.scheduler.model.BlockDetails;
import net.tychecash.explorer.scheduler.model.Logs;
import net.tychecash.explorer.scheduler.model.HelloMessage;
import net.tychecash.explorer.scheduler.service.JobService;
import net.tychecash.explorer.service.model.response.BlockResponse;
import net.tychecash.explorer.service.service.TycheExploreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.HtmlUtils;

/**
 *
 * @author jithin
 */
@Controller
public class SchedulerWebController {

    @Autowired
    @Lazy
    JobService jobService;
    
    @Autowired
    TycheExploreService tycheExploreService;
    
    @RequestMapping(value = "/dashboard", method = RequestMethod.GET)
    public ModelAndView showDashboard(ModelAndView modelAndView) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication.isAuthenticated()) {
            UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            List<Map<String, Object>> jobList = jobService.getAllJobs();
            modelAndView.addObject("username", userDetails.getUsername());
            modelAndView.addObject("jobList", jobList);
            modelAndView.setViewName("dashboard");
        } else {
            modelAndView.setViewName("index");
        }
        return modelAndView;
    }

    @RequestMapping(value = "/jobs/{jobname}/start", method = RequestMethod.GET)
    public ModelAndView startOneTimeJob(ModelAndView modelAndView, @PathVariable("jobname") String jobname) {
        if (!jobService.isJobRunning(jobname)) {
            String jobName = null;
            for (JobNamesEnum jobNamesEnum : JobNamesEnum.values()) {
                String jobType = jobNamesEnum.getType();
                if (jobType.equalsIgnoreCase(jobname)) {
                    jobName = jobNamesEnum.name();
                }
            }
            if (jobName != null) {
                if (jobName.equals(JobNamesEnum.MASTER_JOB.name())) {
                    jobService.scheduleCronJob(jobname, MasterJob.class, new Date(),"0/10 0/1 * 1/1 * ? *");
                }
                if (jobName.equals(JobNamesEnum.BLOCKCHAIN_DOWNLOAD_JOB.name())) {
                    jobService.scheduleOneTimeJob(jobname, BlockChainDownloadJob.class, new Date());
                }
                if (jobName.equals(JobNamesEnum.RECENTBLOCK_DOWNLOAD_JOB.name())) {
                    jobService.scheduleCronJob(jobname, RecentTychBlockRequestJob.class, new Date(), "0/2 0/1 * 1/1 * ? *");
                }
            }
        }
        modelAndView.setViewName("redirect:/jobs/listjobs");
        return modelAndView;
    }

    @RequestMapping(value = "/jobs/{jobname}/stop", method = RequestMethod.GET)
    public ModelAndView stopOneTimeJob(ModelAndView modelAndView, @PathVariable("jobname") String jobname) {
        if (jobService.isJobRunning(jobname)) {
            String jobName = null;
            for (JobNamesEnum jobNamesEnum : JobNamesEnum.values()) {
                String jobType = jobNamesEnum.getType();
                if (jobType.equalsIgnoreCase(jobname)) {
                    jobName = jobNamesEnum.name();
                }
            }
            if (jobName != null) {
                jobService.stopJob(jobname);
            }
        }
        modelAndView.setViewName("redirect:/jobs/listjobs");
        return modelAndView;
    }

    @RequestMapping(value = "/jobs/listjobs", method = RequestMethod.GET)
    public ModelAndView listJobs(ModelAndView modelAndView) {
        List<Map<String, Object>> jobList = new ArrayList<>();
        if (jobList.isEmpty()) {
            for (JobNamesEnum jobNamesEnum : JobNamesEnum.values()) {
                Map<String, Object> map = new HashMap<String, Object>();
                String jobStatus = "NOT RUNNING";
                map.put("jobName", jobNamesEnum);
                map.put("groupName", "SampleGroup");
                map.put("scheduleTime", "");
                map.put("lastFiredTime", "");
                map.put("nextFireTime", "");
                if(jobService.getJobState(jobNamesEnum.getType())!=null){
                    jobStatus = jobService.getJobState(jobNamesEnum.getType());
                }
                map.put("jobStatus", jobStatus);
                jobList.add(map);
            }
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.isAuthenticated()) {
            UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            modelAndView.addObject("username", userDetails.getUsername());
            modelAndView.addObject("jobList", jobList);
            modelAndView.setViewName("jobs");
        } else {
            modelAndView.setViewName("index");
        }

        return modelAndView;
    }
    
    @RequestMapping(value = "/jobs/showLogs", method = RequestMethod.GET)
    public ModelAndView showLogs(ModelAndView modelAndView) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication.isAuthenticated()) {
                UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
                modelAndView.addObject("username", userDetails.getUsername());
                modelAndView.setViewName("logs");
            } else {
                modelAndView.setViewName("index");
            }
        return modelAndView;
    }
    
    @MessageMapping("/logs")
    @SendTo("/topic/logs")
    public Logs getLogs(HelloMessage message) throws Exception {
        return new Logs("Test , " + HtmlUtils.htmlEscape(message.getName()) + "!");
    }
    
    @MessageMapping("/recentblock")
    @SendTo("/topic/recentblock")
    public BlockDetails getBlockDetails() throws Exception {
        BlockResponse blockResponse = tycheExploreService.getLastBlockResponse();
        String height = blockResponse.getResult().getBlock_header().getHeight().toString();
        String hash = blockResponse.getResult().getBlock_header().getHash();
        String foundDate = blockResponse.getResult().getBlock_header().getTimestamp();
        return new BlockDetails(height, hash, foundDate);
    }
}
