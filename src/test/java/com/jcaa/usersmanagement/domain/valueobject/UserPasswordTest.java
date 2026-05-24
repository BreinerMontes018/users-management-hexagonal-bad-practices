package com.jcaa.usersmanagement.domain.valueobject;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import com.jcaa.usersmanagement.domain.exception.InvalidUserPasswordException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayName("UserPassword")
class UserPasswordTest {

  @ParameterizedTest
  @ValueSource(strings = {"password123", "   password123   "})
  @DisplayName("fromPlainText() should normalize and hash password")
  void shouldNormalizeAndHashPassword(final String input) {

    // correccion regla 11 usar estructura Arrange Act Assert

    // Act
    final UserPassword result =
            UserPassword.fromPlainText(input);

    // Assert
    // correccion regla 11 usar assertNotNull en lugar de assertTrue(x != null)
    assertNotNull(result.value());

    assertNotEquals(
            input.trim(),
            result.value());
  }

  @ParameterizedTest
  @ValueSource(strings = {"clave", "    clave     "})
  @DisplayName("fromPlainText() should throw exception when password is too short")
  void shouldFailWhenPasswordIsTooShort(final String password) {

    // correccion regla 11 usar estructura AAA

    // Act + Assert
    assertThrows(
            InvalidUserPasswordException.class,
            () -> UserPassword.fromPlainText(password));
  }

  @ParameterizedTest
  @ValueSource(strings = {"", "  ", "\r", "\t", "\n", "\f", "\b", "\0"})
  @DisplayName("fromPlainText() should throw exception when password is blank")
  void shouldThrowWhenPasswordIsEmptyOrBlank(final String password) {

    // correccion regla 11 usar estructura AAA

    // Act + Assert
    assertThrows(
            InvalidUserPasswordException.class,
            () -> UserPassword.fromPlainText(password));
  }

  @Test
  @DisplayName("fromPlainText() should throw NullPointerException when password is null")
  void shouldThrowWhenPasswordIsNull() {

    // correccion regla 11 agregar estructura AAA

    // Act + Assert
    assertThrows(
            NullPointerException.class,
            () -> UserPassword.fromPlainText(null));
  }

  @Test
  @DisplayName("verifyPlain() should validate original plain password")
  void shouldVerifyPlainPassword() {

    // Arrange
    final String plainPassword =
            "mySecurePassword";

    // Act
    final UserPassword userPassword =
            UserPassword.fromPlainText(plainPassword);

    // Assert
    assertTrue(
            userPassword.verifyPlain(plainPassword));
  }

  @Test
  @DisplayName("fromHash() should create UserPassword from existing hash")
  void shouldCreateUserPasswordFromExistingHash() {

    // Arrange
    final String rawPassword =
            "Abcde1234567";

    final UserPassword originalUserPassword =
            UserPassword.fromPlainText(rawPassword);

    final String generatedHash =
            originalUserPassword.value();

    // Act
    final UserPassword fromHashUserPassword =
            UserPassword.fromHash(generatedHash);

    // Assert
    assertEquals(
            originalUserPassword,
            fromHashUserPassword);

    assertTrue(
            fromHashUserPassword.verifyPlain(rawPassword));
  }

  @Test
  @DisplayName("equals() should return false when object is not UserPassword")
  void shouldReturnFalseWhenOtherIsNotInstanceOfUserPassword() {

    // Arrange
    final UserPassword password =
            UserPassword.fromPlainText("MiPassword123");

    final Object nonUserPassword =
            mock(Object.class);

    // Act + Assert
    assertNotEquals(
            password,
            nonUserPassword);
  }

  @Test
  @DisplayName("equals() should return false when hash is different")
  void shouldReturnFalseWhenDifferentHash() {

    // Arrange
    final UserPassword firstPassword =
            UserPassword.fromPlainText("MiPassword123");

    final UserPassword secondPassword =
            UserPassword.fromPlainText("OtroPassword456");

    // Act + Assert
    assertNotEquals(
            firstPassword,
            secondPassword);
  }

  @Test
  @DisplayName("hashCode() should return consistent value for same instance")
  void shouldReturnConsistentHashCode() {

    // Arrange
    final UserPassword password =
            UserPassword.fromPlainText("MiPassword123");

    // Act
    final int firstHashCode =
            password.hashCode();

    final int secondHashCode =
            password.hashCode();

    // Assert
    assertEquals(
            firstHashCode,
            secondHashCode);
  }

  @Test
  @DisplayName("hashCode() should return same value for equal objects")
  void shouldHaveSameHashCodeWhenEqual() {

    // Arrange
    final UserPassword firstPassword =
            UserPassword.fromPlainText("MiPassword123");

    final UserPassword secondPassword =
            UserPassword.fromHash(firstPassword.value());

    // Act + Assert
    assertEquals(
            firstPassword,
            secondPassword);

    assertEquals(
            firstPassword.hashCode(),
            secondPassword.hashCode());
  }
}