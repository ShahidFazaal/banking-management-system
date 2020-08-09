package controller;

import business.BOFactory;
import business.BOType;
import business.custom.CustomerBO;
import business.custom.TransactionBO;
import com.jfoenix.controls.JFXButton;
import entity.CustomEntity;
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
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.util.Duration;
import util.CustomerTM;
import util.TransactionTM;
import util.UserTM;

import java.awt.event.ActionListener;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
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
    
    public TextField txtDInitialDeposit;
    public TextField txtDCustomerName;
    public TextField txtDEmailAddress;
    public TextField txtDAccountBalance;
    public TextField txtDNic;
    public TextField txtDContactNumber;
    public TextField txtDAccountNumber;
    public JFXButton btnDUpdate;
    public ComboBox cmbDAccountType;
    public ComboBox cmbDGender;
    public DatePicker dpDDob;
    public TextField txtDBranch;

    public JFXButton btnCBSearch;
    public TextField txtCBCustomername;
    public TextField txtCBNic;
    public TextField txtCBAccountNumber;
    public Label lblCBCurrentBalance;
    
    public TextField txtDepositAccountNumber;
    public TextField txtDepositNic;
    public JFXButton btnDepositsearch;
    public TextField txtDepositCustomerName;
    public TextField txtDepositCurrentBalance;
    public TextField txtDepostAmmount;
    
    public TextField txtTransactionSearch;
    public JFXButton btnTSearch;
    public TableView<TransactionTM> tblTransaction;
    public JFXButton btnDeposit;


    CustomerBO customerBO = BOFactory.getInstance().getBO(BOType.CUSTOMER);
    TransactionBO transactionBO = BOFactory.getInstance().getBO(BOType.TRANSACTION);

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

       txtSearch.textProperty().addListener(new ChangeListener<String>() {
           @Override
           public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
               if (txtSearch.getText().equals("")){
                   loadAllAccountDetails();
               }
           }
       });

        // load Account Type in Account detail
        ObservableList dAccountType = cmbDAccountType.getItems();
        dAccountType.add("Saving Account");
        dAccountType.add("Current Account");
        // load gender in Account detail
        ObservableList dGender = cmbDGender.getItems();
        dGender.add("Male");
        dGender.add("Female");

        txtDNic.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
               if (!txtDNic.getText().equals("") && !txtDAccountNumber.getText().equals("")){
                   txtDAccountNumber.setDisable(false);
                   txtDNic.setDisable(false);
                   txtDNic.setEditable(false);
                   txtDAccountNumber.setEditable(false);
                   return;
               }

                if (!newValue.equals("")){
                    txtDAccountNumber.setDisable(true);
                }else {
                    txtDAccountNumber.setDisable(false);
                }
            }
        });
        txtDAccountNumber.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!txtDNic.getText().equals("") && !txtDAccountNumber.getText().equals("")){
                    txtDAccountNumber.setDisable(false);
                    txtDNic.setDisable(false);
                    txtDNic.setEditable(false);
                    txtDAccountNumber.setEditable(false);
                    return;
                }
                if (!newValue.equals("")){
                    txtDNic.setDisable(true);
                }else{
                    txtDNic.setDisable(false);

                }
            }
        });

        btnDUpdate.setDisable(false);
        btnDeposit.setDisable(true);

        tblTransaction.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("transactionId"));
        tblTransaction.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("accountNumber"));
        tblTransaction.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("transactionType"));
        tblTransaction.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("date"));
        tblTransaction.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("transactionAmount"));
        tblTransaction.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("userId"));
        tblTransaction.getColumns().get(6).setCellValueFactory(new PropertyValueFactory<>("branch"));

        txtTransactionSearch.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (txtTransactionSearch.getText().equals("")){
                    loadAllTransaction();
                }
            }
        });






    loadAllTransaction();
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

    public void txtSearch_OnKeyPressed(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER){
            String accountNumber = txtSearch.getText();
            try {
                CustomerTM account = customerBO.getAccount(accountNumber);
                tblAccountDetails.getItems().clear();
                tblAccountDetails.getItems().add(account);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void txtDAccountNumber_OnAction(ActionEvent actionEvent) {
        if (txtDAccountNumber.getText().trim().equals("") && txtDNic.getText().trim().equals("")){
            return;
        }
        String search;
        if (txtDAccountNumber.getText().equals("")){
            search = txtDNic.getText();
        }else {
            search=txtDAccountNumber.getText();
        }
        try {
            CustomEntity account = customerBO.getJoinAccount(search);
            if (!(account ==null)) {
                txtDNic.setText(account.getNic());
                txtDAccountNumber.setText(account.getAccountNumber());
                cmbDAccountType.getSelectionModel().select(account.getAccountType());
                txtDCustomerName.setText(account.getCustomerName());
                txtDEmailAddress.setText(account.getEmailAddress());
                txtDContactNumber.setText(account.getContactNumber());
                dpDDob.setValue(account.getDob().toLocalDate());
                cmbDGender.getSelectionModel().select(account.getGender());
                txtDAccountBalance.setText(account.getAccountBalance().toString());
                txtDInitialDeposit.setText(account.getInitialDeposit().toString());
                txtDBranch.setText(account.getLocation());

                btnDUpdate.setDisable(false);
            }else {
                new Alert(Alert.AlertType.ERROR,"Invalid Account Number or Nic Number!",ButtonType.OK).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void btnDUpdate_OnAction(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                "Are you sure whether you want to delete this customer?",
                ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> buttonType = alert.showAndWait();

        try {
            if (buttonType.get().equals(ButtonType.YES)) {
                String accountNumber = txtDAccountNumber.getText();
                customerBO.deleteAccount(accountNumber);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        txtDAccountNumber.clear();
        txtDNic.clear();
        txtDAccountNumber.setEditable(true);
        txtDNic.setEditable(true);
        cmbDGender.getSelectionModel().clearSelection();
        cmbDAccountType.getSelectionModel().clearSelection();
        txtDCustomerName.clear();
        txtDInitialDeposit.clear();
        txtDEmailAddress.clear();
        txtDContactNumber.clear();
        txtDBranch.clear();
        txtDAccountBalance.clear();
        dpDDob.getEditor().clear();
        btnDUpdate.setDisable(true);
        loadAllAccountDetails();

    }

    public void txtDNic_OnAction(ActionEvent actionEvent) {
        txtDAccountNumber_OnAction(actionEvent);
    }

    public void ad_OnKeyPressed(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ESCAPE) {
            txtDAccountNumber.clear();
            txtDNic.clear();
            txtDAccountNumber.setEditable(true);
            txtDNic.setEditable(true);
            cmbDGender.getSelectionModel().clearSelection();
            cmbDAccountType.getSelectionModel().clearSelection();
            txtDCustomerName.clear();
            txtDInitialDeposit.clear();
            txtDEmailAddress.clear();
            txtDContactNumber.clear();
            txtDBranch.clear();
            txtDAccountBalance.clear();
            dpDDob.getEditor().clear();
            btnDUpdate.setDisable(true);
        }
    }

    public void btnCBSearch_onAction(ActionEvent actionEvent) {
        txtCBAccountNumber_OnAction(actionEvent);
    }

    public void txtCBAccountNumber_OnAction(ActionEvent actionEvent) {
        String accountNumber = txtCBAccountNumber.getText();
        try {
            CustomerTM account = customerBO.getAccount(accountNumber);
            if (!(account ==null)){
                txtCBCustomername.setText(account.getCustomerName());
                txtCBNic.setText(account.getNic());
                lblCBCurrentBalance.setText(account.getAccountBalance().toString());

                txtCBAccountNumber.clear();
            }else {
                new Alert(Alert.AlertType.ERROR,"Account Number is invalid!",ButtonType.OK).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void txtDepositAccountNumber_OnAction(ActionEvent actionEvent) {
        String accountNumber = txtDepositAccountNumber.getText();
        try {
            CustomerTM account = customerBO.getAccount(accountNumber);
            if (!(account ==null)){
                txtDepositNic.setText(account.getNic());
                txtDepositCustomerName.setText(account.getCustomerName());
                txtDepositCurrentBalance.setText(account.getAccountBalance().toString());
                btnDeposit.setDisable(false);
                txtDepostAmmount.requestFocus();
            }else {
                new Alert(Alert.AlertType.ERROR,"Account Number is invalid!",ButtonType.OK).show();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void btnDepositsearch_OnAction(ActionEvent actionEvent) {
        txtDepositAccountNumber_OnAction(actionEvent);
    }

    public void btnDeposit(ActionEvent actionEvent) {
        int transactionId =0;
        String accountNumber = txtDepositAccountNumber.getText();
        String transactionType = "Deposit";
        Date date = Date.valueOf(LocalDate.now());
        BigDecimal depositAmmount = BigDecimal.valueOf(Double.parseDouble(txtDepostAmmount.getText()));
        String userId = lblUserId.getText();
        String branchId = lblBranchId.getText();
        try {
            boolean result = transactionBO.doDeposit(new TransactionTM(transactionId,accountNumber,transactionType,date,depositAmmount,userId,branchId));
            if (result==true){
                new Alert(Alert.AlertType.INFORMATION,"successfully deposited "+depositAmmount.toString()+" to the account " + accountNumber,ButtonType.OK).show();
                txtDepositAccountNumber.clear();
                txtDepostAmmount.clear();
                txtDepositCurrentBalance.clear();
                txtDepositCustomerName.clear();
                txtDepositNic.clear();
                txtDepositAccountNumber.requestFocus();
                btnDeposit.setDisable(true);
                loadAllTransaction();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }



    public void btnTSearch(ActionEvent actionEvent) {

    }
    public void loadAllTransaction(){
        tblTransaction.getItems().clear();
        try {
            List<TransactionTM> allCustomerAccountDetails = transactionBO.getAllTransaction();
            ObservableList<TransactionTM> transaction = FXCollections.observableArrayList(allCustomerAccountDetails);
            tblTransaction.setItems(transaction);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void txtTransactionSearch(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER){
            String accountNumber = txtTransactionSearch.getText();
            try {
                List<TransactionTM> transactions = transactionBO.getTransactions(accountNumber);
                ObservableList<TransactionTM> transaction = FXCollections.observableArrayList(transactions);
                tblTransaction.getItems().clear();
                tblTransaction.setItems(transaction);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void calculator_OnAction(ActionEvent actionEvent) {
        Runtime run = Runtime.getRuntime();
        try {
            run.exec("gnome-calculator");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void notePad_OnAction(ActionEvent actionEvent) {
        Runtime run = Runtime.getRuntime();
        try {
            run.exec("gedit");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void btnCurencyConvertor_OnAction(ActionEvent actionEvent) {
        Runtime rt = Runtime.getRuntime();
        String url = "https://www.xe.com/";
        String[] browsers = { "epiphany", "firefox", "mozilla", "konqueror","chrome",
                "netscape", "opera", "links", "lynx" };

        StringBuffer cmd = new StringBuffer();
        for (int i = 0; i < browsers.length; i++)
            if(i == 0)
                cmd.append(String.format(    "%s \"%s\"", browsers[i], url));
            else
                cmd.append(String.format(" || %s \"%s\"", browsers[i], url));
        // If the first didn't work, try the next browser and so on

        try {
            rt.exec(new String[] { "sh", "-c", cmd.toString() });
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}

