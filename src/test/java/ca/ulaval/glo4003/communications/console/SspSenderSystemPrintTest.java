package ca.ulaval.glo4003.communications.console;

import static ca.ulaval.glo4003.accesspasses.helpers.AccessPassBuilder.anAccessPass;
import static ca.ulaval.glo4003.parkings.helpers.ParkingStickerBuilder.aParkingSticker;

import ca.ulaval.glo4003.accesspasses.domain.AccessPass;
import ca.ulaval.glo4003.communications.domain.ReceptionMethod;
import ca.ulaval.glo4003.parkings.domain.ParkingSticker;
import com.google.common.truth.Truth;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SspSenderSystemPrintTest {
  private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
  private final PrintStream originalOut = System.out;

  private SspSenderSystemPrint sspSenderSystemPrint;

  private ParkingSticker parkingSticker;
  private AccessPass accessPass;

  @Before
  public void setUp() {
    System.setOut(new PrintStream(outContent));

    sspSenderSystemPrint = new SspSenderSystemPrint();
  }

  @After
  public void tearDown() {
    System.setOut(originalOut);
  }

  @Test
  public void givenPostalReceptionMethod_whenListeningParkingStickerCreation_thenPrintMessage() {
    parkingSticker = aParkingSticker().withReceptionMethod(ReceptionMethod.SSP).build();

    sspSenderSystemPrint.listenParkingStickerCreated(parkingSticker);

    Truth.assertThat(outContent.toString()).contains(parkingSticker.getCode().toString());
  }

  @Test
  public void
      givenEmailReceptionMethod_whenListeningParkingStickerCreation_thenDoNotPrintAnything() {
    parkingSticker = aParkingSticker().withReceptionMethod(ReceptionMethod.EMAIL).build();

    sspSenderSystemPrint.listenParkingStickerCreated(parkingSticker);

    Truth.assertThat(outContent.toString()).isEmpty();
  }

  @Test
  public void
      givenPostalReceptionMethod_whenListeningParkingStickerCreation_thenDoNotPrintAnything() {
    parkingSticker = aParkingSticker().withReceptionMethod(ReceptionMethod.POSTAL).build();

    sspSenderSystemPrint.listenParkingStickerCreated(parkingSticker);

    Truth.assertThat(outContent.toString()).isEmpty();
  }

  @Test
  public void givenPostalReceptionMethod_whenListeningAccessPassCreation_thenPrintMessage() {
    accessPass = anAccessPass().withReceptionMethod(ReceptionMethod.SSP).build();

    sspSenderSystemPrint.listenAccessPassCreated(accessPass);

    Truth.assertThat(outContent.toString()).contains(accessPass.getCode().toString());
  }

  @Test
  public void givenEmailReceptionMethod_whenListeningAccessPassCreation_thenDoNotPrintAnything() {
    accessPass = anAccessPass().withReceptionMethod(ReceptionMethod.EMAIL).build();

    sspSenderSystemPrint.listenAccessPassCreated(accessPass);

    Truth.assertThat(outContent.toString()).isEmpty();
  }

  @Test
  public void givenPostalReceptionMethod_whenListeningAccessPassCreation_thenDoNotPrintAnything() {
    accessPass = anAccessPass().withReceptionMethod(ReceptionMethod.POSTAL).build();

    sspSenderSystemPrint.listenAccessPassCreated(accessPass);

    Truth.assertThat(outContent.toString()).isEmpty();
  }
}
