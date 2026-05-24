package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli;

import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler.CreateUserHandler;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler.DeleteUserHandler;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler.FindUserByIdHandler;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler.ListUsersHandler;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler.LoginHandler;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler.OperationHandler;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler.UpdateUserHandler;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.ConsoleIO;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.UserResponsePrinter;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.menu.MenuOption;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.controller.UserController;
import jakarta.validation.ConstraintViolationException;
import java.util.Map;
import java.util.Optional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class UserManagementCli {

  private static final String BANNER =
          """
          ==========================================
               Users Management System
          ==========================================""";

  private static final String MENU_BORDER =
          "  ==========================================";

  // correccion regla 10 usar constantes
  private static final String INVALID_OPTION_MESSAGE =
          "  Invalid option. Please try again.";

  private static final String GOODBYE_MESSAGE =
          "\n  Goodbye!\n";

  private static final String VALIDATION_ERRORS_MESSAGE =
          "  Validation errors:";

  private static final String UNEXPECTED_ERROR_MESSAGE =
          "  Unexpected error: ";

  private static final String MAIN_MENU_TITLE =
          "    Main Menu";

  private final UserController userController;
  private final ConsoleIO console;

  public void start() {

    console.println(BANNER);

    final UserResponsePrinter printer =
            new UserResponsePrinter(console);

    runLoop(buildHandlers(printer));
  }

  private void runLoop(
          final Map<MenuOption, OperationHandler> handlers) {

    boolean running = true;

    while (running) {

      printMenu();

      final int choice =
              console.readInt("\n  Option: ");

      final Optional<MenuOption> option =
              MenuOption.fromNumber(choice);

      if (option.isEmpty()) {

        console.println(INVALID_OPTION_MESSAGE);

      } else if (option.get() == MenuOption.EXIT) {

        console.println(GOODBYE_MESSAGE);

        running = false;

      } else {

        executeHandler(
                handlers,
                option.get());
      }
    }
  }

  private void executeHandler(
          final Map<MenuOption, OperationHandler> handlers,
          final MenuOption option) {

    try {

      handlers.get(option).handle();

    } catch (final ConstraintViolationException exception) {

      console.println(VALIDATION_ERRORS_MESSAGE);

      exception.getConstraintViolations()
              .forEach(
                      violation ->
                              console.println(
                                      "    - " + violation.getMessage()));

    } catch (final RuntimeException exception) {

      console.println(
              UNEXPECTED_ERROR_MESSAGE
                      + exception.getMessage());
    }
  }

  private Map<MenuOption, OperationHandler> buildHandlers(
          final UserResponsePrinter printer) {

    return Map.of(
            MenuOption.LIST_USERS,
            new ListUsersHandler(
                    userController,
                    printer),

            MenuOption.FIND_USER,
            new FindUserByIdHandler(
                    userController,
                    console,
                    printer),

            MenuOption.CREATE_USER,
            new CreateUserHandler(
                    userController,
                    console,
                    printer),

            MenuOption.UPDATE_USER,
            new UpdateUserHandler(
                    userController,
                    console,
                    printer),

            MenuOption.DELETE_USER,
            new DeleteUserHandler(
                    userController,
                    console),

            MenuOption.LOGIN,
            new LoginHandler(
                    userController,
                    console,
                    printer));
  }

  private void printMenu() {

    console.println();

    // correccion regla 10 reutilizar constante
    console.println(MENU_BORDER);

    console.println(MAIN_MENU_TITLE);

    console.println(MENU_BORDER);

    // correccion regla 4 usar nombres descriptivos
    for (final MenuOption option : MenuOption.values()) {

      console.printf(
              "    [%d] %s%n",
              option.getNumber(),
              option.getDescription());
    }

    console.println(MENU_BORDER);
  }
}