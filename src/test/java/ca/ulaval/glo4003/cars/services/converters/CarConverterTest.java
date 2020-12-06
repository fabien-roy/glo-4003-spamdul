package ca.ulaval.glo4003.cars.services.converters;

import static ca.ulaval.glo4003.accounts.helpers.AccountMother.createAccountId;
import static ca.ulaval.glo4003.cars.helpers.CarDtoBuilder.aCarDto;
import static ca.ulaval.glo4003.cars.helpers.LicensePlateMother.createLicensePlate;
import static com.google.common.truth.Truth.assertThat;
import static org.mockito.Mockito.when;

import ca.ulaval.glo4003.accounts.domain.AccountId;
import ca.ulaval.glo4003.accounts.services.converters.AccountIdConverter;
import ca.ulaval.glo4003.cars.domain.Car;
import ca.ulaval.glo4003.cars.domain.LicensePlate;
import ca.ulaval.glo4003.cars.exceptions.InvalidCarYearException;
import ca.ulaval.glo4003.cars.exceptions.InvalidManufacturerException;
import ca.ulaval.glo4003.cars.exceptions.InvalidModelException;
import ca.ulaval.glo4003.cars.services.dto.CarDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CarConverterTest {

  @Mock private LicensePlateConverter licensePlateConverter;
  @Mock private AccountIdConverter accountIdConverter;

  private CarConverter carConverter;

  private static final int INVALID_YEAR = 100000;

  private final LicensePlate licensePlate = createLicensePlate();
  private final AccountId accountId = createAccountId();
  private final CarDto carDto = aCarDto().withLicensePlate(licensePlate.toString()).build();

  @Before
  public void setUp() {
    carConverter = new CarConverter(licensePlateConverter);

    when(licensePlateConverter.convert(licensePlate.toString())).thenReturn(licensePlate);
    when(accountIdConverter.convert(accountId.toString())).thenReturn(accountId);
  }

  @Test
  public void whenConverting_shouldCarWithLicensePlate() {
    Car car = carConverter.convert(carDto);

    assertThat(car.getLicensePlate().toString()).isEqualTo(carDto.licensePlate);
  }

  @Test
  public void whenConverting_shouldCarWithManufacturer() {
    Car car = carConverter.convert(carDto);

    assertThat(car.getManufacturer()).isEqualTo(carDto.manufacturer);
  }

  @Test
  public void whenConverting_shouldCarWithModel() {
    Car car = carConverter.convert(carDto);

    assertThat(car.getModel()).isEqualTo(carDto.model);
  }

  @Test
  public void whenConverting_shouldCarWithYear() {
    Car car = carConverter.convert(carDto);

    assertThat(car.getYear()).isEqualTo(carDto.year);
  }

  @Test
  public void whenConverting_shouldCarWithConsumptionType() {
    Car car = carConverter.convert(carDto);

    assertThat(car.getConsumptionType().toString()).isEqualTo(carDto.consumptionType);
  }

  @Test(expected = InvalidCarYearException.class)
  public void givenCarWithInvalidYear_whenConverting_shouldThrowInvalidYearException() {
    CarDto carDto = aCarDto().withYear(INVALID_YEAR).build();

    carConverter.convert(carDto);
  }

  @Test(expected = InvalidCarYearException.class)
  public void givenCarWithNullYear_whenConverting_shouldThrowInvalidYearException() {
    // The year is parsed to zero when it is fed a null
    CarDto carDto = aCarDto().withYear(0).build();

    carConverter.convert(carDto);
  }

  @Test(expected = InvalidManufacturerException.class)
  public void
      givenCarWithNullManufacturer_whenConverting_shouldThrowInvalidManufacturerException() {
    CarDto carDto = aCarDto().withManufacturer(null).build();

    carConverter.convert(carDto);
  }

  @Test(expected = InvalidModelException.class)
  public void givenCarWithNullModel_whenConverting_shouldThrowInvalidModelException() {
    CarDto carDto = aCarDto().withModel(null).build();

    carConverter.convert(carDto);
  }
}
