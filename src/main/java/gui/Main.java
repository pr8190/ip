package gui;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import seedu.mike.Mike;

/**
 * Main class
 */
public class Main extends Application {

    private static String fileName = "./src/main/java/data/data.txt";
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image userImage = new Image(
            "https://cdn-icons-png.flaticon.com/512/9187/9187532.png");
    private Image mikeImage = new Image(
            "https://img.freepik.com/free-vector/chatbot-chat"
                    + "-message-vectorart_78370-4104.jpg?semt=ais_hybrid&w=740&q=80");
    private Mike mike = new Mike(fileName);

    @Override
    public void start(Stage stage) {
        // Setting up required components

        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        sendButton = new Button("Send");
        userInput = new TextField();

        if (userImage.isError()) {
            System.out.println("Image loading failed!");
        }
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });
        userInput.setOnAction((event) -> {
            handleUserInput();
        });
        scrollPane.setContent(dialogContainer);

        AnchorPane mainLayout = new AnchorPane();
        stage.setTitle("Mike");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);

        mainLayout.setPrefSize(400.0, 600.0);

        scrollPane.setPrefSize(385, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

        userInput.setPrefWidth(325.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput, 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        scene = new Scene(mainLayout);

        stage.setScene(scene);
        stage.show();

        // More code to be added here later
    }

    /**
     * Handles the user input
     */
    private void handleUserInput() {
        String userText = userInput.getText();
        String mikeText = mike.getResponse(userInput.getText());
        DialogBox mikeDialogBox = new DialogBox(mikeText, mikeImage);
        mikeDialogBox.flip();
        dialogContainer.getChildren().addAll(
                new DialogBox(userText, userImage),
                mikeDialogBox);
        if (userText.trim().equalsIgnoreCase("bye")) {
            // Exit after a short delay (optional, for user to see message)
            Platform.exit();
        }
        userInput.clear();
    }
}
