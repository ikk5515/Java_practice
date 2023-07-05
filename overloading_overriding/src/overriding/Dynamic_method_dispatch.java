package overriding;

class Parent1 {
    public void method1() {
        System.out.println("Parent method1()");
    }
}

class Child1 extends Parent1 {
    public void method1() {
        System.out.println("Child method1()");
    }
}
public class Dynamic_method_dispatch {
    public static void main(String[] args) {
        Parent1 parent1 = new Child1();
        parent1.method1();  // 동적 메소드 디스패치
    }
}
