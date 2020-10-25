package ca.ulaval.glo4003.interfaces.systemtime;

import static ca.ulaval.glo4003.interfaces.systemtime.SchedulerBuilder.newSchedule;
import static com.google.common.truth.Truth.assertThat;

import java.util.Collections;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.quartz.Scheduler;

@RunWith(MockitoJUnitRunner.class)
public class SchedulerBuilderTest {
  @Mock private JobHandler jobHandler;

  @Test
  public void whenBuilding_thenReturnScheduler() {
    Scheduler scheduler = newSchedule().build();

    assertThat(scheduler).isNotNull();
  }

  @Test
  public void givenJobHandlers_whenBuilding_thenReturnScheduler() {
    List<JobHandler> jobHandlers = Collections.singletonList(jobHandler);

    Scheduler scheduler = newSchedule().withJobHandlers(jobHandlers).build();

    assertThat(scheduler).isNotNull();
  }
}
