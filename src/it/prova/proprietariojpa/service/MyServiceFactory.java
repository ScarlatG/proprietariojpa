package it.prova.proprietariojpa.service;

import it.prova.proprietariojpa.dao.MyDaoFactory;
import it.prova.proprietariojpa.service.automobile.AutomobileService;
import it.prova.proprietariojpa.service.automobile.AutomobileServiceImpl;
import it.prova.proprietariojpa.service.proprietario.ProprietarioService;
import it.prova.proprietariojpa.service.proprietario.ProprietarioServiceImpl;

public class MyServiceFactory {
	// rendiamo le istanze restituite SINGLETON
		private static AutomobileService automobileServiceInstance = null;
		private static ProprietarioService proprietarioServiceInstance = null;

		public static AutomobileService getAutomobileServiceInstance() {
			if (automobileServiceInstance == null) {
				automobileServiceInstance = new AutomobileServiceImpl();
				automobileServiceInstance.setAutomobileDAO(MyDaoFactory.getAutomobileDAOInstance());
			}
			return automobileServiceInstance;
		}

		public static ProprietarioService getProprietarioServiceInstance() {
			if (proprietarioServiceInstance == null) {
				proprietarioServiceInstance = new ProprietarioServiceImpl();
				proprietarioServiceInstance.setProprietarioDAO(MyDaoFactory.getProprietarioDAOInstance());
			}
			return proprietarioServiceInstance;
		}

}
