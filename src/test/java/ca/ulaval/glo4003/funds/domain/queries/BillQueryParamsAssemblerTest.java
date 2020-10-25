package ca.ulaval.glo4003.funds.domain.queries;

import static ca.ulaval.glo4003.funds.helpers.BillMother.createBillType;
import static com.google.common.truth.Truth.assertThat;

import ca.ulaval.glo4003.funds.domain.BillType;
import ca.ulaval.glo4003.times.helpers.CustomDateTimeMother;
import org.junit.Before;
import org.junit.Test;

public class BillQueryParamsAssemblerTest {
  private BillQueryParamsAssembler billQueryParamsAssembler;

  private int year = CustomDateTimeMother.createDateTime().getYear();
  private BillType billType = createBillType();

  @Before
  public void setUp() {
    billQueryParamsAssembler = new BillQueryParamsAssembler();
  }

  @Test
  public void whenAssembling_thenReturnBillQueryParams() {
    BillQueryParams billQueryParams = billQueryParamsAssembler.assembleWithYear(year, billType);

    assertThat(billQueryParams.getParam()).containsKey(BillQueryParam.BILL_TYPE);
    assertThat(billQueryParams.getParam()).containsKey(BillQueryParam.YEAR);
  }
}
