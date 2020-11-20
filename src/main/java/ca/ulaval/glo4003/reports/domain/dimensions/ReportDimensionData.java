package ca.ulaval.glo4003.reports.domain.dimensions;

public interface ReportDimensionData<T> {

  ReportDimensionType getType();

  T getValue();
}
