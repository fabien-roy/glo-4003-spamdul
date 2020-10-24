package ca.ulaval.glo4003.times.systemtime;

import static org.quartz.JobBuilder.*;

import ca.ulaval.glo4003.times.JobHandler;
import ca.ulaval.glo4003.times.services.TimeScheduler;
import java.util.ArrayList;
import java.util.List;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

public class QuartzTimeScheduler implements TimeScheduler {
  private final String MONTHLY_CRON_EXPRESSION = "0 0 0 L * ?";
  private final String SECOND_CRON_EXPRESSION = "* 0 0 ? * * *";
  private List<JobHandler> JobList = new ArrayList();

  @Override
  public void start() {
    try {
      Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

      scheduler.start();

      JobDetail job = newJob(ExecuteHandlerJob.class).build();

      Trigger trigger =
          TriggerBuilder.newTrigger()
              .withSchedule(CronScheduleBuilder.cronSchedule(SECOND_CRON_EXPRESSION))
              .build();

      scheduler.scheduleJob(job, trigger);

    } catch (SchedulerException se) {
      se.printStackTrace();
    }
  }

  public void SubscribeJob(JobHandler job) {
    JobList.add(job);
  }

  private class ExecuteHandlerJob implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
      for (JobHandler job : JobList) {
        job.invoke();
      }
    }
  }
}
