package ca.ulaval.glo4003.access.api;

import static ca.ulaval.glo4003.access.helper.AccessPassCodeDtoBuilder.anAccessPassCodeDtoBuilder;
import static ca.ulaval.glo4003.accounts.helpers.AccountMother.createAccountId;
import static org.mockito.Mockito.when;

import ca.ulaval.glo4003.access.api.dto.AccessPassCodeDto;
import ca.ulaval.glo4003.access.api.dto.AccessPassDto;
import ca.ulaval.glo4003.access.services.AccessService;
import ca.ulaval.glo4003.accounts.domain.AccountId;
import com.google.common.truth.Truth;
import javax.ws.rs.core.Response;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class AccessResourceImplementationTest {
  private @Mock AccessService accessService;
  private @Mock AccessPassDto accessPassDto;

  private AccessResourceImplementation accessResourceImplementation;
  private AccountId accountId = createAccountId();
  private AccessPassCodeDto accessPassCodeDto = anAccessPassCodeDtoBuilder().build();

  @Before
  public void setUp() {
    accessResourceImplementation = new AccessResourceImplementation(accessService);
  }

  @Test
  public void whenAddingAccessPass_thenAddAccessPassToService() {
    when(accessService.addAccessPass(accessPassDto, accountId.toString()))
        .thenReturn(accessPassCodeDto);

    Response response =
        accessResourceImplementation.addAccessPass(accessPassDto, accountId.toString());
    AccessPassCodeDto respondedAccessPassCodeDto = (AccessPassCodeDto) response.getEntity();

    Truth.assertThat(respondedAccessPassCodeDto.accessPassCode)
        .isEqualTo(accessPassCodeDto.accessPassCode);
  }

  @Test
  public void whenAddingAccessPass_thenResponseCreatedStatus() {
    when(accessService.addAccessPass(accessPassDto, accountId.toString()))
        .thenReturn(accessPassCodeDto);

    Response response =
        accessResourceImplementation.addAccessPass(accessPassDto, accountId.toString());

    Truth.assertThat(response.getStatus()).isEqualTo(Response.Status.CREATED.getStatusCode());
  }
}
