package it.uniroma3.diadia.ambienti;

import java.util.HashMap;
import java.util.Map;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class LabirintoBuilder {
	
	private Labirinto labirinto;
	private Map<String,Stanza> stanze;
	private Stanza ultimaStanzaInserita;
	
	public LabirintoBuilder() {
		this.labirinto=new Labirinto();
		this.stanze=new HashMap<>();
	}
	
	public Map<String,Stanza> getStanze() {
		return this.stanze;
	}
	
	public LabirintoBuilder addStanzaIniziale(String nome) {
		Stanza stanza=new Stanza(nome);
		this.labirinto.setStanzaIniziale(stanza);
		this.stanze.put(nome, stanza);
		this.ultimaStanzaInserita=stanza;
		return this;
	}
	
	public LabirintoBuilder addStanzaVincente(String nome) {
		Stanza stanza=new Stanza(nome);
		this.labirinto.setStanzaVincente(stanza);
		this.stanze.put(nome, stanza);
		this.ultimaStanzaInserita=stanza;
		return this;
	}
	
	public LabirintoBuilder addStanza(String nome) {
		Stanza stanza=new Stanza(nome);
		this.stanze.put(nome, stanza);
		this.ultimaStanzaInserita=stanza;
		return this;
	}
	
	public LabirintoBuilder addStanzaBloccata(String nome, String direzione, String attrezzo) {
		StanzaBloccata stanza=new StanzaBloccata(nome, direzione, attrezzo);
		this.stanze.put(nome, stanza);
		this.ultimaStanzaInserita=stanza;
		return this;
	}
	
	public LabirintoBuilder addStanzaBuia(String nome, String attrezzo) {
		StanzaBuia stanza=new StanzaBuia(nome, attrezzo);
		this.stanze.put(nome, stanza);
		this.ultimaStanzaInserita=stanza;
		return this;
	}
	
	public LabirintoBuilder addStanzaMagica(String nome, int soglia) {
		StanzaMagica stanza=new StanzaMagica(nome,soglia);
		this.stanze.put(nome, stanza);
		this.ultimaStanzaInserita=stanza;
		return this;
	}
	
	public LabirintoBuilder addAdiacenza(String partenza, String arrivo, String direzione) {
        this.stanze.get(partenza).impostaStanzaAdiacente(direzione, this.stanze.get(arrivo));
        String direzioneOpposta=getDirezioneOpposta(direzione);
        this.stanze.get(arrivo).impostaStanzaAdiacente(direzioneOpposta, this.stanze.get(partenza));
        return this;
    }
	
	public LabirintoBuilder addAttrezzo(String nome, int peso) {
		Attrezzo attrezzo=new Attrezzo(nome, peso);
		this.ultimaStanzaInserita.addAttrezzo(attrezzo);
		return this;
	}
	
	public Labirinto getLabirinto() {
		return this.labirinto;
	}
	
	private String getDirezioneOpposta(String direzione) {
		String direzioneOpposta=null;
		switch(direzione) {
		case "nord":
			direzioneOpposta="sud";
			break;
		case "sud":
			direzioneOpposta="nord";
			break;
		case "est":
			direzioneOpposta="ovest";
			break;
		case "ovest":
			direzioneOpposta="est";
			break;
			
		default:
			break;
		}
		return direzioneOpposta;
	}

}
