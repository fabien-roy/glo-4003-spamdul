package ca.ulaval.glo4003.interfaces.helpers;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Randomizer {

  private static final Random RANDOM = new Random();

  private Randomizer() {}

  public static <T extends Enum<?>> T randomEnum(Class<T> enumToRandomize) {
    int x = RANDOM.nextInt(enumToRandomize.getEnumConstants().length);
    return enumToRandomize.getEnumConstants()[x];
  }

  public static <T extends Enum<?>> T randomEnumExcept(
      Class<T> enumToRandomize, List<T> enumsToExclude) {
    T randomEnum;
    do {
      randomEnum = randomEnum(enumToRandomize);
    } while (enumsToExclude.contains(randomEnum));
    return randomEnum;
  }

  public static <T extends Enum<?>> List<T> randomEnums(Class<T> enumToRandomize, int quantity) {
    List<T> randomEnums = new ArrayList<>();

    while (randomEnums.size() < quantity) {
      T randomEnum = randomEnum(enumToRandomize);
      if (!randomEnums.contains(randomEnum)) {
        randomEnums.add(randomEnum);
      }
    }

    return randomEnums;
  }
}
