package Daemon_Thread;

/*
    데몬 스레드는 주 스레드의 작업을 돕는 보조 스레드로, 스레드가 종료되면 데몬 스레드는 같이 종료되는 특징이 있다.
    데몬 스레드의 적용 예로는 워드프로세서의 자동 저장, 미디어 플레이어의 동영상 및 음악 재생, 가비지 컬렉터 등이 있다.
    이 기능들은 주 스레드인 워드 프로세서, 미디어 플레이어, JVM 이 종료되면 같이 종료된다.

    스레드를 데몬 스레드로 만들어 주려면 thread 내부의 setDaemon() 메소드의 매개 변수를 true 로 하여 실행하면 된다.

    AutoSaveThread 는 메인 스레드의 데몬 스레드가 되어 보조 역할을 수행한다.
    AutoSaveThread 는 1초 주기로 작업 내용을 저장하닥 메인 스레드가 종료되면 같이 종료된다.
 */

import java.util.Map;

public class Thread_Daemon {

    public static void main(String[] args) {
        AutoSaveThread autoSaveThread = new AutoSaveThread();
        autoSaveThread.setName("AutoSaveThread");
        autoSaveThread.setDaemon(true);
        autoSaveThread.start();

        // 스레드 그룹 이름 얻기
        /*
            Thread.currentThread().getThreadGroup()를 이용하여 특정 스레드의 그룹 이름을 가져오거나
            Thread.getAllStackTraces()를 이용하여 현재 프로세스 내에서 실행하는 모든 스레드에 대한 정보를 얻어와서
            그룹 이름을 얻을 수 있다.
         */
        Map<Thread, StackTraceElement[]> threadInfos = Thread.getAllStackTraces();
        threadInfos.keySet().forEach(thread -> {
            System.out.println("NAME : " + thread.getName() + ((thread.isDaemon()) ? "(데몬)" : "(주)"));
            System.out.println("\t" + "소속그룹 : " + thread.getThreadGroup().getName());
            System.out.println();
        } );

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("메인 스레드 종료");
    }

    private static class AutoSaveThread extends Thread {

        public void save() {
            System.out.println("작업 내용을 저장함.");
        }

        @Override
        public void run() {
            while (true) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    break;
                }
                save();
            }
        }
    }
}
