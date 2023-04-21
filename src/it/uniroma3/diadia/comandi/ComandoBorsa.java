package it.uniroma3.diadia.comandi;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoBorsa implements Comando {

	private IO io;
	private String param;
	private List<Attrezzo> list;
	private SortedSet<Attrezzo> set;
	private Map<Integer,Set<Attrezzo>> map;
	StringBuilder descrizione;

	@Override
	public void setParametro(String param) {
		this.param= param;
	}

	@Override
	public void setIO(IO io) {
		this.io= io;
	}

	@Override
	public void esegui(Partita partita) {

		descrizione= new StringBuilder();

		String out="Completa il comando con un parametro valido \n"
				+"nome: stampa gli attrezzi ordinati per nome \n"
				+"peso: stampa gli attrezzi ordinati per peso \n"
				+"gruppo: stampa gli attrezzi raggruppati per peso \n";
		
		if(param==null){
			io.mostraMessaggio(out);
			return;
		}
		
		if(param.equals("peso")) {
			list= partita.getGiocatore().getBorsa().getContenutoOrdinatoPerPeso();
			descrizione.append("Elenco degli attrezzi ordinati per peso:");
			
			for(Attrezzo attrezzo: this.list)
				descrizione.append(" -"+attrezzo);
		}

		else if(param.equals("nome")) {
			set= partita.getGiocatore().getBorsa().getContenutoOrdinatoPerNome();
			descrizione.append("Elenco degli attrezzi ordinati per nome:");
			
			for(Attrezzo attrezzo: this.set)
				descrizione.append(" -"+attrezzo);
		}

		else if(param.equals("gruppo")) {
			map= partita.getGiocatore().getBorsa().getContenutoRaggruppatoPerPeso();
			descrizione.append("Elenco degli attrezzi raggruppati per peso:");
			
			for(Integer i: map.keySet()) {
				descrizione.append("\n"+i+"-");
				descrizione.append(map.get(i));
			}
		}
		
		else {
			io.mostraMessaggio(out);
			return;
		}

		if(partita.getGiocatore().getBorsa().getAttrezzi().isEmpty()) {
			descrizione.append(" Non si sono attrezzi nella borsa");
		}

		io.mostraMessaggio(descrizione.toString());
	}


	@Override
	public String getParametro() {
		return this.param;
	}

	@Override
	public String getNome() {
		return "borsa";
	}

}