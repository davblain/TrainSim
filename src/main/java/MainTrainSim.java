/**
 * Created by gemini on 04.07.17.
 */

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainTrainSim extends Application {

    public static void main(String[] args) throws Exception {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        String fxmlFile ="/fxml/hello.fxml";
        FXMLLoader loader = new FXMLLoader();
        Parent root = (Parent) loader.load(getClass().getResourceAsStream(fxmlFile));
        primaryStage.setTitle("TrainSim");
        primaryStage.setScene(new Scene(root));
    }
}
