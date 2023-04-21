package it.uniroma3.diadia.comandi;


import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOconsole;



public class FabbricaDiComandiFisarmonicaTest {
	
	Comando comandoDaEseguire;
	IO io;
	
	

	@Before
	public void setUp() throws Exception {
		this.io = new IOconsole();
	}
	
	public boolean testFabbricaDiComandi(String istruzione,String testComando,String testParametro){
		
		FabbricaDiComandi factory = new FabbricaDiComandiFisarmonica();
		comandoDaEseguire = factory.costruisciComando(istruzione,io);
		comandoDaEseguire.setParametro(testParametro);
		return(comandoDaEseguire.getNome().equals(testComando) && comandoDaEseguire.getParametro()==testParametro);
	}
	
	@Test
	public void testVai(){
		assertTrue(testFabbricaDiComandi("vai","vai","sud"));
	}

	@Test
	public void testPrendi(){
		assertTrue(testFabbricaDiComandi("prendi","prendi","osso"));
	}
	
	@Test
	public void testPosa(){
		assertTrue(testFabbricaDiComandi("posa","posa","osso"));
	}

	@Test
	public void testGuarda(){
		assertTrue(testFabbricaDiComandi("guarda","guarda",null));
	}

	@Test
	public void testAiuto(){
		assertTrue(testFabbricaDiComandi("aiuto","aiuto",null));
	}
	
	@Test
	public void testFine(){
		assertTrue(testFabbricaDiComandi("fine","fine",null));
	}
	
	@Test
	public void testNonValido(){
		assertTrue(testFabbricaDiComandi("","non valido",null));

	}
	
}