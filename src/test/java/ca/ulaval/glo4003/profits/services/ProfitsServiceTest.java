package ca.ulaval.glo4003.profits.services;

import static com.google.common.truth.Truth.assertThat;
import static org.mockito.Mockito.when;

import ca.ulaval.glo4003.funds.domain.Money;
import ca.ulaval.glo4003.funds.services.BillService;
import ca.ulaval.glo4003.profits.api.dto.ProfitsDto;
import ca.ulaval.glo4003.profits.assemblers.ProfitsAssembler;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ProfitsServiceTest {

  private static final int A_YEAR = 2020; // TODO : BUILDER AND OBJECT MOTHER HERE
  private static final Money AN_AMOUNT = new Money(200);

  private ProfitsService profitsService;

  private ProfitsAssembler profitsAssembler;
  @Mock private BillService billService;

  @Before
  public void setup() {
    profitsAssembler = new ProfitsAssembler();
    when(billService.getProfitsFromParkingStickerBillsByYear(A_YEAR)).thenReturn(AN_AMOUNT);

    profitsService = new ProfitsService(profitsAssembler, billService);
  }

  @Test
  public void whenGettingProfitsFromParkingSticker_shouldReturnProfitsDto() {
    ProfitsDto profitsDto = profitsService.getParkingStickerProfits(A_YEAR);

    assertThat(profitsDto.profits).isEqualTo(AN_AMOUNT.toDouble());
  }
}
