package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class LabirintoTest {
	
	Labirinto labirinto;
	Stanza stanza;

	@Before
	public void setUp() throws Exception {
		labirinto=  new Labirinto();
		stanza= new Stanza("stanza");
	}

	@Test
	public void testSetStanzaVincente() {
		
		labirinto.setStanzaVincente(stanza);
		assertEquals(stanza, labirinto.getStanzaVincente());
	}

	@Test
	public void testSetStanzaIniziale() {

		labirinto.setStanzaIniziale(stanza);
		assertEquals(stanza, labirinto.getStanzaIniziale());
	}

}
