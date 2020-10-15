package ca.ulaval.glo4003.funds.domain;

import static com.google.common.truth.Truth.assertThat;
import static org.mockito.Mockito.when;

import ca.ulaval.glo4003.funds.domain.queries.BillQueryParamAssembler;
import ca.ulaval.glo4003.funds.domain.queries.BillTypeQueryParamAssembler;
import java.util.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class BillQueryFactoryTest {
  private static BillQueryFactory billQueryFactory;

  @Mock private static BillQueryBuilder billQueryBuilder;
  @Mock private static BillQueryBuilder filteredBillQueryBuilder;
  @Mock private BillTypeQueryParamAssembler firstQueryAssembler;
  @Mock private BillQuery query;
  @Mock private BillQuery filteredQuery;

  private Map<String, List<String>> params = new HashMap<>();

  private Set<BillQueryParamAssembler> queryParamAssemblers =
      new HashSet<>(Collections.singletonList(firstQueryAssembler));

  @Before
  public void setUpMocks() {
    when(billQueryBuilder.aBillQuery()).thenReturn(billQueryBuilder);
    when(billQueryBuilder.build()).thenReturn(query);
    when(filteredBillQueryBuilder.build()).thenReturn(filteredQuery);
    when(firstQueryAssembler.assemble(billQueryBuilder, params))
        .thenReturn(filteredBillQueryBuilder);
  }

  @Test
  public void create_withoutAssembler_shouldCreateQuery() {
    billQueryFactory = new BillQueryFactory(billQueryBuilder, Collections.emptySet());

    BillQuery actualQuery = billQueryFactory.create(params);

    assertThat(query).isEqualTo(actualQuery);
  }

  @Test
  public void create_withAssemblers_shouldCreateQuery() {
    billQueryFactory = new BillQueryFactory(billQueryBuilder, queryParamAssemblers);

    BillQuery actualQuery = billQueryFactory.create(params);

    assertThat(filteredQuery).isEqualTo(actualQuery);
  }
}
