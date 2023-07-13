package Thread_pool.Create_tasks_and_request_processing;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/*
    블로킹 방식의 작업 완료 통보
        - submit() 메소드는 매개 값으로 넘긴 Runnable 또는 Callable 작업을 스레드 풀의 작업 큐에 저장하고 즉시 Future 객체를 리턴한다.
        - Future 객체는 작업이 완료될 때까지 기다렸다가 최종 결과를 얻는데 사용된다.
            => Future 객체는 이름에서 알 수 있듯이 작업 결과를 나타내지 않는다.
            => 그래서 Future를 지연 완료 객체라고 부른다.

        - Future의 get() 메소드를 호출하면 스레드가 작업을 완료할 때까지 블로킹되었다가 작업을 완료하면 처리 결과를 리턴한다.
        - Future를 이용한 블로킹 방식의 작업 완료 통보에서 주의할 점은
        작업을 처리하는 스레드가 작업을 완료하기 전까지는 get() 메소드가 블로킹되므로 다른 코드를 실행할 수 없다.
            => 만약 UI를 변경하고 이벤트를 처리하는 스레드가 get() 메소드를 호출하면 작업을 완료하기 전까지 UI를 변경할 수 없다.
            => 그래서 get() 메소드를 호출하는 스레드는 별도의 스레드여야 한다. (새로운 스레드를 만들거나, 스레드 풀의 스레드에게 작업을 요청)
 */


public class submit {
    public static void main(String[] args) throws Exception {

        /*
        submit() 메소드로 작업 처리 요청한 경우
            => 예외가 발생하더라도 기존 스레드를 재활용하여 작업을 처리한다.
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
            executorService.submit(runnable);
            Thread.sleep(100);
        }
        executorService.shutdown();
    }
}
