/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.tychecash.explorer.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;
import net.tychecash.explorer.jobs.MasterJob;
import net.tychecash.explorer.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author jithin
 */
@Controller
public class SchedulerWebController {
    
    @Autowired
    @Lazy
    JobService jobService;
    
    @RequestMapping(value = "/jobs/{jobname}/start", method = RequestMethod.GET)
    public ModelAndView startOneTimeJob(ModelAndView modelAndView, @PathVariable("jobname") String jobname) {
        if (!jobService.isJobRunning(jobname) && jobService.startJobNow(jobname) && jobService.isJobWithNamePresent(jobname)) {
            modelAndView.addObject("isStarted", Boolean.TRUE);
        } else {
            modelAndView.addObject("isStarted", Boolean.FALSE);
        }
        modelAndView.setViewName("jobs");
        return modelAndView;
    }
    
    @RequestMapping(value = "/jobs/{jobname}/stop", method = RequestMethod.GET)
    public ModelAndView stopOneTimeJob(ModelAndView modelAndView, @PathVariable("jobname") String jobname) {
        if (jobService.isJobRunning(jobname) && jobService.stopJob(jobname) && jobService.isJobWithNamePresent(jobname)) {
            modelAndView.addObject("isStopped", Boolean.TRUE);
        } else {
            modelAndView.addObject("isStopped", Boolean.FALSE);
        }
        modelAndView.setViewName("jobs");
        return modelAndView;
    }
    
    @RequestMapping(value = "/jobs/listjobs", method = RequestMethod.GET)
    public ModelAndView listJobs(ModelAndView modelAndView) {
        List<Map<String, Object>> jobList = jobService.getAllJobs();
        if (jobList == null || jobList.isEmpty()) {
            jobService.scheduleOneTimeJob("MasterJob", MasterJob.class, new Date());
            jobList = jobService.getAllJobs();
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
}
