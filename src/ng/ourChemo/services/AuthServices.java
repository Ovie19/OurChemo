package ng.ourChemo.services;

import ng.ourChemo.dtos.request.LoginRequest;
import ng.ourChemo.dtos.request.LogoutRequest;
import ng.ourChemo.dtos.request.RegisterUserRequest;
import ng.ourChemo.dtos.response.LoginResponse;
import ng.ourChemo.dtos.response.LogoutResponse;
import ng.ourChemo.dtos.response.RegisterUserResponse;

public interface AuthServices {

    RegisterUserResponse register(RegisterUserRequest registerUserRequest);

    LoginResponse login(LoginRequest loginRequest);

    LogoutResponse logout(LogoutRequest logoutRequest);
}
