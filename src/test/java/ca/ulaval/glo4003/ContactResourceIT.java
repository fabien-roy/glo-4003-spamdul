package ca.ulaval.glo4003;

import static io.restassured.RestAssured.get;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
@Ignore
public class ContactResourceIT {

  @Before
  public void setUp() {
    Thread t =
        new Thread(
            () -> {
              try {
                Main.main(new String[] {});
              } catch (Exception e) {
                e.printStackTrace();
              }
            });
    t.setDaemon(true);
    t.start();
  }

  @Test
  public void givenContacts_whenGetAllContacts_thenContactsReturned() {
    get("/api/telephony/contacts").then().body("name", Matchers.hasItem("Steve Jobs"));
  }
}
