package ca.ulaval.glo4003.locations.console;

import static ca.ulaval.glo4003.parkings.helpers.ParkingStickerBuilder.aParkingSticker;

import ca.ulaval.glo4003.parkings.domain.ParkingSticker;
import ca.ulaval.glo4003.parkings.domain.ReceptionMethods;
import com.google.common.truth.Truth;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class PostalSenderSystemPrintTest {
  private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
  private final PrintStream originalOut = System.out;

  private PostalSenderSystemPrint postalSenderSystemPrint;

  private ParkingSticker parkingSticker;

  @Before
  public void setUp() {
    System.setOut(new PrintStream(outContent));

    postalSenderSystemPrint = new PostalSenderSystemPrint();
  }

  @After
  public void tearDown() {
    System.setOut(originalOut);
  }

  @Test
  public void givenPostalReceptionMethod_whenListeningParkingStickerCreation_thenPrintMessage() {
    parkingSticker = aParkingSticker().withReceptionMethod(ReceptionMethods.POSTAL).build();

    postalSenderSystemPrint.listenParkingStickerCreated(parkingSticker);

    Truth.assertThat(outContent.toString()).contains(parkingSticker.getPostalCode().toString());
    Truth.assertThat(outContent.toString()).contains(parkingSticker.getCode().toString());
  }

  @Test
  public void
      givenEmailReceptionMethod_whenListeningParkingStickerCreation_thenDoNotPrintAnything() {
    parkingSticker = aParkingSticker().withReceptionMethod(ReceptionMethods.EMAIL).build();

    postalSenderSystemPrint.listenParkingStickerCreated(parkingSticker);

    Truth.assertThat(outContent.toString()).isEmpty();
  }
}
