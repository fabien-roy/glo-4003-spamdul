package ca.ulaval.glo4003.accessPass.domain;

import static ca.ulaval.glo4003.accessPass.helper.AccessPassBuilder.anAccessPass;
import static ca.ulaval.glo4003.accessPass.helper.AccessPassMother.createAccessPassCode;

import ca.ulaval.glo4003.access.domain.AccessPass;
import ca.ulaval.glo4003.access.domain.AccessPassCode;
import ca.ulaval.glo4003.access.domain.AccessPassCodeGenerator;
import ca.ulaval.glo4003.access.domain.AccessPassFactory;
import com.google.common.truth.Truth;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class AccessPassFactoryTest {
  private AccessPassFactory accessPassFactory;

  private static final AccessPassCode ACCESS_PASS_CODE = createAccessPassCode();
  private AccessPass accessPass = anAccessPass().build();

  @Mock private AccessPassCodeGenerator accessPassCodeGenerator;

  @Before
  public void setUp() {
    accessPassFactory = new AccessPassFactory(accessPassCodeGenerator);

    BDDMockito.given(accessPassCodeGenerator.generate()).willReturn(ACCESS_PASS_CODE);
  }

  @Test
  public void whenCreating_thenGeneratorCode() {
    accessPassFactory.create(accessPass);

    Truth.assertThat(accessPass.getAccessPassCode()).isSameInstanceAs(ACCESS_PASS_CODE);
  }
}
