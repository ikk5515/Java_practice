package Thread_State;

/*
    StatePrintThread
        - StatePrintThread는 스레드의 상태를 출력한다.
        - 스레드가 NEW 상태면 RUNNABLE 상태로 만들어 주고, 스레드가 TERMINATED 상태면 무한 루프를 종료한다.

    TargetThread
        - TargetThread는 StatePrintThread의 타겟 스레드로서, 10억번 루핑 후 1초간 TIMED_WAITING 상태로 들어가고 다시 10억번 루핑한다.
 */

public class Basic_Thread_State {
    public static void main(String[] args) {
        StatePrintThread statePrintThread = new StatePrintThread(new TargetThread());
        statePrintThread.start();
    }
}

class StatePrintThread extends Thread {
    private final Thread targetThread;

    public StatePrintThread(Thread targetThread) {
        this.targetThread = targetThread;
    }

    @Override
    public void run() {
        while (true) {
            Thread.State state = targetThread.getState();
            System.out.println("타켓 스레드 상태 : " + state);

            if (state == State.NEW) {
                targetThread.start();
            }

            if (state == State.TERMINATED) {
                break;
            }

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}


class TargetThread extends Thread {

    @Override
    public void run() {
        for (long i = 0; i < 1000000000; i++) {
        }

        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (long i = 0; i < 1000000000; i++) {

        }
    }
}
