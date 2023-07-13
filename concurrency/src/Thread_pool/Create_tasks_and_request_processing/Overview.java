package Thread_pool.Create_tasks_and_request_processing;

public class Overview {
    /*
        작업 생성
            - 작업은 Runnable 또는 Callable 구현 클래스로 표현한다.
            - 전자는 작업 완료 이후 리턴 값이 없지만, 후자는 리턴 값이 존재한다.

    // Runnable
    Runnable task = new Runnable() {
        @Override
        public void run() {
        // 스레드가 처리할 내용
        }
    }

    // Callable
    Callable<T> task = new Callable<T>() {
        @Override
        public T call() throws Exception {
            // 스레드가 처리할 내용
            return T;
        }
    }
    */

    /*
    작업 처리 요청
        - ExecutorService의 작업 큐에 Runnable 또는 Callable 객체를 넣는 행위를 말한다.
        - 작업 처리 요청을 위해 ExecutorService는 2가지 메소드를 제공한다.
            - execute(Runnable command)
                => void 리턴 타입
                => Runnable을 작업 큐에 저장하고, 작업 처리 결과를 받지 못함
                => 예외가 발생하면 해당 스레드를 스레드 풀에서 제거함

            -submit(Runnable task), submit(Runnable task, V result), submit(Callable task)
                => Future 리턴 타입
                => Runnable 또는 Callable을 작업 큐에 저장
                => 리턴된 Future를 통해 작업 처리 결과를 알 수 있음
                => 예외가 발생하더라도 스레드는 종료되지 않고 다른 작업에 재사용될 수 있음
                => 가급적 이 메소드를 사용하는 것을 추천

     */
}
