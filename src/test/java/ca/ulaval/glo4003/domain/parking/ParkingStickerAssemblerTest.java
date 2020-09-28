package ca.ulaval.glo4003.domain.parking;

import static ca.ulaval.glo4003.api.parking.helpers.ParkingStickerDtoBuilder.aParkingStickerDto;
import static ca.ulaval.glo4003.domain.account.helpers.AccountMother.createAccountId;
import static ca.ulaval.glo4003.domain.location.helpers.PostalCodeMother.createPostalCode;
import static ca.ulaval.glo4003.domain.parking.helpers.ParkingAreaMother.createParkingAreaCode;
import static ca.ulaval.glo4003.domain.parking.helpers.ParkingStickerMother.createReceptionMethod;
import static ca.ulaval.glo4003.domain.time.helpers.DayMother.createDay;
import static org.mockito.Mockito.when;

import ca.ulaval.glo4003.api.parking.dto.ParkingStickerDto;
import ca.ulaval.glo4003.domain.account.AccountId;
import ca.ulaval.glo4003.domain.account.AccountIdAssembler;
import ca.ulaval.glo4003.domain.location.PostalCode;
import ca.ulaval.glo4003.domain.location.PostalCodeAssembler;
import ca.ulaval.glo4003.domain.parking.exception.InvalidReceptionMethodException;
import ca.ulaval.glo4003.domain.parking.exception.MissingPostalCodeException;
import ca.ulaval.glo4003.domain.time.Days;
import ca.ulaval.glo4003.domain.time.exception.InvalidDayException;
import com.google.common.truth.Truth;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ParkingStickerAssemblerTest {
  private static final AccountId ACCOUNT_ID = createAccountId();
  private static final ParkingAreaCode PARKING_AREA = createParkingAreaCode();
  private static final ReceptionMethods RECEPTION_METHOD = createReceptionMethod();
  private static final PostalCode POSTAL_CODE = createPostalCode();
  private static final Days VALID_DAY = createDay();

  @Mock private AccountIdAssembler accountIdAssembler;
  @Mock private ParkingAreaCodeAssembler parkingAreaCodeAssembler;
  @Mock private PostalCodeAssembler postalCodeAssembler;

  private ParkingStickerDto parkingStickerDto;

  private ParkingStickerAssembler parkingStickerAssembler;

  @Before
  public void setUp() {
    parkingStickerAssembler =
        new ParkingStickerAssembler(
            accountIdAssembler, parkingAreaCodeAssembler, postalCodeAssembler);

    when(accountIdAssembler.assemble(ACCOUNT_ID.toString())).thenReturn(ACCOUNT_ID);
    when(parkingAreaCodeAssembler.assemble(PARKING_AREA.toString())).thenReturn(PARKING_AREA);
    when(postalCodeAssembler.assemble(POSTAL_CODE.toString())).thenReturn(POSTAL_CODE);

    parkingStickerDto =
        aParkingStickerDto()
            .withAccountId(ACCOUNT_ID.toString())
            .withParkingArea(PARKING_AREA.toString())
            .withReceptionMethod(RECEPTION_METHOD.toString())
            .withPostalCode(POSTAL_CODE.toString())
            .withValidDay(VALID_DAY.toString())
            .build();
  }

  @Test
  public void whenAssembling_thenReturnParkingStickerWithAccountId() {
    ParkingSticker parkingSticker = parkingStickerAssembler.assemble(parkingStickerDto);

    Truth.assertThat(parkingSticker.getAccountId()).isSameInstanceAs(ACCOUNT_ID);
  }

  @Test
  public void whenAssembling_thenReturnParkingStickerWithParkingArea() {
    ParkingSticker parkingSticker = parkingStickerAssembler.assemble(parkingStickerDto);

    Truth.assertThat(parkingSticker.getParkingAreaCode()).isEqualTo(PARKING_AREA);
  }

  @Test(expected = InvalidReceptionMethodException.class)
  public void
      givenInvalidReceptionMethod_whenAssembling_thenThrowInvalidReceptionMethodException() {
    parkingStickerDto = aParkingStickerDto().withReceptionMethod("invalidReceptionMethod").build();

    parkingStickerAssembler.assemble(parkingStickerDto);
  }

  @Test(expected = MissingPostalCodeException.class)
  public void
      givenPostalReceptionMethodAndNoPostalCode_whenAssembling_thenThrowMissingPostalCodeException() {
    parkingStickerDto =
        aParkingStickerDto()
            .withReceptionMethod(ReceptionMethods.POSTAL.toString())
            .withoutPostalCode()
            .build();

    parkingStickerAssembler.assemble(parkingStickerDto);
  }

  @Test
  public void whenAssembling_thenReturnParkingStickerWithReceptionMethod() {
    ParkingSticker parkingSticker = parkingStickerAssembler.assemble(parkingStickerDto);

    Truth.assertThat(parkingSticker.getReceptionMethod()).isEqualTo(RECEPTION_METHOD);
  }

  @Test
  public void whenAssembling_thenReturnParkingStickerWithPostalCode() {
    ParkingSticker parkingSticker = parkingStickerAssembler.assemble(parkingStickerDto);

    Truth.assertThat(parkingSticker.getPostalCode()).isEqualTo(POSTAL_CODE);
  }

  @Test
  public void whenAssembling_thenReturnParkingStickerWithValidDay() {
    ParkingSticker parkingSticker = parkingStickerAssembler.assemble(parkingStickerDto);

    Truth.assertThat(parkingSticker.getValidDay()).isEqualTo(VALID_DAY);
  }

  @Test(expected = InvalidDayException.class)
  public void givenInvalidValidDay_whenAssembling_thenThrowInvalidDayException() {
    parkingStickerDto = aParkingStickerDto().withValidDay("invalidDay").build();

    parkingStickerAssembler.assemble(parkingStickerDto);
  }
}
