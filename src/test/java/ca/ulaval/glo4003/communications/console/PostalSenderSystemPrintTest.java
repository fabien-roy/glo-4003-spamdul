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

public class PostalSenderSystemPrintTest {
  private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
  private final PrintStream originalOut = System.out;

  private PostalSenderSystemPrint postalSenderSystemPrint;

  private ParkingSticker parkingSticker;
  private AccessPass accessPass;

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
    parkingSticker = aParkingSticker().withReceptionMethod(ReceptionMethod.POSTAL).build();

    postalSenderSystemPrint.listenParkingStickerCreated(parkingSticker);

    Truth.assertThat(outContent.toString()).contains(parkingSticker.getPostalCode().toString());
    Truth.assertThat(outContent.toString()).contains(parkingSticker.getCode().toString());
  }

  @Test
  public void
      givenEmailReceptionMethod_whenListeningParkingStickerCreation_thenDoNotPrintAnything() {
    parkingSticker = aParkingSticker().withReceptionMethod(ReceptionMethod.EMAIL).build();

    postalSenderSystemPrint.listenParkingStickerCreated(parkingSticker);

    Truth.assertThat(outContent.toString()).isEmpty();
  }

  @Test
  public void givenSSPReceptionMethod_whenListeningParkingStickerCreation_thenDoNotPrintAnything() {
    parkingSticker = aParkingSticker().withReceptionMethod(ReceptionMethod.SSP).build();

    postalSenderSystemPrint.listenParkingStickerCreated(parkingSticker);

    Truth.assertThat(outContent.toString()).isEmpty();
  }

  @Test
  public void givenPostalReceptionMethod_whenListeningAccessPassCreation_thenPrintMessage() {
    accessPass = anAccessPass().withReceptionMethod(ReceptionMethod.POSTAL).build();

    postalSenderSystemPrint.listenAccessPassCreated(accessPass);

    Truth.assertThat(outContent.toString()).contains(accessPass.getPostalCode().toString());
    Truth.assertThat(outContent.toString()).contains(accessPass.getCode().toString());
  }

  @Test
  public void givenEmailReceptionMethod_whenListeningAccessPassCreation_thenDoNotPrintAnything() {
    accessPass = anAccessPass().withReceptionMethod(ReceptionMethod.EMAIL).build();

    postalSenderSystemPrint.listenAccessPassCreated(accessPass);

    Truth.assertThat(outContent.toString()).isEmpty();
  }

  @Test
  public void givenSSPReceptionMethod_whenListeningAccessPassCreation_thenDoNotPrintAnything() {
    accessPass = anAccessPass().withReceptionMethod(ReceptionMethod.SSP).build();

    postalSenderSystemPrint.listenAccessPassCreated(accessPass);

    Truth.assertThat(outContent.toString()).isEmpty();
  }
}
