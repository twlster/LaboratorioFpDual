package edu.fpdualjavafx.ejemplofx;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import com.mongodb.client.MongoCollection;
import com.sun.javafx.binding.StringFormatter;

import edu.fpdualjavafx.ejemplofx.persistence.conector.Conector;
import edu.fpdualjavafx.ejemplofx.persistence.model.Log;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextArea;
import javafx.scene.paint.Color;

public class MainWindowController implements Initializable {

	@FXML
	private ColorPicker backgroundColor;

	@FXML
	private ColorPicker textColor;

	@FXML
	private TextArea text;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		backgroundColor.setValue(Color.BLACK);
		textColor.setValue(Color.LIGHTGREEN);
		text.setStyle("-fx-control-inner-background: " + format(backgroundColor.getValue())
				+ ";-fx-font-family: Consolas; -fx-text-fill:" + format(textColor.getValue()) + ";");
		loadText();
	}

	@FXML
	public void switchToPrimary() throws IOException {
		App.setRoot("login", "Login");
	}

	@FXML
	public void closeWindow() throws IOException {
		App.setRoot("login", "Login");
	}

	@FXML
	public void deleteCache() throws IOException {
		Alert alerta = new Alert(AlertType.CONFIRMATION);
		alerta.setTitle("Delete Cache");
		alerta.setHeaderText("Are you sure want to delete this file cache?");
		Optional<ButtonType> option = alerta.showAndWait();
		if (option.get() == ButtonType.OK) {
			Alert confirmacion = new Alert(AlertType.CONFIRMATION);
			confirmacion.setTitle("Delete Cache");
			confirmacion.setHeaderText("Cache deleted!");
			confirmacion.show();
		} else if (option.get() == ButtonType.CANCEL) {
			Alert cancelation = new Alert(AlertType.WARNING);
			cancelation.setTitle("Delete Cache");
			cancelation.setHeaderText("Cache wasn't deleted.");
			cancelation.show();
		} else {
			System.out.println("Nothing.");
		}
	}

	@FXML
	public void about() throws IOException {
		Alert alerta = new Alert(AlertType.INFORMATION);
		alerta.setTitle("About!");
		alerta.setHeaderText("This is the coolest application you have ever seen!!");
		alerta.show();
	}

	private String format(Color c) {
		int r = (int) (255 * c.getRed());
		int g = (int) (255 * c.getGreen());
		int b = (int) (255 * c.getBlue());
		return String.format("#%02x%02x%02x", r, g, b);
	}

	private void loadText() {
		MongoCollection<Log> collection = new Conector().getMongoDBDatabase().getCollection("logs", Log.class);
		for (String log : collection.find().map(data -> StringFormatter
				.format("[%s] -- [%s] -- %s", data.getType(), data.getDateTime(), data.getText()).toString())) {
			text.appendText(log);
		}
	}
}