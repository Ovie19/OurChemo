package ng.ourChemo.data.repositories;

import ng.ourChemo.data.models.User;

public interface UserRepository {
    long count();

    User save(User user);

    User findById(int id);

    void deleteById(int id);

    void delete(User user);

    void deleteAll();

    boolean existsById(int id);

}
