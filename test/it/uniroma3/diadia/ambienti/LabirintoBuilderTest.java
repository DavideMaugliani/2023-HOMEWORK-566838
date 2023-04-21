package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class LabirintoBuilderTest {
	
	LabirintoBuilder builder;
	Labirinto labirinto;
	
	@Before
	public void setUp() throws Exception{
		builder=new LabirintoBuilder();
	}

	@Test
	public void testAddStanzaIniziale() {
		labirinto=builder.addStanzaIniziale("stanza").getLabirinto();
		assertNotNull(builder.getStanze().get("stanza"));
		assertEquals(labirinto.getStanzaIniziale().getNome(),"stanza");
	}
	
	@Test
	public void testAddStanzaVincente() {
		labirinto=builder.addStanzaVincente("vincente").getLabirinto();
		assertNotNull(builder.getStanze().get("vincente"));
		assertEquals(labirinto.getStanzaVincente().getNome(),"vincente");
	}
	
	@Test
	public void testAddStanza() {
		labirinto=builder.addStanza("stanza").getLabirinto();
		assertNotNull(builder.getStanze().get("stanza"));
	}
	
	@Test
	public void testAddStanzaMagica() {
		labirinto=builder.addStanzaMagica("magica",3).getLabirinto();
		assertNotNull(builder.getStanze().get("magica"));
	}
	
	@Test
	public void testAddStanzaBuia() {
		labirinto=builder.addStanzaBuia("buia","lanterna").getLabirinto();
		assertNotNull(builder.getStanze().get("buia"));
	}
	
	@Test
	public void testAddStanzaBloccata() {
		labirinto=builder.addStanzaBloccata("bloccata","nord","chiave").getLabirinto();
		assertNotNull(builder.getStanze().get("bloccata"));
	}
	
	@Test
	public void testAddStanzaAdiacenza() {
        labirinto= builder.addStanzaIniziale("iniziale").addStanza("bagno").addAdiacenza("iniziale", "bagno", "nord").getLabirinto();
        assertEquals(labirinto.getStanzaIniziale().getStanzaAdiacente("nord").getNome(),"bagno");
    }
	
	@Test
	public void testAddAttrezzo() {
		labirinto=builder.addStanzaIniziale("stanza").addAttrezzo("forchetta", 1).getLabirinto();
		assertTrue(labirinto.getStanzaIniziale().hasAttrezzo("forchetta"));
	}

}
