package test.com.huoteng.controller; 

import com.huoteng.controller.ThreadDemo;
import org.junit.Test;

/** 
* ThreadDemo Tester. 
* 
* @author <Authors name> 
* @since <pre>Apr 20, 2015</pre> 
* @version 1.0 
*/ 
public class ThreadDemoTest { 

    /**
    *
    * Method: run()
    *
    */
    @Test
    public void testRun() throws Exception {
        ThreadDemo demo1 = new ThreadDemo("T1", 1);
        ThreadDemo demo2 = new ThreadDemo("T2", 2);
        ThreadDemo demo3 = new ThreadDemo("T3", 3);
        ThreadDemo demo4 = new ThreadDemo("T4", 4);
        ThreadDemo demo5 = new ThreadDemo("T5", 5);
        System.out.println("当前线程数为:" + demo2.activeCount());
        demo1.start();
        demo2.start();
        demo3.start();
        demo4.start();
        demo5.start();
        System.out.println("当前线程数为:" + demo2.activeCount());

    }

} 
