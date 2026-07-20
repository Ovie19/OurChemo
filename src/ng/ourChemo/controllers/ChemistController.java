package ng.ourChemo.controllers;

import ng.ourChemo.dtos.request.AddDrugRequest;
import ng.ourChemo.dtos.request.DeleteDrugRequest;
import ng.ourChemo.dtos.request.DispenseDrugsRequest;
import ng.ourChemo.dtos.request.UpdateDrugRequest;
import ng.ourChemo.dtos.response.AddDrugResponse;
import ng.ourChemo.dtos.response.DeleteDrugResponse;
import ng.ourChemo.dtos.response.DispenseDrugsResponse;
import ng.ourChemo.dtos.response.UpdateDrugResponse;
import ng.ourChemo.services.ChemistServices;
import ng.ourChemo.services.ChemistServicesImpl;

public class ChemistController {

    private ChemistServices chemistServices = new ChemistServicesImpl();

    public AddDrugResponse addDrug(AddDrugRequest addDrugRequest) {
        return chemistServices.addDrug(addDrugRequest);
    }

    public UpdateDrugResponse updateDrug(UpdateDrugRequest updateDrugRequest) {
        return chemistServices.updateDrug(updateDrugRequest);
    }

    public DeleteDrugResponse deleteDrug(DeleteDrugRequest deleteDrugRequest) {
        return chemistServices.deleteDrug(deleteDrugRequest);
    }

    public DispenseDrugsResponse dispenseDrugs(DispenseDrugsRequest dispenseDrugsRequest) {
        return chemistServices.dispenseDrugs(dispenseDrugsRequest);
    }
}
