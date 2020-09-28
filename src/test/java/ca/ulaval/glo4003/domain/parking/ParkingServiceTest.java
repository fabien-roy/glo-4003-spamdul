package ca.ulaval.glo4003.domain.parking;

import static ca.ulaval.glo4003.domain.account.helpers.AccountBuilder.anAccount;
import static ca.ulaval.glo4003.domain.parking.helpers.ParkingStickerBuilder.aParkingSticker;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;

import ca.ulaval.glo4003.api.parking.dto.ParkingStickerCodeDto;
import ca.ulaval.glo4003.api.parking.dto.ParkingStickerDto;
import ca.ulaval.glo4003.domain.account.Account;
import ca.ulaval.glo4003.domain.account.AccountRepository;
import com.google.common.truth.Truth;
import java.time.LocalDate;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ParkingServiceTest {
  @Mock private ParkingStickerDto parkingStickerDto;
  @Mock private ParkingStickerCodeDto parkingStickerCodeDto;
  @Mock private ParkingStickerAssembler parkingStickerAssembler;
  @Mock private AccessStatusAssembler accessStatusAssembler;
  @Mock private ParkingStickerCodeAssembler parkingStickerCodeAssembler;
  @Mock private ParkingStickerFactory parkingStickerFactory;
  @Mock private AccountRepository accountRepository;
  @Mock private ParkingAreaRepository parkingAreaRepository;
  @Mock private ParkingStickerRepository parkingStickerRepository;
  @Mock private Account mockedAccount;

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
            accessStatusAssembler);
    LocalDate date = LocalDate.now();
    String dayOfWeek = date.getDayOfWeek().toString().toLowerCase();
    parkingSticker = aParkingSticker().withValidDay(dayOfWeek).build();
    parkingStickerCode = parkingSticker.getCode();
    account = anAccount().build();

    when(parkingStickerAssembler.assemble(parkingStickerDto)).thenReturn(parkingSticker);
    when(parkingStickerCodeAssembler.assemble(parkingSticker.getCode()))
        .thenReturn(parkingStickerCodeDto);
    when(accountRepository.findById(parkingSticker.getAccountId())).thenReturn(account);
    when(parkingStickerFactory.create(parkingSticker)).thenReturn(parkingSticker);
    when(parkingStickerCodeAssembler.assemble(parkingStickerCode.toString()))
        .thenReturn(parkingStickerCode);
    when(parkingStickerRepository.findByCode(parkingStickerCode)).thenReturn(parkingSticker);
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
  public void whenAddParkingSticker_thenAddParkingStickerToAccount() {
    when(accountRepository.findById(parkingSticker.getAccountId())).thenReturn(mockedAccount);

    parkingService.addParkingSticker(parkingStickerDto);

    Mockito.verify(mockedAccount).addParkingSticker(parkingSticker);
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
    parkingService.validateParkingStickerCode(parkingStickerCode.toString());

    Mockito.verify(parkingStickerCodeAssembler).assemble(eq(parkingStickerCode.toString()));
  }

  @Test
  public void
      givenParkingStickerCode_whenValidateParkingStickerCode_thenParkingStickerRepositoryIsCalled() {
    parkingService.validateParkingStickerCode(parkingStickerCode.toString());

    Mockito.verify(parkingStickerRepository).findByCode(eq(parkingStickerCode));
  }

  @Test
  public void
      givenValidParkingStickerCode_whenValidateParkingStickerCode_thenAccessGrantedResponseIsReturned() {
    parkingService.validateParkingStickerCode(parkingStickerCode.toString());

    Mockito.verify(accessStatusAssembler).assemble(eq(AccessStatus.ACCESS_GRANTED.toString()));
  }

  @Test
  public void
      givenInvalidParkingStickerCode_whenValidateParkingStickerCode_thenAccessRefusedResponseIsReturned() {
    ParkingSticker invalidParkingStickerDay =
        aParkingSticker().withValidDay("friday").build(); // TODO : Do not hardcore "friday"
    when(parkingStickerRepository.findByCode(parkingStickerCode))
        .thenReturn(invalidParkingStickerDay);

    parkingService.validateParkingStickerCode(parkingStickerCode.toString());

    Mockito.verify(accessStatusAssembler).assemble(eq(AccessStatus.ACCESS_REFUSED.toString()));
  }
}
