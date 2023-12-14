public class ThreadVoorbeeld {
    private int count = 0;


    public static void main(String[] args) {
        ThreadVoorbeeld voorbeeld = new ThreadVoorbeeld();
        InnerThread thread1 = new InnerThread(voorbeeld); //een exemplaar van de klasse Thread
        InnerThread thread2 = new InnerThread(voorbeeld);
        InnerThread thread3 = new InnerThread(voorbeeld);


        thread1.start(); //start the stream
        try {
            thread1.join(); // Wachten tot de eerste thread is voltooid voordat de tweede wordt gestart
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread2.start(); // dezeldde als boven
        try {
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread3.start();

        System.out.println("Hallo vanaf de hoofdthread "); //Checking that the code inside the thread is being executed

    }


    //Het creëren van een nieuwe innerlijke klasse
    private static class InnerThread extends Thread {
        //gebruik synchronized

        private ThreadVoorbeeld voorbeeld;

        //gesynchroniseerd
        public InnerThread(ThreadVoorbeeld voorbeeld) {
            this.voorbeeld = voorbeeld;

        }

        @Override  //De methode run() overschrijven
        public void run() {
            voorbeeld.incrementCount();
            //Code definiëren die op een thread moet worden uitgevoerd
            System.out.println("Hallo vanaf thread" + this.getId() + " ,count is nu " + voorbeeld.getCount());

            try {
                Thread.sleep(1000); //Adding a delay to the thread execution
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    public synchronized void incrementCount() {
        count++;
    }

    public int getCount() {
        return count;
    }
}
