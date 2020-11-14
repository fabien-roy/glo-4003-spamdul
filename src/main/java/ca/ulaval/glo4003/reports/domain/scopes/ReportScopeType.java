package ca.ulaval.glo4003.reports.domain.scopes;

import ca.ulaval.glo4003.reports.exceptions.InvalidReportScopeException;
import java.util.HashMap;
import java.util.Map;

public enum ReportScopeType {
  YEARLY("yearly"),
  MONTHLY("monthly"),
  DAILY("daily");

  private final String scope;
  private static final Map<String, ReportScopeType> lookup = new HashMap<>();

  static {
    for (ReportScopeType scopeType : ReportScopeType.values()) {
      lookup.put(scopeType.toString(), scopeType);
    }
  }

  ReportScopeType(String scope) {
    this.scope = scope;
  }

  @Override
  public String toString() {
    return scope;
  }

  public static ReportScopeType get(String scope) {
    ReportScopeType foundScope = lookup.get(scope);

    if (foundScope == null) throw new InvalidReportScopeException();

    return foundScope;
  }
}
