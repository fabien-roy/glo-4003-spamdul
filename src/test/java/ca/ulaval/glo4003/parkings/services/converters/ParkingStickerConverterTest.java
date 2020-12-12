package ca.ulaval.glo4003.parkings.services.converters;

import static ca.ulaval.glo4003.accounts.helpers.AccountMother.createAccountId;
import static ca.ulaval.glo4003.communications.helpers.EmailMother.createEmailAddress;
import static ca.ulaval.glo4003.locations.helpers.PostalCodeMother.createPostalCode;
import static ca.ulaval.glo4003.parkings.helpers.ParkingAreaMother.createParkingAreaCode;
import static ca.ulaval.glo4003.parkings.helpers.ParkingStickerDtoBuilder.aParkingStickerDto;
import static ca.ulaval.glo4003.parkings.helpers.ParkingStickerMother.createReceptionMethod;
import static com.google.common.truth.Truth.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import ca.ulaval.glo4003.accounts.domain.AccountId;
import ca.ulaval.glo4003.accounts.services.converters.AccountIdConverter;
import ca.ulaval.glo4003.communications.domain.EmailAddress;
import ca.ulaval.glo4003.communications.services.converters.EmailAddressConverter;
import ca.ulaval.glo4003.locations.domain.PostalCode;
import ca.ulaval.glo4003.locations.services.converters.PostalCodeConverter;
import ca.ulaval.glo4003.parkings.domain.ParkingAreaCode;
import ca.ulaval.glo4003.parkings.domain.ParkingSticker;
import ca.ulaval.glo4003.parkings.domain.ReceptionMethod;
import ca.ulaval.glo4003.parkings.domain.exceptions.InvalidReceptionMethodException;
import ca.ulaval.glo4003.parkings.domain.exceptions.MissingEmailException;
import ca.ulaval.glo4003.parkings.domain.exceptions.MissingPostalCodeException;
import ca.ulaval.glo4003.parkings.services.assemblers.ParkingAreaCodeAssembler;
import ca.ulaval.glo4003.parkings.services.assemblers.ParkingPeriodAssembler;
import ca.ulaval.glo4003.parkings.services.dto.ParkingStickerDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ParkingStickerConverterTest {
  private static final AccountId ACCOUNT_ID = createAccountId();
  private static final ParkingAreaCode PARKING_AREA = createParkingAreaCode();
  private static final ReceptionMethod RECEPTION_METHOD = createReceptionMethod();
  private static final PostalCode POSTAL_CODE = createPostalCode();
  private static final EmailAddress EMAIL_ADDRESS = createEmailAddress();

  @Mock private AccountIdConverter accountIdConverter;
  @Mock private ParkingAreaCodeAssembler parkingAreaCodeAssembler;
  @Mock private PostalCodeConverter postalCodeConverter;
  @Mock private EmailAddressConverter emailAddressConverter;
  @Mock private ParkingPeriodAssembler parkingPeriodAssembler;

  private ParkingStickerDto parkingStickerDto;

  private ParkingStickerConverter parkingStickerConverter;

  @Before
  public void setUp() {
    parkingStickerConverter =
        new ParkingStickerConverter(
            parkingAreaCodeAssembler,
            accountIdConverter,
            postalCodeConverter,
            emailAddressConverter,
            parkingPeriodAssembler);

    when(accountIdConverter.convert(ACCOUNT_ID.toString())).thenReturn(ACCOUNT_ID);
    when(parkingAreaCodeAssembler.assemble(PARKING_AREA.toString())).thenReturn(PARKING_AREA);
    when(postalCodeConverter.convert(POSTAL_CODE.toString())).thenReturn(POSTAL_CODE);
    when(emailAddressConverter.convert(EMAIL_ADDRESS.toString())).thenReturn(EMAIL_ADDRESS);

    parkingStickerDto =
        aParkingStickerDto()
            .withParkingArea(PARKING_AREA.toString())
            .withReceptionMethod(RECEPTION_METHOD.toString())
            .withPostalCode(POSTAL_CODE.toString())
            .withEmail(EMAIL_ADDRESS.toString())
            .build();
  }

  @Test
  public void whenConverting_thenReturnParkingStickerWithAccountId() {
    ParkingSticker parkingSticker =
        parkingStickerConverter.convert(parkingStickerDto, ACCOUNT_ID.toString());

    assertThat(parkingSticker.getAccountId()).isSameInstanceAs(ACCOUNT_ID);
  }

  @Test
  public void whenConverting_thenReturnParkingStickerWithParkingArea() {
    ParkingSticker parkingSticker =
        parkingStickerConverter.convert(parkingStickerDto, ACCOUNT_ID.toString());

    assertThat(parkingSticker.getParkingAreaCode()).isEqualTo(PARKING_AREA);
  }

  @Test(expected = InvalidReceptionMethodException.class)
  public void
      givenInvalidReceptionMethod_whenConverting_thenThrowInvalidReceptionMethodException() {
    parkingStickerDto = aParkingStickerDto().withReceptionMethod("invalidReceptionMethod").build();

    parkingStickerConverter.convert(parkingStickerDto, ACCOUNT_ID.toString());
  }

  @Test(expected = MissingPostalCodeException.class)
  public void
      givenPostalReceptionMethodAndNoPostalCode_whenConverting_thenThrowMissingPostalCodeException() {
    parkingStickerDto =
        aParkingStickerDto()
            .withReceptionMethod(ReceptionMethod.POSTAL.toString())
            .withPostalCode(null)
            .build();

    parkingStickerConverter.convert(parkingStickerDto, ACCOUNT_ID.toString());
  }

  @Test(expected = MissingEmailException.class)
  public void givenEmailReceptionMethodAndNoEmail_whenConverting_thenThrowMissingEmailException() {
    parkingStickerDto =
        aParkingStickerDto()
            .withReceptionMethod(ReceptionMethod.EMAIL.toString())
            .withEmail(null)
            .build();

    parkingStickerConverter.convert(parkingStickerDto, ACCOUNT_ID.toString());
  }

  @Test
  public void givenEmailReceptionMethod_whenConverting_thenReturnParkingStickerWithEmailAddress() {
    parkingStickerDto =
        aParkingStickerDto()
            .withReceptionMethod(ReceptionMethod.EMAIL.toString())
            .withEmail(EMAIL_ADDRESS.toString())
            .build();

    ParkingSticker parkingSticker =
        parkingStickerConverter.convert(parkingStickerDto, ACCOUNT_ID.toString());

    assertThat(parkingSticker.getEmailAddress()).isEqualTo(EMAIL_ADDRESS);
  }

  @Test
  public void whenConverting_thenReturnParkingStickerWithReceptionMethod() {
    ParkingSticker parkingSticker =
        parkingStickerConverter.convert(parkingStickerDto, ACCOUNT_ID.toString());

    assertThat(parkingSticker.getReceptionMethod()).isEqualTo(RECEPTION_METHOD);
  }

  @Test
  public void
      givenUpperCaseReceptionMethod_whenConverting_thenReturnParkingStickerWithReceptionMethod() {
    parkingStickerDto =
        aParkingStickerDto().withReceptionMethod(RECEPTION_METHOD.toString().toUpperCase()).build();

    ParkingSticker parkingSticker =
        parkingStickerConverter.convert(parkingStickerDto, ACCOUNT_ID.toString());

    assertThat(parkingSticker.getReceptionMethod()).isEqualTo(RECEPTION_METHOD);
  }

  @Test
  public void givenPostalReceptionMethod_whenConverting_thenReturnParkingStickerWithPostalCode() {
    parkingStickerDto =
        aParkingStickerDto()
            .withReceptionMethod(ReceptionMethod.POSTAL.toString())
            .withPostalCode(POSTAL_CODE.toString())
            .build();

    ParkingSticker parkingSticker =
        parkingStickerConverter.convert(parkingStickerDto, ACCOUNT_ID.toString());

    assertThat(parkingSticker.getPostalCode()).isEqualTo(POSTAL_CODE);
  }

  @Test
  public void givenEmailReceptionMethod_whenConverting_thenReturnParkingStickerWithEmail() {
    parkingStickerDto =
        aParkingStickerDto()
            .withReceptionMethod(ReceptionMethod.EMAIL.toString())
            .withEmail(EMAIL_ADDRESS.toString())
            .build();

    ParkingSticker parkingSticker =
        parkingStickerConverter.convert(parkingStickerDto, ACCOUNT_ID.toString());

    assertThat(parkingSticker.getEmailAddress()).isEqualTo(EMAIL_ADDRESS);
  }

  @Test
  public void givenSSPReceptionMethod_whenConverting_thenReturnParkingStickerWithoutEmailAddress() {
    parkingStickerDto =
        aParkingStickerDto().withReceptionMethod(ReceptionMethod.SSP.toString()).build();

    ParkingSticker parkingSticker =
        parkingStickerConverter.convert(parkingStickerDto, ACCOUNT_ID.toString());

    assertThat(parkingSticker.getEmailAddress()).isNotEqualTo(EMAIL_ADDRESS);
  }

  @Test
  public void whenConverting_thenAssembleParkingPeriod() {
    parkingStickerConverter.convert(parkingStickerDto, ACCOUNT_ID.toString());

    verify(parkingPeriodAssembler).assemble(parkingStickerDto.parkingPeriod);
  }
}
