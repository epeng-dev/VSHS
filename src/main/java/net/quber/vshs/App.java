package net.quber.vshs;

import java.io.IOException;

/**
 * Hello world!
 *
 */
public class App
{	
	private static WebServer mWebServer;
	
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        
        try {
			mWebServer = new WebServer(8080);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
