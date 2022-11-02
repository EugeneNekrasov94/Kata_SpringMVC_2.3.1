package web.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Component
public class UserDaoImpl implements UserDao {
    @PersistenceContext()
    private EntityManager entityManager;

    @Autowired
    public UserDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void addUser(User user) {
        entityManager.persist(user);
    }

    @Override
    public User getUserById(Long id) {
        TypedQuery<User> query = entityManager.createQuery("SELECT u FROM User u WHERE u.id = :id", User.class);
        query.setParameter("id", id);
        return query.setMaxResults(1).getSingleResult();
    }

    @Override
    public void update(Long id, User user) {
        User updated = getUserById(id);
        updated.setName(user.getName());
        updated.setSurname(user.getSurname());
        updated.setAge(user.getAge());
    }

    @Override
    public void remove(Long id) {
        User user = getUserById(id);
        entityManager.remove(user);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> listUsers() {
        TypedQuery<User> userTypedQuery = entityManager.createQuery("SELECT u FROM User u", User.class);
        return userTypedQuery.getResultList();
    }
}
