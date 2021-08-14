package ang.neggaw.batchs.listeners;

import lombok.extern.log4j.Log4j2;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.stereotype.Component;

/**
 * Created by:
 *
 * @author ANG
 * @since 13-08-2021 17:56
 */

@Log4j2
@Component
public class FilmJobExecutionListener implements JobExecutionListener {

    @Override
    public void beforeJob(JobExecution jobExecution) {
        log.info("beforeJobExecution...");
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        log.info("afterJobExecution: " + jobExecution.getStatus());
    }
}
