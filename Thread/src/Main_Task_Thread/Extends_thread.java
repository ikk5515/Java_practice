package Main_Task_Thread;

import java.awt.*;

/*
    Thread 클래스를 상속받아 작업스레드를 생성하는 방법
    Thread 클래스를 상속받은 후 run() 메소드를 오버라이딩하여 작업스레드가 실행할 코드를 작성하고,
    작업스레드의 인스턴스를 생성한 후 start() 메소드를 호출하여 작업스레드를 실행함
 */
class BeepThread extends Thread {

    @Override
    public void run() {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        for (int i = 0; i < 5; i++) {
            toolkit.beep();
            try {
                Thread.sleep(1000);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    // 추가 로직 작성 가능
}

public class Extends_thread {
    public static void main(String[] args) {
        Thread thread = new BeepThread();
        thread.start();

        for (int i = 0; i < 5; i++) {
            System.out.println("띵");
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
