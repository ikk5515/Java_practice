package Thread_pool.Task_completion_notification;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

// Runnable 객체로 작업을 생성
public class without_return_value {

    /*
        리턴 값이 없고 단순히 1부터 10까지 합을 출력하는 작업을 Runnable 객체로 생성하고,
        스레드 풀의 스레드가 처리하도록 요청함
     */

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

        System.out.println("작업 처리 요청");
        Runnable runnable = () -> {
            int sum = 0;
            for (int i = 1; i <= 10; i++) {
                sum += i;
            }
            System.out.println("[처리 결과] " + sum);
        };
        Future<?> future = executorService.submit(runnable);

        try {
            future.get();
            System.out.println("작업 처리 완료");
        } catch (Exception e) {
            System.out.println("[실행 예외 발생항] " + e.getMessage());
        }
        executorService.shutdown();
    }
}
