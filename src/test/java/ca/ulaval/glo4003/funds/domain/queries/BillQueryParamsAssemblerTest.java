package ca.ulaval.glo4003.funds.domain.queries;

import static ca.ulaval.glo4003.funds.helpers.BillMother.createBillType;
import static ca.ulaval.glo4003.times.helpers.CustomDateTimeMother.createDateTime;
import static com.google.common.truth.Truth.assertThat;

import ca.ulaval.glo4003.funds.domain.BillType;
import ca.ulaval.glo4003.funds.exception.InvalidYearException;
import org.junit.Before;
import org.junit.Test;

public class BillQueryParamsAssemblerTest {
  private BillQueryParamsAssembler billQueryParamsAssembler;

  private int year = createDateTime().getYear();
  private BillType billType = createBillType();

  @Before
  public void setUp() {
    billQueryParamsAssembler = new BillQueryParamsAssembler();
  }

  @Test
  public void whenAssembling_thenReturnBillQueryParamsWithYear() {
    BillQueryParams billQueryParams = billQueryParamsAssembler.assemble(year, billType);

    assertThat(billQueryParams.getParam()).containsKey(BillQueryParam.YEAR);
  }

  @Test
  public void whenAssembling_thenReturnBillQueryParamsWithBillType() {
    BillQueryParams billQueryParams = billQueryParamsAssembler.assemble(year, billType);

    assertThat(billQueryParams.getParam()).containsKey(BillQueryParam.BILL_TYPE);
  }

  @Test(expected = InvalidYearException.class)
  public void givenNegativeYear_whenAssembling_thenThrowInvalidYearException() {
    int negativeYear = -1000;

    billQueryParamsAssembler.assemble(negativeYear, billType);
  }

  @Test(expected = InvalidYearException.class)
  public void givenNullYear_whenAssembling_thenThrowInvalidYearException() {
    int nullYear = 0;

    billQueryParamsAssembler.assemble(nullYear, billType);
  }
}
