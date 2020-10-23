package ca.ulaval.glo4003.times.systemtime;

import static org.quartz.JobBuilder.*;

import ca.ulaval.glo4003.times.services.TimeScheduler;
import ca.ulaval.glo4003.times.services.TimeService;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

public class QuartzTimeScheduler implements TimeScheduler {
  private final String MONTHLY_CRON_EXPRESSION = "0 0 0 L * ?";
  private final String SECOND_CRON_EXPRESSION = "0/5 0 0 ? * * *";

  @Override
  public void start(TimeService timeService) {
    try {
      Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
      scheduler.getContext().put("timeService", timeService);

      scheduler.start();

      JobDetail job = newJob(CallTimeServiceJob.class).build();

      Trigger trigger =
          TriggerBuilder.newTrigger()
              .withSchedule(CronScheduleBuilder.cronSchedule(SECOND_CRON_EXPRESSION))
              .build();

      scheduler.scheduleJob(job, trigger);

      //            scheduler.shutdown();

    } catch (SchedulerException se) {
      se.printStackTrace();
    }
  }

  private class CallTimeServiceJob implements Job {

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
      TimeService timeService;

      try {
        timeService =
            (TimeService) jobExecutionContext.getScheduler().getContext().get("timeService");
        timeService.timeToExtract();
      } catch (SchedulerException e) {
        e.printStackTrace();
      }
    }
  }
}
