package it.uniroma3.diadia.giocatore;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import it.uniroma3.diadia.attrezzi.*;

public class Borsa {
	public final static int DEFAULT_PESO_MAX_BORSA=10;
	private List<Attrezzo> attrezzi;
	private int pesoMax;
	
	public Borsa() {
		this(DEFAULT_PESO_MAX_BORSA);
	}
	
	public Borsa(int pesoMax) {
		this.pesoMax=pesoMax;
		this.attrezzi=new ArrayList<Attrezzo>();
	}
	
	public void SetAttrezzi(List<Attrezzo> attrezzi) {
		this.attrezzi=attrezzi;
	}
	
	public List<Attrezzo> getAttrezzi(){
		return this.attrezzi;
	}
	
	public boolean addAttrezzo(Attrezzo attrezzo) {
		int size=this.attrezzi.size();
		if(attrezzo!=null && this.getPeso()+attrezzo.getPeso()<=this.getPesoMax())
			this.attrezzi.add(attrezzo);
		return (this.attrezzi.size()>size);
	}
	
	public int getPesoMax() {
		return pesoMax;
	}
	
	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		Attrezzo attrezzoDaCercare=new Attrezzo(nomeAttrezzo,0);
		int index=this.attrezzi.indexOf(attrezzoDaCercare);
		if(index!=-1)
			return this.attrezzi.get(index);
		return null;
	}
	
	public int getPeso() {
		int peso=0;
		Iterator<Attrezzo> itAttrezzo=attrezzi.iterator();
		Attrezzo a=null;
		while(itAttrezzo.hasNext()) {
			a=itAttrezzo.next();
			peso=peso+a.getPeso();
		}
		return peso;
	}
	
	public boolean isEmpty() {
		return this.attrezzi.size()==0;
	}
	
	public boolean hasAttrezzo(String nomeAttrezzo) {
		return this.getAttrezzo(nomeAttrezzo)!=null;
	}
	
	public Attrezzo removeAttrezzo(String nomeAttrezzo) {
		Attrezzo attrezzoDaCercare=new Attrezzo(nomeAttrezzo,0);
		Attrezzo a=null;
		int index=attrezzi.indexOf(attrezzoDaCercare);
		if(index!=-1) {
			a=attrezzi.get(index);
			attrezzi.remove(index);
		}
		return a;
	}
	
	/*
	 *restituisce la quantità di attrezzi inseriti
	 */
	public int getNumeroAttrezzi() {
		return attrezzi.size();
	}
	
	public List<Attrezzo> getContenutoOrdinatoPerPeso(){
		Comparator<Attrezzo> cmp=new ComparatorePerPeso();
		List<Attrezzo> lista=new ArrayList<Attrezzo>(this.attrezzi);
		lista.sort(cmp);
		//Collections.sort(lista);
		return lista;
	}
	
	public SortedSet<Attrezzo> getContenutoOrdinatoPerNome(){
		ComparatorePerNome cmp=new ComparatorePerNome();
		SortedSet<Attrezzo> setAttrezziOrdinatiPerNome=new TreeSet<Attrezzo>(cmp);
		setAttrezziOrdinatiPerNome.addAll(this.attrezzi);
		return setAttrezziOrdinatiPerNome;
	}
	
	public Map<Integer,Set<Attrezzo>> getContenutoRaggruppatoPerPeso(){
		Map<Integer,Set<Attrezzo>> peso2Attrezzi;
		peso2Attrezzi=new HashMap<Integer,Set<Attrezzo>>();
		for(Attrezzo attrezzo : this.attrezzi) {
			int peso=attrezzo.getPeso();
			if(peso2Attrezzi.containsKey(Integer.valueOf(peso))){
				//Ho già visto questo peso
				Set<Attrezzo> attrezziDelloStessoPeso = peso2Attrezzi.get(peso);
				attrezziDelloStessoPeso.add(attrezzo);
			}else {
				//Mai visto questo peso
				Set<Attrezzo> nuovoSetAttrezzi=new HashSet<>();
				nuovoSetAttrezzi.add(attrezzo);
				peso2Attrezzi.put(Integer.valueOf(peso), nuovoSetAttrezzi);
			}
		}
		return peso2Attrezzi;
	}
	
	public SortedSet<Attrezzo> getSortedSetOrdinatoPerPeso(){
		ComparatorePerPeso cmp = new ComparatorePerPeso();
		SortedSet<Attrezzo> setOrdinato = new TreeSet<Attrezzo>(cmp);
		setOrdinato.addAll(this.attrezzi);
		return setOrdinato;
	}
	
	public String toString() {
		StringBuilder s=new StringBuilder();
		if(!this.isEmpty()) {
			s.append(" ("+this.getPeso()+"kg/"+this.getPesoMax()+"kg): ");
			s.append(attrezzi.toString()+" ");
	    }
		else
			s.append("Borsa vuota");
		return s.toString();
	}
		
}
