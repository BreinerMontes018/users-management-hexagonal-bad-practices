package com.jcaa.usersmanagement.application.service;

import com.jcaa.usersmanagement.domain.enums.UserRole;
import com.jcaa.usersmanagement.domain.enums.UserStatus;
import com.jcaa.usersmanagement.domain.model.UserModel;
import lombok.experimental.UtilityClass;

@UtilityClass
public class UserValidationUtils {

  private static final int MIN_PASSWORD_LENGTH = 8;

  private static final String STATUS_ACTIVE = "ACTIVE";
  private static final String STATUS_PENDING = "PENDING";

  public static boolean isUserActive(final UserModel user) {
    return user.getStatus() == UserStatus.ACTIVE;
  }

  public static boolean isAdmin(final UserModel user) {
    return user.getRole() == UserRole.ADMIN;
  }

  public static boolean isValidEmail(final String email) {
    if (email == null || email.isBlank()) {
      return false;
    }

    return email.contains("@") && email.contains(".");
  }

  public static boolean isValidPassword(final String password) {
    return password != null && password.length() >= MIN_PASSWORD_LENGTH;
  }

  public static boolean canPerformAction(
          final String userId,
          final String email,
          final String status,
          final int maxInactivityDays) {

    if (userId == null || userId.isBlank() || email == null || !email.contains("@")) {
      return false;
    }

    return (STATUS_ACTIVE.equals(status) || STATUS_PENDING.equals(status))
            && maxInactivityDays >= 0;
  }
}