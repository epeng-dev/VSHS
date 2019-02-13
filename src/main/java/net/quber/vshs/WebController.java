package net.quber.vshs;

import fi.iki.elonen.NanoHTTPD.IHTTPSession;
import fi.iki.elonen.NanoHTTPD.Method;
import fi.iki.elonen.NanoHTTPD.Response;
import fi.iki.elonen.NanoHTTPD.Response.Status;

public class WebController {
	@RequestMapping(method=Method.GET, url="/")
    public Response index(IHTTPSession session) {
    	System.out.println("index is called");
    	return HttpTool.getTextResponce(Status.OK, "Hello World");
	}
}
