package it.uniroma3.diadia.fixture;

import java.util.List;

import it.uniroma3.diadia.DiaDia;
import it.uniroma3.diadia.IOSimulator;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Fixture {
	
	public static IOSimulator creaSimulazionePartitaEGioca(List<String> righeDaLeggere) {
		//List<String> temp=Arrays.asList(righeDaLeggere);
		IOSimulator io=new IOSimulator(righeDaLeggere);
		new DiaDia(io).gioca();
		return io;
	}
	
	public static IOSimulator creaSimulazionePartitaEGioca(List<String> righeDaLeggere, Labirinto lab) {
		//List<String> temp=Arrays.asList(righeDaLeggere);
		IOSimulator io=new IOSimulator(righeDaLeggere);
		new DiaDia(io,lab).gioca();
		return io;
	}
	
	public static Attrezzo creaAttrezzoEAggiungiAStanza(Stanza stanzaDaRiempire, String nomeAttrezzo, int peso) {
		Attrezzo attrezzo=new Attrezzo(nomeAttrezzo, peso);
		stanzaDaRiempire.addAttrezzo(attrezzo);
		return attrezzo;
	}
	
}
