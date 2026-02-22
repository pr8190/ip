package gui;

// Remove the incorrect import
// import com.azul.crs.internal.asm.Label;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label; // Correct import for Label
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import seedu.mike.Mike;

/**
 * Main class for the Mike chatbot GUI application.
 */
public class Main extends Application {

    /** ScrollPane to display the dialog container. */
    private ScrollPane scrollPane;

    /** VBox to contain dialog boxes. */
    private VBox dialogContainer;

    /** TextField for user input. */
    private TextField userInput;

    /** Button to send user input. */
    private Button sendButton;

    /** Main scene of the application. */
    private Scene scene;

    /** Image representing the user. */
    private Image userImage = new Image(getClass().getResourceAsStream("/images/DaUser.png"));

    /** Image representing the chatbot (Mike). */
    private Image mikeImage = new Image(getClass().getResourceAsStream("/images/DaChat.png"));

    /** Instance of the Mike chatbot. */
    private Mike mike = new Mike();

    @Override
    public void start(Stage stage) {
        setupComponents();
        setupLayout(stage);
        setupEventHandlers();

        stage.show();
    }

    /**
     * Sets up the components of the GUI, such as buttons, text fields, and scroll
     * pane.
     */
    private void setupComponents() {
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        sendButton = new Button("Send");
        userInput = new TextField();

        scrollPane.setContent(dialogContainer);
        scrollPane.setPrefSize(585, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

        userInput.setPrefWidth(525.0);
        sendButton.setPrefWidth(55.0);
    }

    /**
     * Configures the layout of the GUI, including positioning and styling of
     * components.
     *
     * @param stage The primary stage for this application.
     */
    private void setupLayout(Stage stage) {
        AnchorPane mainLayout = new AnchorPane();
        stage.setTitle("Mike");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);

        mainLayout.setPrefSize(400.0, 600.0);

        Label messageLabel = createMessageLabel();

        AnchorPane.setTopAnchor(scrollPane, 1.0);
        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);
        AnchorPane.setLeftAnchor(userInput, 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);
        AnchorPane.setLeftAnchor(messageLabel, 1.0);
        AnchorPane.setBottomAnchor(messageLabel, 30.0);

        DialogBox welcomeBox = new DialogBox(mike.getResponse("hello"), mikeImage);
        welcomeBox.flip();
        dialogContainer.getChildren().addAll(welcomeBox);

        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton, messageLabel);

        scene = new Scene(mainLayout);
        stage.setScene(scene);
    }

    /**
     * Creates and returns a label with a message for the user.
     *
     * @return A styled Label with a message for the user.
     */
    private Label createMessageLabel() {
        Label messageLabel = new Label("Type 'hello' to understand the chatbot features");
        messageLabel.setStyle("-fx-font-size: 12px; -fx-text-fill: gray;");
        return messageLabel;
    }

    /**
     * Sets up event handlers for user interactions, such as button clicks and text
     * input actions.
     */
    private void setupEventHandlers() {
        sendButton.setOnMouseClicked((event) -> handleUserInput());
        userInput.setOnAction((event) -> handleUserInput());
    }

    /**
     * Handles the user input by creating dialog boxes for the user and chatbot
     * responses.
     * Exits the application if the user types "bye".
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
