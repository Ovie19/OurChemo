package ng.ourChemo.services;

import ng.ourChemo.data.models.Drug;
import ng.ourChemo.data.repositories.DrugRepository;
import ng.ourChemo.data.repositories.DrugRepositoryImpl;
import ng.ourChemo.dtos.request.AddDrugRequest;
import ng.ourChemo.dtos.request.DeleteDrugRequest;
import ng.ourChemo.dtos.request.DispenseDrugsRequest;
import ng.ourChemo.dtos.request.UpdateDrugRequest;
import ng.ourChemo.dtos.response.AddDrugResponse;
import ng.ourChemo.dtos.response.DeleteDrugResponse;
import ng.ourChemo.dtos.response.DispenseDrugsResponse;
import ng.ourChemo.dtos.response.UpdateDrugResponse;

import java.math.BigDecimal;
import java.time.LocalDate;

import static ng.ourChemo.utils.Mapper.*;

public class ChemistServicesImpl implements ChemistServices {

    private DrugRepository drugRepository = new DrugRepositoryImpl();

    @Override
    public AddDrugResponse addDrug(AddDrugRequest addDrugRequest) {
        boolean isInvalidName = addDrugRequest.getName() == null || addDrugRequest.getName().isBlank();
        if (isInvalidName) throw new IllegalArgumentException("Invalid drug name!!!");

        boolean isInvalidBrand = addDrugRequest.getBrand() == null || addDrugRequest.getBrand().isBlank();
        if (isInvalidBrand) throw new IllegalArgumentException("Invalid brand!!!");

        boolean isInvalidPrice = addDrugRequest.getPrice().compareTo(BigDecimal.ZERO) <= 0;
        if (isInvalidPrice) throw new IllegalArgumentException("Invalid price!!!");

        boolean isExpired = LocalDate.now().isAfter(addDrugRequest.getExpiryDate());
        if (isExpired) throw new IllegalArgumentException("Drugs have expired!!!");

        addDrugRequest.setName(addDrugRequest.getName().toLowerCase());
        addDrugRequest.setBrand(addDrugRequest.getBrand().toLowerCase());
        Drug newDrug = mapToDrug(addDrugRequest);
        Drug savedDrug = drugRepository.save(newDrug);
        return mapToAddDrugResponse(savedDrug);
    }

    @Override
    public UpdateDrugResponse updateDrug(UpdateDrugRequest updateDrugRequest) {
        boolean isInvalidName = updateDrugRequest.getName() == null || updateDrugRequest.getName().isBlank();
        if (isInvalidName) throw new IllegalArgumentException("Invalid drug name!!!");

        boolean isInvalidBrand = updateDrugRequest.getBrand() == null || updateDrugRequest.getBrand().isBlank();
        if (isInvalidBrand) throw new IllegalArgumentException("Invalid brand!!!");

        boolean isInvalidPrice = updateDrugRequest.getPrice().compareTo(BigDecimal.ZERO) <= 0;
        if (isInvalidPrice) throw new IllegalArgumentException("Invalid price!!!");

        boolean isExpired = LocalDate.now().isAfter(updateDrugRequest.getExpiryDate());
        if (isExpired) throw new IllegalArgumentException("Drugs have expired!!!");

        updateDrugRequest.setName(updateDrugRequest.getName().toLowerCase());
        updateDrugRequest.setBrand(updateDrugRequest.getBrand().toLowerCase());
        Drug foundDrug = drugRepository.findByDrugName(updateDrugRequest.getName());

        if (foundDrug == null) throw new IllegalArgumentException("Drug not found!!!");

        mapToUpdatedDrug(updateDrugRequest, foundDrug);
        return mapToUpdateDrugResponse(foundDrug);
    }

    @Override
    public DeleteDrugResponse deleteDrug(DeleteDrugRequest deleteDrugRequest) {
        return null;
    }

    @Override
    public DispenseDrugsResponse dispenseDrugs(DispenseDrugsRequest dispenseDrugsRequest) {
        return null;
    }
}
