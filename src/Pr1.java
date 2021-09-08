public class Pr1 extends Thread  {

    private static final Object obj= new Object();
    private int score = 0;
    private String print;
    public Pr1(String s){
        this.print = s;
    }
    public void run(){
        while (score < 5){
            synchronized(obj){
                System.out.println(this.print);
                obj.notify();
                score++;
                try {
                    Thread.sleep(1000);
                    obj.wait();
                } catch (InterruptedException e) {}
            }
        }
    }
    public static void main(String[] args) throws InterruptedException {
        Pr1 a = new Pr1("ping");
        Pr1 b = new Pr1("pong");
        a.start();
        b.start();
        try {
            a.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}