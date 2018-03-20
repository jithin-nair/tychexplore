/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.tychecash.explorer.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 *
 * @author jithin
 */
@Configuration
@PropertySource("classpath:tychexplore.properties")
public class TycheExploreConfig {

    @Value("${RPC_SERVER_URL}")
    private String rpcServerUrl;

    public String getRpcServerUrl() {
        return rpcServerUrl;
    }

    public void setRpcServerUrl(String rpcServerUrl) {
        this.rpcServerUrl = rpcServerUrl;
    }

}
