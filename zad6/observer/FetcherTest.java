package observer;
//import static org.junit.Assert.*;

import java.net.URL;
import java.net.URLConnection;
import java.util.Date;
import main.Lista;

import org.junit.Test;

import static org.mockito.Mockito.*;

public class FetcherTest {

	@Test
	public void test() {
		
		
	   Lista list = new Lista();
	   Lista spy = spy(list);
	 
	   //optionally, you can stub out some methods:
	   when(spy.size()).thenReturn(100);
	 
	   //using the spy calls real methods
	   spy.add("one");
	   spy.add("two");
	 
	   //prints "one" - the first element of a list
	   System.out.println(spy.get(0));
	 
	   //size() method was stubbed - 100 is printed
	   System.out.println(spy.size());
	 
	   //optionally, you can verify
	   verify(spy).add("one");
	   verify(spy).add("two");
		
	}
	
	@Test
	public void Test2() {

		try {
			URL url = new URL("http://www.4lc.pl");
			URLConnection urlconnection = url.openConnection();
			URLConnection spy = spy(urlconnection);
			
			Date d = new Date();
			
			when(spy.getLastModified()).thenReturn(d.getTime());
			
			Fetcher f = new Fetcher(spy);
			f.run();
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		

	}

}
