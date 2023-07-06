package synchronized_method_block;

/*
    User1이 계산기에 100을 입력하고 잠시 후에 User2가 50을 입력한다.
    그러면 User1이 보는 결과는 50이므로 의도한 결과와 다르게 나온다.
 */

public class Sharing_Object_wrong {
    public static void main(String[] args) {
        Calculator calculator = new Calculator();

        User1 user1 = new User1();
        user1.setCalculator(calculator);
        user1.start();

        User2 user2 = new User2();
        user2.setCalculator(calculator);
        user2.start();
    }
}

class Calculator {

    private int memory;

    public int getMemory() {
        return memory;
    }


    public void setMemory(int memory) {
        this.memory = memory;

        try {
            Thread.sleep(2000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " : " + this.memory);
    }
}

class User1 extends Thread {
    private Calculator calculator;

    public void setCalculator(Calculator calculator) {
        this.setName("User1");
        this.calculator = calculator;
    }

    @Override
    public void run() {
        calculator.setMemory(100);
    }
}

class User2 extends Thread {
    private Calculator calculator;

    public void setCalculator(Calculator calculator) {
        this.setName("User2");
        this.calculator = calculator;
    }

    @Override
    public void run() {
        calculator.setMemory(50);
    }
}