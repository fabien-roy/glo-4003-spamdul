package ca.ulaval.glo4003.schedulers.systemtime;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;

@RunWith(MockitoJUnitRunner.class)
public class InvokeHandlerTest {

  @Mock private JobHandler firstJobHandler;
  @Mock private JobHandler secondJobHandler;
  @Mock private JobExecutionContext jobExecutionContext;
  @Mock private JobDetail jobDetail;
  @Mock private JobDataMap jobDataMap;

  private InvokeHandler invokeHandler;

  @Before
  public void setUp() {
    invokeHandler = new InvokeHandler();

    when(jobExecutionContext.getJobDetail()).thenReturn(jobDetail);
    when(jobDetail.getJobDataMap()).thenReturn(jobDataMap);
    when(jobDataMap.get("jobHandlers"))
        .thenReturn(Arrays.asList(firstJobHandler, secondJobHandler));
  }

  @Test
  public void whenExecuting_thenInvokeHandlers() {
    invokeHandler.execute(jobExecutionContext);

    verify(firstJobHandler).invoke();
    verify(secondJobHandler).invoke();
  }
}
