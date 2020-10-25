package ca.ulaval.glo4003.times.systemtime;

import static org.quartz.JobBuilder.*;

import ca.ulaval.glo4003.times.services.TimeScheduler;
import java.util.ArrayList;
import java.util.List;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

public class QuartzTimeScheduler implements TimeScheduler {
  private static final String MONTHLY_CRON_EXPRESSION = "0 0 0 L * ?";
  private static final String SECOND_CRON_EXPRESSION = "* * * ? * * *";
  private static final String MINUTE_CRON_EXPRESSION = "0 * * ? * *";
  private List<JobHandler> JobList = new ArrayList();

  public void start() {
    try {
      Scheduler scheduler = new StdSchedulerFactory().getScheduler();

      JobDetail job = newJob(ExecuteHandlerJob.class).build();
      job.getJobDataMap().put("JobList", JobList);

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
}
