package fr.insat.om2m.tp3.ipe;

import java.util.HashMap;

import com.philips.lighting.hue.sdk.PHHueSDK;
import com.philips.lighting.model.PHBridge;
import com.philips.lighting.model.PHLight;
import com.philips.lighting.model.PHLightState;

/**
 * This class will implement the method called by the listening server to execute 
 * a command sent by the middleware
 *
 */
public class Controller {

	public static void execute(HashMap<String, String> queryStrings) {
		PHBridge bridge = PHHueSDK.getInstance().getSelectedBridge();
		String id = queryStrings.get("id");
		id = id.split("LAMP_")[1];
		PHLight l = bridge.getResourceCache().getLights().get(id);
		if(l == null) {
			System.out.println("Lamp id not found: " + id);
			return;
		}
		String hue = queryStrings.get("hue"),
				sat = queryStrings.get("sat"),
				bri = queryStrings.get("bri"),
				on = queryStrings.get("on");
		
		PHLightState state = new PHLightState();
		if(bri != null){
			state.setBrightness(Integer.valueOf(bri));			
		}
		if(on != null){
			state.setOn(Boolean.valueOf(on));
		}
		if(sat != null){
			state.setSaturation(Integer.valueOf(sat));
		}
		if(hue != null){
			state.setHue(Integer.valueOf(hue));
		}
		
		// TODO Perform the operation on the lamp (see documentation).
		System.out.println("NOT YET IMPLEMENTED IN CONTROLLER CLASS. Perform the operation on the lamp via the bridge.");
	}
	
}
