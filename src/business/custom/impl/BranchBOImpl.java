package business.custom.impl;

import business.custom.BranchBO;
import dao.DAOFactory;
import dao.DAOType;
import dao.SuperDAO;
import dao.custom.BranchDAO;
import entity.Branch;
import util.BranchTM;

import java.util.ArrayList;
import java.util.List;

public class BranchBOImpl implements BranchBO {
    BranchDAO branchDAO = DAOFactory.getInstance().getDAO(DAOType.BRANCH);

    @Override
    public List<BranchTM> getAllBranches() throws Exception {
        ArrayList<BranchTM> branches = new ArrayList<>();
        List<Branch> allBranches = branchDAO.findAll();
        for (Object entity : allBranches) {
            Branch branch = (Branch) entity;
            branches.add(new BranchTM(branch.getBranchId(),branch.getLocation(),branch.getPhone(),branch.getEmail()));
        }
        return branches;
    }

    @Override
    public boolean saveBranches(String id, String location, String phone, String email) throws Exception {
        return branchDAO.save(new Branch(id,location,phone,email));
    }

    @Override
    public String getNewBranchId() throws Exception {
        String lastBranchId = branchDAO.getLastBranchId();
        if (lastBranchId == null){
            return "B001";
        }else{
            int maxId = Integer.parseInt(lastBranchId.replace("B", ""));
            maxId = maxId + 1;
            String id = "";
            if (maxId < 10) {
                id = "B00" + maxId;
            } else if (maxId < 100) {
                id = "B0" + maxId;
            } else {
                id = "B" + maxId;
            }
            return id;
        }
    }

    @Override
    public boolean deleteBranch(String branchId) throws Exception {
        return branchDAO.delete(branchId);
    }

    @Override
    public boolean UpdateBranch(String locationText, String phone, String email,String branchId) throws Exception {
        return branchDAO.update(new Branch(branchId,locationText,phone,email));
    }

}
