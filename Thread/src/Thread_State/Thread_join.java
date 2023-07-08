package Thread_State;

/*
    A 스레드가 B 스레드의 일이 종료할 때까지 기다렸다가 일을 수행해야 할 수 있다.
    이때 사용하는 메소드가 join()이다.

    Main 함수에서 sumThread.join()을 통해 sumThread의 작업이 끝날 때까지 메인 스레드는 대기한다.
    만약 join() 메소드가 없다면 바로 출력문을 실행하므로 1에서 100까지 합이 0으로 출력될 것이다.
 */

public class Thread_join {

    public static void main(String[] args) {
        SumThread sumThread = new SumThread();
        sumThread.start();

        try {
            sumThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("1~100의 합 : " + sumThread.getSum());
    }
}

class SumThread extends Thread {
    private long sum;

    public long getSum() {
        return sum;
    }

    public void setSum(long sum) {
        this.sum = sum;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 100; i++) {
            sum += i;
        }
    }
}
