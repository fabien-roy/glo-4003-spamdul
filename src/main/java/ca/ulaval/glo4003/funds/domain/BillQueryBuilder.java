package ca.ulaval.glo4003.funds.domain;

public interface BillQueryBuilder<Query extends BillQuery> {

  BillQueryBuilder<Query> aBillQuery(); // TODO : À voir si nécessaire

  BillQueryBuilder<Query> withBillType(BillType billType);

  BillQueryBuilder<Query> withYear(int Year);

  BillQueryBuilder<Query> withNoAmountDue();
}
