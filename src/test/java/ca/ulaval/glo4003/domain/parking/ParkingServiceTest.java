package ca.ulaval.glo4003.domain.parking;

import static ca.ulaval.glo4003.domain.account.helpers.AccountBuilder.anAccount;
import static ca.ulaval.glo4003.domain.parking.helpers.ParkingStickerBuilder.aParkingSticker;
import static org.mockito.Matchers.eq;

import ca.ulaval.glo4003.api.parking.dto.ParkingStickerCodeDto;
import ca.ulaval.glo4003.api.parking.dto.ParkingStickerDto;
import ca.ulaval.glo4003.domain.account.Account;
import ca.ulaval.glo4003.domain.account.AccountRepository;
import com.google.common.truth.Truth;
import java.time.LocalDate;
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
  @Mock private ParkingAccessDayAssembler parkingAccessDayAssembler;
  @Mock private ParkingStickerCodeAssembler parkingStickerCodeAssembler;
  @Mock private ParkingStickerFactory parkingStickerFactory;
  @Mock private AccountRepository accountRepository;
  @Mock private ParkingAreaRepository parkingAreaRepository;
  @Mock private ParkingStickerRepository parkingStickerRepository;

  private ParkingSticker parkingSticker;
  private ParkingStickerCode parkingStickerCode;
  private ParkingService parkingService;
  private Account account;

  @Before
  public void setUp() {
    parkingService =
        new ParkingService(
            parkingStickerAssembler,
            parkingStickerCodeAssembler,
            parkingStickerFactory,
            accountRepository,
            parkingAreaRepository,
            parkingStickerRepository,
            parkingAccessDayAssembler);
    LocalDate date = LocalDate.now();
    String dayOfWeek = date.getDayOfWeek().toString();
    parkingSticker = aParkingSticker().withValidDay(dayOfWeek).build();
    parkingStickerCode = parkingSticker.getCode();
    account = anAccount().build();

    BDDMockito.given(parkingStickerAssembler.assemble(parkingStickerDto))
        .willReturn(parkingSticker);
    BDDMockito.given(parkingStickerCodeAssembler.assemble(parkingSticker.getCode()))
        .willReturn(parkingStickerCodeDto);
    BDDMockito.given(accountRepository.findById(parkingSticker.getAccountId())).willReturn(account);
    BDDMockito.given(parkingStickerFactory.create(parkingSticker)).willReturn(parkingSticker);
    BDDMockito.given(parkingStickerCodeAssembler.assemble(parkingStickerCodeDto))
        .willReturn(parkingStickerCode);
    BDDMockito.given(parkingStickerRepository.findByCode(parkingStickerCode))
        .willReturn(parkingSticker);
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

  @Test
  public void
      givenParkingStickerCode_whenValidateParkingStickerCode_thenParkingStickerCodeAssemblerIsCalled() {
    parkingService.validateParkingStickerCode(parkingStickerCodeDto);

    Mockito.verify(parkingStickerCodeAssembler).assemble(eq(parkingStickerCodeDto));
  }

  @Test
  public void
      givenParkingStickerCode_whenValidateParkingStickerCode_thenParkingStickerRepositoryIsCalled() {
    parkingService.validateParkingStickerCode(parkingStickerCodeDto);

    Mockito.verify(parkingStickerRepository).findByCode(eq(parkingStickerCode));
  }

  @Test
  public void
      givenValidParkingStickerCode_whenValidateParkingStickerCode_thenAccessGrantedResponseIsReturned() {
    parkingService.validateParkingStickerCode(parkingStickerCodeDto);

    Mockito.verify(parkingAccessDayAssembler).assemble(eq(AccessStatus.ACCESS_GRANTED.toString()));
  }

  @Test
  public void
      givenInvalidParkingStickerCode_whenValidateParkingStickerCode_thenAccessRefusedResponseIsReturned() {
    ParkingSticker invalidParkingStickerDay = aParkingSticker().withValidDay("friday").build();
    BDDMockito.given(parkingStickerRepository.findByCode(parkingStickerCode))
        .willReturn(invalidParkingStickerDay);

    parkingService.validateParkingStickerCode(parkingStickerCodeDto);

    Mockito.verify(parkingAccessDayAssembler).assemble(eq(AccessStatus.ACCESS_REFUSED.toString()));
  }
}
