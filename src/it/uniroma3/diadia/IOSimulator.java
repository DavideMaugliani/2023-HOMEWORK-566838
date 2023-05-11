package it.uniroma3.diadia;

import java.util.LinkedList;
import java.util.List;

public class IOSimulator implements IO {
	
	private List<String> output;
	private List<String> input;
	
	
	public IOSimulator(List<String> righeDaLeggere) {
		this.input=righeDaLeggere;
		this.output=new LinkedList<>();
	}

	@Override
	public void mostraMessaggio(String msg) {
		this.output.add(msg);
	}

	@Override
	public String leggiRiga() {
		if(!this.input.isEmpty())
			return this.input.remove(0);
		return null;
	}
	
	public String nextMessaggio() {
		if(hasNextMessaggio())
			return this.output.remove(0);
		return null;
	}
	
	public boolean hasNextMessaggio() {
		return !this.output.isEmpty();
	}
	
	public List<String> getOutput(){
		return output;
	}

}
