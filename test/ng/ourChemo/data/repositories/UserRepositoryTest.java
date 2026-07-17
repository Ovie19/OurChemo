package ng.ourChemo.data.repositories;

import ng.ourChemo.data.models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserRepositoryTest {

    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        userRepository = new UserRepositoryImpl();
        userRepository.deleteAll();
    }

    @Test
    public void newUserRepositoryIsEmptyTest() {
        assertEquals(0, userRepository.count());
    }

    @Test
    public void saveUser_usersInUserRepositoryCountIsOneTest() {
        userRepository.save(new User());
        assertEquals(1, userRepository.count());
    }

    @Test
    public void saveUser_findUserByIdReturnsUserTest() {
        User savedUser = userRepository.save(new User());
        User foundUser = userRepository.findById(savedUser.getId());
        assertEquals(savedUser, foundUser);
    }

    @Test
    public void saveTwoUsers_findUsersByTheirId_returnsTheUsersTest() {
        User userOne =  new User();
        userOne.setFullName("User One");
        userOne.setUsername("User_one");
        User savedUserOne = userRepository.save(userOne);

        User userTwo =  new User();
        userTwo.setFullName("User Two");
        userTwo.setUsername("User_two");
        User savedUserTwo = userRepository.save(userTwo);

        assertEquals(2, userRepository.count());

        User foundUserOne = userRepository.findById(savedUserOne.getId());
        User foundUserTwo = userRepository.findById(savedUserTwo.getId());

        assertEquals(savedUserOne, foundUserOne);
        assertEquals(savedUserTwo, foundUserTwo);
    }

    @Test
    public void saveUser_saveSameUser_usersInUserRepositoryCountIsOneTest() {
        User user =  new User();
        user.setFullName("User One");
        user.setUsername("User_one");
        User savedUser = userRepository.save(user);
        assertEquals(1, userRepository.count());

        savedUser.setUsername("User_one_updated");
        User updatedUser = userRepository.save(savedUser);
        User foundUser = userRepository.findById(updatedUser.getId());
        assertEquals(updatedUser, foundUser);
        assertEquals(1, userRepository.count());
    }

    @Test
    public void saveTwoUsers_deleteUserOne_findUserTwoById_returnsUserTwoTest() {
        User userOne =  new User();
        userOne.setFullName("User One");
        userOne.setUsername("User_one");
        userRepository.save(userOne);

        User userTwo =  new User();
        userTwo.setFullName("User Two");
        userTwo.setUsername("User_two");
        User savedUserTwo = userRepository.save(userTwo);

        assertEquals(2, userRepository.count());

        userRepository.deleteById(userOne.getId());
        User foundUserTwo = userRepository.findById(savedUserTwo.getId());

        assertEquals(1, userRepository.count());
        assertEquals(savedUserTwo, foundUserTwo);
    }

    @Test
    public void saveUserX_saveUserY_deleteXById_saveUserZ_findUserZById_returnsUserZTest() {
        User userOne =  new User();
        userOne.setFullName("User One");
        userOne.setUsername("User_one");
        User savedUserOne = userRepository.save(userOne);

        User userTwo =  new User();
        userTwo.setFullName("User Two");
        userTwo.setUsername("User_two");
        userRepository.save(userTwo);

        assertEquals(2, userRepository.count());
        userRepository.deleteById(savedUserOne.getId());
        assertEquals(1, userRepository.count());

        User userThree =  new User();
        userThree.setFullName("User Three");
        userThree.setUsername("User_three");
        User savedUserThree = userRepository.save(userThree);

        assertEquals(2, userRepository.count());
        User foundUserThree = userRepository.findById(savedUserThree.getId());
        assertEquals(savedUserThree, foundUserThree);
    }

    @Test
    public void saveThreeUsers_deleteAll_usersInUserRepositoryIsZeroTest() {
        User userOne =  new User();
        userOne.setFullName("User One");
        userOne.setUsername("User_one");
        userRepository.save(userOne);

        User userTwo =  new User();
        userTwo.setFullName("User Two");
        userTwo.setUsername("User_two");
        userRepository.save(userTwo);

        User userThree =  new User();
        userThree.setFullName("User Three");
        userThree.setUsername("User_three");
        userRepository.save(userThree);

        assertEquals(3, userRepository.count());
        userRepository.deleteAll();

        assertEquals(0, userRepository.count());
    }

    @Test
    public void saveUser_existsById_returnsTrueTest() {
        User userOne =  new User();
        userOne.setFullName("User One");
        userOne.setUsername("User_one");
        User savedUserOne = userRepository.save(userOne);

        assertTrue(userRepository.existsById(savedUserOne.getId()));
    }

    @Test
    public void saveUser_deleteUserById_existsById_returnsFalseTest() {
        User userOne =  new User();
        userOne.setFullName("User One");
        userOne.setUsername("User_one");
        User savedUserOne = userRepository.save(userOne);
        assertTrue(userRepository.existsById(savedUserOne.getId()));

        userRepository.deleteById(savedUserOne.getId());
        assertFalse(userRepository.existsById(savedUserOne.getId()));
    }
}