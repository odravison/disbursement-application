package es.sequra.disbursementwebapplication;

import es.sequra.disbursementwebapplication.services.DisbursementService;
import org.jobrunr.scheduling.JobScheduler;
import org.jobrunr.scheduling.cron.Cron;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class DisbursementWebApplication {

    public static void main(String[] args) {
        ApplicationContext app = SpringApplication.run(DisbursementWebApplication.class, args);
        DisbursementService disbursementService = app.getBean(DisbursementService.class);
        JobScheduler jobScheduler = app.getBean(JobScheduler.class);
        jobScheduler.scheduleRecurrently(Cron.every5minutes(), () -> disbursementService.doDisbursementCalculations());
    }

}
