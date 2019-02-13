package net.quber.vshs;

import java.io.IOException;

import fi.iki.elonen.NanoHTTPD.Method;
import fi.iki.elonen.NanoHTTPD.Response;

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
			mWebServer = new WebServer(8080, WebController.class);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

}
