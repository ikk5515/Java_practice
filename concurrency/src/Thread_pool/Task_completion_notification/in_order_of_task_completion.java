package Thread_pool.Task_completion_notification;

import java.util.concurrent.*;

/*
    작업 완료 순으로 통보
        -작업의 양과 스레드 스케줄링에 따라서 작업을 빨리 요청한다고 해서 가장 빠르게 완료되지 않을 수 있다.
        - 만약 여러 개의 작업이 순차적으로 처리될 필요 없고, 처리 결과도 꼭 순차적으로 이용할 필요가 없다면 작업 처리가 완료된 것부터 결과를 얻어 이용하면 된다.
        - CompletionService의 poll() 메소드 또는 take() 메소드를 사용한다.
            => poll() : 완료된 작업의 Future를 가져오되, 완료된 작업이 하나도 없다면 null을 리턴
            => take() : 완료된 작업의 Future를 가져오되, 완료된 작업이 없다면 블로킹

        - 아래 예제는 3개의 Callable 작업을 처리 요청하고 처리가 완료되는 순으로
        작업의 결과 값을 출력하도록 하였다.

        Future<Integer> future = completionService.take();
        int value = future.get();
        - 특히 위 코드는 completionService.take() 메소드가 완료된 작업이 있을 때까지
        블록킹하므로 다음 줄의 future.get()은 블로킹 없이 곧바로 리턴된다.
 */

public class in_order_of_task_completion {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

        CompletionService<Integer> completionService = new ExecutorCompletionService<>(executorService);

        System.out.println("[작업 처리 요청]");

        for (int i = 0; i < 3; i++) {
            completionService.submit(() -> {
                int sum = 0;
                for (int j = 1; j <= 10; j++) {
                    sum += j;
                }
                return sum;
            });
        }

        System.out.println("[처리 완료된 작업 확인]");
        executorService.submit(() -> {
            while (true) {
                try {
                    Future<Integer> future = completionService.take();
                    int value = future.get();
                    System.out.println("[처리 결과] " + value);
                } catch (Exception e) {
                    break;
                }
            }
        });

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        executorService.shutdownNow();
    }
}
