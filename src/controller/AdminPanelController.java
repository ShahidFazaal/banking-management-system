package controller;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AdminPanelController {
    public Pane pnBranches;
    public Pane pnUsers;
    public Label lblTime;
    public Label lblDate;

    public void initialize(){
        pnUsers.setVisible(true);
        initClock();
        initDate();
    }

    public void btnUsers_OnAction(ActionEvent actionEvent) {
        pnUsers.setVisible(true);
        pnBranches.setVisible(false);
    }

    public void btnBranch_onAction(ActionEvent actionEvent) {
        pnUsers.setVisible(false);
        pnBranches.setVisible(true);
    }
    private void initClock() {

        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm a");
            lblTime.setText(LocalDateTime.now().format(formatter));
        }), new KeyFrame(Duration.seconds(1)));
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();
    }

    private void initDate() {

        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE,dd-MMMM-yy");
            lblDate.setText(LocalDateTime.now().format(formatter));
        }), new KeyFrame(Duration.seconds(1)));
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();
    }

}
