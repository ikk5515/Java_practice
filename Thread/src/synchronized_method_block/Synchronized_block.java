package synchronized_method_block;

/*
    멀티 스레드 프로그램에서 단 하나의 스레드만 실행할 수 있는 코드 영역을 임계 영역(critical section)이라고 한다.
    임계 영역을 지정하기 위해서는 synchronized 키워드를 사용한다.
    synchronized 키워드를 사용하면 임계 영역에 한 번에 한 스레드만 접근할 수 있다.
    임계 영역을 지정하는 방법은 두 가지가 있다.
    1. 메소드 전체를 임계 영역으로 지정하는 방법
    2. 일부 코드를 임계 영역으로 지정하는 방법
    1번 방법은 메소드 선언부에 synchronized 키워드를 붙이면 된다.
    2번 방법은 synchronized 키워드 뒤에 괄호를 붙이고 괄호 안에 임계 영역으로 지정하고자 하는 객체를 적으면 된다.
    이렇게 하면 객체의 임계 영역에만 스레드가 접근할 수 있다.
 */

public class Synchronized_block {
    public static void main(String[] args) {
        Calculator2 calculator2 = new Calculator2();

        User1 user1 = new User1();
        user1.setCalculator(calculator2);
        user1.start();

        User2 user2 = new User2();
        user2.setCalculator(calculator2);
        user2.start();
    }
}

class Calculator3 extends Calculator {
    private int memory;

    public int getMemory() {
        return memory;
    }

    @Override
    public void setMemory(int memory) {
        synchronized (this) {
            this.memory = memory;
            try {
                Thread.sleep(2000);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + ": " + this.memory);
        }
    }
}
