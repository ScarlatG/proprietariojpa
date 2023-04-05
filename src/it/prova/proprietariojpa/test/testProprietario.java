package it.prova.proprietariojpa.test;

import it.prova.proprietariojpa.service.MyServiceFactory;
import it.prova.proprietariojpa.service.automobile.AutomobileService;

import java.time.LocalDate;
import java.util.List;

import it.prova.proprietariojpa.dao.EntityManagerUtil;
import it.prova.proprietariojpa.model.Automobile;
import it.prova.proprietariojpa.model.Proprietario;
import it.prova.proprietariojpa.service.proprietario.ProprietarioService;

public class testProprietario {

	public static void main(String[] args) {
		ProprietarioService proprietarioService = MyServiceFactory.getProprietarioServiceInstance();
		AutomobileService automobileService = MyServiceFactory.getAutomobileServiceInstance();

		try {
			testInserisciProprietario(proprietarioService);
//			System.out.println(
//					"in tabella sono presenti " + proprietarioService.listAllProprietari().size() + " elementi.");
//
//			testInserisciAutomobile(proprietarioService, automobileService);
//			System.out.println(
//					"in tabella sono presenti " + proprietarioService.listAllProprietari().size() + " elementi.");
//
//			testAggiornaProprietario(proprietarioService);
//			System.out.println(
//					"in tabella sono presenti " + proprietarioService.listAllProprietari().size() + " elementi.");
//
			testCercaErrori(proprietarioService, automobileService);
			System.out
					.println("in tabella sono presenti " + automobileService.listAllAutomobile().size() + " elementi.");

		} catch (Throwable e) {
			e.printStackTrace();
		} finally {
			// questa è necessaria per chiudere tutte le connessioni quindi rilasciare il
			// main
			EntityManagerUtil.shutdown();
		}
	}

	//
	private static void testInserisciProprietario(ProprietarioService proprietarioService) throws Exception {
		System.out.println(".......testInserisciProprietario inizio.............");
		// creo nuovo municipio
		Proprietario nuovoProprietario = new Proprietario("nome", "nome", "nome", LocalDate.of(2008, 05, 12));
		if (nuovoProprietario.getId() != null)
			throw new RuntimeException("testInserisciProprietario fallito: record già presente ");
		// salvo
		proprietarioService.inserisciNuovo(nuovoProprietario);
		// da questa riga in poi il record, se correttamente inserito, ha un nuovo id
		// (NOVITA' RISPETTO AL PASSATO!!!)
		if (nuovoProprietario.getId() == null)
			throw new RuntimeException("testInserisciProprietario fallito ");

		System.out.println(".......testInserisciProprietario fine: PASSED.............");

	}

	private static void testInserisciAutomobile(ProprietarioService proprietarioService,
			AutomobileService automobieService) throws Exception {
		System.out.println(".......testInserisciAutomobile inizio.............");

		// creo nuovo abitante ma prima mi serve un municipio
		List<Proprietario> listaProprietari = proprietarioService.listAllProprietari();
		if (listaProprietari.isEmpty())
			throw new RuntimeException("testInserisciAutomobile fallito: non ci sono municipi a cui collegarci ");

		Automobile nuovaAutomobile = new Automobile("prova", "prova", "prova");
		// lo lego al primo proprietario che trovo
		nuovaAutomobile.setProprietario(listaProprietari.get(0));
		;

		// salvo il nuovo abitante
		automobieService.inserisciNuovo(nuovaAutomobile);

		// da questa riga in poi il record, se correttamente inserito, ha un nuovo id
		// (NOVITA' RISPETTO AL PASSATO!!!)
		if (nuovaAutomobile.getId() == null)
			throw new RuntimeException("testInserisciAbitante fallito ");

		// il test fallisce anche se non è riuscito a legare i due oggetti
		if (nuovaAutomobile.getProprietario() == null)
			throw new RuntimeException("testInserisciAutomobile fallito: non ha collegato il municipio ");

		System.out.println(".......testInserisciAutomobile fine: PASSED.............");
	}

	//
	private static void testAggiornaProprietario(ProprietarioService proprietarioService) throws Exception {
		System.out.println("......testUpdateProprietario inizio........");

		Proprietario test = new Proprietario();
		test.setNome("ale");
		proprietarioService.aggiorna(test);

		if (test.getNome() == null)
			throw new Exception();

		System.out.println("......testUpdateProprietario fine........");
	}

	//
	private static void testCercaErrori(ProprietarioService proprietarioService, AutomobileService automobileService)
			throws Exception {
		System.out.println("......testCercaErrori inizio........");
		List<Proprietario> listaProprietari = proprietarioService.listAllProprietari();
		if (listaProprietari.isEmpty())
			throw new RuntimeException(
					"testCercaTuttiMinorenni fallito: non ci sono municipi a cui collegarci ");

		Automobile nuovaAutomobile = new Automobile("prv", "prv", "prv");
		// lo lego al primo municipio che trovo
		nuovaAutomobile.setProprietario(listaProprietari.get(0));

		// salvo i nuovi abitante
		automobileService.inserisciNuovo(nuovaAutomobile);

		//automobileService.cercaErrori();
		List<Automobile> errori= automobileService.cercaErrori();
	}

}
