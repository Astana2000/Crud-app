package ru.natalia.spring.binders;

import ru.natalia.spring.dao.ProjectWorkaroundDAO;
import ru.natalia.spring.models.Workaround;

import java.beans.PropertyEditorSupport;
public class WorkaroundPropertyEditor extends PropertyEditorSupport {
    private ProjectWorkaroundDAO workaroundDAO;

    public WorkaroundPropertyEditor(ProjectWorkaroundDAO workaroundDAO) {
        this.workaroundDAO = workaroundDAO;
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        Integer id = Integer.parseInt(text);
        Workaround value = id == 0 ? null : workaroundDAO.findWorkaroundById(id); ;
        setValue(value);
    }

    @Override
    public String getAsText() {
        Workaround d = (Workaround) getValue();
        return d != null ? String.valueOf(d.getId()) : "";
    }
    /*public Workaround retrieveDocumentNumber(String text){
        return (Workaround) this.sessionFactory.getCurrentSession().get(Workaround.class, text);
    }*/
}
