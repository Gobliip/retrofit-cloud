package com.gobliip.retrofit.cloud.endpoint;

import com.gobliip.retrofit.cloud.exceptions.InstanceNotFoundException;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import retrofit.Endpoint;

/**
 * Created by lsamayoa on 7/15/15.
 */
public abstract class ServiceDiscoveryEndpoint implements Endpoint {

    private DiscoveryClient discoveryClient;
    private String serviceName;

    public ServiceDiscoveryEndpoint() {
    }

    public ServiceDiscoveryEndpoint(DiscoveryClient discoveryClient, String serviceName) {
        this.discoveryClient = discoveryClient;
        this.serviceName = serviceName;
    }

    public DiscoveryClient getDiscoveryClient() {
        return discoveryClient;
    }

    public void setDiscoveryClient(DiscoveryClient discoveryClient) {
        this.discoveryClient = discoveryClient;
    }

    @Override
    public String getUrl() {
        return getInstance().getUri().toString();
    }

    @Override
    public String getName() {
        return serviceName;
    }

    public abstract ServiceInstance getInstance() throws InstanceNotFoundException;

}
