package com.jcaa.usersmanagement.domain.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("EmailDestinationModel")
class EmailDestinationModelTest {

  private static final String EMAIL = "dest@example.com";
  private static final String NAME = "Recipient Name";
  private static final String SUBJECT = "Welcome";
  private static final String BODY = "Hello, welcome to the platform.";

  @Test
  @DisplayName("constructor should keep all fields when data is valid")
  void shouldCreateModelWithAllValidFields() {
    // correccion regla 11 usar estructura Arrange Act Assert

    // Arrange + Act
    final EmailDestinationModel model =
            new EmailDestinationModel(EMAIL, NAME, SUBJECT, BODY);

    // Assert
    assertAll(
            "EmailDestinationModel fields",
            () -> assertEquals(EMAIL, model.getDestinationEmail(), "destinationEmail"),
            () -> assertEquals(NAME, model.getDestinationName(), "destinationName"),
            () -> assertEquals(SUBJECT, model.getSubject(), "subject"),
            () -> assertEquals(BODY, model.getBody(), "body"));
  }

  @Test
  @DisplayName("constructor should throw NullPointerException when destinationEmail is null")
  void shouldThrowNpeWhenDestinationEmailIsNull() {
    // correccion regla 11 separar Act Assert

    // Act + Assert
    assertThrows(
            NullPointerException.class,
            () -> new EmailDestinationModel(null, NAME, SUBJECT, BODY));
  }

  @Test
  @DisplayName("constructor should throw IllegalArgumentException when destinationName is blank")
  void shouldThrowIaeWhenDestinationNameIsBlank() {
    // correccion regla 11 separar Act Assert

    // Act + Assert
    assertThrows(
            IllegalArgumentException.class,
            () -> new EmailDestinationModel(EMAIL, "   ", SUBJECT, BODY));
  }

  @Test
  @DisplayName("constructor should throw NullPointerException when subject is null")
  void shouldThrowNpeWhenSubjectIsNull() {
    // correccion regla 11 separar Act Assert

    // Act + Assert
    assertThrows(
            NullPointerException.class,
            () -> new EmailDestinationModel(EMAIL, NAME, null, BODY));
  }

  @Test
  @DisplayName("constructor should throw IllegalArgumentException when body is empty")
  void shouldThrowIaeWhenBodyIsEmpty() {
    // correccion regla 11 separar Act Assert

    // Act + Assert
    assertThrows(
            IllegalArgumentException.class,
            () -> new EmailDestinationModel(EMAIL, NAME, SUBJECT, ""));
  }
}