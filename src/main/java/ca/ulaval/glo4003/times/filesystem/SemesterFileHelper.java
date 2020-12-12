package ca.ulaval.glo4003.times.filesystem;

import ca.ulaval.glo4003.files.domain.StringFileReader;
import ca.ulaval.glo4003.files.domain.exceptions.InvalidFileException;
import ca.ulaval.glo4003.times.services.dto.SemesterDto;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.List;

public class SemesterFileHelper {
  private static final String SEMESTERS_PATH = "data/semesters.json";

  private final StringFileReader fileReader;

  public SemesterFileHelper(StringFileReader fileReader) {
    this.fileReader = fileReader;
  }

  public List<SemesterDto> getSemesters() {
    ObjectMapper objectMapper = new ObjectMapper();

    try {
      String jsonData = fileReader.readFile(SEMESTERS_PATH);
      return objectMapper.readValue(jsonData, new TypeReference<List<SemesterDto>>() {});
    } catch (IOException exception) {
      throw new InvalidFileException(SEMESTERS_PATH);
    }
  }
}
