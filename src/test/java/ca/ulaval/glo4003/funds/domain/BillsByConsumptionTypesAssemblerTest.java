package ca.ulaval.glo4003.funds.domain;

import static com.google.common.truth.Truth.assertThat;

import ca.ulaval.glo4003.funds.assemblers.BillsByConsumptionsTypeAssembler;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

public class BillsByConsumptionTypesAssemblerTest {
  private BillsByConsumptionsTypeAssembler billsByConsumptionsTypeAssembler;
  private List<Bill> bills = new ArrayList<>();

  @Before
  public void setUp() {
    billsByConsumptionsTypeAssembler = new BillsByConsumptionsTypeAssembler();
  }

  @Test
  public void whenAssembling_thenReturnBillsConsumptionType() {
    BillsByConsumptionTypes billsByConsumptionTypes =
        billsByConsumptionsTypeAssembler.assemble(bills);

    assertThat(billsByConsumptionTypes).isNotNull();
  }
}
