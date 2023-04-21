package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOconsole;
import it.uniroma3.diadia.Partita;

public class ComandoFine implements Comando {

	private IO io;
	public static final String Messaggio_finale="Grazie per aver giocato!";
	public final static String Nome="fine";
	
	@Override
	public void esegui(Partita partita) {
		partita.setFinita();
		io.mostraMessaggio(Messaggio_finale);
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
