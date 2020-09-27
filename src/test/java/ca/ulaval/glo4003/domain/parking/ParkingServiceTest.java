package ca.ulaval.glo4003.domain.parking;

import static ca.ulaval.glo4003.domain.account.helpers.AccountBuilder.anAccount;
import static ca.ulaval.glo4003.domain.parking.helpers.ParkingStickerBuilder.aParkingSticker;
import static org.mockito.Matchers.eq;

import ca.ulaval.glo4003.api.parking.dto.ParkingStickerCodeDto;
import ca.ulaval.glo4003.api.parking.dto.ParkingStickerDto;
import ca.ulaval.glo4003.domain.account.Account;
import ca.ulaval.glo4003.domain.account.AccountRepository;
import com.google.common.truth.Truth;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ParkingServiceTest {
  @Mock private ParkingStickerDto parkingStickerDto;
  @Mock private ParkingStickerCodeDto parkingStickerCodeDto;
  @Mock private ParkingStickerAssembler parkingStickerAssembler;
  @Mock private ParkingStickerCodeAssembler parkingStickerCodeAssembler;
  @Mock private ParkingStickerFactory parkingStickerFactory;
  @Mock private AccountRepository accountRepository;
  @Mock private ParkingAreaRepository parkingAreaRepository;
  @Mock private ParkingStickerRepository parkingStickerRepository;

  private ParkingSticker parkingSticker;
  private Account account;

  private ParkingService parkingService;

  @Before
  public void setUp() {
    parkingService =
        new ParkingService(
            parkingStickerAssembler,
            parkingStickerCodeAssembler,
            parkingStickerFactory,
            accountRepository,
            parkingAreaRepository,
            parkingStickerRepository);
    parkingSticker = aParkingSticker().build();
    account = anAccount().build();

    BDDMockito.given(parkingStickerAssembler.assemble(parkingStickerDto))
        .willReturn(parkingSticker);
    BDDMockito.given(parkingStickerCodeAssembler.assemble(parkingSticker.getCode()))
        .willReturn(parkingStickerCodeDto);
    BDDMockito.given(accountRepository.findById(parkingSticker.getAccountId())).willReturn(account);
    BDDMockito.given(parkingStickerFactory.create(parkingSticker)).willReturn(parkingSticker);
  }

  @Test
  public void whenAddParkingSticker_thenGetAccount() {
    parkingService.addParkingSticker(parkingStickerDto);

    Mockito.verify(accountRepository).findById(eq(parkingSticker.getAccountId()));
  }

  @Test
  public void whenAddParkingSticker_thenGetParkingArea() {
    parkingService.addParkingSticker(parkingStickerDto);

    Mockito.verify(parkingAreaRepository).findByCode(eq(parkingSticker.getParkingAreaCode()));
  }

  @Test
  public void whenAddParkingSticker_thenCreateParkingSticker() {
    parkingService.addParkingSticker(parkingStickerDto);

    Mockito.verify(parkingStickerFactory).create(eq(parkingSticker));
  }

  @Test
  public void whenAddParkingSticker_thenAddParkingStickerCodeToAccount() {
    parkingService.addParkingSticker(parkingStickerDto);

    Truth.assertThat(account.getParkingStickerCodes()).contains(parkingSticker.getCode());
  }

  @Test
  public void whenAddParkingSticker_thenAddZonePriceBillToAccount() {
    parkingService.addParkingSticker(parkingStickerDto);

    Truth.assertThat(account.getBill().getMoneyToPay()).isNotEqualTo(0f);
  }

  @Test
  public void whenAddParkingSticker_thenUpdateAccount() {
    parkingService.addParkingSticker(parkingStickerDto);

    Mockito.verify(accountRepository).update(eq(account));
  }

  @Test
  public void whenAddParkingSticker_thenAddParkingSticker() {
    parkingService.addParkingSticker(parkingStickerDto);

    Mockito.verify(parkingStickerRepository).save(eq(parkingSticker));
  }

  @Test
  public void whenAddParkingSticker_thenReturnParkingStickerCode() {
    ParkingStickerCodeDto parkingStickerCodeDto =
        parkingService.addParkingSticker(parkingStickerDto);

    Truth.assertThat(parkingStickerCodeDto).isSameInstanceAs(this.parkingStickerCodeDto);
  }
}
