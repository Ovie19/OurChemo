package ng.ourChemo.services;

import ng.ourChemo.data.repositories.UserRepository;
import ng.ourChemo.data.repositories.UserRepositoryImpl;
import ng.ourChemo.dtos.request.LoginRequest;
import ng.ourChemo.dtos.request.LogoutRequest;
import ng.ourChemo.dtos.request.RegisterUserRequest;
import ng.ourChemo.dtos.response.LoginResponse;
import ng.ourChemo.dtos.response.LogoutResponse;
import ng.ourChemo.dtos.response.RegisterUserResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AuthServicesTest {

    private AuthServices authServices;
    private final UserRepository userRepository = new UserRepositoryImpl();

    @BeforeEach
    void setUp() {
        authServices = new AuthServicesImpl();
        userRepository.deleteAll();
    }

    @Test
    public void registerChemist_usersCountIsOneTest() {
        RegisterUserRequest registerUserRequest = new RegisterUserRequest();
        registerUserRequest.setFullName("Lionel Messi");
        registerUserRequest.setUsername("Lionel_the_goat");
        registerUserRequest.setPassword("Mundial10");
        authServices.register(registerUserRequest);
        assertEquals(1, userRepository.count());
    }

    @Test
    public void registerChemist_returnsUserResponseWithUsernameAndFullNameTest() {
        RegisterUserRequest registerUserRequest = new RegisterUserRequest();
        registerUserRequest.setFullName("Lionel Messi");
        registerUserRequest.setUsername("Lionel_the_goat");
        registerUserRequest.setPassword("Mundial10");
        RegisterUserResponse userResponse = authServices.register(registerUserRequest);
        assertEquals("lionel_the_goat", userResponse.getUsername());
        assertEquals("Lionel Messi", userResponse.getFullName());
        assertEquals(1, userRepository.count());
    }

    @Test
    public void registerChemist_registerWithSameUsernameThrowsException_usersCountIsZeroTest() {
        RegisterUserRequest registerUserRequest = new RegisterUserRequest();
        registerUserRequest.setFullName("Lionel Messi");
        registerUserRequest.setUsername("Lionel_the_goat");
        registerUserRequest.setPassword("Mundial10");
        authServices.register(registerUserRequest);

        RegisterUserRequest registerUserRequest2 = new RegisterUserRequest();
        registerUserRequest2.setUsername("lionel_the_goat");
        registerUserRequest2.setFullName("Lionel Messi");
        registerUserRequest2.setPassword("123fsfah773");
        assertThrows(IllegalArgumentException.class, () -> authServices.register(registerUserRequest2));
        assertEquals(1, userRepository.count());
    }

    @Test
    public void registerChemist_login_isLoggedInIsTrueTest() {
        RegisterUserRequest registerUserRequest = new RegisterUserRequest();
        registerUserRequest.setFullName("Lionel Messi");
        registerUserRequest.setUsername("Lionel_the_goat");
        registerUserRequest.setPassword("Mundial10");
        authServices.register(registerUserRequest);

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("Lionel_the_goat");
        loginRequest.setPassword("Mundial10");
        LoginResponse loginResponse = authServices.login(loginRequest);
        assertTrue(loginResponse.isLoggedIn());
    }

    @Test
    public void registerChemist_loginWithInvalidPassword_throwExceptionTest() {
        RegisterUserRequest registerUserRequest = new RegisterUserRequest();
        registerUserRequest.setFullName("Chemist One");
        registerUserRequest.setUsername("Chemist_one");
        registerUserRequest.setPassword("password");
        authServices.register(registerUserRequest);

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("Chemist_one");
        loginRequest.setPassword("PASSWORD");
        assertThrows(IllegalArgumentException.class, () -> authServices.login(loginRequest));
    }

    @Test
    public void registerChemist_loginWithInvalidUsername_throwExceptionTest() {
        RegisterUserRequest registerUserRequest = new RegisterUserRequest();
        registerUserRequest.setFullName("Chemist One");
        registerUserRequest.setUsername("Chemist_one");
        registerUserRequest.setPassword("password");
        authServices.register(registerUserRequest);

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("Chemist_on");
        loginRequest.setPassword("password");
        assertThrows(IllegalArgumentException.class, () -> authServices.login(loginRequest));
    }

    @Test
    public void registerChemist_login_logout_isLoggedInIsFalseTest() {
        RegisterUserRequest registerUserRequest = new RegisterUserRequest();
        registerUserRequest.setFullName("Chemist One");
        registerUserRequest.setUsername("Chemist_one");
        registerUserRequest.setPassword("password");
        authServices.register(registerUserRequest);

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("Chemist_one");
        loginRequest.setPassword("password");
        authServices.login(loginRequest);

        LogoutRequest logoutRequest = new LogoutRequest();
        logoutRequest.setUserName("Chemist_one");
        LogoutResponse logoutResponse = authServices.logout(logoutRequest);

        assertFalse(logoutResponse.isLoggedIn());
    }

    @Test
    public void registerChemist_login_logoutWithInvalidUsernameThrowsExceptionTest() {
        RegisterUserRequest registerUserRequest = new RegisterUserRequest();
        registerUserRequest.setFullName("Chemist One");
        registerUserRequest.setUsername("Chemist_one");
        registerUserRequest.setPassword("password");
        authServices.register(registerUserRequest);

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("Chemist_one");
        loginRequest.setPassword("password");
        authServices.login(loginRequest);

        LogoutRequest logoutRequest = new LogoutRequest();
        logoutRequest.setUserName("Chemist_on");
        assertThrows(IllegalArgumentException.class ,() ->authServices.logout(logoutRequest));
    }
}