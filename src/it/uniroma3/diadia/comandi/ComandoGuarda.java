package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOconsole;
import it.uniroma3.diadia.Partita;

public class ComandoGuarda implements Comando {

	private IO io;
	private final static String Nome="guarda";
	
	@Override
	public void esegui(Partita partita) {
		this.io.mostraMessaggio("Stanza corrente: "+partita.getStanzaCorrente().toString());
		this.io.mostraMessaggio("Informazioni partita: "+partita.getGiocatore().getCfu());
		this.io.mostraMessaggio("Attrezzi posseduti: "+partita.getGiocatore().getBorsa().toString());
	}

	@Override
	public void setParametro(String parametro) {}

	@Override
	public void setIO(IO io) {
		this.io=io;
	}

	@Override
	public String getParametro() {
		return null;
	}

	@Override
	public String getNome() {
		return Nome;
	}
	

}