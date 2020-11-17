package ca.ulaval.glo4003.reports.domain.dimensions;

import ca.ulaval.glo4003.reports.domain.ReportEvent;
import ca.ulaval.glo4003.reports.domain.ReportPeriodData;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class ReportDimension<T> {

  protected abstract ReportDimensionType getType();

  protected abstract List<T> getValues();

  protected abstract boolean filter(ReportEvent reportEvent, T value);

  public List<ReportPeriodData> splitAll(List<ReportPeriodData> data) {
    return data.stream().flatMap(this::split).collect(Collectors.toList());
  }

  private Stream<ReportPeriodData> split(ReportPeriodData data) {
    return getValues().stream().map(value -> filterAll(data, value));
  }

  protected ReportPeriodData filterAll(ReportPeriodData data, T value) {
    List<ReportEvent> splitEvents =
        data.getEvents().stream()
            .filter(event -> filter(event, value))
            .collect(Collectors.toList());

    ReportPeriodData filteredData = new ReportPeriodData(splitEvents);
    filteredData.setDimensions(new ArrayList<>(data.getDimensions()));
    filteredData.addDimension(toDimensionData(value));

    return filteredData;
  }

  protected ReportDimensionData<T> toDimensionData(T value) {
    return new ReportDimensionData<T>() {
      @Override
      public ReportDimensionType getType() {
        return ReportDimension.this.getType();
      }

      @Override
      public T getValue() {
        return value;
      }
    };
  }
}
