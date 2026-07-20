package ng.ourChemo.services;

import ng.ourChemo.data.models.Drug;
import ng.ourChemo.data.repositories.DrugRepository;
import ng.ourChemo.data.repositories.DrugRepositoryImpl;
import ng.ourChemo.dtos.request.AddDrugRequest;
import ng.ourChemo.dtos.request.UpdateDrugRequest;
import ng.ourChemo.dtos.response.AddDrugResponse;
import ng.ourChemo.dtos.response.UpdateDrugResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ChemistServicesTest {

    private ChemistServices chemistServices;
    private DrugRepository drugRepository = new DrugRepositoryImpl();

    @BeforeEach
    void setUp() {
        chemistServices = new ChemistServicesImpl();
        drugRepository.deleteAll();
    }

    @Test
    public void addDrug_drugsCountIsOneTest() {
        AddDrugRequest addDrugRequest = new AddDrugRequest();
        addDrugRequest.setName("Paracetamol");
        addDrugRequest.setBrand("Emzor");
        addDrugRequest.setPrice(new BigDecimal("500"));
        addDrugRequest.setExpiryDate(LocalDate.of(2027, 5, 21));
        chemistServices.addDrug(addDrugRequest);
        assertEquals(1, drugRepository.count());
    }

//    @Test
//    public void addDrug_addSameDrugThrowsExceptionTest() {
//        AddDrugRequest addDrugRequest = new AddDrugRequest();
//        addDrugRequest.setName("Paracetamol");
//        addDrugRequest.setBrand("Emzor");
//        addDrugRequest.setPrice(new BigDecimal("500"));
//        addDrugRequest.setExpiryDate(LocalDate.of(2027, 5, 21));
//        chemistServices.addDrug(addDrugRequest);
//
//
//    }

    @Test
    public void addDrug_nameIsNullThrowsExceptionTest() {
        AddDrugRequest addDrugRequest = new AddDrugRequest();
        addDrugRequest.setName(null);
        addDrugRequest.setBrand("Emzor");
        addDrugRequest.setPrice(new BigDecimal("500"));
        addDrugRequest.setExpiryDate(LocalDate.of(2028, 5, 21));
        assertThrows(IllegalArgumentException.class, () -> chemistServices.addDrug(addDrugRequest));
    }

    @Test
    public void addDrug_nameIsEmptyThrowsExceptionTest() {
        AddDrugRequest addDrugRequest = new AddDrugRequest();
        addDrugRequest.setName(" ");
        addDrugRequest.setBrand("Emzor");
        addDrugRequest.setPrice(new BigDecimal("500"));
        addDrugRequest.setExpiryDate(LocalDate.of(2028, 5, 21));
        assertThrows(IllegalArgumentException.class, () -> chemistServices.addDrug(addDrugRequest));
    }

    @Test
    public void addDrug_brandIsNullThrowsExceptionTest() {
        AddDrugRequest addDrugRequest = new AddDrugRequest();
        addDrugRequest.setName("Paracetamol");
        addDrugRequest.setBrand(null);
        addDrugRequest.setPrice(new BigDecimal("500"));
        addDrugRequest.setExpiryDate(LocalDate.of(2028, 5, 21));
        assertThrows(IllegalArgumentException.class, () -> chemistServices.addDrug(addDrugRequest));
    }

    @Test
    public void addDrug_brandIsEmptyThrowsExceptionTest() {
        AddDrugRequest addDrugRequest = new AddDrugRequest();
        addDrugRequest.setName("Paracetamol");
        addDrugRequest.setBrand("");
        addDrugRequest.setPrice(new BigDecimal("500"));
        addDrugRequest.setExpiryDate(LocalDate.of(2028, 5, 21));
        assertThrows(IllegalArgumentException.class, () -> chemistServices.addDrug(addDrugRequest));
    }

    @Test
    public void addDrug_priceIsNegativeThrowsExceptionTest() {
        AddDrugRequest addDrugRequest = new AddDrugRequest();
        addDrugRequest.setName("Paracetamol");
        addDrugRequest.setBrand("Emzor");
        addDrugRequest.setPrice(new BigDecimal("-500"));
        addDrugRequest.setExpiryDate(LocalDate.of(2028, 5, 21));
        assertThrows(IllegalArgumentException.class, () -> chemistServices.addDrug(addDrugRequest));
    }

    @Test
    public void addDrug_priceIsZeroThrowsExceptionTest() {
        AddDrugRequest addDrugRequest = new AddDrugRequest();
        addDrugRequest.setName("Paracetamol");
        addDrugRequest.setBrand("Emzor");
        addDrugRequest.setPrice(BigDecimal.ZERO);
        addDrugRequest.setExpiryDate(LocalDate.of(2028, 5, 21));
        assertThrows(IllegalArgumentException.class, () -> chemistServices.addDrug(addDrugRequest));
    }

    @Test
    public void addDrugWithExpiryDateAlreadyElapsedThrowsExceptionTest() {
        AddDrugRequest addDrugRequest = new AddDrugRequest();
        addDrugRequest.setName("Paracetamol");
        addDrugRequest.setBrand("Emzor");
        addDrugRequest.setPrice(new BigDecimal("500"));
        addDrugRequest.setExpiryDate(LocalDate.of(2025, 5, 21));
        assertThrows(IllegalArgumentException.class, () -> chemistServices.addDrug(addDrugRequest));
    }

    @Test
    public void updateDrug_updateIsSuccessfulTest() {
        AddDrugRequest addDrugRequest = new AddDrugRequest();
        addDrugRequest.setName("Paracetamol");
        addDrugRequest.setBrand("Emzor");
        addDrugRequest.setPrice(new BigDecimal("500"));
        addDrugRequest.setExpiryDate(LocalDate.of(2027, 5, 21));
        AddDrugResponse addDrugResponse = chemistServices.addDrug(addDrugRequest);
        Drug drug = drugRepository.findByDrugName(addDrugResponse.getName());

        UpdateDrugRequest updateDrugRequest = new UpdateDrugRequest();
        updateDrugRequest.setName(drug.getName());
        updateDrugRequest.setBrand(drug.getBrand());
        updateDrugRequest.setPrice(new BigDecimal("800"));
        updateDrugRequest.setExpiryDate(drug.getExpiryDate());
        UpdateDrugResponse updateDrugResponse = chemistServices.updateDrug(updateDrugRequest);
        Drug updatedDrug = drugRepository.findByDrugName(updateDrugResponse.getName());
        assertEquals(drug.getId(), updatedDrug.getId());
        assertEquals(new BigDecimal("800"), updatedDrug.getPrice());
    }

    @Test
    public void updateDrug_nameIsNullThrowsExceptionTest() {
        AddDrugRequest addDrugRequest = new AddDrugRequest();
        addDrugRequest.setName("Paracetamol");
        addDrugRequest.setBrand("Emzor");
        addDrugRequest.setPrice(new BigDecimal("500"));
        addDrugRequest.setExpiryDate(LocalDate.of(2027, 5, 21));
        chemistServices.addDrug(addDrugRequest);

        UpdateDrugRequest updateDrugRequest = new UpdateDrugRequest();
        updateDrugRequest.setName(null);
        updateDrugRequest.setBrand("Emzor");
        updateDrugRequest.setPrice(new BigDecimal("500"));
        updateDrugRequest.setExpiryDate(LocalDate.of(2028, 5, 21));
        assertThrows(IllegalArgumentException.class, () -> chemistServices.updateDrug(updateDrugRequest));
    }

    @Test
    public void updateDrug_nameIsEmptyThrowsExceptionTest() {
        AddDrugRequest addDrugRequest = new AddDrugRequest();
        addDrugRequest.setName("Paracetamol");
        addDrugRequest.setBrand("Emzor");
        addDrugRequest.setPrice(new BigDecimal("500"));
        addDrugRequest.setExpiryDate(LocalDate.of(2027, 5, 21));
        chemistServices.addDrug(addDrugRequest);

        UpdateDrugRequest updateDrugRequest = new UpdateDrugRequest();
        updateDrugRequest.setName(" ");
        updateDrugRequest.setBrand("Emzor");
        updateDrugRequest.setPrice(new BigDecimal("500"));
        updateDrugRequest.setExpiryDate(LocalDate.of(2028, 5, 21));
        assertThrows(IllegalArgumentException.class, () -> chemistServices.updateDrug(updateDrugRequest));
    }

    @Test
    public void updateDrug_brandIsNullThrowsExceptionTest() {
        AddDrugRequest addDrugRequest = new AddDrugRequest();
        addDrugRequest.setName("Paracetamol");
        addDrugRequest.setBrand("Emzor");
        addDrugRequest.setPrice(new BigDecimal("500"));
        addDrugRequest.setExpiryDate(LocalDate.of(2027, 5, 21));
        chemistServices.addDrug(addDrugRequest);

        UpdateDrugRequest updateDrugRequest = new UpdateDrugRequest();
        updateDrugRequest.setName("Paracetamol");
        updateDrugRequest.setBrand(null);
        updateDrugRequest.setPrice(new BigDecimal("500"));
        updateDrugRequest.setExpiryDate(LocalDate.of(2028, 5, 21));
        assertThrows(IllegalArgumentException.class, () -> chemistServices.updateDrug(updateDrugRequest));
    }

    @Test
    public void updateDrug_brandIsEmptyThrowsExceptionTest() {
        AddDrugRequest addDrugRequest = new AddDrugRequest();
        addDrugRequest.setName("Paracetamol");
        addDrugRequest.setBrand("Emzor");
        addDrugRequest.setPrice(new BigDecimal("500"));
        addDrugRequest.setExpiryDate(LocalDate.of(2027, 5, 21));
        chemistServices.addDrug(addDrugRequest);

        UpdateDrugRequest updateDrugRequest = new UpdateDrugRequest();
        updateDrugRequest.setName("Paracetamol");
        updateDrugRequest.setBrand("");
        updateDrugRequest.setPrice(new BigDecimal("500"));
        updateDrugRequest.setExpiryDate(LocalDate.of(2028, 5, 21));
        assertThrows(IllegalArgumentException.class, () -> chemistServices.updateDrug(updateDrugRequest));
    }

    @Test
    public void updateDrug_priceIsNegativeThrowsExceptionTest() {
        AddDrugRequest addDrugRequest = new AddDrugRequest();
        addDrugRequest.setName("Paracetamol");
        addDrugRequest.setBrand("Emzor");
        addDrugRequest.setPrice(new BigDecimal("500"));
        addDrugRequest.setExpiryDate(LocalDate.of(2027, 5, 21));
        chemistServices.addDrug(addDrugRequest);

        UpdateDrugRequest updateDrugRequest = new UpdateDrugRequest();
        updateDrugRequest.setName("Paracetamol");
        updateDrugRequest.setBrand("Emzor");
        updateDrugRequest.setPrice(new BigDecimal("-500"));
        updateDrugRequest.setExpiryDate(LocalDate.of(2028, 5, 21));
        assertThrows(IllegalArgumentException.class, () -> chemistServices.updateDrug(updateDrugRequest));
    }

    @Test
    public void updateDrug_priceIsZeroThrowsExceptionTest() {
        AddDrugRequest addDrugRequest = new AddDrugRequest();
        addDrugRequest.setName("Paracetamol");
        addDrugRequest.setBrand("Emzor");
        addDrugRequest.setPrice(new BigDecimal("500"));
        addDrugRequest.setExpiryDate(LocalDate.of(2027, 5, 21));
        chemistServices.addDrug(addDrugRequest);

        UpdateDrugRequest updateDrugRequest = new UpdateDrugRequest();
        updateDrugRequest.setName("Paracetamol");
        updateDrugRequest.setBrand("Emzor");
        updateDrugRequest.setPrice(BigDecimal.ZERO);
        updateDrugRequest.setExpiryDate(LocalDate.of(2028, 5, 21));
        assertThrows(IllegalArgumentException.class, () -> chemistServices.updateDrug(updateDrugRequest));
    }

    @Test
    public void updateDrugWithExpiryDateAlreadyElapsedThrowsExceptionTest() {
        AddDrugRequest addDrugRequest = new AddDrugRequest();
        addDrugRequest.setName("Paracetamol");
        addDrugRequest.setBrand("Emzor");
        addDrugRequest.setPrice(new BigDecimal("500"));
        addDrugRequest.setExpiryDate(LocalDate.of(2027, 5, 21));
        chemistServices.addDrug(addDrugRequest);

        UpdateDrugRequest updateDrugRequest = new UpdateDrugRequest();
        updateDrugRequest.setName("Paracetamol");
        updateDrugRequest.setBrand("Emzor");
        updateDrugRequest.setPrice(new BigDecimal("500"));
        updateDrugRequest.setExpiryDate(LocalDate.of(2025, 5, 21));
        assertThrows(IllegalArgumentException.class, () -> chemistServices.updateDrug(updateDrugRequest));
    }

    @Test
    public void updateDrug_whenDrugIsNotFoundThrowsExceptionTest() {
        UpdateDrugRequest updateDrugRequest = new UpdateDrugRequest();
        updateDrugRequest.setName("Paracetamol");
        updateDrugRequest.setBrand("Emzor");
        updateDrugRequest.setPrice(new BigDecimal("500"));
        updateDrugRequest.setExpiryDate(LocalDate.of(2027, 5, 21));
        assertThrows(IllegalArgumentException.class, () -> chemistServices.updateDrug(updateDrugRequest));
    }
}