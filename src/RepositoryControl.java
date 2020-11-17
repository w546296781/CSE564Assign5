public class RepositoryControl {
    public void run(){
        Repository repository = Repository.getInstance();
        Pauser pauser = new Pauser();
        for(int i = 1; i <= repository.getCountry().size();i++){
            Thread thread = new Thread(new TspShortest(i,pauser));
            thread.start();
        }
    }

    public void stop(){

    }
}