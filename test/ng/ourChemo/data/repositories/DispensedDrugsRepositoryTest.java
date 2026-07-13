package ng.ourChemo.data.repositories;

import ng.ourChemo.data.models.DispensedDrugs;
import ng.ourChemo.data.models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class DispensedDrugsRepositoryTest {

    private DispensedDrugsRepository dispensedDrugsRepository;

    @BeforeEach
    void setUp() {
        dispensedDrugsRepository = new DispensedDrugsRepositoryImpl();
    }

    @Test
    public void newDispensedDrugsRepositoryIsEmptyTest() {
        assertEquals(0, dispensedDrugsRepository.count());
    }

    @Test
    public void saveDrug_dispensedDrugsRepositoryCountIsOneTest() {
        dispensedDrugsRepository.save(new DispensedDrugs());
        assertEquals(1, dispensedDrugsRepository.count());
    }

    @Test
    public void saveDispensedDrugs_findDispensedDrugsById_returnsDispensedDrugsTest() {
        DispensedDrugs dispensedDrugs = new DispensedDrugs();
        DispensedDrugs savedDispensedDrugs = dispensedDrugsRepository.save(dispensedDrugs);
        DispensedDrugs foundDispensedDrugs = dispensedDrugsRepository.findById(savedDispensedDrugs.getId());
        assertEquals(savedDispensedDrugs, foundDispensedDrugs);
    }

    @Test
    public void saveDispensedDrugsX_saveDispensedDrugsY_findBothDispensedDrugssByTheirId_returnsTheRespectiveDispensedDrugsTest() {
        DispensedDrugs dispensedDrugsX = new DispensedDrugs();
        dispensedDrugsX.setDispensedDate(LocalDateTime.now());
        dispensedDrugsX.setDispensedBy(new User());
        DispensedDrugs savedDispensedDrugsX = dispensedDrugsRepository.save(dispensedDrugsX);

        DispensedDrugs dispensedDrugsY = new DispensedDrugs();
        dispensedDrugsX.setDispensedDate(LocalDateTime.of(2026, 4, 21, 2, 3));
        dispensedDrugsX.setDispensedBy(new User());
        DispensedDrugs savedDispensedDrugsY = dispensedDrugsRepository.save(dispensedDrugsY);

        assertEquals(2, dispensedDrugsRepository.count());

        DispensedDrugs foundDispensedDrugsX = dispensedDrugsRepository.findById(savedDispensedDrugsX.getId());
        DispensedDrugs foundDispensedDrugsY = dispensedDrugsRepository.findById(savedDispensedDrugsY.getId());

        assertEquals(savedDispensedDrugsX, foundDispensedDrugsX);
        assertEquals(savedDispensedDrugsY, foundDispensedDrugsY);
    }

    @Test
    public void saveDispensedDrugsX_saveDispensedDrugsXAgain_dispensedDrugsInDispensedDrugsRepositoryCountIsOneTest() {
        DispensedDrugs dispensedDrugsX = new DispensedDrugs();
        dispensedDrugsX.setDispensedDate(LocalDateTime.now());
        User userX = new User();
        userX.setUsername("Ghost");
        dispensedDrugsX.setDispensedBy(userX);
        DispensedDrugs savedDispensedDrugsX = dispensedDrugsRepository.save(dispensedDrugsX);
        assertEquals(1, dispensedDrugsRepository.count());

        User userY = new User();
        userY.setUsername("Tommy");
        savedDispensedDrugsX.setDispensedBy(userY);
        DispensedDrugs updatedDispensedDrugsX = dispensedDrugsRepository.save(savedDispensedDrugsX);
        DispensedDrugs foundDispensedDrugsX = dispensedDrugsRepository.findById(updatedDispensedDrugsX.getId());

        assertEquals(1, dispensedDrugsRepository.count());
        assertEquals(updatedDispensedDrugsX, foundDispensedDrugsX);
    }

    @Test
    public void saveDispensedDrugs_deleteDispensedDrugsById_dispensedDrugsRepositoryCountIsZeroTest() {
        DispensedDrugs dispensedDrugsX = new DispensedDrugs();
        DispensedDrugs savedDispensedDrugsX = dispensedDrugsRepository.save(dispensedDrugsX);
        assertEquals(1, dispensedDrugsRepository.count());

        dispensedDrugsRepository.deleteById(savedDispensedDrugsX.getId());
        assertEquals(0, dispensedDrugsRepository.count());
    }

    @Test
    public void saveDispensedDrugsX_saveDispensedDrugsY_deleteXById_saveDispensedDrugsZ_findDispensedDrugsZById_returnsDispensedDrugsZTest() {
        DispensedDrugs dispensedDrugsX = new DispensedDrugs();
        DispensedDrugs savedDispensedDrugsX = dispensedDrugsRepository.save(dispensedDrugsX);

        DispensedDrugs dispensedDrugsY = new DispensedDrugs();
        DispensedDrugs savedDispensedDrugsY = dispensedDrugsRepository.save(dispensedDrugsY);
        assertEquals(2, dispensedDrugsRepository.count());

        dispensedDrugsRepository.deleteById(savedDispensedDrugsX.getId());
        assertEquals(1, dispensedDrugsRepository.count());

        DispensedDrugs drugZ = new DispensedDrugs();
        DispensedDrugs savedDispensedDrugsZ = dispensedDrugsRepository.save(drugZ);
        DispensedDrugs foundDispensedDrugsZ = dispensedDrugsRepository.findById(savedDispensedDrugsZ.getId());

        assertEquals(2, dispensedDrugsRepository.count());
        assertEquals(savedDispensedDrugsZ, foundDispensedDrugsZ);
    }

    @Test
    public void saveTwoDispensedDrugss_deleteAll_dispensedDrugsRepositoryIsEmptyTest() {
        DispensedDrugs dispensedDrugsX = new DispensedDrugs();
        DispensedDrugs savedDispensedDrugsX = dispensedDrugsRepository.save(dispensedDrugsX);

        DispensedDrugs dispensedDrugsY = new DispensedDrugs();
        DispensedDrugs savedDispensedDrugsY = dispensedDrugsRepository.save(dispensedDrugsY);
        assertEquals(2, dispensedDrugsRepository.count());

        dispensedDrugsRepository.deleteAll();
        assertEquals(0, dispensedDrugsRepository.count());
    }

    @Test
    public void saveDispensedDrugs_checkDispensedDrugsExistsById_returnsTrueTest() {
        DispensedDrugs dispensedDrugsX = new DispensedDrugs();
        DispensedDrugs savedDispensedDrugsX = dispensedDrugsRepository.save(dispensedDrugsX);
        assertTrue(dispensedDrugsRepository.existsById(savedDispensedDrugsX.getId()));
    }

    @Test
    public void saveDispensedDrugs_deleteDispensedDrugsById_checkDispensedDrugsExistsById_returnsFalseTest() {
        DispensedDrugs dispensedDrugsX = new DispensedDrugs();
        DispensedDrugs savedDispensedDrugsX = dispensedDrugsRepository.save(dispensedDrugsX);
        assertTrue(dispensedDrugsRepository.existsById(savedDispensedDrugsX.getId()));

        dispensedDrugsRepository.deleteById(savedDispensedDrugsX.getId());
        assertEquals(0, dispensedDrugsRepository.count());
        assertFalse(dispensedDrugsRepository.existsById(savedDispensedDrugsX.getId()));
    }
}