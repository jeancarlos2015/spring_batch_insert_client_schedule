package br.com.jeancarlos.spring_batch_v5.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduleConfig {
    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private Job job;

    @Scheduled(cron = "${CRON_BATCH}") // Runs the job every hour
    public void scheduleMyBatchJob() throws Exception {
        JobParameters params = new JobParametersBuilder() 
                .addString("JobID", String.valueOf(System.currentTimeMillis())) 
                .toJobParameters(); 
        jobLauncher.run(job, params); 
    }
    
}
