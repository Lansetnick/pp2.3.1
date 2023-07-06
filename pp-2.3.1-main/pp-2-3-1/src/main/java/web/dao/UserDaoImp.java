package web.dao;

import org.springframework.stereotype.Repository;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void addUser(User user) {
        entityManager.persist(user);
    }

    @Override
    public void delUser(Long userId) {
       User us = entityManager.find(User.class, userId);
        if (us != null) {
            entityManager.remove(us);
        }
    }

    @Override
    public void changeUser(User user) {
        entityManager.merge(user);
    }

    @Override
    public List<User> listUser() {
        return entityManager.createQuery(
                "select u from User u", User.class).getResultList();
    }
}