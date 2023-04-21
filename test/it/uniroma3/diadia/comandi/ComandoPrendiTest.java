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

public class ComandoPrendiTest {

	private Partita partita;
	private Stanza stanza;
	private ComandoPrendi comandoPrendi;
	
	@Before
	public void setUp() throws Exception {
		stanza= new Stanza("stanza");
		
		partita= new Partita();
		partita.setStanzaCorrente(stanza);
		
		Fixture.creaAttrezzoEAggiungiAStanza(stanza, "osso", 1);
		
		comandoPrendi= new ComandoPrendi();
		
		this.comandoPrendi.setIO(new IOconsole());
	}

	@Test
	public void testPrendiAttrezzoPresente() {
		comandoPrendi.setParametro("osso");
		comandoPrendi.esegui(this.partita);
		
		assertTrue(this.partita.getGiocatore().getBorsa().hasAttrezzo("osso"));
		assertFalse(stanza.hasAttrezzo("osso"));
	}
	
	@Test
	public void testPrendiAttrezzoNonPresente() {
		comandoPrendi.setParametro("spada");
		comandoPrendi.esegui(this.partita);
		
		assertFalse(this.partita.getGiocatore().getBorsa().hasAttrezzo("spada"));
		assertTrue(stanza.hasAttrezzo("osso"));
	}
	
	@Test
	public void testPrendiAttrezzoStanzaVuota() {
		
		Stanza stanza2= new Stanza("seconda");
		partita.setStanzaCorrente(stanza2);
		
		this.comandoPrendi.setParametro("osso");
		this.comandoPrendi.esegui(this.partita);
		
		assertFalse(partita.getGiocatore().getBorsa().hasAttrezzo("osso"));
	}
	
	@Test
	public void testPrendiAttrezzoBorsaPesante() {
		
		Attrezzo attrezzo= new Attrezzo("pane",10);
		this.partita.getGiocatore().getBorsa().addAttrezzo(attrezzo);
	
		this.comandoPrendi.setParametro("osso");
		this.comandoPrendi.esegui(this.partita);
		
		assertFalse(partita.getGiocatore().getBorsa().hasAttrezzo("osso"));
		assertTrue(stanza.hasAttrezzo("osso"));
	}
	
	@Test
	public void testEseguiPosaInUnaStanza() {
		Attrezzo attrezzo=new Attrezzo("scudo", 2);
		List<String> comandi=new ArrayList<>();
		comandi.add("prendi scudo");
		comandi.add("fine");
		
		LabirintoBuilder b=new LabirintoBuilder();
		Labirinto lab=b.addStanzaIniziale("stanza").addAttrezzo("scudo", 2).getLabirinto();
		IOSimulator io=Fixture.creaSimulazionePartitaEGioca(comandi,lab);
		
		assertEquals(io.getOutput().get(1),"Attrezzo scudo preso!");
		assertFalse(lab.getStanzaIniziale().hasAttrezzo("scudo"));
	}

}