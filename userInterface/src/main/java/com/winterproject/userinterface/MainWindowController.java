package com.winterproject.userinterface;

import math.parser.MathExpression;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class MainWindowController {
    @FXML private Pane titlePane;
    @FXML private ImageView btnMinimize, btnClose;
    @FXML private Label lblResult;

    private  String mathExpressionString = "";

    private double x, y;

    public void init(Stage stage) {
        titlePane.setOnMousePressed(mouseEvent -> {
            x = mouseEvent.getSceneX();
            y = mouseEvent.getSceneY();
        });
        titlePane.setOnMouseDragged(mouseEvent -> {
            stage.setX(mouseEvent.getScreenX()-x);
            stage.setY(mouseEvent.getScreenY()-y);
        });

        btnClose.setOnMouseClicked(mouseEvent -> stage.close());
        btnMinimize.setOnMouseClicked(mouseEvent -> stage.setIconified(true));
    }

    @FXML
    void onNumberClicked(MouseEvent event) {
        String number = ((Pane)event.getSource()).getId().replace("btn","");
        this.mathExpressionString += number;
    }

    @FXML
    void onSymbolClicked(MouseEvent event) {
        String symbol = ((Pane)event.getSource()).getId().replace("btn","");
        if(symbol.equals("Equals")) {
            MathExpression mathExpression = new MathExpression(this.mathExpressionString);
            double result = mathExpression.getResult();
            lblResult.setText(String.valueOf(result));
        }
        else if(symbol.equals("Clear")) {
            lblResult.setText(String.valueOf(0.0));
        }
        else if(symbol.equals(".")){
            this.mathExpressionString += symbol;
        }
        else {
            switch (symbol) {
                case "Plus" -> this.mathExpressionString += "+";
                case "Minus" -> this.mathExpressionString += "-";
                case "Multiply" -> this.mathExpressionString += "*";
                case "Divide" -> this.mathExpressionString += "/";
                case "Pow" -> this.mathExpressionString +="^";
            }
            //blink the pointer
        }
    }
}