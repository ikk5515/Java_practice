package Main_Task_Thread;

import java.awt.*;


/*
    메인 스레드만 있다면 비프음을 내는 작업이 다 끝나야만 프린팅이 가능하다.
    실제로 실행해보면 비프음이 5번 발생이 발생한 이후에 출력이 되는 것을 알 수 있다.
 */
public class Only_mainThread {
    public static void main(String[] args) {
        Toolkit toolkit = Toolkit.getDefaultToolkit();

        for (int i = 0; i < 5; i++) {
            toolkit.beep();
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

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