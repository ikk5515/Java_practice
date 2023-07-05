package overriding;

class Parent {
    public void method1() {
        System.out.println("Parent method1()");
    }
}

class Child extends Parent {
    @Override
    public void method1() {
        System.out.println("Child method1()");
    }
}

public class Static_method_dispatch {
    public static void main(String[] args) {
        Child child = new Child();
        child.method1();    // 정적 메소드 디스패치
    }
}
