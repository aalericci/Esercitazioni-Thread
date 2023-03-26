public class Count extends Thread{
	private int tempo = 0;
	private volatile boolean bb=true;
	private volatile boolean wait=false;
	Timer t;
	
	public Count(Timer t){
		this.t=t;
	}

	public void run(){
		while(bb){
			while(wait){

			}
			try {
				tempo++;
				Timer.label.setText("Secondi: "+tempo); 
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void termina(){
		bb=false;
	}

	public void attendi(){
		wait=true;
	}

	public void riprendi(){
		wait=false;
	}
}
