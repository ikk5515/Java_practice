/*
    스레드 이름은 기본적으로 Thread-n 형식으로 되어있다.
    만약 스레드 이름은 모르지만 현재 실행되고 있는 스레드의 이름을 알고 싶다면 CurrentThread() 메소드를 사용하면 된다.
 */

public class Thread_name {
    public static void main(String[] args) {
        Thread mainThread = Thread.currentThread();
        System.out.println("프로그램 시작 스레드 이름 = " + mainThread.getName());

        Thread threadA = new ThreadA();
        System.out.println("작업 스레드 이름 = " + threadA.getName());
        threadA.start();

        Thread threadB = new ThreadB();
        System.out.println("작업 스레드 이름 = " + threadB.getName());
        threadB.start();
    }

    private static class ThreadA extends Thread {
        public ThreadA() {
            setName("ThreadA");
        }

        @Override
        public void run() {
            for (int i = 0; i < 2; i++) {
                System.out.println(getName() + "가 출력한 내용");
            }
        }
    }

    private static class ThreadB extends Thread {

//        public ThreadB() {
//            setName("ThreadB");
//        }
        @Override
        public void run() {
            for (int i = 0; i < 2; i++) {
                System.out.println(getName() + "가 출력한 내용");
            }
        }
    }
}
