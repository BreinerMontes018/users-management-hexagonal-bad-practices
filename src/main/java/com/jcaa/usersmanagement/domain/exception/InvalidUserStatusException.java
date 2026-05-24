package com.jcaa.usersmanagement.domain.exception;

public final class InvalidUserStatusException extends DomainException {

  // correccion regla 10 usar constantes y no textos hardcodeados
  private static final String INVALID_USER_STATUS_MESSAGE =
          "The user status '%s' is not valid.";

  private InvalidUserStatusException(final String message) {
    super(message);
  }

  public static InvalidUserStatusException becauseValueIsInvalid(
          final String status) {

    return new InvalidUserStatusException(
            String.format(INVALID_USER_STATUS_MESSAGE, status));
  }
}