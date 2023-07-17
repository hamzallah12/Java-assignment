package com.example.m82;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.*;
import java.net.Socket;

public class Client extends Application {
    //Declare IO stream in class so accessible everywhere
    ObjectOutputStream toServer = null;
    ObjectInputStream fromServer = null;

    @Override
    public void start(Stage primaryStage) throws Exception{
        //Panel p to hold the label and text field
        BorderPane pane = new BorderPane();
        pane.setPadding(new Insets(5));
        pane.setStyle("-fx-border-color: green");
        Label instructions = new Label("Enter 1-5 for fortune");
        instructions.setFont(Font.font("Arial", 30));
        pane.setLeft(instructions);

        TextField tf = new TextField();
        tf.setAlignment(Pos.BOTTOM_RIGHT);
        tf.setFont(Font.font("Arial", 30));
        pane.setCenter(tf);

        BorderPane mainPane = new BorderPane();
        TextArea ta = new TextArea();
        ta.setFont(Font.font("Arial", 30));
        mainPane.setCenter(ta);
        mainPane.setTop(pane);

        //Create scene
        Scene scene = new Scene(mainPane, 450, 200);
        primaryStage.setTitle("Client");
        primaryStage.setScene(scene);
        primaryStage.show();


        try {
            // Create socket to connect to the server
            Socket socket = new Socket("localhost", 8000);
            // Create an output stream to send data to the server
            toServer = new ObjectOutputStream(socket.getOutputStream());
            // Create an input stream to receive data from the server
            fromServer = new ObjectInputStream(socket.getInputStream());
        } catch (IOException ex) {
            ta.appendText(ex.toString() + '\n');
            return; // Stop further execution if an exception occurs
        }

        // Create a new task object using anonymous inner class and lambda
        tf.setOnAction(e -> {
            try {
                // Get the index from the text field
                int index = Integer.parseInt(tf.getText().trim());

                // Validate the input index
                if (index >= 1 && index <= 5) {
                    // Send the index to the server
                    toServer.writeInt(index);
                    toServer.flush();

                    // Receive the FortuneCookie object from the server
                    FortuneCookie fortuneCookie = (FortuneCookie) fromServer.readObject();

                    // Display the FortuneCookie object in the text area
                    ta.appendText("Received FortuneCookie: " + fortuneCookie.toString() + "\n");
                } else {
                    ta.appendText("Invalid input. Please enter a number from 1 to 5.\n");
                }
            } catch (NumberFormatException ex) {
                ta.appendText("Invalid input. Please enter a number from 1 to 5.\n");
            } catch (IOException | ClassNotFoundException ex) {
                ex.printStackTrace();
            }
        });
    }


    public static void main(String[] args) {
        launch(args);
    }
}
