package ng.ourChemo.utils;

import ng.ourChemo.data.models.Drug;
import ng.ourChemo.data.models.User;
import ng.ourChemo.dtos.request.AddDrugRequest;
import ng.ourChemo.dtos.request.RegisterUserRequest;
import ng.ourChemo.dtos.request.UpdateDrugRequest;
import ng.ourChemo.dtos.response.*;

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

    public static Drug mapToDrug(AddDrugRequest addDrugRequest) {
        Drug drug = new Drug();
        drug.setName(addDrugRequest.getName());
        drug.setBrand(addDrugRequest.getBrand());
        drug.setPrice(addDrugRequest.getPrice());
        drug.setExpiryDate(addDrugRequest.getExpiryDate());
        return drug;
    }

    public static AddDrugResponse mapToAddDrugResponse(Drug drug) {
        AddDrugResponse addDrugResponse = new AddDrugResponse();
        addDrugResponse.setName(drug.getName());
        addDrugResponse.setBrand(drug.getBrand());
        addDrugResponse.setPrice(drug.getPrice());
        addDrugResponse.setExpiryDate(drug.getExpiryDate());
        return addDrugResponse;
    }

    public static void mapToUpdatedDrug(UpdateDrugRequest updateDrugRequest, Drug drug) {
        drug.setName(updateDrugRequest.getName());
        drug.setBrand(updateDrugRequest.getBrand());
        drug.setPrice(updateDrugRequest.getPrice());
        drug.setExpiryDate(updateDrugRequest.getExpiryDate());
    }

    public static UpdateDrugResponse mapToUpdateDrugResponse(Drug drug) {
        UpdateDrugResponse updateDrugResponse = new UpdateDrugResponse();
        updateDrugResponse.setName(drug.getName());
        updateDrugResponse.setBrand(drug.getBrand());
        updateDrugResponse.setPrice(drug.getPrice());
        updateDrugResponse.setExpiryDate(drug.getExpiryDate());
        return updateDrugResponse;
    }
}
