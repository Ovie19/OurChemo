package ng.ourChemo.controllers;

import ng.ourChemo.dtos.request.LoginRequest;
import ng.ourChemo.dtos.request.LogoutRequest;
import ng.ourChemo.dtos.request.RegisterUserRequest;
import ng.ourChemo.dtos.response.LoginResponse;
import ng.ourChemo.dtos.response.LogoutResponse;
import ng.ourChemo.dtos.response.RegisterUserResponse;
import ng.ourChemo.services.AuthServices;
import ng.ourChemo.services.AuthServicesImpl;

public class AuthController {

    AuthServices authServices = new AuthServicesImpl();

    public RegisterUserResponse registerChemist(RegisterUserRequest registerUserRequest) {
        return authServices.register(registerUserRequest);
    }

    public LoginResponse login(LoginRequest loginRequest) {
        return authServices.login(loginRequest);
    }

    public LogoutResponse logout(LogoutRequest logoutRequest) {
        return authServices.logout(logoutRequest);
    }
}
