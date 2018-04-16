/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.tychecash.explorer.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 *
 * @author jithin
 */
@Configuration
@ComponentScan(basePackages = {"net.tychecash.explorer"})
@PropertySource("classpath:tychexplore.properties")
public class TycheExploreConfig {

    @Value("${JSON_RPC_SERVER_URL}")
    private String jsonRpcServerUrl;

    @Value("${HTTP_RPC_SERVER_URL}")
    private String httpRpcServerUrl;

    public String getJsonRpcServerUrl() {
        return jsonRpcServerUrl;
    }

    public void setJsonRpcServerUrl(String jsonRpcServerUrl) {
        this.jsonRpcServerUrl = jsonRpcServerUrl;
    }

    public String getHttpRpcServerUrl() {
        return httpRpcServerUrl;
    }

    public void setHttpRpcServerUrl(String httpRpcServerUrl) {
        this.httpRpcServerUrl = httpRpcServerUrl;
    }
    
    
}
