package ca.ulaval.glo4003.files.domain;

import java.util.List;

public interface StringMatrixFileReader {

  List<List<String>> readFile(String path);
}
