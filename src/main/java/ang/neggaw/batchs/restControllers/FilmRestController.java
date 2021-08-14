package ang.neggaw.batchs.restControllers;

import ang.neggaw.batchs.stepItems.FilmsByYearsItemProcessor;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by:
 *
 * @author ANG
 * @since 12-08-2021 12:53
 */

@RequiredArgsConstructor
@RestController
public class FilmRestController {

    private final JobLauncher jobLauncher;
    private final ApplicationContext applicationContext;

    @GetMapping(value = "/films")
    public BatchStatus loadFilmData() throws Exception {
        Map<String, JobParameter> jobParameterMap = new HashMap<>();
        jobParameterMap.put("Time", new JobParameter(System.currentTimeMillis()));
        JobParameters jobParameters = new JobParameters(jobParameterMap);

        Job job = (Job) applicationContext.getBean("jobFilm");

        JobExecution jobExecution = jobLauncher.run(job, jobParameters);
        while (jobExecution.isRunning()) {
            System.out.println("...");
        }
        return jobExecution.getStatus();
    }

    @GetMapping(value = "/filmsByYear")
    public BatchStatus loadFilmDataByYear() throws Exception {
        Map<String, JobParameter> jobParameterMap = new HashMap<>();
        jobParameterMap.put("Film by years", new JobParameter(FilmsByYearsItemProcessor.class.getName()));
        JobParameters jobParameters = new JobParameters(jobParameterMap);

        Job job = (Job) applicationContext.getBean("jobFilmsByYears");
        JobExecution jobExecution = jobLauncher.run(job, jobParameters);
        while (jobExecution.isRunning()) {
            System.out.println("...");
        }
        return jobExecution.getStatus();
    }
}
