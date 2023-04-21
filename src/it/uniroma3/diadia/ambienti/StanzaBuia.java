package it.uniroma3.diadia.ambienti;

public class StanzaBuia extends Stanza {
	
	private String nomeAttrezzoPerVedere;
	public static final String Descrizione_Stanza_Buia="Qui c'è buio pesto...";
	
	public StanzaBuia(String nome, String nomeAttrezzoPerVedere) {
		super(nome);
		this.nomeAttrezzoPerVedere=nomeAttrezzoPerVedere;
	}
	
	@Override
	public String getDescrizione() {
		if(!super.hasAttrezzo(nomeAttrezzoPerVedere))
			return Descrizione_Stanza_Buia;
		return super.getDescrizione();
	}

}
