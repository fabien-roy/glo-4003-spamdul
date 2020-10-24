package ca.ulaval.glo4003.funds.domain.queries;

import static ca.ulaval.glo4003.funds.domain.queries.YearQueryParamAssembler.YEAR_PARAM;
import static com.google.common.truth.Truth.assertThat;
import static org.mockito.Mockito.when;

import ca.ulaval.glo4003.funds.domain.BillQueryBuilder;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class YearQueryParamAssemblerTest {

  private static BillQueryParamAssembler queryAssembler;
  @Mock private static BillQueryBuilder queryBuilder;
  @Mock private static BillQueryBuilder assembledQueryBuilder;

  private static final int A_YEAR = 2020; // TODO : Add to Bill Object Mother
  private Map<String, List<String>> params = new HashMap<>();

  @Before
  public void setUp() {
    queryAssembler = new YearQueryParamAssembler();
    when(queryBuilder.withYear(A_YEAR)).thenReturn(assembledQueryBuilder);
  }

  @Test
  public void assemble_withoutYear_shouldReturnSameBuilder() {
    BillQueryBuilder actualQueryBuilder = queryAssembler.assemble(queryBuilder, params);

    assertThat(queryBuilder).isEqualTo(actualQueryBuilder);
  }

  @Test
  public void assemble_withYear_shouldAssembleBuilder() {
    params.put(YEAR_PARAM, Collections.singletonList(String.valueOf(A_YEAR)));

    BillQueryBuilder actualQueryBuilder = queryAssembler.assemble(queryBuilder, params);

    assertThat(assembledQueryBuilder).isEqualTo(actualQueryBuilder);
  }
}
