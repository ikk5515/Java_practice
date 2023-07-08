package Group;

/*
    스레드 그룹을 만드는 방법은 2가지가 있다.
    부모 스레드 그릅울 명시하지 않음 (현재 스레드가 속한 하위 그룹으로 생성됨)
       ThreadGroup tg = new ThreadGroup(String name);

    부모 스레드 그룹을 명시함 (매개 변수로 넘겨 준 parent에 해당하는 스레드가 속한 그룹의 하위 그룹으로 생성됨)
        ThreadGroup tg = new ThreadGroup(ThreadGroup parent, String name);


    스레드 그룹의 interrupt() 메소드를 호출하면 해당 그룹에 속한 모든 스레드에
    interrupt 요청을 보낼 수 있다.
 */

public class Thread_Group_all_interrupt {

    public static void main(String[] args) {
        ThreadGroup myGroup = new ThreadGroup("myGroup");
        WorkThread workThreadA = new WorkThread(myGroup, "workThreadA");
        WorkThread workThreadB = new WorkThread(myGroup, "workThreadB");

        workThreadA.start();
        workThreadB.start();

        System.out.println("[main 스레드 그룹의 list() 메소드 출력 내용]");
        ThreadGroup mainGroup = Thread.currentThread().getThreadGroup();
        mainGroup.list();
        System.out.println();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("myGroup 스레드 그룹의 interrupt() 메소드 호출");
        myGroup.interrupt();
    }

    private static class WorkThread extends Thread{

        public WorkThread (ThreadGroup threadGroup, String threadName) {
            super(threadGroup, threadName);
        }

        @Override
        public void run() {
            while (true) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println(getName() + " interrupted");
                    break;
                }
            }
            System.out.println(getName() + "종료됨");
        }
    }
}
