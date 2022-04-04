package ru.natalia.spring.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;
import ru.natalia.spring.models.ProjectWorkaround;
import ru.natalia.spring.models.Workaround;

import javax.transaction.Transactional;
import java.util.List;

@Component
public class ProjectWorkaroundDAO {
    private SessionFactory sessionFactory;

    public ProjectWorkaroundDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional
    public List<ProjectWorkaround> index() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from ProjectWorkaround ").list();
    }
    //Можно ли так делать????
    @Transactional
    public List<Workaround> findAllWorkarounds() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Workaround ").list();
    }
    @Transactional
    public Workaround findWorkaroundById(int id){
        Session session = sessionFactory.getCurrentSession();
        return session.get(Workaround.class,id);
    }
    @Transactional
    public ProjectWorkaround show(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(ProjectWorkaround.class,id);
    }
    @Transactional
    public void save(ProjectWorkaround workaround)  {
        Session session = sessionFactory.getCurrentSession();
        session.persist(workaround);

    }
    @Transactional
    public void update(int id, ProjectWorkaround updateWorkaround) {
        Session session = sessionFactory.getCurrentSession();
        session.update(String.valueOf(id),updateWorkaround);

    }
    @Transactional
    public void delete(int id) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(session.get(ProjectWorkaround.class,id));
    }
}
