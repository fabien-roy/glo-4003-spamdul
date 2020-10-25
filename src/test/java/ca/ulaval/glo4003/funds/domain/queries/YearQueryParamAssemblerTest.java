package ca.ulaval.glo4003.funds.domain.queries;

import static ca.ulaval.glo4003.funds.domain.queries.YearQueryParamAssembler.YEAR_PARAM;
import static ca.ulaval.glo4003.times.helpers.CustomDateTimeMother.createDateTime;
import static com.google.common.truth.Truth.assertThat;
import static org.mockito.Mockito.when;

import ca.ulaval.glo4003.funds.domain.BillQueryBuilder;
import java.util.Collections;
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

  private int A_YEAR = createDateTime().getYear();
  private BillQueryParams params = new BillQueryParams();

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
    params.add(YEAR_PARAM, Collections.singletonList(String.valueOf(A_YEAR)));

    BillQueryBuilder actualQueryBuilder = queryAssembler.assemble(queryBuilder, params);

    assertThat(assembledQueryBuilder).isEqualTo(actualQueryBuilder);
  }
}
