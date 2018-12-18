package fr.insat.om2m.tp3.hue;

import java.util.List;

import com.philips.lighting.hue.sdk.PHAccessPoint;
import com.philips.lighting.hue.sdk.PHBridgeSearchManager;
import com.philips.lighting.hue.sdk.PHHueSDK;
import com.philips.lighting.model.PHBridge;
import com.philips.lighting.model.PHBridgeResourcesCache;
import com.philips.lighting.model.PHLight;
import com.philips.lighting.model.PHLightState;

public class HueUtil {
	private static final String IP_ADDR = "192.168.1.110";
	private static final String USERNAME = "zOkPiqk-gWL1UKuemjoPRzcXQltihvScIAEidSJ6";
	// connection to an already known hub
	public static void connectToBridge() {
		PHAccessPoint accessPoint = new PHAccessPoint();
		accessPoint.setIpAddress(IP_ADDR);
		accessPoint.setUsername(USERNAME);
		PHHueSDK.getInstance().connect(accessPoint);
	}
	
	public static List<PHLight> getLights() {
		PHBridge bridge = PHHueSDK.getInstance().getSelectedBridge();
		PHBridgeResourcesCache cache = bridge.getResourceCache();
		return cache.getAllLights();
	}
	
	// update state of a device
	public static void updateDevice(String deviceId, Integer hueValue, Integer bri, Integer sat, Boolean on) {
		PHBridge bridge = PHHueSDK.getInstance().getSelectedBridge();
		PHBridgeResourcesCache cache = bridge.getResourceCache();
		// TODO Update the state of the light
	}

	public static void getStates(List<PHLight> lights) {
		for(PHLight l : lights){
			System.out.println("Id: " + l.getIdentifier() 
					+ " | state: " + l.getLastKnownLightState().isOn() 
					+ " hue:" + l.getLastKnownLightState().getHue() 
					+ " bri:" + l.getLastKnownLightState().getBrightness() 
					+ " isConnected: " + l.getLastKnownLightState().isReachable());
		}		
	}

	/**
	 * Get the color from the HUE encoded int value
	 * 
	 * @param hue
	 * @return
	 */
	public static String getColor(int hue) {
		switch (hue) {
		case 0:
			return "RED";
		case 12750:
			return "YELLOW";
		case 36210:
			return "GREEN";
		case 46920:
			return "BLUE";
		case 56100:
			return "PURPLE";
		default:
			return "" + hue;
		}
	}

	/**
	 * Get the HUE encoded int value from the color (string)
	 * 
	 * @param color
	 * @return
	 */
	public static int getHue(String color) {
		switch (color) {
		case "RED":
			return 0;
		case "YELLOW":
			return 12750;
		case "GREEN":
			return 36210;
		case "BLUE":
			return 46920;
		case "PURPLE":
			return 56100;
		default:
			return 0;
		}
	}

}
