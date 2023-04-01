package it.uniroma3.diadia.giocatore;

import it.uniroma3.diadia.attrezzi.*;

public class Borsa {
	private static int PESO_MAX= 20;
	private static int ATTREZZI_MAX= 10;
	private int peso;
	private Attrezzo[] attrezzi;
	private int numeroAttrezzi;

	public int getNumeroAttrezzi() {
		return numeroAttrezzi;
	}

	public void aumentaNumeroAttrezzi() {
		this.numeroAttrezzi ++;
	}

	public void diminuisciNumeroAttrezzi() {
		this.numeroAttrezzi --;
	}

	public Borsa() {
		this.peso= 0;
		this.numeroAttrezzi= 0;
		this.attrezzi= new Attrezzo[ATTREZZI_MAX];
	}

	public int getPESO_MAX() {
		return PESO_MAX;
	}

	public Attrezzo getAttrezzo(String attrezzo) { //ritorna il riferimento di un solo attrezzo
		for(int i=0; i< this.numeroAttrezzi; i++)
			if(attrezzi[i]!= null)
				if(attrezzi[i].getNome().equals(attrezzo)) {
					return this.attrezzi[i];
				}
		return null;
	}

	public Attrezzo[] getAttrezzi() { //ritorna il riferimento all'array di attrezzi
		return attrezzi;
	}

	public boolean addAttrezzo(Attrezzo attrezzo) { //assegna un attrezzo
		
		if(this.numeroAttrezzi==ATTREZZI_MAX || this.peso+attrezzo.getPeso()> PESO_MAX) //ho raggiunto il limite di attrezzi o di peso
			return false;

		this.attrezzi[this.numeroAttrezzi]= attrezzo; //aggiungo l'attrezzo in coda all'array
		this.numeroAttrezzi++;
		this.peso+= attrezzo.getPeso(); //aggiorno il eso totale della borsa
		return true;
	}

	public void setAttrezzi(Attrezzo[] attrezzi) { //assegna un array di attrezzi
		this.attrezzi = attrezzi;
	}

	public void removeAttrezzo(String attrezzo) {
		for(int i=0; i< this.numeroAttrezzi; i++)
			if(this.attrezzi[i]!= null)
				if(attrezzi[i].getNome().equals(attrezzo))
				{
					this.peso -= attrezzi[i].getPeso(); //aggiorno il eso totale della borsa
					this.attrezzi[i] = null;
					return;
				}
	}

	public boolean hasAttrezzo(String nomeAttrezzo) {
		return this.getAttrezzo(nomeAttrezzo)!=null;
	}

	public int getPeso() {
		return peso;
	}

	public void setPeso(int peso) {
		this.peso = peso;
	}
}