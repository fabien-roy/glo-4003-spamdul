package ca.ulaval.glo4003.files.helpers;

import com.github.javafaker.Faker;

public class FileMother {
  public static String createFilePath() {
    return Faker.instance().company().name();
  }
}
