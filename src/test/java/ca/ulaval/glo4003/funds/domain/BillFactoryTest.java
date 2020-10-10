package ca.ulaval.glo4003.funds.domain;

import static ca.ulaval.glo4003.access.helper.AccessPassMother.createAccessPassCode;
import static ca.ulaval.glo4003.funds.helpers.BillMother.createBillId;
import static ca.ulaval.glo4003.funds.helpers.MoneyMother.createMoney;
import static ca.ulaval.glo4003.offenses.helpers.OffenseTypeMother.createOffenseCode;
import static ca.ulaval.glo4003.parkings.helpers.ParkingStickerMother.*;
import static org.mockito.Mockito.when;

import ca.ulaval.glo4003.access.domain.AccessPassCode;
import ca.ulaval.glo4003.offenses.domain.OffenseCode;
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
  private final Money fee = createMoney();
  private final ParkingStickerCode parkingStickerCode = createParkingStickerCode();
  private final AccessPassCode accessPassCode = createAccessPassCode();
  private final ReceptionMethods receptionMethod = createReceptionMethod();
  private final OffenseCode offenseCode = createOffenseCode();

  @Before
  public void setUp() {
    billFactory = new BillFactory(billIdGenerator);

    when(billIdGenerator.generate()).thenReturn(billId);
  }

  @Test
  public void whenCreatingForParkingSticker_thenReturnBillWithId() {
    Bill bill = billFactory.createForParkingSticker(fee, parkingStickerCode, receptionMethod);

    Truth.assertThat(bill.getId()).isSameInstanceAs(billId);
  }

  @Test
  public void
      whenCreatingForParkingSticker_thenReturnBillWithDescriptionContainingParkingStickerCode() {
    Bill bill = billFactory.createForParkingSticker(fee, parkingStickerCode, receptionMethod);

    Truth.assertThat(bill.getDescription()).contains(parkingStickerCode.toString());
  }

  @Test
  public void
      givenEmailReceptionMethod_whenCreatingForParkingSticker_thenReturnBillWithAmountEqualToFee() {
    ReceptionMethods emailReceptionMethod = ReceptionMethods.EMAIL;

    Bill bill = billFactory.createForParkingSticker(fee, parkingStickerCode, emailReceptionMethod);

    Truth.assertThat(bill.getAmountDue()).isEqualTo(fee);
  }

  @Test
  public void
      givenPostalReceptionMethod_whenCreatingForParkingSticker_thenReturnBillWithAmountEqualToFeePlusFive() {
    Money expectedAmount = fee.plus(Money.fromDouble(5));
    ReceptionMethods postalReceptionMethod = ReceptionMethods.POSTAL;

    Bill bill = billFactory.createForParkingSticker(fee, parkingStickerCode, postalReceptionMethod);

    Truth.assertThat(bill.getAmountDue()).isEqualTo(expectedAmount);
  }

  @Test
  public void whenCreatingForParkingSticker_thenReturnBillWithParkingStickerType() {
    Bill bill = billFactory.createForParkingSticker(fee, parkingStickerCode, receptionMethod);

    Truth.assertThat(bill.getBillTypes()).isEqualTo(BillTypes.PARKING_STICKER);
  }

  @Test
  public void whenCreatingForAccessPass_thenReturnBillWithId() {
    Bill bill = billFactory.createForAccessPass(fee, accessPassCode);

    Truth.assertThat(bill.getId()).isSameInstanceAs(billId);
  }

  @Test
  public void whenCreatingForAccessPass_thenReturnBillWithDescriptionContainingAccessPassCode() {
    Bill bill = billFactory.createForAccessPass(fee, accessPassCode);

    Truth.assertThat(bill.getDescription()).contains(accessPassCode.toString());
  }

  @Test
  public void whenCreatingForAccessPass_thenReturnBillWithAmountEqualToFee() {
    Bill bill = billFactory.createForAccessPass(fee, accessPassCode);

    Truth.assertThat(bill.getAmountDue()).isEqualTo(fee);
  }

  @Test
  public void whenCreatingForAccessPass_thenReturnBillWithAccessPassType() {
    Bill bill = billFactory.createForAccessPass(fee, accessPassCode);

    Truth.assertThat(bill.getBillTypes()).isEqualTo(BillTypes.ACCESS_PASS);
  }

  @Test
  public void whenCreatingForOffense_thenReturnBillWithId() {
    Bill bill = billFactory.createForOffense(fee, offenseCode);

    Truth.assertThat(bill.getId()).isSameInstanceAs(billId);
  }

  @Test
  public void whenCreatingForOffense_thenReturnBillWithDescriptionContainingOffenseCode() {
    Bill bill = billFactory.createForOffense(fee, offenseCode);

    Truth.assertThat(bill.getDescription()).contains(offenseCode.toString());
  }

  @Test
  public void whenCreatingForOffense_thenReturnBillWithAmountEqualToFee() {
    Bill bill = billFactory.createForOffense(fee, offenseCode);

    Truth.assertThat(bill.getAmountDue()).isEqualTo(fee);
  }

  @Test
  public void whenCreatingForOffense_thenReturnBillWithAccessPassType() {
    Bill bill = billFactory.createForOffense(fee, offenseCode);

    Truth.assertThat(bill.getBillTypes()).isEqualTo(BillTypes.OFFENSE);
  }
}
