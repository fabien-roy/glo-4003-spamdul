package ca.ulaval.glo4003.files.domain;

import java.util.List;

public interface StringMatrixFileHelper {

  List<List<String>> readFile(String path);
}
