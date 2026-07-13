package ng.ourChemo.data.repositories;

import ng.ourChemo.data.models.DispensedDrug;
import ng.ourChemo.data.models.Drug;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DispensedDrugRepositoryTest {

    private DispensedDrugRepository dispensedDrugRepository;

    @BeforeEach
    void setUp() {
        dispensedDrugRepository = new DispensedDrugRepositoryImpl();
    }

    @Test
    public void newDispensedDrugRepositoryIsEmptyTest() {
        assertEquals(0, dispensedDrugRepository.count());
    }

    @Test
    public void saveDrug_dispensedDrugRepositoryCountIsOneTest() {
        dispensedDrugRepository.save(new DispensedDrug());
        assertEquals(1, dispensedDrugRepository.count());
    }

    @Test
    public void saveDispensedDrug_findDispensedDrugById_returnsDispensedDrugTest() {
        DispensedDrug dispensedDrug = new DispensedDrug();
        DispensedDrug savedDispensedDrug = dispensedDrugRepository.save(dispensedDrug);
        DispensedDrug foundDispensedDrug = dispensedDrugRepository.findById(savedDispensedDrug.getId());
        assertEquals(savedDispensedDrug, foundDispensedDrug);
    }

    @Test
    public void saveDispensedDrugX_saveDispensedDrugY_findBothDispensedDrugsByTheirId_returnsTheRespectiveDispensedDrugTest() {
        DispensedDrug dispensedDrugX = new DispensedDrug();
        dispensedDrugX.setDrug(new Drug());
        dispensedDrugX.setQuantity(20);
        DispensedDrug savedDispensedDrugX = dispensedDrugRepository.save(dispensedDrugX);

        DispensedDrug dispensedDrugY = new DispensedDrug();
        dispensedDrugY.setDrug(new Drug());
        dispensedDrugY.setQuantity(15);
        DispensedDrug savedDispensedDrugY = dispensedDrugRepository.save(dispensedDrugY);

        assertEquals(2, dispensedDrugRepository.count());

        DispensedDrug foundDispensedDrugX = dispensedDrugRepository.findById(savedDispensedDrugX.getId());
        DispensedDrug foundDispensedDrugY = dispensedDrugRepository.findById(savedDispensedDrugY.getId());

        assertEquals(savedDispensedDrugX, foundDispensedDrugX);
        assertEquals(savedDispensedDrugY, foundDispensedDrugY);
    }

    @Test
    public void saveDispensedDrugX_saveDispensedDrugXAgain_dispensedDrugInDispensedDrugRepositoryCountIsOneTest() {
        DispensedDrug dispensedDrugX = new DispensedDrug();
        dispensedDrugX.setDrug(new Drug());
        dispensedDrugX.setQuantity(20);
        DispensedDrug savedDispensedDrugX = dispensedDrugRepository.save(dispensedDrugX);
        assertEquals(1, dispensedDrugRepository.count());

        dispensedDrugX.setQuantity(10);
        DispensedDrug updatedDispensedDrugX = dispensedDrugRepository.save(savedDispensedDrugX);
        DispensedDrug foundDispensedDrugX = dispensedDrugRepository.findById(updatedDispensedDrugX.getId());

        assertEquals(1, dispensedDrugRepository.count());
        assertEquals(updatedDispensedDrugX, foundDispensedDrugX);
    }

    @Test
    public void saveDispensedDrug_deleteDispensedDrugById_dispensedDrugRepositoryCountIsZeroTest() {
        DispensedDrug dispensedDrugX = new DispensedDrug();
        DispensedDrug savedDispensedDrugX = dispensedDrugRepository.save(dispensedDrugX);
        assertEquals(1, dispensedDrugRepository.count());

        dispensedDrugRepository.deleteById(savedDispensedDrugX.getId());
        assertEquals(0, dispensedDrugRepository.count());
    }

    @Test
    public void saveDispensedDrugX_saveDispensedDrugY_deleteXById_saveDispensedDrugZ_findDispensedDrugZById_returnsDispensedDrugZTest() {
        DispensedDrug dispensedDrugX = new DispensedDrug();
        DispensedDrug savedDispensedDrugX = dispensedDrugRepository.save(dispensedDrugX);

        DispensedDrug dispensedDrugY = new DispensedDrug();
        DispensedDrug savedDispensedDrugY = dispensedDrugRepository.save(dispensedDrugY);
        assertEquals(2, dispensedDrugRepository.count());

        dispensedDrugRepository.deleteById(savedDispensedDrugX.getId());
        assertEquals(1, dispensedDrugRepository.count());

        DispensedDrug drugZ = new DispensedDrug();
        DispensedDrug savedDispensedDrugZ = dispensedDrugRepository.save(drugZ);
        DispensedDrug foundDispensedDrugZ = dispensedDrugRepository.findById(savedDispensedDrugZ.getId());

        assertEquals(2, dispensedDrugRepository.count());
        assertEquals(savedDispensedDrugZ, foundDispensedDrugZ);
    }

    @Test
    public void saveTwoDispensedDrugs_deleteAll_dispensedDrugRepositoryIsEmptyTest() {
        DispensedDrug dispensedDrugX = new DispensedDrug();
        DispensedDrug savedDispensedDrugX = dispensedDrugRepository.save(dispensedDrugX);

        DispensedDrug dispensedDrugY = new DispensedDrug();
        DispensedDrug savedDispensedDrugY = dispensedDrugRepository.save(dispensedDrugY);
        assertEquals(2, dispensedDrugRepository.count());

        dispensedDrugRepository.deleteAll();
        assertEquals(0, dispensedDrugRepository.count());
    }

    @Test
    public void saveDispensedDrug_checkDispensedDrugExistsById_returnsTrueTest() {
        DispensedDrug dispensedDrugX = new DispensedDrug();
        DispensedDrug savedDispensedDrugX = dispensedDrugRepository.save(dispensedDrugX);
        assertTrue(dispensedDrugRepository.existsById(savedDispensedDrugX.getId()));
    }

    @Test
    public void saveDispensedDrug_deleteDispensedDrugById_checkDispensedDrugExistsById_returnsFalseTest() {
        DispensedDrug dispensedDrugX = new DispensedDrug();
        DispensedDrug savedDispensedDrugX = dispensedDrugRepository.save(dispensedDrugX);
        assertTrue(dispensedDrugRepository.existsById(savedDispensedDrugX.getId()));

        dispensedDrugRepository.deleteById(savedDispensedDrugX.getId());
        assertEquals(0, dispensedDrugRepository.count());
        assertFalse(dispensedDrugRepository.existsById(savedDispensedDrugX.getId()));
    }
}