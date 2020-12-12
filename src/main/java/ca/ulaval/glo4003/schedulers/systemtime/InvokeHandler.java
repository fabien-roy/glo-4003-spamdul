package ca.ulaval.glo4003.schedulers.systemtime;

import java.util.List;
import org.quartz.Job;
import org.quartz.JobExecutionContext;

public class InvokeHandler implements Job {
  @Override
  public void execute(JobExecutionContext jobExecutionContext) {
    List<JobHandler> jobHandlers =
        (List<JobHandler>) jobExecutionContext.getJobDetail().getJobDataMap().get("jobHandlers");
    for (JobHandler jobHandler : jobHandlers) {
      jobHandler.invoke();
    }
  }
}
