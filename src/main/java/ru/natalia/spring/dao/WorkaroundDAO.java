package ru.natalia.spring.dao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;
import ru.natalia.spring.models.Workaround;

import javax.transaction.Transactional;
import java.util.List;
@Component
public class WorkaroundDAO {

    private SessionFactory sessionFactory;

    public WorkaroundDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    @Transactional
    public List<Workaround> index() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Workaround ").list();
    }
    @Transactional
    public Workaround show(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Workaround.class,id);
    }
    @Transactional
    public void save(Workaround workaround)  {
        Session session = sessionFactory.getCurrentSession();
        session.persist(workaround);

    }
    @Transactional
    public void update(int id, Workaround updateWorkaround) {
        Session session = sessionFactory.getCurrentSession();
        session.update(String.valueOf(id),updateWorkaround);

    }
    @Transactional
    public void delete(int id) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(session.get(Workaround.class,id));
    }
}
