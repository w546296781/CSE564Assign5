public class RepositoryControl {
    public void run(){
        Repository repository = Repository.getInstance();
        for(int i = 1; i <= repository.getCountry().size();i++){
            Thread thread = new Thread(new TspShortest(i));
            thread.start();
        }
    }

    public void stop(){

    }
}