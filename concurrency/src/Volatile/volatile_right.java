package Volatile;

/*
    volatile 이란 동시성 프로그래밍에서 발생할 수 있는 문제 중 하나인 가시성 문제를 해결하기 위해 사용되는 키워드이다.

    CPU cache memory와 RAM의 데이터가 서로 일치하지 않아 생기는 문제를 의미한다.
    volatile 키워드를 붙인 공유 자원은 RAM에 직접 읽고 쓰는 작업을 수행할 수 있도록 해준다.
 */

public class volatile_right {

    private static volatile boolean stopRequested;

    public static void main(String[] args) throws InterruptedException {
        Thread backgroundThread = new Thread(() -> {
            int i = 0;
            while (!stopRequested) {
                i++;
            }
        });
        backgroundThread.start();

        Thread.sleep(1000);
        stopRequested = true;
    }
}
