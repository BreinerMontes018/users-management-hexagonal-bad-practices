package com.jcaa.usersmanagement.domain.exception;

public final class UserAlreadyExistsException extends DomainException {

  // correccion regla 10 usar constantes y no textos hardcodeados
  private static final String USER_ALREADY_EXISTS_MESSAGE =
          "A user with email '%s' already exists.";

  private UserAlreadyExistsException(final String message) {
    super(message);
  }

  public static UserAlreadyExistsException becauseEmailAlreadyExists(
          final String email) {

    return new UserAlreadyExistsException(
            String.format(USER_ALREADY_EXISTS_MESSAGE, email));
  }
}