package Mutual_semaphore_monitor;

/*
    세마포어
        - 세마포어 변수를 통해 wait, signal을 관리한다. 세마포어 변수는 0 이상의 정수형 변수를 갖는다.
        - n개의 공유 자원에 대한 접근을 제한할 수 있으며 이를 계수 세마포어라고 한다.
        - 접근 가능한 공유 자원으 수가 1개일 때는 이진 세마포어로 뮤텍스처럼 사용할 수 있다.
        - 큐에 연결된 스레드를 깨우는 방식에 따라 강성 세마포어 (큐에 연결된 스레드를 깨울 때 FIFO 정책),
          약성 세마포어 (큐에 연결된 스레드를 꺠울 때 순서를 특별히 명시하지 않음)로 구분된다.
        - 세마포어는 Signaling 메커니즘으로 Lock을 걸지 않은 스레드도 Signal을 보내 Lock을 해제할 수 있다.

    세마포어로 동시 접근 제어
    synchronized의 경우 오직 하나의 스레드만 수행 가능하다면,
    세마포어는 동시에 실행할 수 있는 스레드의 수를 제어할 수 있다.
 */

import java.util.concurrent.Semaphore;

public class Semaphore_ex {

    private final Semaphore semaphore;
    private final int maxThread;

    public Semaphore_ex(int maxThread) {
        this.maxThread = maxThread;
        this.semaphore = new Semaphore(maxThread);
    }

    public void use() {
        try {
            semaphore.acquire();
            System.out.println("[" + Thread.currentThread().getName() + "]" +
                    "개의 스레드가 점유중");
            Thread.sleep(1000);
            semaphore.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Semaphore_ex semaphoreEx = new Semaphore_ex(3);
        for (int i = 1; i <= 10; i++) {
            Thread t = new Thread(semaphoreEx::use);
            t.start();
        }
    }
}
