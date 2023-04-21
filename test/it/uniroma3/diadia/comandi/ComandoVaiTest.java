package it.uniroma3.diadia.comandi;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.DiaDia;
import it.uniroma3.diadia.IOSimulator;
import it.uniroma3.diadia.IOconsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.LabirintoBuilder;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.fixture.Fixture;

public class ComandoVaiTest {
	
	private static final String Nome_Stanza_Partenza="Partenza";
	private static final String Nord="nord";
	private Partita partita;
	private Comando comandoVai;
	private Stanza partenza;
	
	@Before
	public void setUp() throws Exception{
		this.comandoVai=new ComandoVai();
		this.comandoVai.setIO(new IOconsole());
		this.partita=new Partita();
		this.partenza=new Stanza(Nome_Stanza_Partenza);
		this.partita.setStanzaCorrente(this.partenza);
	}

	@Test
	public void testVaiStanzaNonPresente() {
		this.comandoVai.setParametro(Nord);
		this.comandoVai.esegui(this.partita);
		assertEquals(Nome_Stanza_Partenza, this.partita.getStanzaCorrente().getNome());
	}
	
	@Test
	public void testVaiStanzaPresente() {
		Stanza destinazione=new Stanza("Destinazione");
		this.partenza.impostaStanzaAdiacente(Nord, destinazione);
		this.comandoVai.setParametro(Nord);
		this.comandoVai.esegui(partita);
		assertEquals("Destinazione", this.partita.getStanzaCorrente().getNome());
	}
	
	@Test
	public void testVaiStanzaPresenteInDirezioneSbagliata() {
		Stanza destinazione=new Stanza("Destinazione");
		this.partenza.impostaStanzaAdiacente("sud", destinazione);
		this.comandoVai.setParametro(Nord);
		this.comandoVai.esegui(partita);
		assertEquals(Nome_Stanza_Partenza, this.partita.getStanzaCorrente().getNome());
	}
	
	@Test
	public void testPartitaComandoVaiUnaStanza() {
		List<String> comandoDaEseguire= new ArrayList<>();
		comandoDaEseguire.add("vai sud");
		comandoDaEseguire.add(ComandoFine.Nome);
		LabirintoBuilder b=new LabirintoBuilder();
		Labirinto lab=b.addStanzaIniziale("iniziale").getLabirinto();
		IOSimulator io=Fixture.creaSimulazionePartitaEGioca(comandoDaEseguire,lab);
		assertEquals(io.getOutput().get(1),"Direzione inesistente");
	}
	
	@Test
	public void testPartitaComandoVaiDueStanze() {
		List<String> comandoDaEseguire= new ArrayList<>();
		comandoDaEseguire.add("vai sud");
		comandoDaEseguire.add(ComandoFine.Nome);
		LabirintoBuilder b=new LabirintoBuilder();
		Labirinto lab=b.addStanzaIniziale("iniziale").addStanza("bagno").addAdiacenza("iniziale", "bagno", "sud").getLabirinto();
		IOSimulator io=Fixture.creaSimulazionePartitaEGioca(comandoDaEseguire,lab);
		assertEquals(io.getOutput().get(1),lab.getStanzaIniziale().getStanzaAdiacente("sud").getDescrizione());
	}
	
	@Test
	public void testPartitaComandoVaiTreStanze() {
		List<String> comandoDaEseguire= new ArrayList<>();
		comandoDaEseguire.add("vai sud");
		comandoDaEseguire.add("vai nord");
		comandoDaEseguire.add("vai ovest");
		comandoDaEseguire.add(ComandoFine.Nome);
		LabirintoBuilder b=new LabirintoBuilder();
		Labirinto lab=b.addStanzaIniziale("iniziale").addStanza("bagno").addAdiacenza("iniziale", "bagno", "sud")
				.addStanza("cameretta").addAdiacenza("iniziale", "cameretta", "ovest").getLabirinto();
		IOSimulator io=Fixture.creaSimulazionePartitaEGioca(comandoDaEseguire,lab);
		assertEquals(io.getOutput().get(1),lab.getStanzaIniziale().getStanzaAdiacente("sud").getDescrizione());
		assertEquals(io.getOutput().get(2),lab.getStanzaIniziale().getDescrizione());
		assertEquals(io.getOutput().get(3),lab.getStanzaIniziale().getStanzaAdiacente("ovest").getDescrizione());
	}
	
	public void assertContains(String expected, String interaRiga) {
		assertTrue(interaRiga.contains(expected));
	}
	
}
