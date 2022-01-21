package ru.natalia.spring.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.natalia.spring.models.Status;

import java.util.List;

@Component
public class StatusDAO {
    @Autowired
    private final JdbcTemplate jdbcTemplate;

    public StatusDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Status> index() {
       return jdbcTemplate.query("Select * from Status",new BeanPropertyRowMapper<>(Status.class));

    }

    public Status show(int id) {
        return jdbcTemplate.query("Select * from Status where id=?", new Integer[]{id}, 
                new BeanPropertyRowMapper<>(Status.class)).stream().findAny().orElse(null);
    }

    public void save(Status status)  {
       jdbcTemplate.update("Insert into Status values (1,?)", status.getName());
    }

    public void update(int id, Status updateStatus) {
        jdbcTemplate.update("Update Status Set name=? where id = ?", updateStatus.getName(),id);
    }

    public void delete(int id) {
       jdbcTemplate.update("Delete from Status where id=?",id);
    }
}
