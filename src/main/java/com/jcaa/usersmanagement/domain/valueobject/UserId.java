package com.jcaa.usersmanagement.domain.valueobject;

import com.jcaa.usersmanagement.domain.exception.InvalidUserIdException;
import java.util.Objects;

public record UserId(String value) {

  public UserId {

    // correccion regla 4 usar Objects.requireNonNull
    final String normalizedValue =
            Objects.requireNonNull(value, "UserId cannot be null")
                    .trim();

    validateNotEmpty(normalizedValue);

    value = normalizedValue;
  }

  private static void validateNotEmpty(final String normalizedValue) {

    if (normalizedValue.isEmpty()) {
      throw InvalidUserIdException.becauseValueIsEmpty();
    }
  }

  @Override
  public String toString() {
    return value;
  }
}