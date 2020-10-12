package ca.ulaval.glo4003.users.api;

import ca.ulaval.glo4003.access.api.dto.AccessPassDto;
import ca.ulaval.glo4003.cars.api.dto.CarDto;
import ca.ulaval.glo4003.funds.api.dto.BillPaymentDto;
import ca.ulaval.glo4003.users.api.dto.UserDto;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/users")
public interface UserResource {

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  Response addUser(UserDto userDto);

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @Path("{accountId}")
  Response getUser(@PathParam("accountId") String accountId);

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  @Path("{accountId}/access")
  Response addAccessPass(AccessPassDto accessPassDto, @PathParam("accountId") String accountId);

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  @Path("{accountId}/cars")
  Response addCar(CarDto carDto, @PathParam("accountId") String accountId);

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @Path("{accountId}/bills")
  Response getBills(@PathParam("accountId") String accountId);

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  @Path("{accountId}/bills/{billId}")
  Response payBill(
      BillPaymentDto billPaymentDto,
      @PathParam("accountId") String accountId,
      @PathParam("billId") String billId);
}
