package it.prova.proprietariojpa.dao;

import it.prova.proprietariojpa.dao.automobile.AutomobileDAO;
import it.prova.proprietariojpa.dao.automobile.AutomobileDAOImpl;
import it.prova.proprietariojpa.dao.proprietario.ProprietarioDAO;
import it.prova.proprietariojpa.dao.proprietario.ProprietarioDAOImpl;

public class MyDaoFactory {
	// rendiamo questo factory SINGLETON
		private static AutomobileDAO autombileDAOInstance = null;
		private static ProprietarioDAO proprietarioDAOInstance = null;

		public static AutomobileDAO getAutomobileDAOInstance() {
			if (autombileDAOInstance == null)
				autombileDAOInstance = new AutomobileDAOImpl();
			return autombileDAOInstance;
		}

		public static ProprietarioDAO getProprietarioDAOInstance(){
			if(proprietarioDAOInstance == null)
				proprietarioDAOInstance= new ProprietarioDAOImpl();
			return proprietarioDAOInstance;
		}

}
