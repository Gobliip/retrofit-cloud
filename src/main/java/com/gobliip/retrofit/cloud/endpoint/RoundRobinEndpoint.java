package com.gobliip.retrofit.cloud.endpoint;

import com.gobliip.retrofit.cloud.exceptions.InstanceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by lsamayoa on 7/15/15.
 */
public class RoundRobinEndpoint extends ServiceDiscoveryEndpoint{

    private static final Logger LOGGER = LoggerFactory.getLogger(RoundRobinEndpoint.class);

    private AtomicInteger currentInstanceIdx = new AtomicInteger(0);

    public RoundRobinEndpoint(DiscoveryClient discoveryClient, String serviceName) {
        super(discoveryClient, serviceName);
    }

    @Override
    public ServiceInstance getInstance() throws InstanceNotFoundException {
        final String serviceName = getName();
        final List<ServiceInstance> instances = getDiscoveryClient().getInstances(serviceName);
        if (instances.size() <= 0) {
            LOGGER.warn("No ServiceInstance found for {}", serviceName);
            throw new InstanceNotFoundException(serviceName);
        }
        if (currentInstanceIdx.get() > instances.size()) {
            currentInstanceIdx.set(0);
        }
        return instances.get(currentInstanceIdx.getAndIncrement());
    }
}
