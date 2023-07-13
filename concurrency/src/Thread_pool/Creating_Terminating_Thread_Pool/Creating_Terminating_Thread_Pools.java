package Thread_pool.Creating_Terminating_Thread_Pool;

/*
    쓰레드 풀 종료
        - 기본적으로 스레드 풀의 스레드는 데몬 스레드가 아니므로
        main 스레드가 종료되더라도 프로세스는 계속 실행중이다.
        - 따라서 main 스레드가 종료되면 해당 스레드 풀을 종료해야 한다.


        메소드 종료
            - shutdown()
                => void 리턴 타입
                => 현재 처리 중인 작업 뿐만 아니라
                작업 큐에 대기하고 있는 모든 작업을 처리한 뒤에 스레드 풀을 종료한다.

            - shutdownNow()
                => List 리턴 타입
                => 현재 작업 처리 중인 스레드를 interrupt해서 작업 중지를 시도하고 스레드 풀을 종료한다.
                => 리턴 값은 작업 큐에 있는 미처리된 작업의 목록이다.

            - awaitTermination(long timeout, TimeUnit unit)
                => boolean 리턴 타입
                => shutdown() 메소드 호출 이후, 모든 작업 처리를 timeout 시간 내에 완료하면 true를 리턴하고,
                그렇지 않으면 작업 처리 중인 스레드를 interrupt하고 false를 리턴한다.
 */

public class Creating_Terminating_Thread_Pools {
/*
    // newCachedThreadPool()
    ExecutorService executorService = Executors.newCachedThreadPool();

    // newCachedThreadPool(int nThreads)
    ExecutorService executorService = Executors.newFixedThreadPool(
            Runtime.getRuntime().availableProcessors()
};

// 커스텀
    ExecutorService threadPool = new ThreadPoolExecutor(
        3, // 코어 스레드 개수
        100, // 최대 스레드 개수
        120L, // 최대 놀 수 있는 시간 (이 시간 넘으면 스레드 풀에서 쫓겨 남.)
        TimeUnit.SECONDS, // 놀 수 있는 시간 단위
        new SynchronousQueue<Runnable>() // 작업 큐
    );
*/
}