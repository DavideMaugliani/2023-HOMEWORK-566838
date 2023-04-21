package it.uniroma3.diadia.comandi;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOSimulator;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.LabirintoBuilder;
import it.uniroma3.diadia.fixture.Fixture;

public class LabirintiTest {
	LabirintoBuilder builder= new LabirintoBuilder();
	IOSimulator io;
	Labirinto lab1,lab2;
	String msgFinale= "Grazie per aver giocato!";

	@Before
	public void setUp() throws Exception {		
		lab1= builder.addStanzaIniziale("iniziale").addStanza("adiacente").addAttrezzo("lanterna", 1)
				.addAdiacenza("iniziale", "adiacente", "nord").getLabirinto();
		
		lab2= builder.addStanzaBuia("buia", "lanterna").addAdiacenza("adiacente","buia","nord").getLabirinto();
	}

	@Test
	public void testLabirintoDueStanze() {
		List<String> comandi= new ArrayList<>();
		comandi.add("vai nord");
		comandi.add("prendi lanterna");
		comandi.add("vai sud");
		comandi.add("posa lanterna");
		comandi.add("fine");
		
		/*descrizioni delle stanze prima dell'esecuzione dei comandi*/
		String d1= lab1.getStanzaIniziale().getStanzaAdiacente("nord").getDescrizione();
		String d2= lab1.getStanzaIniziale().getDescrizione();
		
		io= Fixture.creaSimulazionePartitaEGioca(comandi,lab1);
		
		assertEquals(d1, io.getOutput().get(1));
		assertEquals("Attrezzo lanterna preso!", io.getOutput().get(2));
		assertEquals(d2, io.getOutput().get(3));
		assertEquals("Attrezzo lanterna posato", io.getOutput().get(4));
		assertTrue(lab1.getStanzaIniziale().hasAttrezzo("lanterna"));
		assertEquals("Attrezzo lanterna posato", io.getOutput().get(4));
		assertEquals(msgFinale, io.getOutput().get(5));
	}
	
	@Test
	public void testLabirintoConStanzaBuia() {
		List<String> comandi= new ArrayList<>();
		comandi.add("vai nord");
		comandi.add("vai nord");
		comandi.add("vai sud");
		comandi.add("prendi lanterna");
		comandi.add("vai nord");
		comandi.add("posa lanterna");
		comandi.add("guarda");
		comandi.add("fine");
		
		/*descrizioni delle stanze prima dell'esecuzione dei comandi*/
		String d1= lab2.getStanzaIniziale().getStanzaAdiacente("nord").getDescrizione();
		String d2= lab2.getStanzaIniziale().getStanzaAdiacente("nord").getStanzaAdiacente("nord").getDescrizione();
		
		io= Fixture.creaSimulazionePartitaEGioca(comandi,lab2);
		
		assertEquals(d1, io.getOutput().get(1));
		assertEquals(d2, io.getOutput().get(2),"Qui c'è buio pesto...");
		assertEquals(d1, io.getOutput().get(3));
		assertEquals("Attrezzo lanterna preso!", io.getOutput().get(4));
		assertFalse(lab2.getStanzaIniziale().getStanzaAdiacente("nord").hasAttrezzo("lanterna"));
		assertEquals(d2, io.getOutput().get(5),"Qui c'è buio pesto...");
		assertEquals("Attrezzo lanterna posato", io.getOutput().get(6));
		assertTrue(lab2.getStanzaIniziale().getStanzaAdiacente("nord").getStanzaAdiacente("nord").hasAttrezzo("lanterna"));
		assertNotEquals(lab2.getStanzaIniziale().getStanzaAdiacente("nord").getStanzaAdiacente("nord").getDescrizione(), "Qui c'e buio pesto...");
	}

}