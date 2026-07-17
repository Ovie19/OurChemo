package ng.ourChemo.data.repositories;

import ng.ourChemo.data.models.User;

import java.util.ArrayList;
import java.util.List;


public class UserRepositoryImpl implements UserRepository {

    private static int count;
    private static final List<User> users = new ArrayList<>();

    @Override
    public long count() {
        return users.size();
    }

    @Override
    public User save(User user) {
        if (isNew(user)) saveNewUser(user); else updateUser(user);
        return user;
    }

    private void updateUser(User user) {
        deleteById(user.getId());
        users.add(user);
    }

    private void saveNewUser(User user) {
        user.setId(++count);
        users.add(user);
    }

    private boolean isNew(User user) {
        return user.getId() == 0;
    }

    @Override
    public User findById(int id) {
        for (User user : users)
            if (user.getId() == id)
                return user;

        return null;
    }

    @Override
    public void deleteById(int id) {
        User user = findById(id);
        delete(user);
    }

    @Override
    public void delete(User user) {
        users.remove(user);
    }

    @Override
    public void deleteAll() {
        users.clear();
    }

    @Override
    public boolean existsById(int id) {
        User user = findById(id);
        return user != null;
    }

    @Override
    public User findByUserName(String username) {
        for (User user : users)
            if (user.getUsername().equals(username)) return user;

        return null;
    }
}
