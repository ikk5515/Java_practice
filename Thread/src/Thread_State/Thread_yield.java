package Thread_State;

/*
    yield() 메소드를 호출한 스레드는 실행 대기 상태로 돌아가고, 실행 대기 상태의 스레드들 중에서 우선순위가 동일하거나 높은 다른 스레드가 실행 상태로 바뀐다.
    만약 실행 대기 상태의 스레드가 없으면 yield() 메소드를 호출한 스레드가 다시 실행 상태가 된다.

    ThreadA와 ThreadB모두 각각의 플래그를 가지고 있고, 이 플래그에 따라 출력 내용이 추가되거나 무한 루프를 탈출한다.
    run() 메소드를 보면 work가 false 일 때 yield()를 호출하여 다른 스레드에게 CPU 제어권을 넘기는 것을 알 수 있다.

    그래서 main() 메소드에서 초반에는 두 스레드가 번갈아가면서 CPU 를 얻다가 ThreadA 의 work 가 false 가 되면 yield() 메소드를 호출하여 ThreadB가 CPU 제어권을 많이 얻도록 한다.
 */

public class Thread_yield {

    public static void main(String[] args) {
        ThreadA threadA = new ThreadA();
        ThreadB threadB = new ThreadB();

        threadA.start();
        threadB.start();

        try {
            Thread.sleep(30);
        } catch (Exception e) {
            e.printStackTrace();
        }
        threadA.work = false;

        try {
            Thread.sleep(30);
        } catch (Exception e) {
            e.printStackTrace();
        }
        threadA.work = true;

        try {
            Thread.sleep(30);
        } catch (Exception e) {
            e.printStackTrace();
        }
        threadA.stop = true;
        threadB.stop = true;
    }


    private static class ThreadA extends Thread {

        public boolean stop = false;
        public boolean work = true;

        @Override
        public void run() {
            while (!stop) {
                if (work) {
                    System.out.println("ThreadA 작업 내용");
                } else {
                    Thread.yield();
                }
            }
            System.out.println("ThreadA 작업 종료");
        }
    }

    private static class ThreadB extends Thread {
        public boolean stop = false;
        public boolean work = true;

        @Override
        public void run() {
            while (!stop) {
                if (work) {
                    System.out.println("ThreadB 작업 내용");
                } else {
                    Thread.yield();
                }
            }
            System.out.println("ThreadB 작업 종료");
        }
    }
}