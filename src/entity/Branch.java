package entity;

public class Branch implements SuperEntity {
    private String branchId;
    private String location;
    private String phone;
    private String email;

    public Branch() {
    }

    public Branch(String branchId, String location, String phone, String email) {
        this.branchId = branchId;
        this.location = location;
        this.phone = phone;
        this.email = email;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Branch{" +
                "branchId='" + branchId + '\'' +
                ", location='" + location + '\'' +
                ", phone=" + phone +
                ", email='" + email + '\'' +
                '}';
    }
}
