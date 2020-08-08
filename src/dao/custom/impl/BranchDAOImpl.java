package dao.custom.impl;

import dao.CrudUtil;
import dao.custom.BranchDAO;
import entity.Branch;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BranchDAOImpl implements BranchDAO {
    @Override
    public List<Branch> findAll() throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT * FROM Branch");
        ArrayList<Branch> branches = new ArrayList<>();
        while (rst.next()){
            branches.add(new Branch(rst.getString("branchId"),rst.getString("location"),rst.getString("phone"),rst.getString("email")));
        }
        return branches;
    }

    @Override
    public Branch find(String pk) throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT * FROM Branch WHERE branchId =?,pk");
        if (rst.next()){
            return new Branch(rst.getString(1),rst.getString(2),rst.getString(3),rst.getString(4));
        }
        return null;
    }

    @Override
    public boolean save(Branch entity) throws Exception {
        return CrudUtil.execute("INSERT INTO Branch VALUES (?,?,?,?)", entity.getBranchId(), entity.getLocation(), entity.getPhone(), entity.getEmail());

    }

    @Override
    public boolean update(Branch entity) throws Exception {
        return CrudUtil.execute("UPDATE Branch SET location =?,phone=?,email=? WHERE branchId=?",entity.getLocation(),entity.getPhone(),entity.getEmail(),entity.getBranchId());
    }

    @Override
    public boolean delete(String key) throws Exception {
        return CrudUtil.execute("DELETE FROM Branch WHERE branchId =?",key);
    }

    @Override
    public String getLastBranchId() throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT branchId FROM Branch ORDER BY branchId DESC LIMIT 1 ");
        if (rst.next()) {
            return rst.getString(1);
        }else{
            return null;
        }
    }
}
