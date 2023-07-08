package Thread_State;

/*
    스레드 간 협업 ( wait(), notify(), notifyAll() )

    정확히 교대 작업이 필요한 상황이 있다.
    자신의 작업이 끝나면 상대방 스레드를 일시 정지 상태에서 풀어 주고, 자신은 일시 정지 상태로 만드는 것이다.

    이 방법은 공유 객체를 사용하는데, 먼저 공유 객체에 대해 두 스레드가 작업할 내용을 각각 동기화 메소드 또는 동기화 블록 처리를 한다.
    그리고 한 스레드가 작업을 완료하면 notify() 메소드를 호출해서 일시정지 상태에 있는 다른 스레드를 실행 대기 상태로 만든다.
    그리고 자신은 wait()메소드를 호출해서 일시 정지 상태로 만든다.

    만약 wait()대신 wait(long timeout)을 사용하면 timeout 시간이 지나면 자동으로 실행 대기 상태로 만들어 준다.
    참고로 notify()는 wait()에 의해 일시 정지된 스레드 중 하나를 실행 대기 상태로 만들고, notifyAll()은 wait()에 의해 일시 정지된 모든 스레드를 실행 대기 상태로 만든다.

    ThreadB를 깨워줄 A가 종료되어서 B를 종료시킬 수 없음
    프로그램을 종료할 방법이 없음
 */

public class Thread_Synchronized {

    public static void main(String[] args) {
        WorkObject sharedObject = new WorkObject();

        ThreadA threadA = new ThreadA(sharedObject);
        ThreadB threadB = new ThreadB(sharedObject);

        threadA.start();
        threadB.start();

    }
}

class WorkObject {

    public synchronized void methodA() {
        System.out.println("ThreadA의 methodA() 작업 실행");
        notify();   // 일시 정지 상태에 있는 ThreadB를 실행 대기 상태로 만듦
        try {
            wait(); // ThreadA를 일시 정지 상태로 만듦
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void methodB() {
        System.out.println("ThreadB의 methodB() 작업 실행");
        notify();   // 일시 정지 상태에 있는 ThreadA를 실행 대기 상태로 만듦
        try {
            wait(); // ThreadB를 일시 정지 상태로 만듦
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class ThreadA extends Thread {
    private WorkObject workObject;

    public ThreadA(WorkObject workObject) {
        this.workObject = workObject;
    }

    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {
            workObject.methodA();
        }
    }
}

class ThreadB extends Thread {
    private WorkObject workObject;

    public ThreadB(WorkObject workObject) {
        this.workObject = workObject;
    }

    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {
            workObject.methodB();
        }
    }
}

