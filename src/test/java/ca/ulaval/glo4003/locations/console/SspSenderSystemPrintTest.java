package ca.ulaval.glo4003.locations.console;

import static ca.ulaval.glo4003.parkings.helpers.ParkingStickerBuilder.aParkingSticker;

import ca.ulaval.glo4003.parkings.domain.ParkingSticker;
import ca.ulaval.glo4003.parkings.domain.ReceptionMethod;
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
}
