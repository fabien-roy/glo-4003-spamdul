package ca.ulaval.glo4003.funds.infrastructure;

import static ca.ulaval.glo4003.funds.helpers.BillMother.createBillType;
import static com.google.common.truth.Truth.assertThat;

import ca.ulaval.glo4003.funds.domain.BillQuery;
import ca.ulaval.glo4003.funds.domain.BillType;
import ca.ulaval.glo4003.funds.infrastructure.filters.BillTypeFilterInMemory;
import ca.ulaval.glo4003.funds.infrastructure.filters.YearFilterInMemory;
import org.junit.Before;
import org.junit.Test;

public class BillQueryBuilderInMemoryTest {

  private BillQueryBuilderInMemory billQueryBuilderInMemory;
  private final BillType billType = createBillType();
  private static final int A_YEAR = 2020; // TODO : Create a builder for years

  @Before
  public void setup() {
    billQueryBuilderInMemory = new BillQueryBuilderInMemory();
  }

  @Test
  public void whenBuilding_thenShouldReturnBillBuilder() {
    BillQuery billQuery = billQueryBuilderInMemory.build();

    assertThat(billQuery).isInstanceOf(BillQueryInMemory.class);
  }

  @Test
  public void whenWithBillType_thenShouldAddBillTypeFilter() {
    billQueryBuilderInMemory.withBillType(billType);

    assertThat(billQueryBuilderInMemory.getFilters().get(0))
        .isInstanceOf(BillTypeFilterInMemory.class);
  }

  @Test
  public void whenWithYear_thenShouldAddYearFilter() {
    billQueryBuilderInMemory.withYear(A_YEAR);

    assertThat(billQueryBuilderInMemory.getFilters().get(0)).isInstanceOf(YearFilterInMemory.class);
  }
}
