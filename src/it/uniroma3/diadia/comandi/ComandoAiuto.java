package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOconsole;
import it.uniroma3.diadia.Partita;

public class ComandoAiuto implements Comando {

	private IO io;
	private final String[] elencoComandi;
	private final static String Nome="aiuto";
	
	public ComandoAiuto(String[] elencoComandi) {
		this.elencoComandi=elencoComandi;
	}
	
	@Override
	public void esegui(Partita partita) {
		for(int i=0; i< elencoComandi.length; i++) 
			io.mostraMessaggio(elencoComandi[i]+" ");
		io.mostraMessaggio("");
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
