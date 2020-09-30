package ca.ulaval.glo4003.parkings.services;

import static ca.ulaval.glo4003.parkings.helpers.ParkingStickerBuilder.aParkingSticker;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;

import ca.ulaval.glo4003.accounts.domain.Account;
import ca.ulaval.glo4003.accounts.domain.AccountRepository;
import ca.ulaval.glo4003.communications.domain.EmailSender;
import ca.ulaval.glo4003.locations.domain.PostalSender;
import ca.ulaval.glo4003.parkings.api.dto.ParkingStickerCodeDto;
import ca.ulaval.glo4003.parkings.api.dto.ParkingStickerDto;
import ca.ulaval.glo4003.parkings.assemblers.AccessStatusAssembler;
import ca.ulaval.glo4003.parkings.assemblers.ParkingStickerAssembler;
import ca.ulaval.glo4003.parkings.assemblers.ParkingStickerCodeAssembler;
import ca.ulaval.glo4003.parkings.domain.*;
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
  @Mock private EmailSender emailSender;
  @Mock private PostalSender postalSender;
  @Mock private Account account;

  private ParkingSticker parkingSticker;
  private ParkingStickerCode parkingStickerCode;
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
            parkingStickerRepository,
            accessStatusAssembler,
            emailSender,
            postalSender);
    LocalDate date = LocalDate.now();
    String dayOfWeek = date.getDayOfWeek().toString().toLowerCase();
    parkingSticker = aParkingSticker().withValidDay(dayOfWeek).build();
    parkingStickerCode = parkingSticker.getCode();

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
    when(accountRepository.findById(parkingSticker.getAccountId())).thenReturn(account);

    parkingService.addParkingSticker(parkingStickerDto);

    Mockito.verify(account).addParkingSticker(parkingSticker);
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
  public void givenReceptionMethodIsMail_whenAddParkingSticker_thenMailSenderIsCalled() {
    parkingSticker = aParkingSticker().withReceptionMethod(ReceptionMethods.EMAIL).build();
    when(parkingStickerAssembler.assemble(parkingStickerDto)).thenReturn(parkingSticker);
    when(parkingStickerFactory.create(parkingSticker)).thenReturn(parkingSticker);
    when(accountRepository.findById(parkingSticker.getAccountId())).thenReturn(account);

    parkingService.addParkingSticker(parkingStickerDto);

    Mockito.verify(emailSender)
        .sendEmail(eq(parkingSticker.getEmailAddress().toString()), anyString(), anyString());
  }

  @Test
  public void givenReceptionMethodPostal_whenAddParkingSticker_thenPostalSenderIsCalled() {
    parkingSticker = aParkingSticker().withReceptionMethod(ReceptionMethods.POSTAL).build();
    when(parkingStickerAssembler.assemble(parkingStickerDto)).thenReturn(parkingSticker);
    when(parkingStickerFactory.create(parkingSticker)).thenReturn(parkingSticker);
    when(accountRepository.findById(parkingSticker.getAccountId())).thenReturn(account);

    parkingService.addParkingSticker(parkingStickerDto);

    Mockito.verify(postalSender).sendPostal(eq(parkingSticker.getPostalCode()), anyString());
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
