package fr.insat.om2m.tp2.client;

import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.apache.http.Header;
import org.apache.http.HttpRequest;
import org.apache.http.client.methods.*;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class Client implements ClientInterface {

	public Response retrieve(String url, String originator) throws IOException {
		Response response = new Response();
		// Instantiate a new Client
		CloseableHttpClient client = HttpClients.createDefault();
		// Instantiate the correct Http Method
		HttpGet get = new HttpGet(url);
		get.addHeader("X-M2M-Origin", originator);
		//get.addHeader("KEY", "VALUE");
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

	public Response create(String url, String representation, String originator, String type)
			throws IOException {
		Response response = new Response();
		CloseableHttpClient client = HttpClients.createDefault();
		HttpPost post = new HttpPost(url);
		StringEntity body = new StringEntity(representation);
		post.addHeader("X-M2M-Origin", originator);
		post.addHeader("Accept", "application/json");
		post.addHeader("Content-Type", "application/xml;ty="+type);
		post.setEntity(body);

		//get.addHeader("KEY", "VALUE");
		try {
			// send request
			CloseableHttpResponse reqResp = client.execute(post);
			response.setStatusCode(reqResp.getStatusLine().getStatusCode());
			response.setRepresentation(IOUtils.toString(reqResp.getEntity().getContent(), "UTF-8"));
		} catch (IOException e1) {
			e1.printStackTrace();
		} finally {
			client.close();
		}
		// return response
		return response;
	}

	public Response update(String url, String representation, String originator) throws IOException {
        Response response = new Response();
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPut put = new HttpPut(url);
        StringEntity body = new StringEntity(representation);
        put.addHeader("X-M2M-Origin", originator);
        put.addHeader("Accept", "application/json");
        put.setEntity(body);

        //get.addHeader("KEY", "VALUE");
        try {
            // send request
            CloseableHttpResponse reqResp = client.execute(put);
            response.setStatusCode(reqResp.getStatusLine().getStatusCode());
            response.setRepresentation(IOUtils.toString(reqResp.getEntity().getContent(), "UTF-8"));
        } catch (IOException e1) {
            e1.printStackTrace();
        } finally {
            client.close();
        }
        // return response
        return response;
	}

	public Response delete(String url, String originator) throws IOException {
        Response response = new Response();
        CloseableHttpClient client = HttpClients.createDefault();
        HttpDelete del = new HttpDelete(url);
        del.addHeader("X-M2M-Origin", originator);
        del.addHeader("Accept", "application/json");

        //get.addHeader("KEY", "VALUE");
        try {
            // send request
            CloseableHttpResponse reqResp = client.execute(del);
            response.setStatusCode(reqResp.getStatusLine().getStatusCode());
            response.setRepresentation(IOUtils.toString(reqResp.getEntity().getContent(), "UTF-8"));
        } catch (IOException e1) {
            e1.printStackTrace();
        } finally {
            client.close();
        }
        // return response
        return response;
	}

}
