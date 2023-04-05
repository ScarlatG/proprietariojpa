package it.prova.proprietariojpa.service.proprietario;

import java.util.List;

import it.prova.proprietariojpa.dao.automobile.AutomobileDAO;
import it.prova.proprietariojpa.dao.proprietario.ProprietarioDAO;
import it.prova.proprietariojpa.model.Proprietario;


public interface ProprietarioService {
	public List<Proprietario> listAllProprietari() throws Exception;
	public void aggiorna(Proprietario proprietarioInstance) throws Exception;

	public void inserisciNuovo(Proprietario proprietarioInstance) throws Exception;

	public void rimuovi(Proprietario proprietarioInstance) throws Exception;

	//per injection
			public void setProprietarioDAO(ProprietarioDAO proprietarioDAO);

	
	

}
