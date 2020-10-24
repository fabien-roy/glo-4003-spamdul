package ca.ulaval.glo4003.times.systemtime;

import static org.quartz.JobBuilder.*;

import ca.ulaval.glo4003.times.services.TimeScheduler;
import java.util.ArrayList;
import java.util.List;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

public class QuartzTimeScheduler implements TimeScheduler {
  private static final String MONTHLY_CRON_EXPRESSION = "0 0 0 L * ?";
  private static final String SECOND_CRON_EXPRESSION = "* 0 0 ? * * *";
  private List<JobHandler> JobList = new ArrayList();
  private Scheduler scheduler;

  public void start() {
    try {
      scheduler = new StdSchedulerFactory().getScheduler();

      JobDetail job = newJob(ExecuteHandlerJob.class).build();

      Trigger trigger =
          TriggerBuilder.newTrigger()
              .withSchedule(CronScheduleBuilder.cronSchedule(SECOND_CRON_EXPRESSION))
              .build();

      scheduler.scheduleJob(job, trigger);

      scheduler.start();

      System.out.println("Scheduler ready");
    } catch (SchedulerException se) {
      se.printStackTrace();
    }
  }

  @Override
  public void SubscribeJob(JobHandler job) {
    JobList.add(job);
  }

  public class ExecuteHandlerJob implements Job {

    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
      System.out.println("Launching stuff");
      for (JobHandler job : JobList) {
        job.invoke();
      }
    }
  }
}
