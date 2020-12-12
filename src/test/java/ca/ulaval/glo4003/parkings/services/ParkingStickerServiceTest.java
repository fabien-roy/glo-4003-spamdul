package ca.ulaval.glo4003.parkings.services;

import static ca.ulaval.glo4003.accounts.helpers.AccountMother.createAccountId;
import static ca.ulaval.glo4003.funds.helpers.BillBuilder.aBill;
import static ca.ulaval.glo4003.parkings.helpers.ParkingAreaBuilder.aParkingArea;
import static ca.ulaval.glo4003.parkings.helpers.ParkingStickerBuilder.aParkingSticker;
import static com.google.common.truth.Truth.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import ca.ulaval.glo4003.accounts.domain.AccountId;
import ca.ulaval.glo4003.accounts.services.AccountService;
import ca.ulaval.glo4003.funds.domain.Bill;
import ca.ulaval.glo4003.funds.services.BillService;
import ca.ulaval.glo4003.parkings.domain.*;
import ca.ulaval.glo4003.parkings.services.assemblers.ParkingStickerCodeAssembler;
import ca.ulaval.glo4003.parkings.services.converters.ParkingStickerConverter;
import ca.ulaval.glo4003.parkings.services.dto.ParkingStickerCodeDto;
import ca.ulaval.glo4003.parkings.services.dto.ParkingStickerDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ParkingStickerServiceTest {
  @Mock private ParkingStickerDto parkingStickerDto;
  @Mock private ParkingStickerCodeDto parkingStickerCodeDto;
  @Mock private ParkingStickerConverter parkingStickerConverter;
  @Mock private ParkingStickerCodeAssembler parkingStickerCodeAssembler;
  @Mock private ParkingStickerFactory parkingStickerFactory;
  @Mock private AccountService accountService;
  @Mock private ParkingAreaRepository parkingAreaRepository;
  @Mock private ParkingStickerCreationObserver parkingStickerCreationObserver;
  @Mock private BillService billService;

  private ParkingStickerService parkingStickerService;

  private final AccountId accountId = createAccountId();
  private final ParkingSticker parkingSticker = aParkingSticker().build();
  private final ParkingArea parkingArea = aParkingArea().build();
  private final Bill bill = aBill().build();

  @Before
  public void setUp() {
    parkingStickerService =
        new ParkingStickerService(
            parkingStickerConverter,
            parkingStickerCodeAssembler,
            parkingStickerFactory,
            accountService,
            parkingAreaRepository,
            billService);

    when(parkingStickerConverter.convert(parkingStickerDto, accountId.toString()))
        .thenReturn(parkingSticker);
    when(parkingStickerCodeAssembler.assemble(parkingSticker.getCode()))
        .thenReturn(parkingStickerCodeDto);
    when(parkingStickerFactory.create(parkingSticker)).thenReturn(parkingSticker);
    when(parkingAreaRepository.get(parkingSticker.getParkingAreaCode())).thenReturn(parkingArea);
    //    when(billService.addBillForParkingSticker(parkingSticker, parkingArea))
    //        .thenReturn(bill.getId());
    when(parkingStickerCodeAssembler.assemble(parkingSticker.getCode().toString()))
        .thenReturn(parkingSticker.getCode());
  }

  @Test
  public void whenAddingParkingSticker_thenVerifyAccountExists() {
    parkingStickerService.addParkingSticker(parkingStickerDto, accountId.toString());

    verify(accountService).getAccount(accountId.toString());
  }

  @Test
  public void whenAddingParkingSticker_thenAddParkingStickerToAccount() {
    parkingStickerService.addParkingSticker(parkingStickerDto, accountId.toString());

    //    verify(accountService)
    //        .addParkingStickerToAccount(parkingSticker.getAccountId(), parkingSticker,
    // bill.getId());
  }

  @Test
  public void whenAddingParkingSticker_thenReturnParkingStickerCode() {
    ParkingStickerCodeDto receivedParkingStickerCodeDto =
        parkingStickerService.addParkingSticker(parkingStickerDto, accountId.toString());

    assertThat(receivedParkingStickerCodeDto).isSameInstanceAs(parkingStickerCodeDto);
  }

  @Test
  public void whenAddingParkingSticker_thenParkingStickerCreationObserversAreNotified() {
    parkingStickerService.register(parkingStickerCreationObserver);

    parkingStickerService.addParkingSticker(parkingStickerDto, accountId.toString());

    verify(parkingStickerCreationObserver).listenParkingStickerCreated(parkingSticker);
  }
}
