package ca.ulaval.glo4003.funds.domain;

import java.util.List;

public interface BillQuery {

  List<Bill> execute();
}
