package ca.ulaval.glo4003.schedulers.systemtime;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

import ca.ulaval.glo4003.schedulers.domain.exceptions.CannotBuildSchedulerException;
import java.util.ArrayList;
import java.util.List;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

public class SchedulerBuilder {
  private static final String MONTHLY_CRON_EXPRESSION = "0 0 0 L * ?";
  private static final String SECOND_CRON_EXPRESSION = "* * * ? * * *";
  private static final String MINUTE_CRON_EXPRESSION = "0 * * ? * *";
  private List<JobHandler> jobHandlers = new ArrayList<>();

  public static SchedulerBuilder newScheduler() {
    return new SchedulerBuilder();
  }

  public SchedulerBuilder withJobHandlers(List<JobHandler> jobHandlers) {
    this.jobHandlers = jobHandlers;
    return this;
  }

  public Scheduler build() {
    Scheduler scheduler;

    try {
      scheduler = new StdSchedulerFactory().getScheduler();

      JobDetail job = newJob(InvokeHandler.class).build();
      job.getJobDataMap().put("jobHandlers", jobHandlers);

      Trigger trigger =
          newTrigger()
              .withSchedule(CronScheduleBuilder.cronSchedule(MONTHLY_CRON_EXPRESSION))
              .build();

      scheduler.scheduleJob(job, trigger);
    } catch (SchedulerException se) {
      throw new CannotBuildSchedulerException();
    }

    return scheduler;
  }
}
