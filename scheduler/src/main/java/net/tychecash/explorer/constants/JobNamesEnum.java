/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.tychecash.explorer.constants;

import java.util.stream.Stream;

/**
 *
 * @author jithin
 */
public enum JobNamesEnum {

    MASTER_JOB("MasterJob"),
    BLOCKCHAIN_DOWNLOAD_JOB("BlockChainDownloadJob"),
    RECENTBLOCK_DOWNLOAD_JOB("RecentBlockDownloadJob");

    private String type;

    JobNamesEnum(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public static Stream<JobNamesEnum> stream() {
        return Stream.of(JobNamesEnum.values());
    }

}
