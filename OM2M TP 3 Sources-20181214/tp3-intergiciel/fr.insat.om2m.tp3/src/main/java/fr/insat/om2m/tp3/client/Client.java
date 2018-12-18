package fr.insat.om2m.tp3.client;

import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class Client implements ClientInterface {

	private static final String ORIGIN_HEADER = "X-M2M-Origin";
	private static final String CT_HEADER = "Content-Type";
	private static final String ACCEPT = "accept";
	private static final String XML = "application/xml";

	public Response retrieve(String url, String originator) throws IOException {
		Response response = new Response();
		// Instantiate a new Client
		CloseableHttpClient client = HttpClients.createDefault();
		// Instantiate the correct Http Method
		HttpGet get = new HttpGet(url);
		// add headers
		get.addHeader(ORIGIN_HEADER, originator);
		get.addHeader(ACCEPT, XML);
		try {
			// send request
			CloseableHttpResponse reqResp = client.execute(get);
			response.setStatusCode(reqResp.getStatusLine().getStatusCode());
			response.setRepresentation(IOUtils.toString(reqResp.getEntity()
					.getContent(), "UTF-8"));
		} catch (IOException e1) {
			e1.printStackTrace();
		} finally {
			client.close();
		}
		// return response
		return response;
	}

	public Response create(String url, String representation,
			String originator, String type) throws IOException {
		Response response = new Response();
		// Instantiate a new Client
		CloseableHttpClient client = HttpClients.createDefault();
		// Instantiate the correct Http Method
		HttpPost post = new HttpPost(url);
		post.setEntity(new StringEntity(representation));
		// add headers
		post.addHeader(ORIGIN_HEADER, originator);
		post.addHeader(CT_HEADER, XML + ";ty=" + type);
		post.addHeader(ACCEPT, XML);

		try {
			// send request
			CloseableHttpResponse reqResp = client.execute(post);
			response.setStatusCode(reqResp.getStatusLine().getStatusCode());
			response.setRepresentation(IOUtils.toString(reqResp.getEntity()
					.getContent(), "UTF-8"));
		} catch (IOException e1) {
			e1.printStackTrace();
		} finally {
			client.close();
		}
		return response;
	}

	public Response update(String url, String representation, String originator) throws IOException {
		Response response = new Response();
		// Instantiate a new Client
		CloseableHttpClient client = HttpClients.createDefault();
		// Instantiate the correct Http Method
		HttpPut put = new HttpPut(url);
		put.setEntity(new StringEntity(representation));
		// add headers
		put.addHeader(ORIGIN_HEADER, originator);
		put.addHeader(CT_HEADER, XML);
		put.addHeader(ACCEPT, XML);

		try {
			// send request
			CloseableHttpResponse reqResp = client.execute(put);
			response.setStatusCode(reqResp.getStatusLine().getStatusCode());
			response.setRepresentation(IOUtils.toString(reqResp.getEntity()
					.getContent(), "UTF-8"));
		} catch (IOException e1) {
			e1.printStackTrace();
		} finally {
			client.close();
		}
		return response;
	}

	public Response delete(String url, String originator) throws IOException {
		Response response = new Response();
		// Instantiate a new Client
		CloseableHttpClient client = HttpClients.createDefault();
		// Instantiate the correct Http Method
		HttpDelete delete = new HttpDelete(url);
		// add headers
		delete.addHeader(ORIGIN_HEADER, originator);
		delete.addHeader(ACCEPT, XML);
		try {
			// send request
			CloseableHttpResponse reqResp = client.execute(delete);
			response.setStatusCode(reqResp.getStatusLine().getStatusCode());
			response.setRepresentation(IOUtils.toString(reqResp.getEntity()
					.getContent(), "UTF-8"));
		} catch (IOException e1) {
			e1.printStackTrace();
		} finally {
			client.close();
		}
		// return response
		return response;
	}

}
