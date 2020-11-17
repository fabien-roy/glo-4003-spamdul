package ca.ulaval.glo4003.reports.assemblers;

import static com.google.common.truth.Truth.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import ca.ulaval.glo4003.reports.api.dto.ReportPeriodDataDto;
import ca.ulaval.glo4003.reports.api.dto.ReportPeriodDto;
import ca.ulaval.glo4003.reports.domain.ReportPeriod;
import ca.ulaval.glo4003.reports.domain.ReportPeriodData;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ReportPeriodAssemblerTest {

  @Mock private ReportPeriodDataAssembler reportPeriodDataAssembler;
  @Mock private ReportPeriod period;
  @Mock private ReportPeriod otherPeriod;

  private ReportPeriodAssembler reportPeriodAssembler;

  private final String periodName = "periodName";
  private final String otherPeriodName = "otherPeriodName";
  private final List<ReportPeriodData> data =
      Collections.singletonList(mock(ReportPeriodData.class));
  private final List<ReportPeriodData> otherData =
      Collections.singletonList(mock(ReportPeriodData.class));
  private final List<ReportPeriodDataDto> dataDtos =
      Collections.singletonList(mock(ReportPeriodDataDto.class));
  private final List<ReportPeriodDataDto> otherDataDtos =
      Arrays.asList(mock(ReportPeriodDataDto.class), mock(ReportPeriodDataDto.class));
  private List<ReportPeriod> singlePeriod;
  private List<ReportPeriod> multiplePeriods;

  @Before
  public void setUp() {
    reportPeriodAssembler = new ReportPeriodAssembler(reportPeriodDataAssembler);

    singlePeriod = Collections.singletonList(period);
    multiplePeriods = Arrays.asList(period, otherPeriod);

    when(period.getName()).thenReturn(periodName);
    when(otherPeriod.getName()).thenReturn(otherPeriodName);
    when(period.getData()).thenReturn(data);
    when(otherPeriod.getData()).thenReturn(otherData);
    when(reportPeriodDataAssembler.assembleMany(data)).thenReturn(dataDtos);
    when(reportPeriodDataAssembler.assembleMany(otherData)).thenReturn(otherDataDtos);
  }

  @Test
  public void givenSinglePeriod_whenAssemblingMany_thenAssemblePeriod() {
    List<ReportPeriodDto> dtos = reportPeriodAssembler.assembleMany(singlePeriod);

    assertThat(dtos).hasSize(1);
  }

  @Test
  public void givenMultiplePeriods_whenAssemblingMany_thenAssemblePeriods() {
    List<ReportPeriodDto> dtos = reportPeriodAssembler.assembleMany(multiplePeriods);

    assertThat(dtos).hasSize(2);
  }

  @Test
  public void givenSinglePeriod_whenAssemblingMany_thenSetPeriodName() {
    List<ReportPeriodDto> dtos = reportPeriodAssembler.assembleMany(singlePeriod);

    assertThat(dtos.get(0).period).isEqualTo(periodName);
  }

  @Test
  public void givenMultiplePeriods_whenAssemblingMany_thenSetPeriodNames() {
    List<ReportPeriodDto> dtos = reportPeriodAssembler.assembleMany(multiplePeriods);

    assertThat(dtos.get(0).period).isEqualTo(periodName);
    assertThat(dtos.get(1).period).isEqualTo(otherPeriodName);
  }

  @Test
  public void givenSinglePeriod_whenAssemblingMany_thenAssembleData() {
    List<ReportPeriodDto> dtos = reportPeriodAssembler.assembleMany(singlePeriod);

    assertThat(dtos.get(0).data).hasSize(dataDtos.size());
    assertThat(dtos.get(0).data.get(0)).isSameInstanceAs(dataDtos.get(0));
  }

  @Test
  public void givenMultiplePeriods_whenAssemblingMany_thenAssembleData() {
    List<ReportPeriodDto> dtos = reportPeriodAssembler.assembleMany(multiplePeriods);

    assertThat(dtos.get(0).data).hasSize(dataDtos.size());
    assertThat(dtos.get(1).data).hasSize(otherDataDtos.size());
    assertThat(dtos.get(0).data.get(0)).isSameInstanceAs(dataDtos.get(0));
    assertThat(dtos.get(1).data.get(0)).isSameInstanceAs(otherDataDtos.get(0));
    assertThat(dtos.get(1).data.get(1)).isSameInstanceAs(otherDataDtos.get(1));
  }
}
