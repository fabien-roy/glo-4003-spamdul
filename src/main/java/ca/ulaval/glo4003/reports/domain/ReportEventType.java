package ca.ulaval.glo4003.reports.domain;

// TODO : When refactoring reports for profits, make sure we create a report event for PAYING bills
//        and not just creating them.
public enum ReportEventType {
  BILL_PAID_FOR_OFFENSE,
  BILL_PAID_FOR_PARKING_STICKER,
  BILL_PAID_FOR_ACCESS_PASS,
  GATE_ENTERED
}
