package com.winter.userinterface;

import com.winter.engine.*;


import com.winter.engine.math.parser.MathExpression;
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

    boolean resultInScreen = false;
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

        this.updateScreen();

        String number = ((Pane)event.getSource()).getId().replace("btn","");
        this.mathExpressionString += number;
        lblResult.setText(mathExpressionString);
    }

    private void updateScreen(){
        if(this.resultInScreen){
            System.out.println("> Expression Auto reset !");
            this.mathExpressionString = "";
            this.resultInScreen=false;
        }
    }

    @FXML
    void onSymbolClicked(MouseEvent event) {
        this.updateScreen();

        String symbol = ((Pane)event.getSource()).getId().replace("btn","");
        //System.out.print(symbol+" ");
        if(symbol.equals("Equals")) {
            MathExpression mathExpression = new MathExpression(this.mathExpressionString);
            Double result = mathExpression.getResult();
            if (result.isNaN()){lblResult.setText("Error");}
            else if (result.isInfinite()) lblResult.setText("Inf");
            else {lblResult.setText(String.valueOf(result));}
            this.resultInScreen = true;
        }
        else if(symbol.equals("Clear")) {
            this.mathExpressionString ="";
            lblResult.setText(String.valueOf(0.0));
            System.out.println("> Expression reset !");
        }
        else if(symbol.equals("Dot")){
            this.mathExpressionString += ".";
            lblResult.setText(this.mathExpressionString);
        }
        //instruction for parenthesis separated for changes later
        else if(symbol.equals("RightBracket")){
            this.mathExpressionString += ")";
            lblResult.setText(this.mathExpressionString);
        }
        else if(symbol.equals("LeftBracket")){
            this.mathExpressionString += "(";
            lblResult.setText(this.mathExpressionString);
        }
        else {
            switch (symbol) {
                case "Pow" -> this.mathExpressionString +="^";
                case "Plus" -> this.mathExpressionString += "+";
                case "Minus" -> this.mathExpressionString += "-";
                case "Multiply" -> this.mathExpressionString += "*";
                case "Divide" -> this.mathExpressionString += "/";

            }
            lblResult.setText(mathExpressionString);
            //blink the pointer
        }
    }
}