package it.uniroma3.diadia.comandi;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import it.uniroma3.diadia.DiaDia;
import it.uniroma3.diadia.IOSimulator;
import it.uniroma3.diadia.fixture.Fixture;

public class ComandoFineTest {

	@Test
	public void testPartitaConComandoFine() {
		List<String> righeDaLeggere= new ArrayList<>();
		righeDaLeggere.add("fine");
		IOSimulator io=Fixture.creaSimulazionePartitaEGioca(righeDaLeggere);
		assertEquals(io.getOutput().get(1),"Grazie per aver giocato!");
	}

}
