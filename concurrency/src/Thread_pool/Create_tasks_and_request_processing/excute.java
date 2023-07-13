package Thread_pool.Create_tasks_and_request_processing;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class excute {

    public static void main(String[] args) throws Exception {

        /*
        execute() 메소드로 작업 처리 요청한 경우
            최대 스레드 개수가 2개인 스레드 풀을 생성한 다음 10개의 작업을 요청하고, 각 작업에는 고의로 예외가 발생하는 코드를 집어 넣었다.

            => 스레드 풀의 스레드 최대 개수 2는 변함이 없지만, 실행 스레드의 이름을 보면 모두 다른 스레드가 작업을 처리하고 있다.
            이것은 작업 처리 도중 예외가 발생하면 해당 스레드를 스레드 풀에서 제거하고, 새 스레드를 생성해서 넣기 때문이다.
         */
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        for (int i = 0; i < 10; i++) {
            Runnable runnable = () -> {
                ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) executorService;
                int poolSize = threadPoolExecutor.getPoolSize();
                String threadName = Thread.currentThread().getName();
                System.out.println("[총 스레드 개수 : " + poolSize + "] 작업 스레드 이름 : " + threadName);
                int value = Integer.parseInt("삼");
            };
            executorService.execute(runnable);
            Thread.sleep(100);
        }
        executorService.shutdown();
    }
}
