package Thread_pool.Task_completion_notification;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/*
    작업 처리 결과를 외부 객체에 저장
        - 스레드가 작업한 결과를 외부 객체에 저장해야 할 경우가 있다.
        ex) 두 개 이상의 스레드가 작업 처리를 완료하고 외부 Result 객체에 작업 결과를 저장하면,
        Result 공유 객체가 작업들을 취합하여 애플리케이션에게 알릴 수 있다.
        그리면 애플리케이션은 해당 정보를 통해 어떤 작업을 처리할 수 있다.

        - submit(Runnable task, V result) 메소드를 사용하면 2번째 인자를 반환 받을 수 있다.
        즉, 위 예제에서는 Result 객체를 집어 넣고 작업을 하면서 Result의 상태를 바꾸면 되는 것이다.
 */
public class external_object {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());


        System.out.println("[작업 처리 요청]");
        Result result = new Result();

        Runnable task1 = new Task(result);
        Runnable task2 = new Task(result);
        Future<Result> future1 = executorService.submit(task1, result);
        Future<Result> future2 = executorService.submit(task2, result);

        try {
            result = future1.get();
            result = future2.get();
            System.out.println("[처리 결과] " + result.accumValue);
            System.out.println("[작업 처리 완료]");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("[실행 예외 발생] " + e.getMessage());
        }
        executorService.shutdown();

    }

    private static class Result {
        int accumValue;

        public synchronized void addValue(int value) {
            this.accumValue += value;
        }
    }

    private static class Task implements Runnable {
        private final Result result;
        public Task(Result result) {
            this.result = result;
        }

        @Override
        public void run() {
            int sum = 0;
            for (int i = 1; i <= 10; i++) {
                sum += i;
            }
            result.addValue(sum);
        }
    }
}