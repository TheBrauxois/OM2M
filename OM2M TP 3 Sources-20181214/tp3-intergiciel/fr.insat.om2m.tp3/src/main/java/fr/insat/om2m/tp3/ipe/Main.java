package fr.insat.om2m.tp3.ipe;

import java.util.List;

import com.philips.lighting.model.PHBridge;
import com.philips.lighting.model.PHLightState;
import fr.insat.om2m.tp3.client.Client;
import fr.insat.om2m.tp3.client.Response;
import fr.insat.om2m.tp3.mapper.Mapper;
import org.eclipse.jetty.server.Server;

import com.philips.lighting.hue.sdk.PHHueSDK;
import com.philips.lighting.model.PHLight;

import fr.insat.om2m.tp3.hue.HueProperties;
import fr.insat.om2m.tp3.hue.HueSdkListener;
import fr.insat.om2m.tp3.hue.HueUtil;
import org.eclipse.om2m.commons.resource.AE;
import org.eclipse.om2m.commons.resource.Container;
import org.eclipse.om2m.commons.resource.ContentInstance;
import org.eclipse.om2m.commons.resource.Subscription;

import javax.jws.WebParam;


public class Main {
	/** Csebase id */
	public final static String CSEID = "in-cse";
	/** CseBase Name */
	public final static String CSE_NAME = "in-name";
	/** Generic create method name */
	public final static String METHOD = "CREATE";
	/** Data container id */
	public final static String DATA = "DATA";
	/** Descriptor container id */
	public final static String DESC = "DESCRIPTOR";
	/** Indicate if the program is connected to Hue bridge */
	private static boolean connected = false;

	public final static String APPID = "HUE-app";
	private static final String url = "http://192.168.1.147:8080/~/in-cse/";
	private static final String originator_admin = "admin:admin";

	public static boolean isConnected(){
		synchronized (Main.class) {
			return connected;
		}
	}

	public static void setConnected(boolean state){
		synchronized (Main.class) {
			connected = state;
		}
	}

	public static void main(String[] args) throws Exception {
		Client client = new Client();
		System.out.println("Loading properties from file...");
		HueProperties.loadProperties();

		System.out.println("Registering the listener");
		// TODO Look at the HueSdkListener class.
		PHHueSDK.getInstance().getNotificationManager().registerSDKListener(new HueSdkListener());

		System.out.println("Connecting to bridge");
		// TODO implement this method!!
		HueUtil.connectToBridge();

		System.out.println("Awaiting connection...");
		while(!isConnected()){
			Thread.sleep(500);
		}
		PHBridge bridge = PHHueSDK.getInstance().getSelectedBridge();
		System.out.println("Retrieving lights");
		List<PHLight> lights = HueUtil.getLights();


		System.out.println("Getting last known states");
		HueUtil.getStates(lights);

		PHLightState state = new PHLightState();
		state.setHue(HueUtil.getHue("YELLOW"));
		bridge.updateLightState(lights.get(0),state);
		Monitor monitor = new Monitor();
		monitor.createResources(lights);


		Server server = IpeServer.createServer();
		System.out.println("Starting the server on: " + server.getState());
		server.join();




		// Example: turn on a lamp 1
		//		 HueUtil.updateDevice("1", null, null, null, true);

		
		// --------------------- //
		// To launch server part //
		// --------------------- //
		
		// Creating the IPE Server on :1400/monitor
		//		Server server = IpeServer.createServer();
		//		System.out.println("Starting the server on: " + server.getState());
		//		server.join();
	}



}
