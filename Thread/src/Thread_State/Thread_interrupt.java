package Thread_State;

/*
    interrupt()메소드는 스레드가 일시 정지 상태에 있을 때 InterruptedException 예외를 발생시킨다.
    이를 이용하여 프로그래머는 catch 블록에서 스레드를 정상 종료하거나,
    Thread.interrupted() 메소드를 통해 현재 스레드가 interrupt 요청을 받았는지 확인하여 스레드를 종료할 수 있다.

    현재 스레드에 대해 interrupt()메소드가 실행 되었는지 확인하고,
    실행 된 상태라면 true를 반환하여 무한 루프를 탈출하는 로직이다.
 */

public class Thread_interrupt {
    public static void main(String[] args) {
        Thread thread = new PrintThread();
        thread.start();

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        thread.interrupt();
    }

    private static class PrintThread extends Thread {

        @Override
        public void run() {
            try {
                while (true) {
                    System.out.println("실행 중");
                    Thread.sleep(1);
                }
            } catch (InterruptedException e) {
            }
            System.out.println("자원 정리");
            System.out.println("실행 종료");

        }
    }
}
