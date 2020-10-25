package ca.ulaval.glo4003.times.systemtime;

import java.util.List;
import org.quartz.Job;
import org.quartz.JobExecutionContext;

public class ExecuteHandlerJob implements Job {

  @Override
  public void execute(JobExecutionContext jobExecutionContext) {
    List<JobHandler> jobList =
        (List<JobHandler>) jobExecutionContext.getJobDetail().getJobDataMap().get("JobList");
    for (JobHandler job : jobList) {
      job.invoke();
    }
  }
}
