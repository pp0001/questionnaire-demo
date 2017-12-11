package com.process.demo.model;

import org.springframework.cloud.config.java.ServiceScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("cloud")
@ServiceScan
public class CloudServiceConfig {

}