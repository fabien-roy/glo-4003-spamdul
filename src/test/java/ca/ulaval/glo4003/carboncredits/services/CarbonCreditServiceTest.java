package ca.ulaval.glo4003.carboncredits.services;

import static ca.ulaval.glo4003.carboncredits.helpers.CarbonCreditDtoBuilder.aCarbonCreditDto;
import static ca.ulaval.glo4003.carboncredits.helpers.CarbonCreditMother.createCarbonCredit;
import static ca.ulaval.glo4003.carboncredits.helpers.MonthlyPaymentStatusDtoBuilder.aMonthlyPaymentStatusDto;
import static ca.ulaval.glo4003.carboncredits.helpers.MonthlyPaymentStatusMother.createMonthlyPaymentStatus;
import static ca.ulaval.glo4003.funds.helpers.MoneyMother.createMoney;
import static ca.ulaval.glo4003.initiatives.helpers.InitiativeBuilder.anInitiative;
import static com.google.common.truth.Truth.assertThat;
import static org.mockito.Mockito.*;

import ca.ulaval.glo4003.carboncredits.api.dto.CarbonCreditDto;
import ca.ulaval.glo4003.carboncredits.api.dto.MonthlyPaymentStatusDto;
import ca.ulaval.glo4003.carboncredits.assemblers.CarbonCreditAssembler;
import ca.ulaval.glo4003.carboncredits.assemblers.MonthlyPaymentStatusAssembler;
import ca.ulaval.glo4003.carboncredits.configuration.CarbonCreditConfiguration;
import ca.ulaval.glo4003.carboncredits.domain.CarbonCredit;
import ca.ulaval.glo4003.carboncredits.domain.CarbonCreditRepository;
import ca.ulaval.glo4003.carboncredits.domain.MonthlyPaymentStatus;
import ca.ulaval.glo4003.carboncredits.domain.MonthlyPaymentStatusRepository;
import ca.ulaval.glo4003.funds.domain.Money;
import ca.ulaval.glo4003.funds.domain.SustainableMobilityProgramBankRepository;
import ca.ulaval.glo4003.initiatives.domain.Initiative;
import ca.ulaval.glo4003.initiatives.services.InitiativeService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CarbonCreditServiceTest {
  @Mock CarbonCreditRepository carbonCreditRepository;
  @Mock CarbonCreditAssembler carbonCreditAssembler;
  @Mock MonthlyPaymentStatusAssembler monthlyPaymentStatusAssembler;
  @Mock MonthlyPaymentStatusRepository monthlyPaymentStatusRepository;
  @Mock SustainableMobilityProgramBankRepository sustainableMobilityProgramBankRepository;
  @Mock InitiativeService initiativeService;

  private CarbonCreditService carbonCreditService;

  private final MonthlyPaymentStatusDto monthlyPaymentStatusDto =
      aMonthlyPaymentStatusDto().build();
  private final MonthlyPaymentStatus monthlyPaymentStatus = createMonthlyPaymentStatus();
  private final CarbonCreditDto carbonCreditDto = aCarbonCreditDto().build();
  private final CarbonCredit carbonCredit = createCarbonCredit();
  private final Money sustainableMobilityProgramBankAvailableMoney = createMoney();
  private final CarbonCreditConfiguration carbonCreditConfiguration =
      CarbonCreditConfiguration.getConfiguration();
  private final Initiative carbonCreditInitiative =
      anInitiative()
          .withInitiativeCode(carbonCreditConfiguration.getCarbonCreditInitiativeCode())
          .build();

  @Before
  public void setUp() {
    carbonCreditService =
        new CarbonCreditService(
            carbonCreditRepository,
            carbonCreditAssembler,
            monthlyPaymentStatusAssembler,
            monthlyPaymentStatusRepository,
            sustainableMobilityProgramBankRepository,
            initiativeService);

    when(carbonCreditRepository.get()).thenReturn(carbonCredit);
    when(carbonCreditAssembler.assemble(carbonCredit)).thenReturn(carbonCreditDto);
    when(monthlyPaymentStatusAssembler.assemble(monthlyPaymentStatusDto))
        .thenReturn(monthlyPaymentStatus);
    when(sustainableMobilityProgramBankRepository.get())
        .thenReturn(sustainableMobilityProgramBankAvailableMoney);
  }

  @Test
  public void whenGettingCarbonCredits_thenReturnCarbonCreditAmount() {
    CarbonCreditDto receivedCarbonCreditDto = carbonCreditService.getCarbonCredits();

    assertThat(receivedCarbonCreditDto).isSameInstanceAs(carbonCreditDto);
  }

  @Test
  public void whenModifyingMonthlyPaymentStatus_thenSaveMonthlyPaymentStatus() {
    carbonCreditService.modifyMonthlyPaymentStatus(monthlyPaymentStatusDto);

    verify(monthlyPaymentStatusRepository).save(monthlyPaymentStatus);
  }

  @Test
  public void
      givenEnabledMonthlyPaymentStatus_whenAllocatingRemainingFundToCarbonCreditInitiative_thenAllocateAllAvailableMoneyToInitiative() {
    when(monthlyPaymentStatusRepository.get()).thenReturn(MonthlyPaymentStatus.ENABLE);

    carbonCreditService.allocateRemainingFundToCarbonCreditInitiative();

    verify(initiativeService)
        .addAllocatedAmountToInitiative(
            carbonCreditInitiative.getCode(), sustainableMobilityProgramBankAvailableMoney);
  }

  @Test
  public void
      givenDisabledMonthlyPaymentStatus_whenAllocatingRemainingFundToCarbonCreditInitiative_thenDoNotAllocateAllAvailableMoneyToInitiative() {
    when(monthlyPaymentStatusRepository.get()).thenReturn(MonthlyPaymentStatus.DISABLE);

    carbonCreditService.allocateRemainingFundToCarbonCreditInitiative();

    verify(initiativeService, never())
        .addAllocatedAmountToInitiative(
            carbonCreditInitiative.getCode(), sustainableMobilityProgramBankAvailableMoney);
  }

  @Test
  public void
      givenCarbonCreditInitiative_whenListeningToInitiativeAddedAllocatedAmount_thenBuyCarbonCreditForRepository() {
    carbonCreditService.listenInitiativeAddedAllocatedAmount(
        carbonCreditInitiative, sustainableMobilityProgramBankAvailableMoney);

    verify(carbonCreditRepository)
        .add(CarbonCredit.fromMoney(sustainableMobilityProgramBankAvailableMoney));
  }

  @Test
  public void
      givenNotCarbonCreditInitiative_whenListeningToInitiativeAddedAllocatedAmount_thenDoNotBuyCarbonCreditForRepository() {
    Initiative notCarbonCreditInitiative = anInitiative().build();

    carbonCreditService.listenInitiativeAddedAllocatedAmount(
        notCarbonCreditInitiative, sustainableMobilityProgramBankAvailableMoney);

    verify(carbonCreditRepository, never())
        .add(CarbonCredit.fromMoney(sustainableMobilityProgramBankAvailableMoney));
  }
}
