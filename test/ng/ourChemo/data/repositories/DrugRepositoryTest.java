package ng.ourChemo.data.repositories;

import ng.ourChemo.data.models.Drug;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class DrugRepositoryTest {

    private DrugRepository drugRepository;

    @BeforeEach
    void setUp() {
        drugRepository = new DrugRepositoryImpl();
        drugRepository.deleteAll();
    }

    @Test
    public void newDrugRepositoryIsEmptyTest() {
        assertEquals(0, drugRepository.count());
    }

    @Test
    public void saveDrug_drugRepositoryCountIsOneTest() {
        drugRepository.save(new Drug());
        assertEquals(1, drugRepository.count());
    }

    @Test
    public void saveDrug_findDrugById_returnsDrugTest() {
        Drug drug = new Drug();
        Drug savedDrug = drugRepository.save(drug);
        Drug foundDrug = drugRepository.findById(savedDrug.getId());
        assertEquals(savedDrug, foundDrug);
    }

    @Test
    public void saveDrugX_saveDrugY_findBothDrugsByTheirId_returnsTheRespectiveDrugTest() {
        Drug drugX = new Drug();
        drugX.setName("Paracetamol");
        drugX.setBrand("Emzor");
        drugX.setExpiryDate(LocalDate.of(2029, 12, 25));
        Drug savedDrugX = drugRepository.save(drugX);

        Drug drugY = new Drug();
        drugY.setName("Paracetamol");
        drugY.setBrand("M&M");
        drugY.setExpiryDate(LocalDate.of(2039, 12, 25));
        Drug savedDrugY = drugRepository.save(drugY);

        assertEquals(2, drugRepository.count());

        Drug foundDrugX = drugRepository.findById(savedDrugX.getId());
        Drug foundDrugY = drugRepository.findById(savedDrugY.getId());

        assertEquals(savedDrugX, foundDrugX);
        assertEquals(savedDrugY, foundDrugY);
    }

    @Test
    public void saveDrugX_saveDrugXAgain_drugInDrugRepositoryCountIsOneTest() {
        Drug drugX = new Drug();
        drugX.setName("Paracetamol");
        drugX.setBrand("Emzor");
        drugX.setExpiryDate(LocalDate.of(2029, 12, 25));
        drugX.setPrice(new BigDecimal("300"));
        Drug savedDrugX = drugRepository.save(drugX);
        assertEquals(1, drugRepository.count());

        savedDrugX.setPrice(new BigDecimal("500"));
        Drug updatedDrugX = drugRepository.save(savedDrugX);
        Drug foundDrugX = drugRepository.findById(updatedDrugX.getId());

        assertEquals(1, drugRepository.count());
        assertEquals(updatedDrugX, foundDrugX);
    }

    @Test
    public void saveDrug_deleteDrugById_drugRepositoryCountIsZeroTest() {
        Drug drugX = new Drug();
        drugX.setName("Paracetamol");
        drugX.setBrand("Emzor");
        Drug savedDrugX = drugRepository.save(drugX);
        assertEquals(1, drugRepository.count());

        drugRepository.deleteById(savedDrugX.getId());
        assertEquals(0, drugRepository.count());
    }

    @Test
    public void saveDrugX_saveDrugY_deleteXById_saveDrugZ_findDrugZById_returnsDrugZTest() {
        Drug drugX = new Drug();
        drugX.setName("Paracetamol");
        drugX.setBrand("Emzor");
        Drug savedDrugX = drugRepository.save(drugX);

        Drug drugY = new Drug();
        drugY.setName("Paracetamol");
        drugY.setBrand("M&M");
        Drug savedDrugY = drugRepository.save(drugY);
        assertEquals(2, drugRepository.count());

        drugRepository.deleteById(savedDrugX.getId());
        assertEquals(1, drugRepository.count());

        Drug drugZ = new Drug();
        drugZ.setName("Money");
        drugZ.setBrand("Legal");
        Drug savedDrugZ = drugRepository.save(drugZ);
        Drug foundDrugZ = drugRepository.findById(savedDrugZ.getId());

        assertEquals(2, drugRepository.count());
        assertEquals(savedDrugZ, foundDrugZ);
    }

    @Test
    public void saveTwoDrugs_deleteAll_drugRepositoryIsEmptyTest() {
        Drug drugX = new Drug();
        drugX.setName("Paracetamol");
        drugX.setBrand("Emzor");
        Drug savedDrugX = drugRepository.save(drugX);

        Drug drugY = new Drug();
        drugY.setName("Paracetamol");
        drugY.setBrand("M&M");
        Drug savedDrugY = drugRepository.save(drugY);
        assertEquals(2, drugRepository.count());

        drugRepository.deleteAll();
        assertEquals(0, drugRepository.count());
    }

    @Test
    public void saveDrug_checkDrugExistsById_returnsTrueTest() {
        Drug drugX = new Drug();
        Drug savedDrugX = drugRepository.save(drugX);
        assertTrue(drugRepository.existsById(savedDrugX.getId()));
    }

    @Test
    public void saveDrug_deleteDrugById_checkDrugExistsById_returnsFalseTest() {
        Drug drugX = new Drug();
        Drug savedDrugX = drugRepository.save(drugX);
        assertTrue(drugRepository.existsById(savedDrugX.getId()));

        drugRepository.deleteById(savedDrugX.getId());
        assertEquals(0, drugRepository.count());
        assertFalse(drugRepository.existsById(savedDrugX.getId()));
    }
}