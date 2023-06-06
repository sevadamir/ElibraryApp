package az.orient.elibrarydemoboot.schedule;

public class MyThread extends Thread{
    @Override
    public void run() {
        try {
            while (true) {
                System.out.println("Hello world");
                Thread.sleep(3000);
        }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
