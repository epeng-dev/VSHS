package net.quber.vshs;

import java.io.IOException;
import java.util.Map;

import fi.iki.elonen.NanoHTTPD;
import fi.iki.elonen.NanoHTTPD.Response.Status;

public class WebServer extends NanoHTTPD {
	 public <T> WebServer(int port) throws IOException {
         super(port);
         start(NanoHTTPD.SOCKET_READ_TIMEOUT, false);
         System.out.println("\nRunning! Point your browsers to http://localhost:8080/ \n");
     }
 
     @Override
     public Response serve(IHTTPSession session) {
         String msg = "<html><body><h1>Hello server</h1>\n";
         Map<String, String> parms = session.getParms();
         
         System.out.println("URL: " + session.getUri());
         if (parms.get("username") == null) {
             msg += "<form action='?' method='get'>\n  <p>Your name: <input type='text' name='username'></p>\n" + "</form>\n";
         } else {
             msg += "<p>Hello, " + parms.get("username") + "!</p>";
         }
         
         return newFixedLengthResponse(Status.NOT_FOUND, NanoHTTPD.MIME_HTML, msg + "</body></html>\n");
     }
}
