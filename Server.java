package com.example.m82;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;

public class Server extends Application {

    ArrayList<FortuneCookie> fortuneCookies;

    ObjectInputStream fromClient = null;
    ObjectOutputStream toClient = null;

    @Override
    public void start(Stage primaryStage) throws IOException {
        TextArea ta = new TextArea();
        ta.setFont(Font.font("Arial", 30));
        Scene scene = new Scene(ta, 450, 200);
        primaryStage.setTitle("Server");
        primaryStage.setScene(scene);
        primaryStage.show();

        fortuneCookies = new ArrayList<>();
        fortuneCookies.add(new FortuneCookie("Fortune 1", new int[]{1, 2, 3, 4, 5}, "Word 1"));
        fortuneCookies.add(new FortuneCookie("Fortune 2", new int[]{6, 7, 8, 9, 10}, "Word 2"));
        fortuneCookies.add(new FortuneCookie("Fortune 3", new int[]{11, 12, 13, 14, 15}, "Word 3"));
        fortuneCookies.add(new FortuneCookie("Fortune 4", new int[]{16, 17, 18, 19, 20}, "Word 4"));
        fortuneCookies.add(new FortuneCookie("Fortune 5", new int[]{21, 22, 23, 24, 25}, "Word 5"));

        new Thread(() -> {
            Platform.runLater(() -> ta.appendText("Start: " + new Date() + "\n"));
            try {
                ServerSocket server = new ServerSocket(8000);
                Socket socket = server.accept();
                fromClient = new ObjectInputStream(socket.getInputStream());
                toClient = new ObjectOutputStream(socket.getOutputStream());
                while (true) {
                    int index = fromClient.readInt();
                    FortuneCookie selectedCookie = fortuneCookies.get(index);
                    toClient.writeObject(selectedCookie);
                    toClient.flush();
                    Platform.runLater(() -> {
                        ta.appendText("Received index: " + index + "\n");
                        ta.appendText("Sent FortuneCookie: " + selectedCookie.toString() + "\n");
                    });
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }).start();
    }

    public static void main(String[] args) {
        try {
            launch(args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
