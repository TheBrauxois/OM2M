package fr.insat.om2m.tp3.ipe;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletHandler;

public class IpeServer {
	private static int PORT = 1400;
	private static String CONTEXT = "/monitor";

	public static class MonitorServlet extends HttpServlet {

		private static final long serialVersionUID = 2302036096330714914L;

		@Override
		protected void doPost(HttpServletRequest req, HttpServletResponse resp)
				throws ServletException, IOException {
			String queryStringsReq = req.getQueryString();
			HashMap<String, String> queryStringsMap = new HashMap<String, String>();
			
			String[]queryStrings = queryStringsReq.split("&");
			for (String keyValue : queryStrings) {
				String key = keyValue.split("=")[0];
				String value = keyValue.split("=")[1];
				queryStringsMap.put(key, value);				
			}

			// TODO call right IPE operations regarding the key and the value
			System.out.println("Received request with query strings: " + queryStringsMap);
			Controller.execute(queryStringsMap);
			
			
			resp.setStatus(HttpServletResponse.SC_OK);

		}

	}
	
	public static Server createServer() throws Exception{
		// start the server
		Server server = new Server(PORT);
		ServletHandler servletHandler = new ServletHandler();

		// add servlet and context
		servletHandler.addServletWithMapping(MonitorServlet.class, CONTEXT);
		server.setHandler(servletHandler);
		server.start();
		return server;
	}

}
