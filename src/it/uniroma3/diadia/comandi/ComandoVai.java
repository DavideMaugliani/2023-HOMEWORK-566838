package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOconsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.giocatore.Giocatore;

public class ComandoVai implements Comando{
	
	private IO io;
	private String parametro;
	private final static String Nome="vai";
	
	
	@Override
	public void esegui(Partita partita) {
		Stanza stanzaCorrente = partita.getStanzaCorrente();
		Stanza prossimaStanza = null;
		if(this.parametro==null) {
			this.io.mostraMessaggio("Specificare una direzione...");
			return;
		}
		prossimaStanza=stanzaCorrente.getStanzaAdiacente(this.parametro);
		if(prossimaStanza==null) {
			this.io.mostraMessaggio("Direzione inesistente");
			return;
		}
		partita.setStanzaCorrente(prossimaStanza);
		this.io.mostraMessaggio(partita.getStanzaCorrente().getDescrizione());
		Giocatore giocatore=partita.getGiocatore();
		giocatore.setCfu(giocatore.getCfu()-1);
	}
	
	@Override
	public void setParametro(String parametro) {
		this.parametro=parametro;
	}
	
	@Override
	public String getNome() {
		return Nome;
	}
	
	@Override
	public String getParametro() {
		return this.parametro;
	}

	@Override
	public void setIO(IO io) {
		this.io=io;
	}

}
