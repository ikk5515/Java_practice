package Main_Task_Thread;

import javax.tools.Tool;
import java.awt.*;

public class Main_Task_Thread {
    public static void main(String[] args) {
        /*
            메인스레드에서 작업스레드를 생성하여 작업스레드가 작업을 수행하게 함
            thread.start()를 호출하는 순간 새로운 작업 스레드가 비프음을 발생하는 일을 시작하고,
            메인스레드는 for문을 수행하면서 띵을 출력함
         */

        Thread thread = new Thread(() -> {
            Toolkit toolkit = Toolkit.getDefaultToolkit();

            for (int i = 0; i < 5; i++) {
                toolkit.beep();
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
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
