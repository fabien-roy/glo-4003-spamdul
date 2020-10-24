package ca.ulaval.glo4003.times.services;

import ca.ulaval.glo4003.times.systemtime.JobHandler;

public interface TimeScheduler {
  void SubscribeJob(JobHandler jobHandler);
}
