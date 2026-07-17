package ng.ourChemo.utils;

import ng.ourChemo.data.models.User;
import ng.ourChemo.dtos.request.RegisterUserRequest;
import ng.ourChemo.dtos.response.LoginResponse;
import ng.ourChemo.dtos.response.LogoutResponse;
import ng.ourChemo.dtos.response.RegisterUserResponse;

public class Mapper {
    public static User mapUserRequestToUser(RegisterUserRequest registerUserRequest) {
        User user = new User();
        user.setFullName(registerUserRequest.getFullName());
        user.setUsername(registerUserRequest.getUsername());
        user.setPassword(registerUserRequest.getPassword());
        return user;
    }

    public static RegisterUserResponse mapUserToUserResponse(User user) {
        RegisterUserResponse userResponse = new RegisterUserResponse();
        userResponse.setFullName(user.getFullName());
        userResponse.setUsername(user.getUsername());
        return userResponse;
    }

    public static LoginResponse mapUserToLoginResponse(User user) {
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setUsername(user.getUsername());
        loginResponse.setLoggedIn(user.isLoggedIn());
        return loginResponse;
    }

    public static LogoutResponse mapUserToLogoutResponse(User user) {
        LogoutResponse logoutResponse = new LogoutResponse();
        logoutResponse.setLoggedIn(user.isLoggedIn());
        return logoutResponse;
    }
}
