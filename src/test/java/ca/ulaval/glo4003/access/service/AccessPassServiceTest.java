package ca.ulaval.glo4003.access.service;

import ca.ulaval.glo4003.access.assembler.AccessPassAssembler;
import ca.ulaval.glo4003.access.assembler.AccessPassCodeAssembler;
import ca.ulaval.glo4003.access.domain.AccessPassFactory;
import ca.ulaval.glo4003.access.domain.AccessPassRepository;
import ca.ulaval.glo4003.access.domain.AccessPassTypeRepository;
import ca.ulaval.glo4003.access.services.AccessPassService;
import ca.ulaval.glo4003.accounts.services.AccountService;
import ca.ulaval.glo4003.cars.services.CarService;
import ca.ulaval.glo4003.funds.services.BillService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class AccessPassServiceTest {
  @Mock private AccessPassAssembler accessPassAssembler;
  @Mock private AccessPassFactory accessPassFactory;
  @Mock private CarService carService;
  @Mock private AccessPassTypeRepository accessPassTypeRepository;
  @Mock private BillService billService;
  @Mock private AccountService accountService;
  @Mock private AccessPassRepository accessPassRepository;
  @Mock private AccessPassCodeAssembler accessPassCodeAssembler;

  private AccessPassService accessPassService;

  @Before
  public void setUp() {
    accessPassService =
        new AccessPassService(
            accessPassAssembler,
            accessPassFactory,
            carService,
            accessPassTypeRepository,
            accountService,
            billService,
            accessPassRepository,
            accessPassCodeAssembler);
  }

  @Test
  public void randomTest() {
    // TODO : Let's test this
  }
}
