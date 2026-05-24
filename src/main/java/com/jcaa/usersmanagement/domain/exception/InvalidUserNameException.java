package com.jcaa.usersmanagement.domain.exception;

public final class InvalidUserNameException extends DomainException {

  // correccion regla 10 usar constantes y no textos hardcodeados
  private static final String EMPTY_USER_NAME_MESSAGE =
          "The user name must not be empty.";

  private static final String SHORT_USER_NAME_MESSAGE =
          "The user name must have at least %d characters.";

  private InvalidUserNameException(final String message) {
    super(message);
  }

  public static InvalidUserNameException becauseValueIsEmpty() {
    return new InvalidUserNameException(EMPTY_USER_NAME_MESSAGE);
  }

  public static InvalidUserNameException becauseLengthIsTooShort(
          final int minimumLength) {

    return new InvalidUserNameException(
            String.format(SHORT_USER_NAME_MESSAGE, minimumLength));
  }
}