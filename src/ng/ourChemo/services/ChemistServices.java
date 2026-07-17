package ng.ourChemo.services;

import ng.ourChemo.dtos.request.AddDrugRequest;
import ng.ourChemo.dtos.request.DeleteDrugRequest;
import ng.ourChemo.dtos.request.DispenseDrugsRequest;
import ng.ourChemo.dtos.request.UpdateDrugRequest;
import ng.ourChemo.dtos.response.AddDrugResponse;
import ng.ourChemo.dtos.response.DeleteDrugResponse;
import ng.ourChemo.dtos.response.DispenseDrugsResponse;
import ng.ourChemo.dtos.response.UpdateDrugResponse;

public interface ChemistServices {

    AddDrugResponse addDrug(AddDrugRequest addDrugRequest);

    UpdateDrugResponse updateDrug(UpdateDrugRequest updateDrugRequest);

    DeleteDrugResponse deleteDrug(DeleteDrugRequest deleteDrugRequest);

    DispenseDrugsResponse dispenseDrugs(DispenseDrugsRequest dispenseDrugsRequest);

}
