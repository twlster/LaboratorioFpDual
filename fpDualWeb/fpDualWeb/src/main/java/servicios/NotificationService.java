package servicios;

import edu.fpdual.web.fpdualweb.api.dto.Notification;

public class NotificationService {

	public Notification createNotification(Notification notification) {
		return createNotification(notification.getId(), notification.getTitle(), notification.getBody());
	}

	public Notification createNotification(int id, String title, String body) {
		return new Notification(id, title, body);
	}

}
