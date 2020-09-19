package ca.ulaval.glo4003.api.contact;

import java.util.List;

import ca.ulaval.glo4003.api.contact.dto.ContactDto;
import ca.ulaval.glo4003.domain.contact.ContactService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.google.common.truth.Truth;

import jersey.repackaged.com.google.common.collect.Lists;


@RunWith(MockitoJUnitRunner.class)
public class ContactResourceImplTest {
  @Mock
  private ContactService contactService;
  @Mock
  private ContactDto contactDto;

  private ContactResource contactResource;


  @Before
  public void setUp()
          throws Exception {
    contactResource = new ContactResourceImpl(contactService);
  }

  @Test
  public void whenFindAllContacts_thenFoundContactsFromService() {
    // given
    BDDMockito.given(contactService.findAllContacts()).willReturn(Lists.newArrayList(contactDto));

    // when
    List<ContactDto> contactDtos = contactResource.getContacts();

    // then
    Truth.assertThat(contactDtos).contains(contactDto);
  }

}