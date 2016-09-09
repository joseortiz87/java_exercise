package mx.com.joortizs.les16.nested;

public class NestedClassesMain {
    
    public static void main(String[] args) {
        
        OuterClass co = new OuterClass();
        co.method1();
        co.method2();
        
        co.r.run();
        
        OuterClass.InnerClass i = co.new InnerClass();
        i.innerPrint();
        
        OuterClass.StaticNestedClass sn = new OuterClass.StaticNestedClass();
        sn.staticNestedPrint();
        
        co.new A().new B();
    }
}