package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.controller;

import com.jcaa.usersmanagement.application.port.in.CreateUserUseCase;
import com.jcaa.usersmanagement.application.port.in.DeleteUserUseCase;
import com.jcaa.usersmanagement.application.port.in.GetAllUsersUseCase;
import com.jcaa.usersmanagement.application.port.in.GetUserByIdUseCase;
import com.jcaa.usersmanagement.application.port.in.LoginUseCase;
import com.jcaa.usersmanagement.application.port.in.UpdateUserUseCase;
import com.jcaa.usersmanagement.domain.model.UserModel;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.CreateUserRequest;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.LoginRequest;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.UpdateUserRequest;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.UserResponse;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.mapper.UserDesktopMapper;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public final class UserController {

  private final CreateUserUseCase createUserUseCase;
  private final UpdateUserUseCase updateUserUseCase;
  private final DeleteUserUseCase deleteUserUseCase;
  private final GetUserByIdUseCase getUserByIdUseCase;
  private final GetAllUsersUseCase getAllUsersUseCase;
  private final LoginUseCase loginUseCase;

  public List<UserResponse> listAllUsers() {

    // correccion regla 4 usar nombres descriptivos
    final List<UserModel> users =
            getAllUsersUseCase.execute();

    return UserDesktopMapper.toResponseList(users);
  }

  public UserResponse findUserById(final String id) {

    final var query =
            UserDesktopMapper.toGetByIdQuery(id);

    final UserModel user =
            getUserByIdUseCase.execute(query);

    return UserDesktopMapper.toResponse(user);
  }

  public UserResponse createUser(
          final CreateUserRequest request) {

    // correccion regla 9 usar mapper
    final var command =
            UserDesktopMapper.toCreateCommand(request);

    final UserModel user =
            createUserUseCase.execute(command);

    return UserDesktopMapper.toResponse(user);
  }

  public UserResponse updateUser(
          final UpdateUserRequest request) {

    final var command =
            UserDesktopMapper.toUpdateCommand(request);

    final UserModel user =
            updateUserUseCase.execute(command);

    return UserDesktopMapper.toResponse(user);
  }

  public void deleteUser(final String id) {

    // correccion regla 9 usar mapper
    final var command =
            UserDesktopMapper.toDeleteCommand(id);

    deleteUserUseCase.execute(command);
  }

  public UserResponse login(
          final LoginRequest request) {

    // correccion regla 9 usar mapper
    final var command =
            UserDesktopMapper.toLoginCommand(request);

    final UserModel user =
            loginUseCase.execute(command);

    return UserDesktopMapper.toResponse(user);
  }
}