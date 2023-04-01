package it.uniroma3.diadia;


import java.util.Scanner;

import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

/**
 * Classe principale di diadia, un semplice gioco di ruolo ambientato al dia.
 * Per giocare crea un'istanza di questa classe e invoca il letodo gioca
 *
 * Questa e' la classe principale crea e istanzia tutte le altre
 *
 * @author  docente di POO 
 *         (da un'idea di Michael Kolling and David J. Barnes) 
 *          
 * @version base
 */

public class DiaDia {

	static final private String MESSAGGIO_BENVENUTO = ""+
			"Ti trovi nell'Universita', ma oggi e' diversa dal solito...\n" +
			"Meglio andare al piu' presto in biblioteca a studiare. Ma dov'e'?\n"+
			"I locali sono popolati da strani personaggi, " +
			"alcuni amici, altri... chissa!\n"+
			"Ci sono attrezzi che potrebbero servirti nell'impresa:\n"+
			"puoi raccoglierli, usarli, posarli quando ti sembrano inutili\n" +
			"o regalarli se pensi che possano ingraziarti qualcuno.\n\n"+
			"Per conoscere le istruzioni usa il comando 'aiuto'.";
	
	static final private String[] elencoComandi = {"vai", "prendi", "posa", "aiuto", "fine"};

	private Partita partita;
	private IOConsole io;

	public DiaDia() {
		this.partita = new Partita();
		this.io=new IOConsole();
	}

	public void gioca() {
		String istruzione; 

		io.mostraMessaggio(MESSAGGIO_BENVENUTO);		
		do		
			istruzione = io.leggiRiga();
		while (!processaIstruzione(istruzione));
	}   


	/**
	 * Processa una istruzione 
	 *
	 * @return true se l'istruzione e' eseguita e il gioco continua, false altrimenti
	 */
	private boolean processaIstruzione(String istruzione) {
		Comando comandoDaEseguire = new Comando(istruzione);
		
		final String nome = comandoDaEseguire.getNome();

		if(nome==null) {
			return false;
		}
		
		if (nome.equals("fine")) {
			this.fine(); 
			return true;
		} 
		else if (nome.equals("vai"))
			this.vai(comandoDaEseguire.getParametro());
		
		else if (nome.equals("aiuto"))
			this.aiuto();
		
		else if (nome.equals("prendi"))
			this.prendi(comandoDaEseguire.getParametro());
		
		else if (nome.equals("posa"))
			this.posa(comandoDaEseguire.getParametro());
		
		else
			io.mostraMessaggio("Comando sconosciuto");
		
		if (this.partita.vinta()) {
			io.mostraMessaggio("Hai vinto!");
			return true;
		} 
		else if(this.partita.isFinita()){
			io.mostraMessaggio("Hai esaurito i CFU, hai perso...");
			return true;
		}
		else
			return false;
	}   

	// implementazioni dei comandi dell'utente:
	private void prendi(String Attrezzo) {

		if(Attrezzo == null) {
			io.mostraMessaggio("Completa il comando con l' attrezzo che vuoi prendere");
			return;
		}

		if(!this.partita.getStanzaCorrente().hasAttrezzo(Attrezzo)) //se la stanza NON contiene l'attrezzo desiderato
		{
			io.mostraMessaggio(Attrezzo+" non presente..."); //stampa un messaggio di errore
			return;
		}

		Attrezzo attrezzo= this.partita.getStanzaCorrente().getAttrezzo(Attrezzo); //altrimenti salva l'attrezzo in una variabile
		this.partita.getGiocatore().getBorsa().addAttrezzo(attrezzo); //aggiungi l'attrezzo alla borsa

		this.partita.getStanzaCorrente().removeAttrezzo(attrezzo); //rimuovi l'attrezzo dalla stanza corrente

		this.partita.getGiocatore().getBorsa().aumentaNumeroAttrezzi(); //aumenta il numero di attrezzi nella borsa

		io.mostraMessaggio("Ho raccolto l'attrezzo "+Attrezzo);
	}
	
	private void posa(String Attrezzo) {
		if(Attrezzo == null) {
			io.mostraMessaggio("Completa il comando con l' attrezzo che vuoi posare");
			return;
		}

		if(!this.partita.getGiocatore().getBorsa().hasAttrezzo(Attrezzo)) //se l'lattrezzo desiderato NON è presente nella borsa
		{
			io.mostraMessaggio(Attrezzo+" non presente nella borsa"); //stampa un messaggio di errore
			return;
		}

		Attrezzo attrezzo= this.partita.getGiocatore().getBorsa().getAttrezzo(Attrezzo); //salva l'attrezzo in una variabile per aggiungerlo successivamente alla stanza

		this.partita.getGiocatore().getBorsa().removeAttrezzo(Attrezzo); //rimuovi l'attrezzo dalla borsa

		this.partita.getStanzaCorrente().addAttrezzo(attrezzo); //aggiungi l'attrezzo alla stanza corrente

		this.partita.getGiocatore().getBorsa().diminuisciNumeroAttrezzi(); //diminuisci il numero di attrezzi nella borsa

		io.mostraMessaggio("Ho posato l'attrezzo "+Attrezzo);
	}

	/**
	 * Stampa informazioni di aiuto.
	 */
	private void aiuto() {
		for(int i=0; i< elencoComandi.length; i++) 
			io.mostraMessaggio(elencoComandi[i]+" ");
		io.mostraMessaggio("");
	}

	/**
	 * Cerca di andare in una direzione. Se c'e' una stanza ci entra 
	 * e ne stampa il nome, altrimenti stampa un messaggio di errore
	 */
	private void vai(String direzione) {
		if(direzione==null)
			io.mostraMessaggio("Dove vuoi andare ?");
		Stanza prossimaStanza = null;
		prossimaStanza = this.partita.getStanzaCorrente().getStanzaAdiacente(direzione);
		if (prossimaStanza == null)
			io.mostraMessaggio("Direzione inesistente");
		else {
			this.partita.setStanzaCorrente(prossimaStanza);
			int cfu = this.partita.getGiocatore().getCfu();
			cfu--;
			this.partita.getGiocatore().setCfu(cfu);
		}
		io.mostraMessaggio(partita.getStanzaCorrente().getDescrizione());
		io.mostraMessaggio("Cfu rimanenti: "+partita.getGiocatore().getCfu());
	}

	/**
	 * Comando "Fine".
	 */
	private void fine() {
		io.mostraMessaggio("Grazie di aver giocato!");  // si desidera smettere
	}

	public static void main(String[] argc) {
		DiaDia gioco = new DiaDia();
		gioco.gioca();
	}
}