/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.tychecash.explorer.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

/**
 *
 * @author jithin
 */
@Component
@ComponentScan(basePackages = {"net.tychecash.explorer"})
public class TycheExploreConfig {

    @Value("http://127.0.0.1:26026/json_rpc")
    private String jsonRpcServerUrl;

    @Value("http://127.0.0.1:26026/")
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
