package ca.ulaval.glo4003.carboncredits.systemtime;

import static org.mockito.Mockito.verify;

import ca.ulaval.glo4003.carboncredits.services.CarbonCreditService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ConvertCarbonCreditHandlerTest {

  @Mock private CarbonCreditService carbonCreditService;

  private ConvertCarbonCreditHandler convertCarbonCreditHandler;

  @Before
  public void setUp() {
    convertCarbonCreditHandler = new ConvertCarbonCreditHandler(carbonCreditService);
  }

  @Test
  public void whenInvoking_thenExtractMoneyFromSustainableMobilityProgramBank() {
    convertCarbonCreditHandler.invoke();

    verify(carbonCreditService).allocateRemainingFundToCarbonCreditInitiative();
  }
}
