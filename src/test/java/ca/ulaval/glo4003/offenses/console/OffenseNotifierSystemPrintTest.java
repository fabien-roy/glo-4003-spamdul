package ca.ulaval.glo4003.offenses.console;

import static ca.ulaval.glo4003.offenses.helpers.OffenseTypeBuilder.anOffenseType;
import static com.google.common.truth.Truth.assertThat;

import ca.ulaval.glo4003.offenses.domain.OffenseType;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class OffenseNotifierSystemPrintTest {
  private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
  private final PrintStream originalOut = System.out;

  private OffenseNotifierSystemPrint offenseNotifierSystemPrint;

  private final OffenseType offenseType = anOffenseType().build();

  @Before
  public void setUp() {
    System.setOut(new PrintStream(outContent));

    offenseNotifierSystemPrint = new OffenseNotifierSystemPrint();
  }

  @After
  public void tearDown() {
    System.setOut(originalOut);
  }

  @Test
  public void whenNotifyingOffenseWithoutParkingSticker_thenPrintOffenseTypeToSystem() {
    offenseNotifierSystemPrint.notifyOffenseWithoutParkingSticker(offenseType);

    assertThat(outContent.toString()).contains(offenseType.getCode().toString());
    assertThat(outContent.toString()).contains(offenseType.getDescription());
    assertThat(outContent.toString()).contains(offenseType.getAmount().toString());
  }
}
