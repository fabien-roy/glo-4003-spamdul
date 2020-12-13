package ca.ulaval.glo4003.accesspasses.domain;

import static ca.ulaval.glo4003.accesspasses.helpers.AccessPassBuilder.anAccessPass;
import static ca.ulaval.glo4003.accesspasses.helpers.AccessPassMother.createAccessPassCode;

import com.google.common.truth.Truth;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class AccessPassFactoryTest {
  @Mock private AccessPassCodeGenerator accessPassCodeGenerator;

  private AccessPassFactory accessPassFactory;

  private static final AccessPassCode ACCESS_PASS_CODE = createAccessPassCode();
  private final AccessPass accessPass = anAccessPass().build();

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
