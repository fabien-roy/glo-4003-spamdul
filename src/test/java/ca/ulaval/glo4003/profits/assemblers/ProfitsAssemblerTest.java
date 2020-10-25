package ca.ulaval.glo4003.profits.assemblers;

import static ca.ulaval.glo4003.funds.helpers.MoneyMother.createMoney;
import static com.google.common.truth.Truth.assertThat;

import ca.ulaval.glo4003.funds.domain.Money;
import ca.ulaval.glo4003.profits.api.dto.ProfitsDto;
import org.junit.Test;

public class ProfitsAssemblerTest {

  private final Money money = createMoney();

  private ProfitsAssembler profitsAssembler = new ProfitsAssembler();

  @Test
  public void whenAssemblingDTO_thenReturnProfitsDTOWithMoney() {
    ProfitsDto profitsDto = profitsAssembler.assemble(money);

    assertThat(profitsDto.profits).isEqualTo(money.toDouble());
  }
}
