package ca.ulaval.glo4003.parkings.services;

import static ca.ulaval.glo4003.funds.helpers.BillBuilder.aBill;
import static ca.ulaval.glo4003.gateentries.api.helpers.AccessStatusDtoBuilder.anAccessStatusDto;
import static ca.ulaval.glo4003.parkings.helpers.ParkingAreaBuilder.aParkingArea;
import static ca.ulaval.glo4003.parkings.helpers.ParkingStickerBuilder.aParkingSticker;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;

import ca.ulaval.glo4003.accounts.services.AccountService;
import ca.ulaval.glo4003.funds.domain.Bill;
import ca.ulaval.glo4003.funds.services.BillService;
import ca.ulaval.glo4003.gateentries.api.dto.AccessStatusDto;
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
public class ParkingStickerServiceTest {
  @Mock private ParkingStickerDto parkingStickerDto;
  @Mock private ParkingStickerCodeDto parkingStickerCodeDto;
  @Mock private ParkingStickerAssembler parkingStickerAssembler;
  @Mock private AccessStatusAssembler accessStatusAssembler;
  @Mock private ParkingStickerCodeAssembler parkingStickerCodeAssembler;
  @Mock private ParkingStickerFactory parkingStickerFactory;
  @Mock private AccountService accountService;
  @Mock private ParkingAreaRepository parkingAreaRepository;
  @Mock private ParkingStickerRepository parkingStickerRepository;
  @Mock private ParkingStickerCreationObserver parkingStickerCreationObserver;
  @Mock private BillService billService;

  private ParkingStickerService parkingStickerService;

  private final LocalDate date = LocalDate.now();
  private final String dayOfWeek = date.getDayOfWeek().toString().toLowerCase();
  private final String notDayOfWeek = date.getDayOfWeek().plus(1).toString().toLowerCase();
  private ParkingSticker parkingSticker = aParkingSticker().withValidDay(dayOfWeek).build();
  private final ParkingSticker parkingStickerNotValidToday =
      aParkingSticker().withValidDay(notDayOfWeek).build();
  private final ParkingArea parkingArea = aParkingArea().build();
  private final Bill bill = aBill().build();
  private final AccessStatusDto accessStatusGrantedDto = anAccessStatusDto().build();
  private final AccessStatusDto accessStatusRefusedDto = anAccessStatusDto().build();

  @Before
  public void setUp() {
    parkingStickerService =
        new ParkingStickerService(
            parkingStickerAssembler,
            parkingStickerCodeAssembler,
            parkingStickerFactory,
            accountService,
            parkingAreaRepository,
            parkingStickerRepository,
            accessStatusAssembler,
            billService);

    when(parkingStickerAssembler.assemble(parkingStickerDto)).thenReturn(parkingSticker);
    when(parkingStickerCodeAssembler.assemble(parkingSticker.getCode()))
        .thenReturn(parkingStickerCodeDto);
    when(parkingStickerFactory.create(parkingSticker)).thenReturn(parkingSticker);
    when(parkingAreaRepository.findByCode(parkingSticker.getParkingAreaCode()))
        .thenReturn(parkingArea);
    when(billService.addBillForParkingSticker(parkingSticker, parkingArea))
        .thenReturn(bill.getId());
    when(parkingStickerCodeAssembler.assemble(parkingSticker.getCode().toString()))
        .thenReturn(parkingSticker.getCode());
    when(parkingStickerRepository.findByCode(parkingSticker.getCode())).thenReturn(parkingSticker);
    when(accessStatusAssembler.assemble(AccessStatus.ACCESS_GRANTED))
        .thenReturn(accessStatusGrantedDto);
    when(accessStatusAssembler.assemble(AccessStatus.ACCESS_REFUSED))
        .thenReturn(accessStatusRefusedDto);
  }

  @Test
  public void whenAddingParkingSticker_thenAddParkingStickerToAccount() {
    parkingStickerService.addParkingSticker(parkingStickerDto);

    Mockito.verify(accountService)
        .addParkingStickerToAccount(
            parkingSticker.getAccountId(), parkingSticker.getCode(), bill.getId());
  }

  @Test
  public void whenAddingParkingSticker_thenAddParkingStickerToRepository() {
    parkingStickerService.addParkingSticker(parkingStickerDto);

    Mockito.verify(parkingStickerRepository).save(eq(parkingSticker));
  }

  @Test
  public void whenAddingParkingSticker_thenReturnParkingStickerCode() {
    ParkingStickerCodeDto receivedParkingStickerCodeDto =
        parkingStickerService.addParkingSticker(parkingStickerDto);

    Truth.assertThat(receivedParkingStickerCodeDto).isSameInstanceAs(parkingStickerCodeDto);
  }

  @Test
  public void whenAddParkingSticker_thenParkingStickerCreationObserversAreNotified() {
    parkingStickerService.register(parkingStickerCreationObserver);

    parkingStickerService.addParkingSticker(parkingStickerDto);

    Mockito.verify(parkingStickerCreationObserver).listenParkingStickerCreated(parkingSticker);
  }

  @Test
  public void
      givenValidParkingStickerCode_whenValidateParkingStickerCode_thenAccessGrantedResponseIsReturned() {
    AccessStatusDto accessStatusDto =
        parkingStickerService.validateParkingStickerCode(parkingSticker.getCode().toString());

    Truth.assertThat(accessStatusDto).isSameInstanceAs(accessStatusGrantedDto);
  }

  @Test
  public void
      givenInvalidParkingStickerCode_whenValidateParkingStickerCode_thenAccessRefusedResponseIsReturned() {
    when(parkingStickerRepository.findByCode(parkingSticker.getCode()))
        .thenReturn(parkingStickerNotValidToday);

    AccessStatusDto accessStatusDto =
        parkingStickerService.validateParkingStickerCode(parkingSticker.getCode().toString());

    Truth.assertThat(accessStatusDto).isSameInstanceAs(accessStatusRefusedDto);
  }
}
