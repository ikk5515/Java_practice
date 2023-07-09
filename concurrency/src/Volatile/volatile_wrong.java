package Volatile;

/*
    메인 스레드가 1초 후 stopRequested 변수를 true로 설정하기 때문에
    backgroundThread는 1초 후 반복문을 빠져나올 것처럼 보일 것이다.

    그러나 실제로 실행시키면 backgroundThread는 영원히 반복문을 빠져나오지 않는다.

    CPU 코어가 각각의 캐시를 가지고 있기 때문에 메인 스레드가 stopRequested를 true로 설정하더라도
    backgroundThread는 이를 인지하지 못한다.

    이를 해결하기 위해서는 stopRequested 변수를 volatile로 선언해야 한다.
    volatile 변수는 항상 가장 최근에 기록된 값을 읽도록 보장한다.

 */

public class volatile_wrong {

    private static boolean stopRequested;

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
