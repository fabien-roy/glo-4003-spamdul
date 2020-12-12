package ca.ulaval.glo4003.offenses.services;

import static ca.ulaval.glo4003.funds.helpers.BillBuilder.aBill;
import static ca.ulaval.glo4003.offenses.helpers.OffenseTypeBuilder.anOffenseType;
import static ca.ulaval.glo4003.offenses.helpers.OffenseTypeDtoBuilder.anOffenseTypeDto;
import static ca.ulaval.glo4003.offenses.helpers.OffenseValidationBuilder.anOffenseValidation;
import static ca.ulaval.glo4003.offenses.helpers.OffenseValidationDtoBuilder.anOffenseValidationDto;
import static com.google.common.truth.Truth.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import ca.ulaval.glo4003.accounts.domain.Account;
import ca.ulaval.glo4003.accounts.services.AccountService;
import ca.ulaval.glo4003.funds.domain.Bill;
import ca.ulaval.glo4003.funds.services.BillService;
import ca.ulaval.glo4003.offenses.domain.*;
import ca.ulaval.glo4003.offenses.services.assemblers.OffenseTypeAssembler;
import ca.ulaval.glo4003.offenses.services.converters.OffenseValidationConverter;
import ca.ulaval.glo4003.offenses.services.dto.OffenseTypeDto;
import ca.ulaval.glo4003.offenses.services.dto.OffenseValidationDto;
import ca.ulaval.glo4003.parkings.domain.ParkingAreaRepository;
import ca.ulaval.glo4003.parkings.domain.ParkingSticker;
import ca.ulaval.glo4003.parkings.domain.exceptions.NotFoundParkingStickerException;
import java.util.Collections;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class OffenseTypeServiceTest {
  @Mock private ParkingAreaRepository parkingAreaRepository;
  @Mock private OffenseValidationConverter offenseValidationConverter;
  @Mock private OffenseTypeAssembler offenseTypeAssembler;
  @Mock private OffenseTypeRepository offenseTypeRepository;
  @Mock private OffenseTypeFactory offenseTypeFactory;
  @Mock private ParkingSticker parkingSticker;
  @Mock private Account account;
  @Mock private BillService billService;
  @Mock private AccountService accountService;
  @Mock private OffenseNotifier offenseNotifier;

  private OffenseTypeService offenseTypeService;

  private final OffenseType offenseType = anOffenseType().build();
  private final List<OffenseType> offenseTypes = Collections.singletonList(offenseType);
  private final OffenseTypeDto offenseTypeDto = anOffenseTypeDto().build();
  private final List<OffenseTypeDto> offenseTypeDtos = Collections.singletonList(offenseTypeDto);
  private OffenseValidation offenseValidation = anOffenseValidation().build();
  private final OffenseValidationDto offenseValidationDto = anOffenseValidationDto().build();
  private final OffenseType wrongZoneOffenseType = anOffenseType().build();
  private final OffenseType invalidStickerOffenseType = anOffenseType().build();
  private final OffenseType absentStickerOffenseType = anOffenseType().build();
  private final OffenseTypeDto wrongZoneOffenseTypeDto = anOffenseTypeDto().build();
  private final OffenseTypeDto invalidStickerOffenseTypeDto = anOffenseTypeDto().build();
  private final OffenseTypeDto absentStickerOffenseTypeDto = anOffenseTypeDto().build();
  private final Bill bill = aBill().build();

  @Before
  public void setUp() {
    offenseTypeService =
        new OffenseTypeService(
            parkingAreaRepository,
            offenseValidationConverter,
            offenseTypeAssembler,
            offenseTypeRepository,
            offenseTypeFactory,
            billService,
            accountService,
            offenseNotifier);

    setUpMocks();
  }

  private void setUpMocks() {
    when(offenseTypeRepository.getAll()).thenReturn(offenseTypes);
    when(offenseTypeAssembler.assembleMany(offenseTypes)).thenReturn(offenseTypeDtos);

    when(offenseValidationConverter.convert(offenseValidationDto)).thenReturn(offenseValidation);
    when(parkingSticker.validateParkingStickerAreaCode(offenseValidation.getParkingAreaCode()))
        .thenReturn(false);
    when(accountService.getParkingSticker(offenseValidation.getParkingStickerCode()))
        .thenReturn(parkingSticker);
    when(account.getParkingSticker(offenseValidation.getParkingStickerCode()))
        .thenReturn(parkingSticker);
    when(offenseTypeFactory.createWrongZoneOffense()).thenReturn(wrongZoneOffenseType);
    when(offenseTypeFactory.createInvalidStickerOffense()).thenReturn(invalidStickerOffenseType);
    when(offenseTypeFactory.createAbsentStickerOffense()).thenReturn(absentStickerOffenseType);
    when(offenseTypeAssembler.assembleMany(Collections.singletonList(wrongZoneOffenseType)))
        .thenReturn(Collections.singletonList(wrongZoneOffenseTypeDto));
    when(offenseTypeAssembler.assembleMany(Collections.singletonList(invalidStickerOffenseType)))
        .thenReturn(Collections.singletonList(invalidStickerOffenseTypeDto));
    when(offenseTypeAssembler.assembleMany(Collections.singletonList(absentStickerOffenseType)))
        .thenReturn(Collections.singletonList(absentStickerOffenseTypeDto));
    when(billService.addBillOffense(
            account.getId(), offenseType.getAmount(), offenseType.getCode()))
        .thenReturn(bill);
  }

  @Test
  public void whenGettingAllOffenseTypes_thenReturnAllOffenseTypes() {
    List<OffenseTypeDto> receivedOffenseTypeDtos = offenseTypeService.getAllOffenseTypes();

    assertThat(receivedOffenseTypeDtos).isSameInstanceAs(offenseTypeDtos);
  }

  @Test
  public void whenValidatingOffense_thenVerifyParkingAreaExists() {
    offenseTypeService.validateOffense(offenseValidationDto);

    verify(parkingAreaRepository).get(offenseValidation.getParkingAreaCode());
  }

  @Test
  public void whenValidatingOffense_thenGetParkingSticker() {
    offenseTypeService.validateOffense(offenseValidationDto);

    verify(accountService).getParkingSticker(offenseValidation.getParkingStickerCode());
  }

  @Test
  public void givenOffenseValidationWithoutOffense_whenValidatingOffense_thenReturnNoOffenseType() {
    when(parkingSticker.validateParkingStickerAreaCode(offenseValidation.getParkingAreaCode()))
        .thenReturn(true);

    List<OffenseTypeDto> offenseTypeDtos = offenseTypeService.validateOffense(offenseValidationDto);

    assertThat(offenseTypeDtos).isEmpty();
  }

  @Test
  public void
      givenValidationWithNoParkingSticker_whenValidatingOffense_thenReturnAbsentStickerOffenseType() {
    offenseValidation = anOffenseValidation().withParkingStickerCode(null).build();
    setUpMocks();

    List<OffenseTypeDto> offenseTypeDtos = offenseTypeService.validateOffense(offenseValidationDto);

    assertThat(offenseTypeDtos).contains(absentStickerOffenseTypeDto);
  }

  @Test
  public void
      givenValidationWithNoParkingSticker_whenValidatingOffense_thenOffenseIsNotifiedForAbsentParkingSticker() {
    offenseValidation = anOffenseValidation().withParkingStickerCode(null).build();
    setUpMocks();

    offenseTypeService.validateOffense(offenseValidationDto);

    verify(offenseNotifier).notifyOffenseWithoutParkingSticker(absentStickerOffenseType);
  }

  @Test
  public void
      givenValidationWithInvalidParkingSticker_whenValidatingOffense_thenReturnInvalidParkingStickerOffenseType() {
    when(accountService.getParkingSticker(offenseValidation.getParkingStickerCode()))
        .thenThrow(new NotFoundParkingStickerException(offenseValidation.getParkingStickerCode()));

    List<OffenseTypeDto> offenseTypeDtos = offenseTypeService.validateOffense(offenseValidationDto);

    assertThat(offenseTypeDtos).contains(invalidStickerOffenseTypeDto);
  }

  @Test
  public void
      givenValidationWithInvalidParkingSticker_whenValidatingOffense_thenOffenseIsNotifiedForInvalidParkingSticker() {
    when(accountService.getParkingSticker(offenseValidation.getParkingStickerCode()))
        .thenThrow(new NotFoundParkingStickerException(offenseValidation.getParkingStickerCode()));

    offenseTypeService.validateOffense(offenseValidationDto);

    verify(offenseNotifier).notifyOffenseWithoutParkingSticker(invalidStickerOffenseType);
  }

  @Test
  public void givenValidationWithWrongZone_whenValidatingOffense_thenReturnWrongZoneOffenseType() {
    when(parkingSticker.validateParkingStickerAreaCode(offenseValidation.getParkingAreaCode()))
        .thenReturn(false);

    List<OffenseTypeDto> offenseTypeDtos = offenseTypeService.validateOffense(offenseValidationDto);

    assertThat(offenseTypeDtos).contains(wrongZoneOffenseTypeDto);
  }

  @Test
  public void givenValidationWithWrongZone_whenValidatingOffense_thenCreateBill() {
    when(parkingSticker.validateParkingStickerAreaCode(offenseValidation.getParkingAreaCode()))
        .thenReturn(false);

    offenseTypeService.validateOffense(offenseValidationDto);

    verify(billService)
        .addBillOffense(
            account.getId(), wrongZoneOffenseType.getAmount(), wrongZoneOffenseType.getCode());
  }

  @Test
  public void givenValidationWithWrongZone_whenValidatingOffense_thenAddBillToAccount() {
    when(parkingSticker.validateParkingStickerAreaCode(offenseValidation.getParkingAreaCode()))
        .thenReturn(false);
    when(billService.addBillOffense(
            account.getId(), wrongZoneOffenseType.getAmount(), wrongZoneOffenseType.getCode()))
        .thenReturn(bill);

    offenseTypeService.validateOffense(offenseValidationDto);

    verify(accountService).addOffenseToAccount(parkingSticker.getAccountId(), bill);
  }
}
