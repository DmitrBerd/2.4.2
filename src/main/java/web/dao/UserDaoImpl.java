package web.dao;

import org.springframework.stereotype.Repository;
import web.model.Role;
import web.model.User;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void add(User user) {
        entityManager.persist(user);
    }

    @Override
    public void remove(long id) {
        entityManager.remove(entityManager.find(User.class, id));
    }

    @Override
    public void edit(User user) {
        entityManager.merge(user);
    }

    @Override
    public User getById(long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public List<User> allUser() {
        return entityManager.createQuery("select u FROM User u", User.class).getResultList();
    }

    @Override
    public User getUserByName(String name) {
        TypedQuery<User> query = entityManager.createQuery("select u from User u where u.firstName=:name",
                User.class).setParameter("name", name);
        return query.getSingleResult();
    }
}
