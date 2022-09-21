package oopassignment.demo1;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;


public class OOP1<Student> extends Application implements EventHandler<ActionEvent> {
    private Stage window;
    private static Label nameLabel, idLabel, dobLabel;
    private static Button addButton, removeButton, listButton, loadButton, saveButton, exitButton;
    private static TextArea textBox;
    private static TextField nameField, idField, dobField;
    private String name, studentID, dob;
    private ArrayList<String> students;
    private File savedList = new File("students_list.txt");


    public static void main(String[] args) {
        launch();
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        try {

            window = primaryStage;

            window.setTitle("MTU Student Record System");

            students = new ArrayList<String>();

            //"Enter Name" section
            nameLabel = new Label("Enter Name");
            nameField = new TextField();

            //"Enter Student ID" section
            idLabel = new Label("Enter Student ID");
            idField = new TextField();

            //"Enter Date of Birth" section
            dobLabel = new Label("Enter Date of Birth");
            dobField = new TextField();

            //add,remove,list buttons
            addButton = new Button("Add");
            addButton.setOnAction(this);

            removeButton = new Button("Remove");
            removeButton.setOnAction(this);

            listButton = new Button("List");
            listButton.setOnAction(this);

            //main text area
            textBox = new TextArea("Students in the application");


            //bottom load,save,exit buttons
            loadButton = new Button("Load");
            loadButton.setOnAction(this);

            saveButton = new Button("Save");
            saveButton.setOnAction(this);

            exitButton = new Button("Exit");
            exitButton.setOnAction(e -> System.exit(0));

            //H boxes
            HBox row1 = new HBox(10);
            row1.getChildren().addAll(nameLabel, nameField);

            HBox row2 = new HBox(10);
            row2.getChildren().addAll(idLabel, idField);

            HBox row3 = new HBox(10);
            row3.getChildren().addAll(dobLabel, dobField);

            HBox row4 = new HBox(10);
            row4.getChildren().addAll(addButton, removeButton, listButton);

            HBox row5 = new HBox(10);
            row5.getChildren().addAll(textBox);

            HBox row6 = new HBox(10);
            row6.setAlignment(Pos.BOTTOM_RIGHT);
            row6.getChildren().addAll(loadButton, saveButton, exitButton);

            // V box
            VBox layout = new VBox(10);
            layout.setPadding(new Insets(20, 20, 20, 20));
            layout.getChildren().addAll(row1, row2, row3, row4, row5, row6);

            Scene scene = new Scene(layout, 450, 400);
            window.setScene(scene);
            window.show();
        }
        catch (Exception e){
            System.out.println("error has occurred");
        }
    }


    public void handle(ActionEvent event) {
        try {

            name = nameField.getText();
            studentID = idField.getText();
            dob = dobField.getText();
            String studentsList = "";


            if (event.getSource() == addButton) {
                System.out.println(name + " " + studentID + " " + dob);
                students.add(name + " " + studentID + " " + dob);
                System.out.println(students);

            }
            else if (event.getSource() == removeButton) {
                students.remove(name + " " + studentID + " " + dob);
            }
            else if (event.getSource() == listButton) {

                for (int i = 0; i < students.size(); i++) {
                    studentsList += students.get(i).toString() + "\n";
                }
                textBox.setText(studentsList);
            }
            else if (event.getSource() == saveButton) {
                FileWriter writer = new FileWriter("Students_list.txt");

                for (int i = 0; i < students.size(); i++) {
                    studentsList += students.get(i).toString() + "\n";
                }
                writer.write(studentsList);
                writer.close();
            }
            else if (event.getSource() == loadButton) {
                String printData = "";
                Scanner reader = new Scanner(savedList);
                while (reader.hasNextLine()) {
                    printData += reader.nextLine() + "\n";
                }
                textBox.setText(printData);
            }
        }
        catch (Exception e) {
            System.out.println("error has occurred");
        }
    }
}