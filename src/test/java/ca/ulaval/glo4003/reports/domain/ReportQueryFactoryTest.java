package ca.ulaval.glo4003.reports.domain;

import static com.google.common.truth.Truth.assertThat;
import static org.mockito.Mockito.when;

import ca.ulaval.glo4003.reports.domain.queryparams.ReportQueryParamAssembler;
import java.util.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ReportQueryFactoryTest {

  @Mock private ReportQueryBuilder reportQueryBuilder;
  @Mock private ReportQueryBuilder filteredReportQueryBuilder;
  @Mock private ReportQueryParamAssembler firstQueryAssembler;
  @Mock private ReportQuery query;
  @Mock private ReportQuery filteredQuery;

  private ReportQueryFactory reportQueryFactory;

  private final Map<String, List<String>> params = new HashMap<>();
  private Set<ReportQueryParamAssembler> queryParamAssemblers;

  @Before
  public void setUp() {
    queryParamAssemblers = new HashSet<>(Collections.singletonList(firstQueryAssembler));

    when(reportQueryBuilder.aReportQuery()).thenReturn(reportQueryBuilder);
    when(firstQueryAssembler.assemble(reportQueryBuilder, params))
        .thenReturn(filteredReportQueryBuilder);
    when(reportQueryBuilder.build()).thenReturn(query);
    when(filteredReportQueryBuilder.build()).thenReturn(filteredQuery);
  }

  @Test
  public void givenNoAssembler_whenCreating_thenCreateQuery() {
    reportQueryFactory = new ReportQueryFactory(reportQueryBuilder, Collections.emptySet());

    ReportQuery actualQuery = reportQueryFactory.create(params);

    assertThat(actualQuery).isSameInstanceAs(query);
  }

  @Test
  public void givenAssemblers_whenCreating_thenCreateQuery() {
    reportQueryFactory = new ReportQueryFactory(reportQueryBuilder, queryParamAssemblers);

    ReportQuery actualQuery = reportQueryFactory.create(params);

    assertThat(actualQuery).isSameInstanceAs(filteredQuery);
  }
}
