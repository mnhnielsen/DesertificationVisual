package sample.controllers;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.text.Text;
import sample.WorldOfZuul.Strings;

public class EndRoomController {

    public Text questionText;
    public RadioButton A1;
    public RadioButton A2;
    public RadioButton A3;
    public RadioButton A4;
    public Button submit;
    public Label status;

    ToggleGroup question1 = new ToggleGroup();

    public void initialize() {
        A1.setToggleGroup(question1);
        A2.setToggleGroup(question1);
        A3.setToggleGroup(question1);
        A4.setToggleGroup(question1);
        submit.setDisable(true);

        A1.setOnAction(e -> submit.setDisable(false));
        A2.setOnAction(e -> submit.setDisable(false));
        A3.setOnAction(e -> submit.setDisable(false));
        A4.setOnAction(e -> submit.setDisable(false));

        questionText.setWrappingWidth(400);

        A1.setWrapText(true);
        A1.setMaxWidth(500);

        A2.setWrapText(true);
        A2.setMaxWidth(500);

        A3.setWrapText(true);
        A3.setMaxWidth(500);

        A4.setWrapText(true);
        A4.setMaxWidth(500);


        setQuestionText1();
    }

    public void setQuestionText1() {
        questionText.setText(Strings.Question1());
        A1.setText(Strings.answer1("A"));
        A2.setText(Strings.answer1("B"));
        A3.setText(Strings.answer1("C"));
        A4.setText(Strings.answer1("D"));

        submit.setOnAction(e -> {
            if (A1.isSelected()) {
                status.setText("Correct Answer");
                submit.setDisable(true);
                setQuestionText2();
            } else {
                status.setText("Incorrect Answer, try again");
                submit.setDisable(false);
            }
        });
    }

    public void setQuestionText2() {
        questionText.setText(Strings.Question2());
        A1.setText(Strings.answer2("A"));
        A2.setText(Strings.answer2("B"));
        A3.setText(Strings.answer2("C"));
        A4.setText(Strings.answer2("D"));

        submit.setOnAction(e -> {
            if (A3.isSelected()) {
                status.setText("Correct Answer");
                submit.setDisable(true);
                setQuestionText3();
            } else {
                status.setText("Incorrect Answer, try again");
                submit.setDisable(false);
            }
        });
    }

    public void setQuestionText3() {
        questionText.setText(Strings.Question3());
        A1.setText(Strings.answer3("A"));
        A2.setText(Strings.answer3("B"));
        A3.setText(Strings.answer3("C"));
        A4.setText(Strings.answer3("D"));

        submit.setOnAction(e -> {
            if (A4.isSelected()) {
                status.setText("Correct Answer");
                submit.setDisable(true);
                exit();
            } else {
                status.setText("Incorrect Answer, try again");
                submit.setDisable(false);
            }
        });
    }

    public void exit() {
        System.exit(0);
    }
}
