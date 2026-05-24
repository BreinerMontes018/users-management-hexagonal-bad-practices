package com.jcaa.usersmanagement.domain.valueobject;

import static org.junit.jupiter.api.Assertions.*;

import com.jcaa.usersmanagement.domain.exception.InvalidUserIdException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayName("UserId")
class UserIdTest {

  @ParameterizedTest
  @ValueSource(strings = {" user123 ", "  user123  ", "user123\t"})
  @DisplayName("constructor should create UserId with trimmed value")
  void shouldCreateUserIdWithTrimmedValue(final String input) {

    // correccion regla 11 usar estructura Arrange Act Assert

    // Arrange
    final String expectedUserId = "user123";

    // Act
    final UserId userId =
            new UserId(input);

    // Assert
    // correccion regla 11 usar assertEquals en lugar de assertTrue(x.equals(y))
    assertEquals(
            expectedUserId,
            userId.toString());
  }

  @Test
  @DisplayName("constructor should throw NullPointerException when value is null")
  void shouldThrowNullPointerExceptionWhenUserIdIsNull() {

    // correccion regla 11 agregar DisplayName y estructura AAA

    // Act + Assert
    assertThrows(
            NullPointerException.class,
            () -> new UserId(null));
  }

  @ParameterizedTest
  @ValueSource(strings = {"", "   ", "\t", "\n", "\r", "\f", "\b"})
  @DisplayName("constructor should throw InvalidUserIdException when value is empty")
  void shouldThrowInvalidUserIdExceptionWhenUserIdIsEmpty(final String input) {

    // correccion regla 11 agregar estructura AAA

    // Act + Assert
    assertThrows(
            InvalidUserIdException.class,
            () -> new UserId(input));
  }
}