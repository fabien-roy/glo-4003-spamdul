package ca.ulaval.glo4003.parkings.assemblers;

import static ca.ulaval.glo4003.accounts.helpers.AccountMother.createAccountId;
import static ca.ulaval.glo4003.communications.helpers.EmailAddressMother.createEmailAddress;
import static ca.ulaval.glo4003.domain.time.helpers.DayMother.createDay;
import static ca.ulaval.glo4003.locations.helpers.PostalCodeMother.createPostalCode;
import static ca.ulaval.glo4003.parkings.helpers.ParkingAreaMother.createParkingAreaCode;
import static ca.ulaval.glo4003.parkings.helpers.ParkingStickerDtoBuilder.aParkingStickerDto;
import static ca.ulaval.glo4003.parkings.helpers.ParkingStickerMother.createReceptionMethod;
import static org.mockito.Mockito.when;

import ca.ulaval.glo4003.accounts.assemblers.AccountIdAssembler;
import ca.ulaval.glo4003.accounts.domain.AccountId;
import ca.ulaval.glo4003.communications.assemblers.EmailAddressAssembler;
import ca.ulaval.glo4003.communications.domain.EmailAddress;
import ca.ulaval.glo4003.domain.time.Days;
import ca.ulaval.glo4003.domain.time.exception.InvalidDayException;
import ca.ulaval.glo4003.locations.assemblers.PostalCodeAssembler;
import ca.ulaval.glo4003.locations.domain.PostalCode;
import ca.ulaval.glo4003.parkings.api.dto.ParkingStickerDto;
import ca.ulaval.glo4003.parkings.domain.ParkingAreaCode;
import ca.ulaval.glo4003.parkings.domain.ParkingSticker;
import ca.ulaval.glo4003.parkings.domain.ReceptionMethods;
import ca.ulaval.glo4003.parkings.exceptions.InvalidReceptionMethodException;
import ca.ulaval.glo4003.parkings.exceptions.MissingEmailException;
import ca.ulaval.glo4003.parkings.exceptions.MissingPostalCodeException;
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
  private static final EmailAddress EMAIL_ADDRESS = createEmailAddress();
  private static final Days VALID_DAY = createDay();

  @Mock private AccountIdAssembler accountIdAssembler;
  @Mock private ParkingAreaCodeAssembler parkingAreaCodeAssembler;
  @Mock private PostalCodeAssembler postalCodeAssembler;
  @Mock private EmailAddressAssembler emailAddressAssembler;

  private ParkingStickerDto parkingStickerDto;

  private ParkingStickerAssembler parkingStickerAssembler;

  @Before
  public void setUp() {
    parkingStickerAssembler =
        new ParkingStickerAssembler(
            parkingAreaCodeAssembler,
            accountIdAssembler,
            postalCodeAssembler,
            emailAddressAssembler);

    when(accountIdAssembler.assemble(ACCOUNT_ID.toString())).thenReturn(ACCOUNT_ID);
    when(parkingAreaCodeAssembler.assemble(PARKING_AREA.toString())).thenReturn(PARKING_AREA);
    when(postalCodeAssembler.assemble(POSTAL_CODE.toString())).thenReturn(POSTAL_CODE);
    when(emailAddressAssembler.assemble(EMAIL_ADDRESS.toString())).thenReturn(EMAIL_ADDRESS);

    parkingStickerDto =
        aParkingStickerDto()
            .withAccountId(ACCOUNT_ID.toString())
            .withParkingArea(PARKING_AREA.toString())
            .withReceptionMethod(RECEPTION_METHOD.toString())
            .withPostalCode(POSTAL_CODE.toString())
            .withEmail(EMAIL_ADDRESS.toString())
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
            .withPostalCode(null)
            .build();

    parkingStickerAssembler.assemble(parkingStickerDto);
  }

  @Test(expected = MissingEmailException.class)
  public void givenEmailReceptionMethodAndNoEmail_whenAssembling_thenThrowMissingEmailException() {
    parkingStickerDto =
        aParkingStickerDto()
            .withReceptionMethod(ReceptionMethods.EMAIL.toString())
            .withEmail(null)
            .build();

    parkingStickerAssembler.assemble(parkingStickerDto);
  }

  @Test
  public void givenEmailReceptionMethod_whenAssembling_thenReturnParkingStickerWithEmailAddress() {
    parkingStickerDto =
        aParkingStickerDto()
            .withReceptionMethod(ReceptionMethods.EMAIL.toString())
            .withEmail(EMAIL_ADDRESS.toString())
            .build();

    ParkingSticker parkingSticker = parkingStickerAssembler.assemble(parkingStickerDto);

    Truth.assertThat(parkingSticker.getEmailAddress()).isEqualTo(EMAIL_ADDRESS);
  }

  @Test
  public void whenAssembling_thenReturnParkingStickerWithReceptionMethod() {
    ParkingSticker parkingSticker = parkingStickerAssembler.assemble(parkingStickerDto);

    Truth.assertThat(parkingSticker.getReceptionMethod()).isEqualTo(RECEPTION_METHOD);
  }

  @Test
  public void givenPostalReceptionMethod_whenAssembling_thenReturnParkingStickerWithPostalCode() {
    parkingStickerDto =
        aParkingStickerDto()
            .withReceptionMethod(ReceptionMethods.POSTAL.toString())
            .withPostalCode(POSTAL_CODE.toString())
            .build();

    ParkingSticker parkingSticker = parkingStickerAssembler.assemble(parkingStickerDto);

    Truth.assertThat(parkingSticker.getPostalCode()).isEqualTo(POSTAL_CODE);
  }

  @Test
  public void givenEmailReceptionMethod_whenAssembling_thenReturnParkingStickerWithEmail() {
    parkingStickerDto =
        aParkingStickerDto()
            .withReceptionMethod(ReceptionMethods.EMAIL.toString())
            .withEmail(EMAIL_ADDRESS.toString())
            .build();

    ParkingSticker parkingSticker = parkingStickerAssembler.assemble(parkingStickerDto);

    Truth.assertThat(parkingSticker.getEmailAddress()).isEqualTo(EMAIL_ADDRESS);
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
