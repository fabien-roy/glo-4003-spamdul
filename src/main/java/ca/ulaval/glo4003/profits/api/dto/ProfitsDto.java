package ca.ulaval.glo4003.profits.api.dto;

public class ProfitsDto {
  public double profits;

  @Override
  public String toString() {
    return String.format("BillDto{profits='%.2f'", profits);
  }
}
