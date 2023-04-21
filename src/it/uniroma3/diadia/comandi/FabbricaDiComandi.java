package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOconsole;

public interface FabbricaDiComandi {
	
	public Comando costruisciComando(String istruzione, IO io);
}
