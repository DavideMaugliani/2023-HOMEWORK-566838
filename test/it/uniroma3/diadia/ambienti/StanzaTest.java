package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.*;
import it.uniroma3.diadia.attrezzi.*;

import org.junit.Before;
import org.junit.Test;

public class StanzaTest {
	
	private Stanza stanza;
	
	@Before
	public void setUp() throws Exception {
		stanza= new Stanza("principale");
	}

	@Test
	public void testImpostaStanzaAdiacente() { 

		Stanza stanza_adiacente= new Stanza("adiacente");

		stanza.impostaStanzaAdiacente("nord", stanza_adiacente);

		Stanza prova= stanza.getStanzaAdiacente("nord");

		assertEquals(stanza_adiacente, prova);
	}

	@Test
	public void  testImpostaStanzaAdiacente2() {

		Stanza stanza_adiacente= new Stanza("adiacente");

		stanza.impostaStanzaAdiacente("sud", stanza_adiacente);

		Stanza prova= stanza.getStanzaAdiacente("nord");

		assertNotEquals(stanza_adiacente, prova);
	}

	@Test
	public void  testImpostaStanzaAdiacente3() {

		Stanza stanza_adiacente= new Stanza("adiacente");

		stanza.impostaStanzaAdiacente("nord", stanza_adiacente);

		Stanza prova= stanza.getStanzaAdiacente("nord");

		assertNotNull(prova);
	}

	@Test
	public void  testImpostaStanzaAdiacente4() {

		Stanza stanza_adiacente1= new Stanza("nord1");
		stanza.impostaStanzaAdiacente("nord", stanza_adiacente1);

		Stanza stanza_adiacente2= new Stanza("nord2");
		stanza.impostaStanzaAdiacente("nord", stanza_adiacente2);

		Stanza prova= stanza.getStanzaAdiacente("nord");

		assertEquals(prova,stanza_adiacente2);
	}

	@Test
	public void testAddAttrezzo() {

		Attrezzo attrezzo= new Attrezzo("osso",3);

		stanza.addAttrezzo(attrezzo);

		Attrezzo prova= stanza.getAttrezzo("osso");

		assertEquals(prova, attrezzo);
	}

	@Test
	public void testAddAttrezzo2() {

		Attrezzo attrezzo= new Attrezzo("osso",3);

		stanza.addAttrezzo(attrezzo);

		Attrezzo prova= stanza.getAttrezzo("osso");

		assertNotNull(prova);
	}

	@Test
	public void testAddAttrezzo3() {

		Attrezzo attrezzo1= new Attrezzo("osso",3);
		Attrezzo attrezzo2= new Attrezzo("osso",3);
		Attrezzo attrezzo3= new Attrezzo("osso",3);
		Attrezzo attrezzo4= new Attrezzo("osso",3);

		Stanza stanza= new Stanza("principale");

		stanza.addAttrezzo(attrezzo1);
		stanza.addAttrezzo(attrezzo2);
		stanza.addAttrezzo(attrezzo3);

		assertFalse(stanza.addAttrezzo(attrezzo4));
	}

	@Test
	public void testHasAttrezzo() {

		Attrezzo attrezzo= new Attrezzo("osso",3);
		stanza.addAttrezzo(attrezzo);

		assertTrue(stanza.hasAttrezzo("osso"));
	}

	public void testHasAttrezzo2() {

		Attrezzo attrezzo= new Attrezzo("osso",3);
		stanza.addAttrezzo(attrezzo);

		assertFalse(stanza.hasAttrezzo("pane"));
	}

	public void testHasAttrezzo3() {

		Attrezzo attrezzo1= new Attrezzo("osso",3);
		Attrezzo attrezzo2= new Attrezzo("pane",3);

		stanza.addAttrezzo(attrezzo1);
		stanza.addAttrezzo(attrezzo2);

		assertTrue(stanza.hasAttrezzo("pane") && stanza.hasAttrezzo("osso"));
	}

	@Test
	public void testRemoveAttrezzo() {

		Attrezzo attrezzo= new Attrezzo("osso",3);

		stanza.addAttrezzo(attrezzo);

		assertTrue(stanza.removeAttrezzo(attrezzo));
	}

	@Test
	public void testRemoveAttrezzo2() {

		Attrezzo attrezzo= new Attrezzo("osso",3);

		stanza.addAttrezzo(attrezzo);

		Attrezzo non_presente= new Attrezzo("pane",3);

		assertFalse(stanza.removeAttrezzo(non_presente));
	}

	@Test
	public void testRemoveAttrezzo3() {

		Attrezzo attrezzo= new Attrezzo("osso",3);

		stanza.addAttrezzo(attrezzo);

		stanza.removeAttrezzo(attrezzo);

		assertFalse(stanza.hasAttrezzo("osso"));
	}


	@Test
	public void testRemoveAttrezzo4() {

		Attrezzo attrezzo1= new Attrezzo("osso",3);
		stanza.addAttrezzo(attrezzo1);

		Attrezzo attrezzo2= new Attrezzo("pane",3);
		stanza.addAttrezzo(attrezzo2);

		stanza.removeAttrezzo(attrezzo1);

		assertTrue(stanza.hasAttrezzo("pane"));
	}
}