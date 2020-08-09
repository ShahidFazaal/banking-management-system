package controller;

import business.BOFactory;
import business.BOType;
import business.SuperBO;
import business.custom.BranchBO;
import business.custom.UserBO;
import business.custom.impl.BranchBOImpl;
import com.jfoenix.controls.JFXButton;
import dao.custom.UserDAO;
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
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import util.BranchTM;
import util.UserTM;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

public class AdminPanelController {
    public Pane pnBranches;
    public Pane pnUsers;
    public Label lblTime;
    public Label lblDate;

    public JFXButton btnUsers;
    public TextField txtUserId;
    public TextField txtUserName;
    public TextField txtNic;
    public TextField txtPassword;
    public TextField txtEmail;

    public JFXButton btnUserAdd;
    public JFXButton btnUserDelete;
    public TableView<UserTM>tblUsers;
    public JFXButton btnNewUser;


    public JFXButton btnBranch;
    public TextField txtBranchId;
    public TextField txtBContact;
    public TextField txtBLocation;
    public TableView <BranchTM> tblBranch;
    public JFXButton btnAddBranch;
    public JFXButton btnDeleteBranch;
    public JFXButton btnNewBranch;
    public TextField txtBEmail;
    public ComboBox cmbUserRole;
    public ComboBox cmbUBranch;
    public DatePicker dtpDOB;

    BranchBO branchBO = BOFactory.getInstance().getBO(BOType.BRANCH);
    UserBO userBO = BOFactory.getInstance().getBO(BOType.USER);

