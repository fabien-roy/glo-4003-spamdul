package ca.ulaval.glo4003.domain.parking;

import static ca.ulaval.glo4003.api.parking.helpers.ParkingStickerDtoBuilder.aParkingStickerDto;
import static ca.ulaval.glo4003.domain.account.helpers.AccountObjectMother.createAccountId;
import static ca.ulaval.glo4003.domain.parking.helpers.ParkingAreaObjectMother.createParkingAreaCode;
import static ca.ulaval.glo4003.domain.parking.helpers.ParkingStickerObjectMother.createReceptionMethod;

import ca.ulaval.glo4003.api.parking.dto.ParkingStickerDto;
import ca.ulaval.glo4003.domain.account.AccountId;
import ca.ulaval.glo4003.domain.account.AccountIdAssembler;
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
  private static final String PARKING_AREA = createParkingAreaCode().toString();
  private static final String RECEPTION_METHOD = createReceptionMethod().toString();

  @Mock private AccountIdAssembler accountIdAssembler;

  private ParkingStickerDto parkingStickerDto;

  private ParkingStickerAssembler parkingStickerAssembler;

  @Before
  public void setUp() {
    parkingStickerAssembler = new ParkingStickerAssembler(accountIdAssembler);

    BDDMockito.given(accountIdAssembler.assemble(ACCOUNT_ID.toString())).willReturn(ACCOUNT_ID);
  }

  @Test
  public void givenAccountId_whenAssembling_thenReturnParkingStickerWithAccountId() {
    parkingStickerDto = aParkingStickerDto().withAccountId(ACCOUNT_ID.toString()).build();

    ParkingSticker parkingSticker = parkingStickerAssembler.assemble(parkingStickerDto);

    Truth.assertThat(parkingSticker.getAccountId()).isSameInstanceAs(ACCOUNT_ID);
  }

  @Test
  public void whenAssembling_thenReturnParkingStickerWithAccountId() {
    parkingStickerDto = aParkingStickerDto().withParkingArea(PARKING_AREA).build();

    ParkingSticker parkingSticker = parkingStickerAssembler.assemble(parkingStickerDto);

    Truth.assertThat(parkingSticker.getParkingAreaCode().toString()).isEqualTo(PARKING_AREA);
  }

  @Test(expected = InvalidReceptionMethodException.class)
  public void
      givenInvalidReceptionMethod_whenAssembling_thenThrowInvalidReceptionMethodException() {
    parkingStickerDto = aParkingStickerDto().withReceptionMethod("InvalidReceptionMethod").build();

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
    parkingStickerDto = aParkingStickerDto().withReceptionMethod(RECEPTION_METHOD).build();

    ParkingSticker parkingSticker = parkingStickerAssembler.assemble(parkingStickerDto);

    Truth.assertThat(parkingSticker.getReceptionMethod().toString()).isEqualTo(RECEPTION_METHOD);
  }

  // TODO : Should we do something with the address?
}
