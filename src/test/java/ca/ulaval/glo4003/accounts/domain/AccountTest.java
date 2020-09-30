package ca.ulaval.glo4003.accounts.domain;

import static org.mockito.Mockito.verify;

import ca.ulaval.glo4003.accounts.helpers.AccountBuilder;
import ca.ulaval.glo4003.bills.domain.Bill;
import ca.ulaval.glo4003.parkings.domain.ParkingSticker;
import ca.ulaval.glo4003.parkings.domain.ParkingStickerCode;
import ca.ulaval.glo4003.parkings.helpers.ParkingStickerBuilder;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class AccountTest {
  private final String validTimeForParkingSticker = "1j/sem/session";
  private Account account;
  private ParkingSticker parkingSticker;
  @Mock private Bill bill;
  @Mock private List<ParkingStickerCode> parkingStickerCodes;

  @Before
  public void setUp() {
    account = AccountBuilder.anAccount().build();
    parkingSticker = ParkingStickerBuilder.aParkingSticker().build();
    account.setBill(bill);
    account.setParkingStickerCodes(parkingStickerCodes);
  }

  @Test
  public void whenAddingParkingSticker_thenAddParkingCodeIsCalled() {
    account.addParkingSticker(parkingSticker);

    verify(parkingStickerCodes).add(parkingSticker.getCode());
  }

  @Test
  public void whenAddingParkingSticker_thenCalculationParkingBillIsCalled() {
    account.addParkingSticker(parkingSticker);

    verify(bill)
        .calculateZonePriceWithCommunicationType(
            parkingSticker.getReceptionMethod(),
            parkingSticker.getParkingAreaCode().toString(),
            validTimeForParkingSticker);
  }
}
