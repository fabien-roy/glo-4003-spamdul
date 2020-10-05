package ca.ulaval.glo4003.funds.domain;

import com.google.common.truth.Truth;
import org.junit.Before;
import org.junit.Test;

public class BillIdGeneratorTest {

  private BillIdGenerator billIdGenerator;

  @Before
  public void setUp() {
    billIdGenerator = new BillIdGenerator();
  }

  @Test
  public void whenGenerating_thenReturnDifferentIds() {
    BillId firstId = billIdGenerator.generate();
    BillId secondId = billIdGenerator.generate();

    Truth.assertThat(firstId).isNotEqualTo(secondId);
  }
}
