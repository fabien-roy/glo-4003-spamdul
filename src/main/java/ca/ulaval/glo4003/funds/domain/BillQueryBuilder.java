package ca.ulaval.glo4003.funds.domain;

public interface BillQueryBuilder<Query extends BillQuery> {

  BillQueryBuilder<Query> aBillQuery();

  BillQueryBuilder<Query> withBillType(BillType billType);

  BillQueryBuilder<Query> withYear(int Year);

  Query build();
}
