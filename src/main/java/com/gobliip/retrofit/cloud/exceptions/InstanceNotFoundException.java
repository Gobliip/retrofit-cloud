package com.gobliip.retrofit.cloud.exceptions;

/**
 * Created by lsamayoa on 7/15/15.
 */
public class InstanceNotFoundException extends RuntimeException {
    private String serviceName;
    public InstanceNotFoundException(String serviceName) {
        super("Instance not found for service: " + serviceName);
        this.serviceName = serviceName;
    }
}
