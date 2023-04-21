package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOconsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoPosa implements Comando {

	private IO io;
	private String nomeAttrezzo;
	private final static String Nome="posa";
	
	@Override
	public void esegui(Partita partita) {
		if(getParametro() == null) {
			io.mostraMessaggio("Completa il comando con l' attrezzo che vuoi posare");
			return;
		}

		if(!partita.getGiocatore().getBorsa().hasAttrezzo(getParametro())) {
			io.mostraMessaggio("Attrezzo "+getParametro()+" non presente nella borsa");
			return;
		}
		Attrezzo attrezzo=partita.getGiocatore().getBorsa().getAttrezzo(getParametro());
		
		if(partita.getStanzaCorrente().addAttrezzo(attrezzo)) {
			partita.getGiocatore().getBorsa().removeAttrezzo(getParametro());
			//partita.getGiocatore().getBorsa().diminuisciNumAttrezzi();
			io.mostraMessaggio("Attrezzo "+getParametro()+" posato");
		}
		else
			io.mostraMessaggio("La stanza è piena...");
	}

	@Override
	public void setParametro(String parametro) {
		this.nomeAttrezzo=parametro;
	}

	@Override
	public void setIO(IO io) {
		this.io=io;
	}

	@Override
	public String getParametro() {
		return this.nomeAttrezzo;
	}

	@Override
	public String getNome() {
		return Nome;
	}

}
