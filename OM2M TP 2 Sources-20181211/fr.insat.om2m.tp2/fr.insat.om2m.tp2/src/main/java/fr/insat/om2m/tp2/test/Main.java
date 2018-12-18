package fr.insat.om2m.tp2.test;


import fr.insat.om2m.tp2.client.Client;
import fr.insat.om2m.tp2.client.Response;

import java.io.IOException;

public class Main {

	public static void main(String[] args) {
		Client client = new Client();
		Response mResponse;
		String url = "http://192.168.43.152:8080/~/in-cse/";
		String originator_admin = "admin:admin";
		try {
			mResponse = client.retrieve(url,originator_admin);
			System.out.println(mResponse.toString());

			mResponse = client.create(url,"<m2m:ae xmlns:m2m=\"http://www.onem2m.org/xml/protocols\" rn=\"App-Test\">\n" +
					"    <api>app-test</api>\n" +
					"    <rr>false</rr>\n" +
					"</m2m:ae>", originator_admin,"2");
			System.out.println(mResponse.toString());

			mResponse = client.retrieve(url+"in-name/App-Test",originator_admin);
			System.out.println(mResponse.toString());

			mResponse = client.update(url+"in-name/App-Test","<m2m:ae xmlns:m2m=\"http://www.onem2m.org/xml/protocols\">\n" +
					"    <lbl>Casimir/Glabiboulga</lbl>\n" +
					"</m2m:ae>", originator_admin);
			System.out.println(mResponse.toString());

			mResponse = client.retrieve(url+"in-name/App-Test",originator_admin);
			System.out.println(mResponse.toString());

			mResponse = client.create(url+"in-name/App-Test","<m2m:cnt xmlns:m2m=\"http://www.onem2m.org/xml/protocols\" rn=\"cntTest\">\n" +
					"</m2m:cnt>", originator_admin,"3");
			System.out.println(mResponse.toString());

			mResponse = client.retrieve(url+"in-name/App-Test/cntTest",originator_admin);
			System.out.println(mResponse.toString());

			mResponse = client.create(url+"in-name/App-Test/cntTest","<m2m:cin xmlns:m2m=\"http://www.onem2m.org/xml/protocols\" rn=\"MaR\">\n" +
					"    <cnf>text/plain</cnf>\n" +
					"    <con>This is a test</con>\n" +
					"</m2m:cin>", originator_admin,"4");
			System.out.println(mResponse.toString());

			mResponse = client.retrieve(url+"in-name/App-Test/cntTest/MaR",originator_admin);
			System.out.println(mResponse.toString());

			mResponse = client.delete(url+"in-name/App-Test/cntTest/MaR",originator_admin);
			System.out.println(mResponse.toString());

		}catch (IOException e){
			e.printStackTrace();
		}

		System.out.println("Je fonctionne parfaitement bien !");

		
	}

}