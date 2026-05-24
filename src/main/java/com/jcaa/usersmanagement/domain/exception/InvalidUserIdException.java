package com.jcaa.usersmanagement.domain.exception;

public final class InvalidUserIdException extends DomainException {

  // correccion regla 10 usar constantes y no textos hardcodeados
  private static final String EMPTY_USER_ID_MESSAGE =
          "The user id must not be empty.";

  private InvalidUserIdException(final String message) {
    super(message);
  }

  public static InvalidUserIdException becauseValueIsEmpty() {
    return new InvalidUserIdException(EMPTY_USER_ID_MESSAGE);
  }
}