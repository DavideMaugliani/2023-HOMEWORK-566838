package it.uniroma3.diadia.comandi;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.IOSimulator;
import it.uniroma3.diadia.IOconsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.LabirintoBuilder;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.fixture.Fixture;
import it.uniroma3.diadia.giocatore.Borsa;

public class ComandoPosaTest {
	
	private static final String Attrezzo_da_posare="AttrezzoDaPosare";
	private ComandoPosa comandoPosa;
	private Partita partita;
	
	@Before
	public void setUp() throws Exception{
		this.comandoPosa=new ComandoPosa();
		this.comandoPosa.setIO(new IOconsole());
		this.partita=new Partita();
		Borsa borsa=partita.getGiocatore().getBorsa();
		Attrezzo attrezzoNuovo=new Attrezzo(Attrezzo_da_posare, 1);
		borsa.addAttrezzo(attrezzoNuovo);
	}

	@Test
	public void testEseguiAttrezzoPresente() {
		this.comandoPosa.setParametro(Attrezzo_da_posare);
		this.comandoPosa.esegui(partita);
		assertTrue(partita.getStanzaCorrente().hasAttrezzo(Attrezzo_da_posare));
		assertFalse(partita.getGiocatore().getBorsa().hasAttrezzo(Attrezzo_da_posare));
	}
	
	@Test
	public void testEseguiAttrezzoNonPresente() {
		String nonPresente="attrezzoNonPresente";
		this.comandoPosa.setParametro(nonPresente);
		this.comandoPosa.esegui(partita);
		assertFalse(partita.getStanzaCorrente().hasAttrezzo(nonPresente));
		assertFalse(partita.getStanzaCorrente().hasAttrezzo(Attrezzo_da_posare));
		assertTrue(partita.getGiocatore().getBorsa().hasAttrezzo(Attrezzo_da_posare));
	}
	
	@Test
	public void testEseguiPosaInUnaStanza() {
		List<String> comandi=new ArrayList<>();
		comandi.add("prendi "+Attrezzo_da_posare);
		comandi.add("posa "+Attrezzo_da_posare);
		comandi.add("fine");
		
		LabirintoBuilder b=new LabirintoBuilder();
		Labirinto lab=b.addStanzaIniziale("stanza").addAttrezzo(Attrezzo_da_posare, 1).getLabirinto();
		IOSimulator io=Fixture.creaSimulazionePartitaEGioca(comandi,lab);
		
		assertEquals(io.getOutput().get(2),"Attrezzo "+Attrezzo_da_posare+" posato");
		assertTrue(lab.getStanzaIniziale().hasAttrezzo(Attrezzo_da_posare));
	}

}
