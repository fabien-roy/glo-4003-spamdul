package ca.ulaval.glo4003.domain.offense;

import static ca.ulaval.glo4003.domain.offense.helpers.OffenseBuilder.anOffense;

import com.google.common.truth.Truth;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class OffenseTest {
  private Offense offense;

  @Before
  public void setUp() {
    offense =
        anOffense().withReasonText("A reason").withReasonCode("Code-1").withAmount(666).build();
  }

  @Test
  public void givenOffense_whenGettingReasonText_thenReasonTextIsReturned() {
    Truth.assertThat(offense.getReasonText()).isEqualTo("A reason");
  }

  @Test
  public void givenOffense_whenGettingReasonCode_thenReasonCodeIsReturned() {
    Truth.assertThat(offense.getReasonCode()).isEqualTo("Code-1");
  }

  @Test
  public void givenOffense_whenGettingAmount_thenAmountIsReturned() {
    Truth.assertThat(offense.getAmount()).isEqualTo(666);
  }
}
