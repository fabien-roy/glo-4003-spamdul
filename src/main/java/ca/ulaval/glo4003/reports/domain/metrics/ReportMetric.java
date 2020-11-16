package ca.ulaval.glo4003.reports.domain.metrics;

import ca.ulaval.glo4003.reports.domain.ReportPeriodData;

// TODO : Make sure all is used in ReportMetric
public abstract class ReportMetric<T> {

  public abstract ReportMetricType getType();

  public abstract void calculate(ReportPeriodData data);

  protected ReportMetricData<T> toMetricData(T value) {
    return new ReportMetricData<T>() {
      @Override
      public ReportMetricType getType() {
        return ReportMetric.this.getType();
      }

      @Override
      public T getValue() {
        return value;
      }
    };
  }
}
