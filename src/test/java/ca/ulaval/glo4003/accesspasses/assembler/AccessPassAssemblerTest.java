package ca.ulaval.glo4003.accesspasses.assembler;

import static ca.ulaval.glo4003.accesspasses.helpers.AccessPassDtoBuilder.anAccessPassDto;
import static ca.ulaval.glo4003.accounts.helpers.AccountMother.createAccountId;
import static ca.ulaval.glo4003.cars.helpers.LicensePlateMother.createLicensePlate;
import static com.google.common.truth.Truth.assertThat;
import static org.mockito.Mockito.when;

import ca.ulaval.glo4003.accesspasses.api.dto.AccessPassDto;
import ca.ulaval.glo4003.accesspasses.domain.AccessPass;
import ca.ulaval.glo4003.accounts.assemblers.AccountIdAssembler;
import ca.ulaval.glo4003.accounts.domain.AccountId;
import ca.ulaval.glo4003.cars.assemblers.LicensePlateAssembler;
import ca.ulaval.glo4003.cars.domain.LicensePlate;
import ca.ulaval.glo4003.times.exceptions.InvalidDayOfWeekException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class AccessPassAssemblerTest {

  @Mock private AccountIdAssembler accountIdAssembler;
  @Mock private LicensePlateAssembler licensePlateAssembler;

  private AccessPassAssembler accessPassAssembler;

  private final AccountId accountId = createAccountId();
  private final LicensePlate licensePlate = createLicensePlate();
  private AccessPassDto accessPassDto =
      anAccessPassDto().withLicensePlate(licensePlate.toString()).build();

  @Before
  public void setUp() {
    accessPassAssembler = new AccessPassAssembler(accountIdAssembler, licensePlateAssembler);

    when(accountIdAssembler.assemble(accountId.toString())).thenReturn(accountId);
    when(licensePlateAssembler.assemble(licensePlate.toString())).thenReturn(licensePlate);
  }

  @Test
  public void whenAssembling_thenReturnAccessPassWithAccountId() {
    AccessPass accessPass = accessPassAssembler.assemble(accessPassDto, accountId.toString());

    assertThat(accessPass.getAccountId()).isSameInstanceAs(accountId);
  }

  @Test
  public void whenAssembling_thenReturnAccessPassWithAccessDay() {
    AccessPass accessPass = accessPassAssembler.assemble(accessPassDto, accountId.toString());

    assertThat(accessPass.getAccessDay().toString()).isEqualTo(accessPassDto.accessDay);
  }

  @Test(expected = InvalidDayOfWeekException.class)
  public void givenInvalidAccessDay_whenAssembling_thenThrowInvalidDayOfWeekException() {
    String invalidAccessDay = "invalidDayOfWeek";
    accessPassDto = anAccessPassDto().withAccessDay(invalidAccessDay).build();

    accessPassAssembler.assemble(accessPassDto, accountId.toString());
  }

  @Test
  public void whenAssembling_thenReturnAccessPassWithLicensePlate() {
    AccessPass accessPass = accessPassAssembler.assemble(accessPassDto, accountId.toString());

    assertThat(accessPass.getLicensePlate()).isSameInstanceAs(licensePlate);
  }

  @Test
  public void givenNoLicensePlate_whenAssembling_thenReturnAccessPassWithoutLicensePlate() {
    accessPassDto = anAccessPassDto().withLicensePlate(null).build();

    AccessPass accessPass = accessPassAssembler.assemble(accessPassDto, accountId.toString());

    assertThat(accessPass.getLicensePlate()).isNull();
  }
}
