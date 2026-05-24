package com.jcaa.usersmanagement.domain.valueobject;

import static org.junit.jupiter.api.Assertions.*;

import com.jcaa.usersmanagement.domain.exception.InvalidUserNameException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayName("UserName")
class UserNameTest {

  @ParameterizedTest
  @ValueSource(strings = {"John Arrieta", "   John Arrieta   ", "John Arrieta \t"})
  @DisplayName("constructor should create UserName with trimmed value")
  void shouldValidateUserNameMinimumLength(final String userName) {

    // correccion regla 11 usar estructura Arrange Act Assert

    // Arrange
    final String expectedUserName =
            "John Arrieta";

    // Act
    final UserName userNameVo =
            new UserName(userName);

    // Assert
    // correccion regla 11 usar assertEquals en lugar de assertTrue(x.equals(y))
    assertEquals(
            expectedUserName,
            userNameVo.toString());
  }

  @Test
  @DisplayName("constructor should throw NullPointerException when value is null")
  void shouldValidateUserNameIsNotNull() {

    // correccion regla 11 agregar DisplayName y estructura AAA

    // Act + Assert
    assertThrows(
            NullPointerException.class,
            () -> new UserName(null));
  }

  @ParameterizedTest
  @ValueSource(
          strings = {
                  "",
                  "  ",
                  "\t",
                  "\n",
                  "\r",
                  "\f",
                  "\b",
                  "Jo",
                  "Ty  ",
                  "",
                  "   Cy ",
                  "Ed\t"
          })
  @DisplayName("constructor should throw InvalidUserNameException when value is invalid")
  void shouldValidateUserNameIsNotEmptyAndMinimumLength(final String userName) {

    // correccion regla 11 agregar estructura AAA

    // Act + Assert
    assertThrows(
            InvalidUserNameException.class,
            () -> new UserName(userName));
  }
}