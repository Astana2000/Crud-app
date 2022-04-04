package ru.natalia.spring.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;
import ru.natalia.spring.models.Project;

import javax.transaction.Transactional;
import java.util.List;
@Component
public class ProjectDAO {
    private SessionFactory sessionFactory;

    public ProjectDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    @Transactional
    public List<Project> index(){
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Project ").list();
    }
    @Transactional
    public Project show(int id){
        Session session = sessionFactory.getCurrentSession();
        return session.get(Project.class,id);
    }
    @Transactional
    public void save(Project project){
        Session session = sessionFactory.getCurrentSession();
        session.persist(project);
    }
    @Transactional
    public void update(int id, Project updateProject){
        Session session = sessionFactory.getCurrentSession();
        session.update(String.valueOf(id),updateProject);
    }
    @Transactional
    public void delete(int id){
        Session session = sessionFactory.getCurrentSession();
        session.delete(session.get(Project.class,id));
    }

}
