package ca.ulaval.glo4003.errors.helpers;

import static ca.ulaval.glo4003.randomizers.helpers.Randomizer.randomEnum;

import ca.ulaval.glo4003.errors.domain.ErrorCode;
import com.github.javafaker.Faker;

public class ApplicationExceptionMother {

  public static String createError() {
    return Faker.instance().book().title();
  }

  public static String createDescription() {
    return Faker.instance().animal().name();
  }

  public static ErrorCode createCode() {
    return randomEnum(ErrorCode.class);
  }
}
