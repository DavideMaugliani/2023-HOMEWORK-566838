package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOconsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoPrendi implements Comando {

	private IO io;
	private String nomeAttrezzo;
	private final static String Nome="prendi";

	@Override
	public void esegui(Partita partita) {
		if(getParametro()==null) {
			io.mostraMessaggio("Completa il comando...");
			return;
		}
		if(!partita.getStanzaCorrente().hasAttrezzo(getParametro())) {
			io.mostraMessaggio(getParametro()+" non presente...");
			return;
		}
		Attrezzo attrezzo=partita.getStanzaCorrente().getAttrezzo(getParametro());
		
		if(partita.getGiocatore().getBorsa().addAttrezzo(attrezzo)) {
			partita.getStanzaCorrente().removeAttrezzo(attrezzo);
			//partita.getGiocatore().getBorsa().aumentaNumAttrezzi();
			io.mostraMessaggio("Attrezzo "+getParametro()+" preso!");
		}
		else
			io.mostraMessaggio("Non c'è più spazio nella borsa...");
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
