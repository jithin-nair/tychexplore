/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.tychecash.explorer.jobs;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 *
 * @author jithin
 */
public class JSONRPCBlockRequestJob implements org.quartz.Job {

    @Override
    public void execute(JobExecutionContext jec) throws JobExecutionException {
        System.err.println("Hello World!  MyJob is executing.");
    }

}
