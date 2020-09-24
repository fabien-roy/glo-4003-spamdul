package ca.ulaval.glo4003.domain.parking;

import static ca.ulaval.glo4003.api.parking.helpers.ParkingStickerDtoBuilder.aParkingStickerDto;
import static ca.ulaval.glo4003.domain.account.helpers.AccountMother.createAccountId;
import static ca.ulaval.glo4003.domain.parking.helpers.ParkingAreaMother.createParkingAreaCode;
import static ca.ulaval.glo4003.domain.parking.helpers.ParkingStickerMother.createAddress;
import static ca.ulaval.glo4003.domain.parking.helpers.ParkingStickerMother.createReceptionMethod;
import static ca.ulaval.glo4003.domain.time.helpers.DayMother.createDay;

import ca.ulaval.glo4003.api.parking.dto.ParkingStickerDto;
import ca.ulaval.glo4003.domain.account.AccountId;
import ca.ulaval.glo4003.domain.account.AccountIdAssembler;
import ca.ulaval.glo4003.domain.parking.exception.InvalidReceptionMethodException;
import ca.ulaval.glo4003.domain.parking.exception.MissingAddressException;
import ca.ulaval.glo4003.domain.time.Days;
import ca.ulaval.glo4003.domain.time.exception.InvalidDayException;
import com.google.common.truth.Truth;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ParkingStickerAssemblerTest {
  private static final AccountId ACCOUNT_ID = createAccountId();
  private static final ParkingAreaCode PARKING_AREA = createParkingAreaCode();
  private static final ReceptionMethods RECEPTION_METHOD = createReceptionMethod();
  private static final String ADDRESS = createAddress();
  private static final Days VALID_DAY = createDay();

  @Mock private AccountIdAssembler accountIdAssembler;

  private ParkingStickerDto parkingStickerDto;

  private ParkingStickerAssembler parkingStickerAssembler;

  @Before
  public void setUp() {
    parkingStickerAssembler = new ParkingStickerAssembler(accountIdAssembler);

    BDDMockito.given(accountIdAssembler.assemble(ACCOUNT_ID.toString())).willReturn(ACCOUNT_ID);

    parkingStickerDto =
        aParkingStickerDto()
            .withAccountId(ACCOUNT_ID.toString())
            .withParkingArea(PARKING_AREA.toString())
            .withReceptionMethod(RECEPTION_METHOD.toString())
            .withAddress(ADDRESS)
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

  @Test(expected = MissingAddressException.class)
  public void
      givenPostalReceptionMethodAndNoAddress_whenAssembling_thenThrowMissingAddressException() {
    parkingStickerDto =
        aParkingStickerDto()
            .withReceptionMethod(ReceptionMethods.POSTAL.toString())
            .withoutAddress()
            .build();

    parkingStickerAssembler.assemble(parkingStickerDto);
  }

  @Test
  public void whenAssembling_thenReturnParkingStickerWithReceptionMethod() {
    ParkingSticker parkingSticker = parkingStickerAssembler.assemble(parkingStickerDto);

    Truth.assertThat(parkingSticker.getReceptionMethod()).isEqualTo(RECEPTION_METHOD);
  }

  @Test
  public void whenAssembling_thenReturnParkingStickerWithAddress() {
    ParkingSticker parkingSticker = parkingStickerAssembler.assemble(parkingStickerDto);

    Truth.assertThat(parkingSticker.getAddress()).isEqualTo(ADDRESS);
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
