package it.uniroma3.diadia.giocatore;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class GiocatoreTest {
	Giocatore giocatore;

	@Before
	public void setUp() throws Exception {
		giocatore= new Giocatore();
	}

	@Test
	public void testSetCfu() {
		
		giocatore.setCfu(5);
		assertTrue(giocatore.getCfu()== 5);
	}

}
