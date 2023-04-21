package it.uniroma3.diadia;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.ambienti.*;

public class PartitaTest {

	private Partita partita;

	@Before
	public void setUp() throws Exception {

		partita= new Partita();

	}

	@Test
	public void testSetStanzaCorrente() {

		Stanza nuova= new Stanza("nuova");
		partita.setStanzaCorrente(nuova);

		assertEquals(nuova, partita.getStanzaCorrente());
	}

	@Test
	public void testVinta() {

		partita.getLabirinto().setStanzaVincente(partita.getStanzaCorrente());
		assertTrue(partita.vinta());
	}

	@Test
	public void testIsFinita() {

		partita.setFinita();
		assertTrue(partita.isFinita());
	}

	@Test
	public void testIsFinita2() {

		partita.getLabirinto().setStanzaVincente(partita.getStanzaCorrente());

		assertTrue(partita.isFinita());
	}

	@Test
	public void testIsFinita3() {

		partita.getGiocatore().setCfu(0);

		assertTrue(partita.isFinita());
	}

}
