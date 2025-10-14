package coursework;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        stage.setWidth(1000);
        stage.setHeight(800);

        String projectPath = "E:/torrent/Lab_kurs";
        File fxmlFile = new File(projectPath + "/src/main/resources/coursework/MainWindow.fxml");

        if (!fxmlFile.exists()) {
            System.err.println("FXML файл не найден по пути: " + fxmlFile.getAbsolutePath());

            File resourcesDir = new File(projectPath + "/src/main/resources");
            if (resourcesDir.exists()) {
                System.out.println("Содержимое папки resources:");
                for (File file : resourcesDir.listFiles()) {
                    System.out.println(" - " + file.getName() + (file.isDirectory() ? " [папка]" : ""));
                }
            }

            File courseworkDir = new File(projectPath + "/src/main/resources/coursework");
            if (courseworkDir.exists()) {
                System.out.println("Содержимое папки coursework:");
                for (File file : courseworkDir.listFiles()) {
                    System.out.println(" - " + file.getName());
                }
            }

            throw new RuntimeException("FXML файл не найден! " + fxmlFile.getAbsolutePath());
        }

        System.out.println("Успешно! FXML найден по пути: " + fxmlFile.getAbsolutePath());

        FXMLLoader loader = new FXMLLoader(fxmlFile.toURI().toURL());
        Parent root = loader.load();

        Scene scene = new Scene(root, stage.getWidth(), stage.getHeight());
        stage.setScene(scene);
        stage.setTitle("Coursework");
        stage.show();
    }
}