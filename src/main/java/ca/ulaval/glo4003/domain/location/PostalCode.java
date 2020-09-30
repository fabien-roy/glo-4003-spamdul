package ca.ulaval.glo4003.domain.location;

public class PostalCode {
  private final String code;

  public PostalCode(String code) {
    this.code = code;
  }

  @Override
  public String toString() {
    return code;
  }

  @Override
  public boolean equals(Object object) {
    if (object == null || getClass() != object.getClass()) return false;

    PostalCode postalCode = (PostalCode) object;

    return code.equals(postalCode.toString());
  }

  @Override
  public int hashCode() {
    return code.hashCode();
  }
}
