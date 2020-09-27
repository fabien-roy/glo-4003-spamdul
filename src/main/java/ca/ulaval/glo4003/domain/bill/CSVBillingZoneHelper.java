package ca.ulaval.glo4003.domain.bill;

import ca.ulaval.glo4003.domain.bill.exceptions.InvalidFileException;
import ca.ulaval.glo4003.domain.bill.exceptions.InvalidTimeException;
import ca.ulaval.glo4003.domain.bill.exceptions.InvalidZoneException;
import java.io.*;

public class CSVBillingZoneHelper {
  private final String csvFraisZonePath =
      ".\\src\\main\\java\\ca\\ulaval\\glo4003\\document\\frais-zone.csv";
  private boolean isCsvFirstLine;
  private int csvColumnNumber;

  public float getZonePrice(String zone, String time) {
    try {
      return getZoneValueForSpecificTime(zone, time);
    } catch (IOException ignored) {
      throw new InvalidFileException();
    }
  }

  private float getZoneValueForSpecificTime(String zone, String time) throws IOException {
    String line;
    isCsvFirstLine = true;
    File file = new File(csvFraisZonePath);
    BufferedReader bufferedReader = new BufferedReader(new FileReader(file));

    while ((line = bufferedReader.readLine()) != null) {
      String[] rowValues = line.split(",");
      if (isCsvFirstLine) {
        csvColumnNumber = getColumnNumberFromFirstLine(time, rowValues);
        isCsvFirstLine = false;
      } else {
        if (rowValues[0].equals(zone)) {
          return Float.parseFloat(rowValues[csvColumnNumber]);
        }
      }
    }

    throw new InvalidZoneException();
  }

  private int getColumnNumberFromFirstLine(String time, String[] data) {
    csvColumnNumber = 0;

    for (String csvTime : data) {
      if (csvTime.equals(time)) {
        return csvColumnNumber;
      }

      csvColumnNumber++;
    }

    throw new InvalidTimeException();
  }
}
