package test.com.huoteng.view; 

import com.huoteng.view.MainView;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After; 

/** 
* MainView Tester. 
* 
* @author <Authors name> 
* @since <pre>Apr 21, 2015</pre> 
* @version 1.0 
*/ 
public class MainViewTest {

   public static final int SIZE_WIDTH = 800;
   public static final int SIZE_HEIGHT = 600;
/**
*
* Method: getBtns_up()
*
*/
@Test
public void testMainView() throws Exception {
   MainView test = new MainView();
   test.getElevatorContainer().setSize(800, 600);
   test.getElevatorContainer().setVisible(true);
}



} 
