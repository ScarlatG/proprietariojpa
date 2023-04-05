package it.prova.proprietariojpa.dao.automobile;

import java.util.List;

import it.prova.proprietariojpa.dao.IBaseDAO;
import it.prova.proprietariojpa.model.Automobile;

public interface AutomobileDAO extends IBaseDAO<Automobile>{
	public List<Automobile> findErrori() throws Exception;

}
