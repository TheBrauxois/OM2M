package fr.insat.om2m.tp2.util;

import java.util.Scanner;

/**
 * Load the request payload from a resource file in the "requests" resource
 * folder.
 * 
 * @author aissaoui
 *
 */
public class RequestLoader {

	/**
	 * Load the request payload from the file.
	 * 
	 * @param fileName
	 *            the name of the resource file in the requests folder.
	 * @return the payload form the file
	 */
	public static String getRequestFromFile(String fileName) {
		Scanner sc = new Scanner(
				RequestLoader.class
						.getResourceAsStream("/requests/" + fileName));
		String payload = "";
		while (sc.hasNextLine()) {
			payload += sc.nextLine() + "\n";
		}
		sc.close();
		return payload;
	}

}