    public void initialize() throws Exception {
        txtUserName.setDisable(true);
        txtNic.setDisable(true);
        dtpDOB.setDisable(true);
        txtPassword.setDisable(true);
        txtEmail.setDisable(true);
        cmbUBranch.setDisable(true);
        cmbUserRole.setDisable(true);
        btnUserAdd.setDisable(true);
        btnUserDelete.setDisable(true);

        txtBLocation.setDisable(true);
        txtBContact.setDisable(true);
        txtBEmail.setDisable(true);
        btnAddBranch.setDisable(true);
        btnDeleteBranch.setDisable(true);

        pnUsers.setVisible(true);
        initClock();
        initDate();

        txtUserId.setText(userBO.getNewUserId());
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                txtUserName.requestFocus();
            }
        });


        tblBranch.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("branchId"));
        tblBranch.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("location"));
        tblBranch.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("phone"));
        tblBranch.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("email"));

        tblUsers.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("userId"));
        tblUsers.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("userName"));
        tblUsers.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("nic"));
        tblUsers.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("dob"));
        tblUsers.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("password"));
        tblUsers.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("emailAddress"));
        tblUsers.getColumns().get(6).setCellValueFactory(new PropertyValueFactory<>("branch"));
        tblUsers.getColumns().get(7).setCellValueFactory(new PropertyValueFactory<>("userRole"));

        tblBranch.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<BranchTM>() {
            @Override
            public void changed(ObservableValue<? extends BranchTM> observable, BranchTM oldValue, BranchTM newValue) {
                if (newValue == null) {
                    btnAddBranch.setText("Add");
                    btnDeleteBranch.setDisable(true);
                    txtBranchId.clear();
                    txtBLocation.clear();
                    txtBContact.clear();
                    txtBEmail.clear();
                    return;
                }

                txtBranchId.setDisable(false);
                txtBLocation.setDisable(false);
                txtBContact.setDisable(false);
                txtBEmail.setDisable(false);
                btnAddBranch.setDisable(false);
                btnAddBranch.setText("Update");
                btnDeleteBranch.setDisable(false);
                txtBranchId.setText(newValue.getBranchId());
                txtBLocation.setText(newValue.getLocation());
                txtBContact.setText(newValue.getPhone());
                txtBEmail.setText(newValue.getEmail());
            }
        });

        tblUsers.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<UserTM>() {
            @Override
            public void changed(ObservableValue<? extends UserTM> observable, UserTM oldValue, UserTM newValue) {
                if (newValue == null) {
                    btnUserAdd.setText("Add");
                    btnUserDelete.setDisable(true);
                    txtUserId.clear();
                    txtUserName.clear();
                    txtNic.clear();
                    dtpDOB.getEditor().clear();
                    txtPassword.clear();
                    txtEmail.clear();
                    cmbUBranch.getSelectionModel().clearSelection();
                    cmbUserRole.getSelectionModel().clearSelection();
                    return;
                }
                btnUserAdd.setDisable(false);
                btnUserAdd.setText("Update");
                btnUserDelete.setDisable(false);
                txtUserId.setDisable(false);
                txtUserName.setDisable(false);
                txtNic.setDisable(false);
                dtpDOB.setDisable(false);
                txtPassword.setDisable(false);
                txtEmail.setDisable(false);
                cmbUBranch.setDisable(false);
                cmbUserRole.setDisable(false);

                txtUserId.setText(newValue.getUserId());
                txtUserName.setText(newValue.getUserName());
                txtNic.setText(newValue.getNic());
                dtpDOB.setValue(newValue.getDob().toLocalDate());
                txtPassword.setText(newValue.getPassword());
                txtEmail.setText(newValue.getEmailAddress());
                cmbUBranch.getSelectionModel().select(newValue.getBranch());
                cmbUserRole.getSelectionModel().select(newValue.getUserRole());

            }
        });

        // load user Roles
        ObservableList userRole = cmbUserRole.getItems();
        userRole.add("Admin");
        userRole.add("Staff");
        userRole.add("Branch Manager");

        // load branches to know which branch user is working
        ObservableList branch = cmbUBranch.getItems();
        List<BranchTM> allBranches = branchBO.getAllBranches();
        for (BranchTM branchId: allBranches) {
            branch.add(branchId);
        }


        loadAllBranches();
        loadAllUsers();
    }

    public void btnUsers_OnAction(ActionEvent actionEvent) {
        pnUsers.setVisible(true);
        pnBranches.setVisible(false);
        txtUserName.requestFocus();
        try {
            txtUserId.setText(userBO.getNewUserId());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void btnBranch_onAction(ActionEvent actionEvent) {
        pnUsers.setVisible(false);
        pnBranches.setVisible(true);
        txtBContact.requestFocus();
        try {
            txtBranchId.setText(branchBO.getNewBranchId());
        } catch (Exception e) {
            e.printStackTrace();
        }
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

    public void btnUserAdd_OnAction(ActionEvent actionEvent) {
        String userId = txtUserId.getText();
        String userName = txtUserName.getText();
        String nic = txtNic.getText();
        Date dob = Date.valueOf(dtpDOB.getValue());
        String password = txtPassword.getText();
        String email = txtEmail.getText();
        String branch = cmbUBranch.getSelectionModel().getSelectedItem().toString();
        String userRole = cmbUserRole.getSelectionModel().getSelectedItem().toString();

        // Validation
        if (userId.trim().length() == 0 || userName.trim().length() == 0 || nic.trim().length() == 0 || dob == null || password.trim().length() ==0 || email.trim().length()==0 || branch.trim().length()==0 || userRole.trim().length() ==0) {
            new Alert(Alert.AlertType.ERROR, "Fields  can't be empty", ButtonType.OK).show();
            return;
        }

        if (btnUserAdd.getText().equals("Add")){
            try {
                boolean result = userBO.saveUsers(userId, userName, nic, dob, password, email, branch, userRole);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else{
            try {
                userBO.UpdateUsers(userName, nic, dob, password, email, branch, userRole, userId);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        btnNewUser_OnAction(actionEvent);
        loadAllUsers();
    }

    public void btnUserDelete_OnAction(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                "Are you sure whether you want to delete this customer?",
                ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> buttonType = alert.showAndWait();

        if (buttonType.get() == ButtonType.YES){
            String userId = tblUsers.getSelectionModel().getSelectedItem().getUserId();
            try {
                boolean result = userBO.deleteUsers(userId);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        loadAllUsers();



    }

    public void btnNewUser_OnAction(ActionEvent actionEvent) {
        txtUserName.clear();
        txtNic.clear();
        dtpDOB.getEditor().clear();
        txtPassword.clear();
        txtEmail.clear();

        cmbUserRole.getSelectionModel().clearSelection();
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                cmbUBranch.getSelectionModel().clearSelection();
            }
        });



        txtUserName.setDisable(false);
        txtNic.setDisable(false);
        dtpDOB.setDisable(false);
        txtPassword.setDisable(false);
        txtEmail.setDisable(false);
        cmbUBranch.setDisable(false);
        cmbUserRole.setDisable(false);
        btnUserAdd.setDisable(false);

        btnUserDelete.setDisable(true);
        tblUsers.getSelectionModel().clearSelection();


        btnAddBranch.setText("Add");
        btnDeleteBranch.setDisable(true);

        txtUserName.requestFocus();
        try {
            txtUserId.setText(userBO.getNewUserId());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void btnAddBranch_OnAction(ActionEvent actionEvent) {
        String branchIdText = txtBranchId.getText();
        String locationText = txtBLocation.getText();
        String phone = txtBContact.getText();
        String email = txtBEmail.getText();

        // Validation
        if (locationText.trim().length() == 0 || phone.trim().length() == 0 || email.trim().length() == 0) {
            new Alert(Alert.AlertType.ERROR, "Fields  can't be empty", ButtonType.OK).show();
            return;
        }




        try {
            if (btnAddBranch.getText().equals("Add")) {
                boolean result = branchBO.saveBranches(branchIdText, locationText, phone, email);

            }else{
                branchBO.UpdateBranch(locationText,phone,email,branchIdText);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        btnNewBranch_OnAction(actionEvent);
        loadAllBranches();
    }

    public void btnDeleteBranch_OnAction(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                "Are you sure whether you want to delete this Branch?",
                ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> buttonType = alert.showAndWait();

        try {
            if (buttonType.get() == ButtonType.YES){
                String branchId = tblBranch.getSelectionModel().getSelectedItem().getBranchId();
                System.out.println(branchId);
                boolean result= branchBO.deleteBranch(branchId);
                if (!result){
                    new Alert(Alert.AlertType.ERROR, "Failed to delete the Branch", ButtonType.OK).show();
                }else {
                    tblBranch.getSelectionModel().clearSelection();
                    loadAllBranches();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void btnNewBranch_OnAction(ActionEvent actionEvent) {
        txtBranchId.clear();
        txtBLocation.clear();
        txtBContact.clear();
        txtBEmail.clear();

        txtBLocation.setDisable(false);
        txtBContact.setDisable(false);
        txtBEmail.setDisable(false);
        btnAddBranch.setDisable(false);

        btnAddBranch.setText("Add");


        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                txtBContact.requestFocus();
                try {
                    txtBranchId.setText(branchBO.getNewBranchId());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });


    }

    public void loadAllBranches(){
        tblBranch.getItems().clear();
        try {
            List<BranchTM> allBranches = branchBO.getAllBranches();
            ObservableList<BranchTM> branches = FXCollections.observableArrayList(allBranches);
            tblBranch.setItems(branches);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadAllUsers(){
        tblUsers.getItems().clear();
        try {
            List<UserTM> allUsers = userBO.getAllUsers();
            ObservableList<UserTM> users = FXCollections.observableArrayList(allUsers);
            tblUsers.setItems(users);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
