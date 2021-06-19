/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package latestjavafxapp;

import javafx.application.Application;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author Ahemd M. Abd El-Ghany
 */
public class LatestJavaFxApp extends Application {

    private Scene scene;
    private HBox mainPane;
    private VBox leftCol, rightCol;
    private RadioButton radMaleBtn, radFemaleBtn;
    private ComboBox<String> cbRoles;
    private TextField tfUserId, tfFirstName, tfLastName;
    private ListView<String> lvUsers;
    private ObservableList<String> olUsers;

    private String userData, role, gender;

    @Override
    public void start(Stage primaryStage) {
        HBox root = createMainPane();
        scene = new Scene(root, 670, 420);
        primaryStage.setTitle("user Dashboard");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // create the main pane
    private HBox createMainPane() {
        mainPane = new HBox(10);
        addMainUis(mainPane);
        return mainPane;
    }

    // add Uis to main Pane
    private void addMainUis(HBox mainPane) {
        // add left and right cols after adding ui controls to them
        leftCol = getLeftCol();
        rightCol = getRightCol();

        mainPane.getChildren().addAll(leftCol, rightCol);

    }

    // create left Coloumn
    private VBox getLeftCol() {
        leftCol = new VBox(25);
        leftCol.setPadding(new Insets(25, 25, 25, 25));
        leftCol.setMinSize(220, 220);
        // add Uis
        addLeftColUis(leftCol);
        return leftCol;
    }

    // get right coloumn
    private VBox getRightCol() {
        rightCol = new VBox(25);
        rightCol.setPadding(new Insets(11, 11, 11, 11));
        rightCol.setMinSize(320, 220);
        // add Uis
        addRightColUis(rightCol);
        return rightCol;
    }

    // add left col UIs
    private void addLeftColUis(VBox leftCol) {
        Label lbAddNewUser = new Label("Add new User");

        leftCol.setStyle("-fx-background-color: #3498db ;");

        tfUserId = new TextField();
        tfFirstName = new TextField();
        tfLastName = new TextField();

        tfUserId.setPromptText("User Id");
        tfFirstName.setPromptText("First Name");
        tfLastName.setPromptText("Last Name");

        ToggleGroup tgGender = new ToggleGroup();

        radMaleBtn = new RadioButton("Male");
        radFemaleBtn = new RadioButton("female");

        radMaleBtn.setToggleGroup(tgGender);
        radFemaleBtn.setToggleGroup(tgGender);

        cbRoles = new ComboBox<>();
        ObservableList<String> olRoles = cbRoles.getItems();
        olRoles.addAll("Professor", "Student", "IT Staff", "Faculty Staff");

        cbRoles.setPromptText("Choose Role");

        cbRoles.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            System.out.println(newValue);
            role = newValue;
        });

        // gender
        radMaleBtn.selectedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            if (newValue) {
                gender = "Male";
            } else {
                gender = "Female";
            }
        });

        Button btAdd = new Button("Add");
        // handle btAdd
        handleAddBtn(btAdd);

        leftCol.getChildren().addAll(lbAddNewUser, tfUserId, tfFirstName, tfLastName, radMaleBtn, radFemaleBtn, cbRoles, btAdd);
    }

    // add Right col UIs
    private void addRightColUis(VBox rightCol) {
        lvUsers = new ListView<>();
        olUsers = lvUsers.getItems();
        olUsers.add("UserId" + "\t" + "FirstName"  + "\t" + "LastName" + "\t" + "Gender"  + "\t" + "Role");
        rightCol.getChildren().add(lvUsers);
    }

    public void getUserData() {

    }

    private void handleAddBtn(Button btAdd) {
        btAdd.setOnAction(e -> {
            userData =   tfUserId.getText() + "\t\t" + tfFirstName.getText() + "\t\t" + tfLastName.getText() + "\t\t" + gender + "\t\t" + role;
            // add value to listview
            olUsers.add(userData);
        });
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
