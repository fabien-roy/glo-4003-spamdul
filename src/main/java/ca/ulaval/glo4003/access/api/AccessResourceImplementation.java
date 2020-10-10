package ca.ulaval.glo4003.access.api;

import ca.ulaval.glo4003.access.api.dto.AccessPassCodeDto;
import ca.ulaval.glo4003.access.api.dto.AccessPassDto;
import ca.ulaval.glo4003.access.services.AccessService;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class AccessResourceImplementation implements AccessResource {
  private AccessService accessService;

  public AccessResourceImplementation(AccessService accessService) {
    this.accessService = accessService;
  }

  @Override
  public Response addAccessPass(AccessPassDto accessPassDto, String accountId) {
    AccessPassCodeDto accessPassCode = accessService.addAccessPass(accessPassDto, accountId);

    return Response.status(Response.Status.CREATED)
        .entity(accessPassCode)
        .type(MediaType.APPLICATION_JSON)
        .build();
  }
}
