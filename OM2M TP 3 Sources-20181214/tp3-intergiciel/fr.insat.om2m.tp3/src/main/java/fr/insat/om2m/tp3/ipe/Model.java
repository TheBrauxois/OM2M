package fr.insat.om2m.tp3.ipe;

import fr.insat.om2m.tp3.hue.HueUtil;
import obix.Contract;
import obix.Obj;
import obix.Op;
import obix.Str;
import obix.Uri;
import obix.io.ObixEncoder;

/**
 * The model allows to interact with the real devices
 *
 */
public class Model {
	
	public static String LOCATION = "HOME";

	/**
	 * Get the descriptor content instance representation serialized in oBIX
	 * @param cseId
	 * @param appId
	 * @param location
	 * @return
	 */
	public static String getDescriptorRep(String cseId, String appId,
			String location) {
		String prefix = "/" + cseId + "/" + Main.CSE_NAME + "/" + appId;
		// Create the obix object
		Obj obj = new Obj();
		obj.add(new Str("location", location));
		obj.add(new Str("type", "AMB_LAMP"));
		obj.add(new Str("unit", ""));
		// OP GetState from SCL IPU
		Op opStateDirect = new Op();
		opStateDirect.setName("GET");
		opStateDirect.setHref(new Uri(prefix + "/DATA/la"));
		opStateDirect.setIs(new Contract("execute"));
		obj.add(opStateDirect);

		Op op = new Op();

		op.setName("ON");
		op.setHref(new Uri(prefix + "?on=true&bri=254&sat=254&id="+appId));
		op.setIs(new Contract("execute"));
		obj.add(op);

		// OFF
		op = new Op();

		op.setName("OFF");
		op.setHref(new Uri(prefix + "?on=false&id="+appId));
		op.setIs(new Contract("execute"));
		obj.add(op);
		
		op = new Op();

		op.setName("RED");
		op.setHref(new Uri(prefix + "?on=true&bri=254&sat=254&hue="+HueUtil.getHue("RED")+"&id="+appId));
		op.setIs(new Contract("execute"));
		obj.add(op);
		
		// TODO if you want new colors, add them here!

		return ObixEncoder.toString(obj);
	}

	/**
	 * Get the data content instance representation serialized in oBIX
	 * @param location
	 * @param state
	 * @param color
	 * @param hue
	 * @return
	 */
	public static String getDataRep(String location, String state,
			String color, long hue) {
		// Create the obix object
		Obj obj = new Obj();
		obj.add(new Str("location", location));
		obj.add(new Str("state", state));
		obj.add(new Str("color", color));
		obj.add(new Str("hue", "" + hue));
		obj.add(new Str("type", "AMB_LAMP"));

		return ObixEncoder.toString(obj);

	}


}
