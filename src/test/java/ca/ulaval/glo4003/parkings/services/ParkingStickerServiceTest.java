package ca.ulaval.glo4003.parkings.services;

import static ca.ulaval.glo4003.funds.helpers.BillBuilder.aBill;
import static ca.ulaval.glo4003.parkings.helpers.ParkingAreaBuilder.aParkingArea;
import static ca.ulaval.glo4003.parkings.helpers.ParkingStickerBuilder.aParkingSticker;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;

import ca.ulaval.glo4003.accounts.services.AccountService;
import ca.ulaval.glo4003.funds.domain.Bill;
import ca.ulaval.glo4003.funds.services.BillService;
import ca.ulaval.glo4003.parkings.api.dto.ParkingStickerCodeDto;
import ca.ulaval.glo4003.parkings.api.dto.ParkingStickerDto;
import ca.ulaval.glo4003.parkings.assemblers.ParkingStickerAssembler;
import ca.ulaval.glo4003.parkings.assemblers.ParkingStickerCodeAssembler;
import ca.ulaval.glo4003.parkings.domain.*;
import com.google.common.truth.Truth;
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
  @Mock private ParkingStickerCodeAssembler parkingStickerCodeAssembler;
  @Mock private ParkingStickerFactory parkingStickerFactory;
  @Mock private AccountService accountService;
  @Mock private ParkingAreaRepository parkingAreaRepository;
  @Mock private ParkingStickerRepository parkingStickerRepository;
  @Mock private ParkingStickerCreationObserver parkingStickerCreationObserver;
  @Mock private BillService billService;

  private ParkingStickerService parkingStickerService;

  private final ParkingSticker parkingSticker = aParkingSticker().build();
  private final ParkingArea parkingArea = aParkingArea().build();
  private final Bill bill = aBill().build();

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
    when(parkingStickerRepository.get(parkingSticker.getCode())).thenReturn(parkingSticker);
  }

  @Test
  public void whenAddingParkingSticker_thenVerifyAccountExists() {
    parkingStickerService.addParkingSticker(parkingStickerDto);

    Mockito.verify(accountService).getAccount(parkingSticker.getAccountId());
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
  public void whenAddingParkingSticker_thenParkingStickerCreationObserversAreNotified() {
    parkingStickerService.register(parkingStickerCreationObserver);

    parkingStickerService.addParkingSticker(parkingStickerDto);

    Mockito.verify(parkingStickerCreationObserver).listenParkingStickerCreated(parkingSticker);
  }
}
