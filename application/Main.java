package application;
	
// import com.sun.javafx.fxml.FXMLLoaderHelper.FXMLLoaderAccessor;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		Parent root = FXMLLoader.load(getClass().getResource("sample2.fxml"));
		primaryStage.setTitle("Booking system");
		primaryStage.setScene(new Scene(root, 1280, 890));
		primaryStage.show();
	}
	
	 private static Main connectionFactory = null;
	public static Main getInstance() {
		if (connectionFactory == null) {
			connectionFactory = new Main();
		}
		return connectionFactory;
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
}

 
