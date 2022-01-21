package ru.natalia.spring.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.natalia.spring.models.Status;

import javax.transaction.Transactional;
import java.util.List;

@Component
public class StatusDAO {
  /*  @Autowired
    private final JdbcTemplate jdbcTemplate;*/
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
      // jdbcTemplate.update("Insert into Status values (1,?)", status.getName());
    }
    @Transactional
    public void update(int id, Status updateStatus) {
        Session session = sessionFactory.getCurrentSession();
        session.update(String.valueOf(id),updateStatus);
       // jdbcTemplate.update("Update Status Set name=? where id = ?", updateStatus.getName(),id);
    }
    @Transactional
    public void delete(int id) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(session.get(Status.class,id));
      // jdbcTemplate.update("Delete from Status where id=?",id);
    }
}
