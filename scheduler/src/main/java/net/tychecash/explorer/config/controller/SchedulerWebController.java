/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.tychecash.explorer.config.controller;

import java.util.Date;
import net.tychecash.explorer.jobs.SimpleJob;
import net.tychecash.explorer.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author jithin
 */
@Controller
public class SchedulerWebController {

    @Autowired
    @Lazy
    JobService jobService;

    @RequestMapping(value = "/")
    public String showForm(Model model) {
        if (jobService.scheduleOneTimeJob("test", SimpleJob.class, new Date())) {
            model.addAttribute("msg", " job started");
        } else {
            model.addAttribute("msg", "job stopped");
        }
        return "index";
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String runJob(Model model) {
        if (jobService.isJobRunning("test")) {
            jobService.startJobNow("test");
            model.addAttribute("msg", "job stopped");
        } else {
            jobService.startJobNow("test");
            model.addAttribute("msg", "job started");
        }
        return "index";
    }
}
