package org.example.spring.boot.web.app.product;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Callable;

public class TrackingTask implements Callable<Void> {

    private static final Logger logger = LoggerFactory.getLogger(TrackingTask.class);
    private final Runnable task;
    private final String taskName;

    public TrackingTask(Runnable task, String taskName) {
        this.task = task;
        this.taskName = taskName;
    }

    @Override
    public Void call() throws Exception {
        long startTime = System.currentTimeMillis();
        Thread currentThread = Thread.currentThread();

        logger.info("Thread name: {}", currentThread.getName());
        logger.info("Thread state: {}", currentThread.getState());
        logger.info("Task '{}' started at {}", taskName, startTime);

        try {
            task.run();
        } finally {
            long endTime = System.currentTimeMillis();
            long duration = endTime - startTime;

            logger.info("Task '{}' finished at {}", taskName, endTime);
            logger.info("Task '{}' duration: {} ms", taskName, duration);
            logger.info("Thread state after task completion: {}", currentThread.getState());
        }
        return null;
    }
}
