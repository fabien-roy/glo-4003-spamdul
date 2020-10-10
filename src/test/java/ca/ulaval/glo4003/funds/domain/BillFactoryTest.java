package ca.ulaval.glo4003.funds.domain;

import static ca.ulaval.glo4003.funds.helpers.BillMother.createBillId;
import static ca.ulaval.glo4003.funds.helpers.MoneyMother.createMoney;
import static ca.ulaval.glo4003.parkings.helpers.ParkingStickerMother.createParkingStickerCode;
import static ca.ulaval.glo4003.parkings.helpers.ParkingStickerMother.createReceptionMethod;
import static org.mockito.Mockito.when;

import ca.ulaval.glo4003.parkings.domain.ParkingStickerCode;
import ca.ulaval.glo4003.parkings.domain.ReceptionMethods;
import com.google.common.truth.Truth;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class BillFactoryTest {

  @Mock BillIdGenerator billIdGenerator;

  private BillFactory billFactory;

  private final BillId billId = createBillId();
  private final Money feeForPeriod = createMoney();
  private final ParkingStickerCode parkingStickerCode = createParkingStickerCode();
  private final ReceptionMethods receptionMethod = createReceptionMethod();

  @Before
  public void setUp() {
    billFactory = new BillFactory(billIdGenerator);

    when(billIdGenerator.generate()).thenReturn(billId);
  }

  @Test
  public void whenCreatingForParkingSticker_thenReturnBillWithId() {
    Bill bill =
        billFactory.createForParkingSticker(feeForPeriod, parkingStickerCode, receptionMethod);

    Truth.assertThat(bill.getId()).isSameInstanceAs(billId);
  }

  @Test
  public void whenCreatingForParkingSticker_thenReturnBillWithDescription() {
    Bill bill =
        billFactory.createForParkingSticker(feeForPeriod, parkingStickerCode, receptionMethod);

    Truth.assertThat(bill.getDescription()).isNotEmpty();
  }

  @Test
  public void
      whenCreatingForParkingSticker_thenReturnBillWithDescriptionContainingParkingStickerCode() {
    Bill bill =
        billFactory.createForParkingSticker(feeForPeriod, parkingStickerCode, receptionMethod);

    Truth.assertThat(bill.getDescription()).contains(parkingStickerCode.toString());
  }

  @Test
  public void
      givenEmailReceptionMethod_whenCreatingForParkingSticker_thenReturnBillWithAmountEqualToFee() {
    ReceptionMethods emailReceptionMethod = ReceptionMethods.EMAIL;

    Bill bill =
        billFactory.createForParkingSticker(feeForPeriod, parkingStickerCode, emailReceptionMethod);

    Truth.assertThat(bill.getAmountDue()).isEqualTo(feeForPeriod);
  }

  @Test
  public void
      givenPostalReceptionMethod_whenCreatingForParkingSticker_thenReturnBillWithAmountEqualToFeePlusFive() {
    Money expectedAmount = feeForPeriod.plus(Money.fromDouble(5));
    ReceptionMethods postalReceptionMethod = ReceptionMethods.POSTAL;

    Bill bill =
        billFactory.createForParkingSticker(
            feeForPeriod, parkingStickerCode, postalReceptionMethod);

    Truth.assertThat(bill.getAmountDue()).isEqualTo(expectedAmount);
  }
}
