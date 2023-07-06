package Thread_Scheduling;

/* 스레드의 우선순위가 높다고 항상 우선으로 출력되는 것은 아니다.
    다만 스케줄러의 선택의 빈도를 높여주는 것이다.
    우선순위가 높은 스레드는 좀 더 빠르게 실행된다는 장점이 있다.
 */
public class Thread_priority {
    public static void main(String[] args) {
        for (int i = 1; i <= 10; i++) {
            Thread thread = new CalcThread("thread" + i);
            if (i == 10) {
                thread.setPriority(Thread.MAX_PRIORITY);
            } else {
                thread.setPriority(Thread.MIN_PRIORITY);
            }
            thread.start();
        }
    }
}

class CalcThread extends Thread {
    public CalcThread(String s) {
        setName(s);
    }
    @Override
    public void run() {
        for (int i = 0; i < 2000000000; i++) {
        }
        System.out.println(getName());
    }
}
