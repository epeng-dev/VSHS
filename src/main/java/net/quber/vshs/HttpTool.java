package net.quber.vshs;

import fi.iki.elonen.NanoHTTPD;
import fi.iki.elonen.NanoHTTPD.Response;
import fi.iki.elonen.NanoHTTPD.Response.Status;

public class HttpTool {
	public static Response getHtmlResponce(Status code, String html) {
		return NanoHTTPD.newFixedLengthResponse(code, NanoHTTPD.MIME_HTML, html);
	}
	
	public static Response getJsonResponce(Status code, String jsonString) {
		return NanoHTTPD.newFixedLengthResponse(code, "application/json", jsonString);
	}
	
	public static Response getTextResponce(Status code, String text) {
		return NanoHTTPD.newFixedLengthResponse(code, NanoHTTPD.MIME_PLAINTEXT, text);
	}
}
