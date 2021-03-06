package ru.natalia.spring.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;
import ru.natalia.spring.models.Status;

import javax.transaction.Transactional;
import java.util.List;

@Component
public class StatusDAO {

    private SessionFactory sessionFactory;

    public StatusDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    @Transactional
    public List<Status> index() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Status ").list();
    }
    @Transactional
    public Status show(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Status.class,id);
    }
    @Transactional
    public void save(Status status)  {
        Session session = sessionFactory.getCurrentSession();
        session.persist(status);

    }
    @Transactional
    public void update(int id, Status updateStatus) {
        Session session = sessionFactory.getCurrentSession();
        session.update(String.valueOf(id),updateStatus);

    }
    @Transactional
    public void delete(int id) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(session.get(Status.class,id));
    }
}
