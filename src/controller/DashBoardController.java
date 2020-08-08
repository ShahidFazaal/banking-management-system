package controller;

import business.BOFactory;
import business.BOType;
import business.custom.CustomerBO;
import com.jfoenix.controls.JFXButton;
import entity.Customer;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Duration;
import util.CustomerTM;
import util.UserTM;

import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Timer;
import java.util.logging.SimpleFormatter;

public class DashBoardController {
    public Label lblTime;
    public Label lblDate;
    public Label lblUserId;
    public Label lblUserName;
    public Label lblBranchId;

    public TextField txtCustomerName;
    public TextField txtAccountNumber;
    public TextField txtContactNumber;
    public TextField txtNic;
    public TextField txtEmail;
    public TextField txtInnitialDeposit;
    public JFXButton btnCreateAccount;
    public ComboBox cmbAccountType;
    public ComboBox cmbGender;
    public DatePicker dpDOB;
    public CheckBox cbInitialDeposit;

    public TableView<CustomerTM> tblAccountDetails;
    public TextField txtSearch;
    public Button btnGenarateAN;
    public Button btnCancel;


    CustomerBO customerBO = BOFactory.getInstance().getBO(BOType.CUSTOMER);

    public void initialize(){
        initClock();
        initDate();

        txtInnitialDeposit.setDisable(true);
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                btnGenarateAN.requestFocus();
            }
        });


        // load Account Type
        ObservableList accountType = cmbAccountType.getItems();
        accountType.add("Saving Account");
        accountType.add("Current Account");
        // load gender
        ObservableList gender = cmbGender.getItems();
        gender.add("Male");
        gender.add("Female");
        cbInitialDeposit.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                boolean selected = cbInitialDeposit.isSelected();
                if (!selected){
                    txtInnitialDeposit.setDisable(true);
                }else {
                    txtInnitialDeposit.setDisable(false);
                }
            }
        });

        tblAccountDetails.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("customerName"));
        tblAccountDetails.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("accountNumber"));
        tblAccountDetails.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("accountType"));
        tblAccountDetails.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("gender"));
        tblAccountDetails.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("dob"));
        tblAccountDetails.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("contactNumber"));
        tblAccountDetails.getColumns().get(6).setCellValueFactory(new PropertyValueFactory<>("nic"));
        tblAccountDetails.getColumns().get(7).setCellValueFactory(new PropertyValueFactory<>("emailAddress"));
        tblAccountDetails.getColumns().get(8).setCellValueFactory(new PropertyValueFactory<>("accountBalance"));
        tblAccountDetails.getColumns().get(9).setCellValueFactory(new PropertyValueFactory<>("initialDeposit"));
        tblAccountDetails.getColumns().get(10).setCellValueFactory(new PropertyValueFactory<>("branch"));

    loadAllAccountDetails();


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

    public void btnCreateAccount_OnAction(ActionEvent actionEvent) {
        String accountNumber = txtAccountNumber.getText();
        String accountType = cmbAccountType.getSelectionModel().getSelectedItem().toString();
        String customerName = txtCustomerName.getText();
        String gender = cmbGender.getSelectionModel().getSelectedItem().toString();
        Date dob = Date.valueOf(dpDOB.getValue());
        String contactNumber = txtContactNumber.getText();
        String nic = txtNic.getText();
        String email = txtEmail.getText();
        BigDecimal initialDeposit = BigDecimal.valueOf(0);
        if (cbInitialDeposit.isSelected()){
            initialDeposit = new BigDecimal(txtInnitialDeposit.getText());
        }
        BigDecimal accountBalance = initialDeposit;
        System.out.println(accountBalance);
        System.out.println(initialDeposit);
        try {
            boolean result = customerBO.saveCustomer(nic, accountNumber, accountType, customerName, email, contactNumber, dob, gender,accountBalance,lblBranchId.getText(), initialDeposit);
            if (result ==true){
                new Alert(Alert.AlertType.INFORMATION,"Account Created Successfully!",ButtonType.OK).show();
            loadAllAccountDetails();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        txtAccountNumber.clear();
        txtCustomerName.clear();
        cmbAccountType.getSelectionModel().clearSelection();
        cmbGender.getSelectionModel().clearSelection();
        dpDOB.getEditor().clear();
        txtContactNumber.clear();
        txtNic.clear();
        txtEmail.clear();
        txtInnitialDeposit.clear();
        cbInitialDeposit.setSelected(false);
        btnGenarateAN.requestFocus();

    }

    public void btnGenarateAN_OnAction(ActionEvent actionEvent) {
        try {
            txtAccountNumber.setText(customerBO.generateNewAccountNumber());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void btnCancel_OnAction(ActionEvent actionEvent) {
        txtAccountNumber.clear();
        txtCustomerName.clear();
        cmbAccountType.getSelectionModel().clearSelection();
        cmbGender.getSelectionModel().clearSelection();
        dpDOB.getEditor().clear();
        txtContactNumber.clear();
        txtNic.clear();
        txtEmail.clear();
        txtInnitialDeposit.clear();
        cbInitialDeposit.setSelected(false);
        btnGenarateAN.requestFocus();
    }
    public void loadAllAccountDetails(){
        tblAccountDetails.getItems().clear();
        try {
            List<CustomerTM> allCustomerAccountDetails = customerBO.getAllCustomerAccountDetails();
            ObservableList<CustomerTM> users = FXCollections.observableArrayList(allCustomerAccountDetails);
            tblAccountDetails.setItems(users);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

