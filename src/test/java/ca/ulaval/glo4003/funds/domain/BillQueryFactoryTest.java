package ca.ulaval.glo4003.funds.domain;

import static com.google.common.truth.Truth.assertThat;
import static org.mockito.Mockito.when;

import ca.ulaval.glo4003.funds.domain.queries.BillQueryParamAssembler;
import ca.ulaval.glo4003.funds.domain.queries.BillQueryParams;
import java.util.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class BillQueryFactoryTest {
  private BillQueryFactory billQueryFactory;

  @Mock private BillQueryBuilder billQueryBuilder;
  @Mock private BillQueryBuilder filteredBillQueryBuilder;
  @Mock private BillQueryParamAssembler firstQueryAssembler;
  @Mock private BillQuery query;
  @Mock private BillQuery filteredQuery;
  @Mock private BillQueryParams billQueryParams;

  private Set<BillQueryParamAssembler> queryParamAssemblers = new HashSet<>();

  @Before
  public void setUp() {
    queryParamAssemblers.add(firstQueryAssembler);

    when(billQueryBuilder.aBillQuery()).thenReturn(billQueryBuilder);
    when(billQueryBuilder.build()).thenReturn(query);
    when(filteredBillQueryBuilder.build()).thenReturn(filteredQuery);
    when(firstQueryAssembler.assemble(billQueryBuilder, billQueryParams))
        .thenReturn(filteredBillQueryBuilder);
  }

  @Test
  public void create_withoutAssembler_shouldCreateQuery() {
    billQueryFactory = new BillQueryFactory(billQueryBuilder, Collections.emptySet());

    BillQuery actualQuery = billQueryFactory.create(billQueryParams);

    assertThat(query).isEqualTo(actualQuery);
  }

  @Test
  public void create_withAssemblers_shouldCreateQuery() {
    billQueryFactory = new BillQueryFactory(billQueryBuilder, queryParamAssemblers);

    BillQuery actualQuery = billQueryFactory.create(billQueryParams);

    assertThat(filteredQuery).isSameInstanceAs(actualQuery);
  }
}
