package it.uniroma3.diadia.giocatore;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class BorsaTest {
	Borsa borsa;
	Attrezzo attrezzo;

	@Before
	public void setUp() throws Exception {
		borsa= new Borsa();
		attrezzo= new Attrezzo("osso",1);
	}
	
	@Test
	public void testAddAttrezzo() {
		
		assertTrue(borsa.addAttrezzo(attrezzo));
	}
	
	@Test
	public void testAddAttrezzo2() {

		Attrezzo pesante= new Attrezzo("piombo",100);
		
		assertFalse(borsa.addAttrezzo(pesante));
	}
	
	@Test
	public void testAddAttrezzo3() {

		for(int i=0; i<10; i++)
		borsa.addAttrezzo(attrezzo);

		assertFalse(borsa.addAttrezzo(attrezzo));
	}

	@Test
	public void testGetAttrezzo() {
		
		borsa.addAttrezzo(attrezzo);
		assertEquals(attrezzo,borsa.getAttrezzo("osso"));
	}

	
	@Test
	public void testRemoveAttrezzo() {
		
		borsa.addAttrezzo(attrezzo);
		borsa.removeAttrezzo("osso");
		
		assertFalse(borsa.hasAttrezzo("osso"));
	}
	
	@Test
	public void testAttrezziConStessoPesoNomeDiverso() {
		Attrezzo attrezzo=new Attrezzo("libro",1);
		Attrezzo attrezzoStessoPesoFuoriOrdine=new Attrezzo("spada",1);
		this.borsa.addAttrezzo(attrezzoStessoPesoFuoriOrdine);
		this.borsa.addAttrezzo(attrezzo);
		assertEquals(Arrays.asList(attrezzo,attrezzoStessoPesoFuoriOrdine),this.borsa.getContenutoOrdinatoPerPeso());
	}
	
	@Test
	public void testGetContenutoOrdinatoPerNome() {
		Attrezzo attrezzo=new Attrezzo("libro",1);
		ArrayList<Attrezzo> attrezzi=new ArrayList<>();
		attrezzi.add(attrezzo);
		borsa.SetAttrezzi(attrezzi);
		
		SortedSet<Attrezzo> set=borsa.getContenutoOrdinatoPerNome();
		
		attrezzi.clear();
		attrezzi.addAll(set);
		
		assertEquals(attrezzi.get(0),attrezzo);
	}
	
	@Test
	public void testGetContenutoOrdinatoPerNome2() {
		Attrezzo attrezzo=new Attrezzo("unguento",1);
		Attrezzo attrezzo2=new Attrezzo("spada",3);
		Attrezzo attrezzo3=new Attrezzo("libro",1);
		ArrayList<Attrezzo> attrezzi=new ArrayList<>();
		attrezzi.add(attrezzo);
		attrezzi.add(attrezzo2);
		attrezzi.add(attrezzo3);
		borsa.SetAttrezzi(attrezzi);
		
		SortedSet<Attrezzo> set=borsa.getContenutoOrdinatoPerNome();
		
		attrezzi.clear();
		attrezzi.addAll(set);
		
		assertEquals(attrezzi.get(0),attrezzo3);
		assertEquals(attrezzi.get(1),attrezzo2);
		assertEquals(attrezzi.get(2),attrezzo);
	}
	
	@Test
	public void testGetContenutoOrdinatoPerPesoRaggruppatoConSingoloAttrezzoBorsaVuota() {
		assertTrue(this.borsa.getContenutoRaggruppatoPerPeso().isEmpty());
		//assertEquals(Collections.emptyMap(),this.borsa.getContenutoRaggruppatoPerPeso());
	}
	
	@Test
	public void testGetContenutoOrdinatoPerPesoRaggruppatoConSingoloAttrezzoSingleton() {
		Attrezzo attrezzo=new Attrezzo("attrezzo",1);
		this.borsa.addAttrezzo(attrezzo);
		Map<Integer, Set<Attrezzo>> singletonMap = Collections.singletonMap(1, Collections.singleton(attrezzo));
		assertEquals(singletonMap,this.borsa.getContenutoRaggruppatoPerPeso());
	}
	
	@Test
	public void testGetContenutoOrdinatoPerPesoRaggruppatoConSingoloAttrezzoSingleton_DueAttrezzi() {
		int stessoPeso = 1;
		Attrezzo attrezzo=new Attrezzo("attrezzo",stessoPeso);
		Attrezzo attrezzoStessoPeso=new Attrezzo("attrezzoStessoPeso",stessoPeso);
		this.borsa.addAttrezzo(attrezzo);
		this.borsa.addAttrezzo(attrezzoStessoPeso);
		//Set<Attrezzo> insiemeAttrezziStessoPeso = Set.of(attrezzo,attrezzoStessoPeso); //Da java9+
		Set<Attrezzo> insiemeAttrezziStessoPeso = new HashSet<>(Arrays.asList(attrezzo,attrezzoStessoPeso));
		Map<Integer, Set<Attrezzo>> singletonMap = Collections.singletonMap(stessoPeso, insiemeAttrezziStessoPeso);
		assertEquals(singletonMap,this.borsa.getContenutoRaggruppatoPerPeso());
	}
	
	@Test
	public void testGetContenutoOrdinatoPerPesoRaggruppatoConSingoloAttrezzoDoubleton_DueAttrezzi() {
		Attrezzo attrezzo=new Attrezzo("attrezzo",1);
		Attrezzo attrezzoAltroPeso=new Attrezzo("attrezzoAltroPeso",2);
		this.borsa.addAttrezzo(attrezzo);
		this.borsa.addAttrezzo(attrezzoAltroPeso);
		Map<Integer, Set<Attrezzo>> doubletonMap = new HashMap<>();
		doubletonMap.put(1, Collections.singleton(attrezzo));
		doubletonMap.put(2, Collections.singleton(attrezzoAltroPeso));
		assertEquals(doubletonMap,this.borsa.getContenutoRaggruppatoPerPeso());
	}
	
	@Test
	public void testGetContenutoOrdinatoPerPesoRaggruppatoConSingoloAttrezzo() {
		Attrezzo a1=new Attrezzo("sedia",2);
		this.borsa.addAttrezzo(a1);
		Map<Integer,Set<Attrezzo>> listaAttrezziOrdinataPerPeso=this.borsa.getContenutoRaggruppatoPerPeso();
		assertTrue(listaAttrezziOrdinataPerPeso.get(2).contains(a1));
	}

}
