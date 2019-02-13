package net.quber.vshs;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import fi.iki.elonen.NanoHTTPD;
import fi.iki.elonen.NanoHTTPD.Response.Status;

public class WebServer extends NanoHTTPD {
	private HashMap<String, java.lang.reflect.Method> mGetMap = new HashMap();
	private HashMap<String, java.lang.reflect.Method> mPostMap = new HashMap();
	private Object mControllerInstance;

	public WebServer(int port, Class<?> instance) throws IOException {
		super(port);
		try {
			mControllerInstance = instance.newInstance();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		processAnnotation(mControllerInstance);
		start(NanoHTTPD.SOCKET_READ_TIMEOUT, false);
		System.out.println("\nRunning! Point your browsers to http://localhost:8080/ \n");
	}

	private <T> void processAnnotation(T instance) {
		java.lang.reflect.Method[] methods = instance.getClass().getDeclaredMethods();
			
		for (java.lang.reflect.Method method : methods) {
			System.out.println("Method: " + method);
			RequestMapping annotation = method.getAnnotation(RequestMapping.class);
			if (annotation != null) {
				String url = annotation.url();
				if (url.charAt(url.length() - 1) != '/') {
					url = url + "/";
				}

				if (annotation.method() == Method.POST) {
					System.out.println("URL: " + url + " Method: POST");
					mPostMap.put(url, method);
				} else {
					System.out.println("URL: " + url + " Method: GET");
					mGetMap.put(url, method);
				}
			}
		}
	}

	@Override
	public Response serve(IHTTPSession session) {
		Map<String, String> parms = session.getParms();
		
		String url = session.getUri();
		if (url.charAt(url.length() - 1) != '/') {
			url = url + "/";
		}
		System.out.println("URL: " + url);
		
		java.lang.reflect.Method method;
		if (session.getMethod() == Method.POST) {
			method = mPostMap.get(session.getUri());
		} else {
			System.out.println("GET");
			method = mGetMap.get(session.getUri());
		}

		if (method == null) {
			return newFixedLengthResponse(Status.NOT_FOUND, NanoHTTPD.MIME_HTML, "NOT FOUND");
		}

		Response response = newFixedLengthResponse(Status.INTERNAL_ERROR, NanoHTTPD.MIME_HTML, "INTERNAL ERROR!!!");
		try {
			response = (Response) method.invoke(mControllerInstance, session);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}

		return response;
	}
	
	
}
