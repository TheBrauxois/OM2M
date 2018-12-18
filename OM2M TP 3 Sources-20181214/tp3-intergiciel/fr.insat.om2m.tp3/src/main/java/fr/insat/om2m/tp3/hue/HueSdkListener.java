package fr.insat.om2m.tp3.hue;

import java.util.List;

import com.philips.lighting.hue.sdk.PHAccessPoint;
import com.philips.lighting.hue.sdk.PHHueSDK;
import com.philips.lighting.hue.sdk.PHMessageType;
import com.philips.lighting.hue.sdk.PHSDKListener;
import com.philips.lighting.model.PHBridge;
import com.philips.lighting.model.PHLight;
import com.philips.lighting.model.PHLightState;

import fr.insat.om2m.tp3.ipe.Main;
import fr.insat.om2m.tp3.ipe.Monitor;

public class HueSdkListener implements PHSDKListener {
	
	private Monitor monitor = new Monitor();

	@Override
	public void onAccessPointsFound(List accessPoint) {
		// Handle your bridge search results here. Typically if multiple
		// results are returned you will want to display them in a list
		// and let the user select their bridge. If one is found you may
		// opt to connect automatically to that bridge.
		
		// store the accesspoint infos
		PHHueSDK.getInstance().connect((PHAccessPoint) accessPoint.get(0));	
		System.out.println("onAccessPointsFound called with: " + accessPoint.toString());
	}

	@Override
	public void onCacheUpdated(List cacheNotificationsList, PHBridge bridge) {
		// Here you receive notifications that the BridgeResource Cache
		// was updated. Use the PHMessageType to
		// check which cache was updated, e.g.
		if (cacheNotificationsList.contains(PHMessageType.LIGHTS_CACHE_UPDATED)) {
			System.out.println("Lights Cache Updated");
			List<PHLight> lights = HueUtil.getLights();
			for (PHLight l : lights){
				monitor.pushState(l.getIdentifier(), l.getLastKnownLightState().isOn().toString()
						,l.getLastKnownLightState().getHue().toString(),l.getLastKnownLightState().getHue());
			}
		}
		
		
	}

	@Override
	public void onBridgeConnected(PHBridge b, String username) {
		// Here it is recommended to set your connected bridge in your
		// sdk object (as above) and start the heartbeat.
		// At this point you are connected to a bridge so you should
		// pass control to your main program/activity.
		// The username is generated randomly by the bridge.
		// Also it is recommended you store the connected IP Address/
		// Username in your app here. This will allow easy automatic
		// connection on subsequent use.
		PHHueSDK.getInstance().enableHeartbeat(b, PHHueSDK.HB_INTERVAL);
		PHHueSDK.getInstance().setSelectedBridge(b);
		System.out.println("Connected to bridge with username: " + username);
		HueProperties.storeUsername(username);
		Main.setConnected(true);
		
		// TODO create resources in OM2M (use the Monitor class)
	}

	@Override
	public void onAuthenticationRequired(PHAccessPoint accessPoint) {
		// Arriving here indicates that Pushlinking is required (to
		// prove the User has physical access to the bridge). Typically
		// here
		// you will display a pushlink image (with a timer) indicating
		// to to the user they need to push the button on their bridge
		// within 30 seconds.
		System.out.println("Press bridge button...");
		PHHueSDK.getInstance().startPushlinkAuthentication(accessPoint);
	}

	@Override
	public void onConnectionResumed(PHBridge bridge) {
	}

	@Override
	public void onConnectionLost(PHAccessPoint accessPoint) {
		// Here you would handle the loss of connection to your bridge.
		System.err.println("ERROR: Connection to bridge lost.");
	}

	@Override
	public void onError(int code, final String message) {
		// Here you can handle events such as Bridge Not Responding,
		// Authentication Failed and Bridge Not Found
		System.err.println("ERROR [" + code + "] " + message);
	}

	@Override
	public void onParsingErrors(List parsingErrorsList) {
		// Any JSON parsing errors are returned here. Typically your
		// program should never return these.
	}

}
