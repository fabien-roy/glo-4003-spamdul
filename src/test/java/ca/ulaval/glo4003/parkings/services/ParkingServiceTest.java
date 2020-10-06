package ca.ulaval.glo4003.parkings.services;

import static ca.ulaval.glo4003.accounts.helpers.AccountBuilder.anAccount;
import static ca.ulaval.glo4003.funds.helpers.BillBuilder.aBill;
import static ca.ulaval.glo4003.parkings.helpers.AccessStatusDtoBuilder.anAccessStatusDto;
import static ca.ulaval.glo4003.parkings.helpers.ParkingAreaBuilder.aParkingArea;
import static ca.ulaval.glo4003.parkings.helpers.ParkingStickerBuilder.aParkingSticker;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;

import ca.ulaval.glo4003.accounts.domain.Account;
import ca.ulaval.glo4003.accounts.domain.AccountRepository;
import ca.ulaval.glo4003.communications.domain.EmailSender;
import ca.ulaval.glo4003.funds.domain.Bill;
import ca.ulaval.glo4003.funds.services.BillService;
import ca.ulaval.glo4003.locations.domain.PostalSender;
import ca.ulaval.glo4003.parkings.api.dto.AccessStatusDto;
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
  @Mock private BillService billService;

  private Account account = anAccount().build();
  private LocalDate date = LocalDate.now();
  private String dayOfWeek = date.getDayOfWeek().toString().toLowerCase();
  String notDayOfWeek = date.getDayOfWeek().plus(1).toString().toLowerCase();
  private ParkingSticker parkingSticker = aParkingSticker().withValidDay(dayOfWeek).build();
  private ParkingSticker parkingStickerNotValidToday =
      aParkingSticker().withValidDay(notDayOfWeek).build();
  private ParkingStickerCode parkingStickerCode = parkingSticker.getCode();
  private ParkingArea parkingArea = aParkingArea().build();
  private Bill bill = aBill().build();
  private AccessStatusDto accessStatusGrantedDto = anAccessStatusDto().build();
  private AccessStatusDto accessStatusRefusedDto = anAccessStatusDto().build();
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
            postalSender,
            billService);

    when(parkingStickerAssembler.assemble(parkingStickerDto)).thenReturn(parkingSticker);
    when(parkingStickerCodeAssembler.assemble(parkingSticker.getCode()))
        .thenReturn(parkingStickerCodeDto);
    when(accountRepository.findById(parkingSticker.getAccountId())).thenReturn(account);
    when(parkingStickerFactory.create(parkingSticker)).thenReturn(parkingSticker);
    when(parkingAreaRepository.findByCode(parkingSticker.getParkingAreaCode()))
        .thenReturn(parkingArea);
    when(billService.createBillForParkingSticker(parkingSticker, parkingArea)).thenReturn(bill);
    when(parkingStickerCodeAssembler.assemble(parkingStickerCode.toString()))
        .thenReturn(parkingStickerCode);
    when(parkingStickerRepository.findByCode(parkingStickerCode)).thenReturn(parkingSticker);
    when(accessStatusAssembler.assemble(AccessStatus.ACCESS_GRANTED))
        .thenReturn(accessStatusGrantedDto);
    when(accessStatusAssembler.assemble(AccessStatus.ACCESS_REFUSED))
        .thenReturn(accessStatusRefusedDto);
  }

  @Test
  public void whenAddingParkingSticker_thenAddParkingStickerCodeToAccount() {
    parkingService.addParkingSticker(parkingStickerDto);

    Truth.assertThat(account.getParkingStickerCodes()).contains(parkingSticker.getCode());
  }

  @Test
  public void whenAddingParkingSticker_thenAddBillToAccount() {
    parkingService.addParkingSticker(parkingStickerDto);

    Truth.assertThat(account.getBills()).contains(bill);
  }

  @Test
  public void whenAddingParkingSticker_thenUpdateAccount() {
    parkingService.addParkingSticker(parkingStickerDto);

    Mockito.verify(accountRepository).update(account);
  }

  @Test
  public void whenAddingParkingSticker_thenAddParkingStickerToRepository() {
    parkingService.addParkingSticker(parkingStickerDto);

    Mockito.verify(parkingStickerRepository).save(eq(parkingSticker));
  }

  @Test
  public void whenAddingParkingSticker_thenReturnParkingStickerCode() {
    ParkingStickerCodeDto receivedParkingStickerCodeDto =
        parkingService.addParkingSticker(parkingStickerDto);

    Truth.assertThat(receivedParkingStickerCodeDto).isSameInstanceAs(parkingStickerCodeDto);
  }

  @Test
  public void givenReceptionMethodIsEmail_whenAddParkingSticker_thenMailSenderIsCalled() {
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
      givenValidParkingStickerCode_whenValidateParkingStickerCode_thenAccessGrantedResponseIsReturned() {
    AccessStatusDto accessStatusDto =
        parkingService.validateParkingStickerCode(parkingStickerCode.toString());

    Truth.assertThat(accessStatusDto).isSameInstanceAs(accessStatusGrantedDto);
  }

  @Test
  public void
      givenInvalidParkingStickerCode_whenValidateParkingStickerCode_thenAccessRefusedResponseIsReturned() {
    when(parkingStickerRepository.findByCode(parkingStickerCode))
        .thenReturn(parkingStickerNotValidToday);

    AccessStatusDto accessStatusDto =
        parkingService.validateParkingStickerCode(parkingStickerCode.toString());

    Truth.assertThat(accessStatusDto).isSameInstanceAs(accessStatusRefusedDto);
  }
}
