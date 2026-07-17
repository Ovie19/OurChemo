package ng.ourChemo.services;

import ng.ourChemo.data.models.User;
import ng.ourChemo.data.repositories.UserRepository;
import ng.ourChemo.data.repositories.UserRepositoryImpl;
import ng.ourChemo.dtos.request.LoginRequest;
import ng.ourChemo.dtos.request.LogoutRequest;
import ng.ourChemo.dtos.request.RegisterUserRequest;
import ng.ourChemo.dtos.response.LoginResponse;
import ng.ourChemo.dtos.response.LogoutResponse;
import ng.ourChemo.dtos.response.RegisterUserResponse;
import static ng.ourChemo.utils.Mapper.*;

public class AuthServicesImpl implements AuthServices {

    private final UserRepository userRepository = new UserRepositoryImpl();

    @Override
    public RegisterUserResponse register(RegisterUserRequest registerUserRequest) {
        registerUserRequest.setUsername(registerUserRequest.getUsername().toLowerCase());
        if (userRepository.findByUserName(registerUserRequest.getUsername()) != null)
            throw new IllegalArgumentException("Username already exists");

        User user = mapUserRequestToUser(registerUserRequest);
        userRepository.save(user);
        return mapUserToUserResponse(user);
    }

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        loginRequest.setUsername(loginRequest.getUsername().toLowerCase());
        User foundUser = userRepository.findByUserName(loginRequest.getUsername());

        if (foundUser == null)
            throw new IllegalArgumentException("Username not found");

        if  (!foundUser.getPassword().equals(loginRequest.getPassword()))
            throw new IllegalArgumentException("Invalid credentials");

        foundUser.setLoggedIn(true);
        return mapUserToLoginResponse(foundUser);
    }

    @Override
    public LogoutResponse logout(LogoutRequest logoutRequest) {
        logoutRequest.setUserName(logoutRequest.getUsername().toLowerCase());
        User foundUser = userRepository.findByUserName(logoutRequest.getUsername());
        if  (foundUser == null)
            throw new IllegalArgumentException("Username not found");

        foundUser.setLoggedIn(false);
        return mapUserToLogoutResponse(foundUser);
    }
}
