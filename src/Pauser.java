
public class Pauser {
	private boolean isPaused = false;
	public synchronized void pause(){
		System.out.println("pause");
		
	     isPaused=true;
	     System.out.println(isPaused);
	}

	public synchronized void resume(){
	  isPaused=false;
	  notifyAll();
	}

	public synchronized void look(){
	    while(isPaused){
	        try {
	        	System.out.println("dddd");
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	      }
	  }
}
