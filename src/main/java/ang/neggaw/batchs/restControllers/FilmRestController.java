package ang.neggaw.batchs.restControllers;

import ang.neggaw.batchs.stepItems.FilmsByYearsItemProcessor;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

        Job job = (Job) applicationContext.getBean("jobFilm");
        JobExecution jobExecution = jobLauncher.run(job, new JobParametersBuilder()
                .addLong("Time", System.currentTimeMillis())
                .addString("jobName", job.getName())
                .toJobParameters()
        );
        while (jobExecution.isRunning()) {
            System.out.println("Running job with name: " + job.getName() + " ...");
        }
        return jobExecution.getStatus();
    }

    @GetMapping(value = "/filmsByYear/{firstYear}/{secondYear}")
    public Map<String, JobParameter> loadFilmDataByYear(@PathVariable(value = "firstYear") long firstYear,
                                                        @PathVariable(value = "secondYear") long secondYear) throws Exception {

        Job job = (Job) applicationContext.getBean("jobFilmsByYears");

        Map<String, JobParameter> jobParameterMap = new HashMap<>();
        jobParameterMap.put("Film by years", new JobParameter(FilmsByYearsItemProcessor.class.getName()));
        jobParameterMap.put("jobName", new JobParameter(job.getName()));
        jobParameterMap.put("firstYear", new JobParameter(firstYear));
        jobParameterMap.put("secondYear", new JobParameter(secondYear));
        JobParameters jobParameters = new JobParameters(jobParameterMap);

        JobExecution jobExecution = jobLauncher.run(job, jobParameters);
        while (jobExecution.isRunning()) {
            System.out.println("Running job with name: " + job.getName() + " ...");
        }
        return jobParameterMap;
    }
}
