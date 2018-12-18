package fr.insat.om2m.tp2.util;

import org.eclipse.om2m.commons.constants.ShortName;
import org.eclipse.om2m.commons.resource.Notification;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import obix.Obj;
import obix.io.ObixDecoder;

/**
 * Provide util method(s) for handling the notification from OM2M
 * 
 * @author aissaoui
 *
 */
public class NotificationUtil {

	/**
	 * Returns the linked obix object if any in the notification provided
	 * 
	 * @param notification
	 *            the notification to inspect
	 * @return the oBIX object
	 * @throws NotObixContentException
	 *             if the notification do not contain oBIX object
	 */
	public static final Obj getObixFromNotification(Notification notification)
			throws NotObixContentException {
		if (notification.getNotificationEvent().getRepresentation() instanceof Element) {
			Element element = (Element) notification.getNotificationEvent()
					.getRepresentation();
			Node nodeContent = getContentNode(element);
			if (nodeContent == null) {
				throw new NotObixContentException();
			}
			String contentValue = nodeContent.getTextContent();
			Obj obj = ObixDecoder.fromString(contentValue);
			return obj;
		}
		throw new NotObixContentException();
	}

	private static final Node getContentNode(Element element) {
		for (int i = 0; i < element.getChildNodes().getLength(); i++) {
			Node node = element.getChildNodes().item(i);
			if (node.getNodeName().equals(ShortName.CONTENT)) {
				return node;
			}
		}
		return null;
	}

	public static final class NotObixContentException extends Exception {

		private static final long serialVersionUID = 5553296928654621371L;

		public NotObixContentException() {
			super("Content does not contain oBIX representation");
		}
	}

}
