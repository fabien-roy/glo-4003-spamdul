package ca.ulaval.glo4003.access.domain;

import static ca.ulaval.glo4003.access.helpers.AccessPassBuilder.anAccessPass;
import static ca.ulaval.glo4003.access.helpers.AccessPassMother.createAccessPassCode;

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

    Truth.assertThat(accessPass.getCode()).isSameInstanceAs(ACCESS_PASS_CODE);
  }
}
