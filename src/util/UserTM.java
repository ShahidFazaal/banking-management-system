package util;

import entity.SuperEntity;

import java.sql.Date;

public class UserTM implements SuperEntity {
    private String userId;
    private String userName;
    private String nic;
    private Date dob;
    private String password;
    private String emailAddress;
    private String branch;
    private String userRole;

    public UserTM() {
    }

    public UserTM(String userId, String userName, String nic, Date dob, String password, String emailAddress, String branch, String userRole) {
        this.userId = userId;
        this.userName = userName;
        this.nic = nic;
        this.dob = dob;
        this.password = password;
        this.emailAddress = emailAddress;
        this.branch = branch;
        this.userRole = userRole;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    @Override
    public String toString() {
        return userId;
    }
}
