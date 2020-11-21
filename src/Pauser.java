public class Pauser{

  private boolean isPaused=false;
  public synchronized void pause(){
     isPaused=true;
  }
  
  public synchronized void resume(){
    isPaused=false;
    notifyAll();
  }

  public synchronized void look(){
    while(isPaused){
        try {
			wait();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
      }
   }

}
