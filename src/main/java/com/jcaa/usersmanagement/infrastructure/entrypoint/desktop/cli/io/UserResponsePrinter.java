package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io;

import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.UserResponse;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class UserResponsePrinter {

  private static final String SEPARATOR =
          "-".repeat(52);

  private static final String ROW_FORMAT =
          "  %-10s : %s%n";

  // correccion regla 10 usar constantes
  private static final String NO_USERS_FOUND_MESSAGE =
          "  No users found.";

  private static final String UNKNOWN_STATUS_LABEL =
          "Estado desconocido";

  // correccion regla 16 evitar if else largos
  private static final Map<String, String> STATUS_LABELS =
          Map.of(
                  "ACTIVE", "Activo",
                  "INACTIVE", "Inactivo",
                  "PENDING", "Pendiente de activacion",
                  "BLOCKED", "Bloqueado",
                  "DELETED", "Eliminado");

  private final ConsoleIO console;

  public void print(final UserResponse response) {

    console.println(SEPARATOR);

    console.printf(
            ROW_FORMAT,
            "ID",
            response.getId());

    console.printf(
            ROW_FORMAT,
            "Name",
            response.getName());

    console.printf(
            ROW_FORMAT,
            "Email",
            response.getEmail());

    console.printf(
            ROW_FORMAT,
            "Role",
            response.getRole());

    console.printf(
            ROW_FORMAT,
            "Status",
            getStatusLabel(response.getStatus()));

    console.println(SEPARATOR);
  }

  public void printList(final List<UserResponse> users) {

    // correccion evitar null
    if (users == null || users.isEmpty()) {

      console.println(NO_USERS_FOUND_MESSAGE);

      return;
    }

    console.printf(
            "%n  Total: %d user(s)%n",
            users.size());

    users.forEach(this::print);
  }

  // correccion regla 27 simplificar lectura
  public void printSummary(final List<UserResponse> users) {

    if (users == null || users.isEmpty()) {

      console.println(NO_USERS_FOUND_MESSAGE);

      return;
    }

    final StringBuilder summary =
            new StringBuilder();

    for (final UserResponse user : users) {

      summary.append(
              String.format(
                      "  %s (%s)%n",
                      user.getName(),
                      getStatusLabel(user.getStatus())));
    }

    console.println(summary.toString());
  }

  // correccion regla 16 usar Map y evitar if else largos
  private static String getStatusLabel(
          final String status) {

    return STATUS_LABELS.getOrDefault(
            status,
            UNKNOWN_STATUS_LABEL);
  }
}