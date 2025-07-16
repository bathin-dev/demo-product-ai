package com.example.demo.config;

import org.springframework.stereotype.Component;

/**
 * This class is no longer needed since the scheduled job will run automatically at startup.
 * Keeping the file for reference but removing implementation.
 */
@Component
public class DataInitializer {
    // Scheduled task with initialDelay=0 will run at application startup
}
