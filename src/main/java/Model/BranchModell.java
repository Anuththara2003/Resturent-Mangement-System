package Model;

import com.assignment.resturentmanagementsystem.Crud.CrudUtil;
import com.assignment.resturentmanagementsystem.Dto.BranchDto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BranchModell {
    public ArrayList<BranchDto> getAllBranch() throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("select * from branch");

        ArrayList<BranchDto> branchDtos = new ArrayList<>();

        while (rst.next()) {
            BranchDto BranchDTo = new BranchDto(
                    rst.getInt(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getInt(4)


            );
            branchDtos.add(BranchDTo);
        }
        return branchDtos;
    }





    public String getNextBranchId() throws SQLException, ClassNotFoundException {
            ResultSet rst =  CrudUtil.execute("select Branch_Id from branch order by Branch_Id desc limit 1");

            if (rst.next()){
                int lastId = rst.getInt(1);
                int substring = lastId;
                int i = Integer.parseInt(String.valueOf(substring));
                int newIdIndex = i+1;

                return String.format("C%03d",newIdIndex);
            }
            return  "1";

        }

        public boolean onclickedSave(BranchDto branchDto) throws SQLException, ClassNotFoundException {

            boolean isSaved =  CrudUtil.execute(
                    "insert into branch values (?,?,?,?)",

                    branchDto.getBranchId(),
                    branchDto.getBranchName(),
                    branchDto.getBranchAddress(),
                    branchDto.getEmployeeId()

            );

            return isSaved;
        }

        public static boolean updateOnAction(BranchDto branchDto) throws SQLException, ClassNotFoundException {
            return CrudUtil.execute(
                    "update branch set Name=?, Location=?, Location=? where Branch_Id=?",

                    branchDto.getBranchAddress(),
                    branchDto.getEmployeeId(),
                    branchDto.getBranchName(),
                    branchDto.getBranchId()

            );
        }

        public static boolean onDelete(int Branch_Id ) throws SQLException, ClassNotFoundException {

            return CrudUtil.execute("delete from branch where Branch_Id=?", Branch_Id);
        }


        public ArrayList<String> getAllCustomerIds() throws SQLException, ClassNotFoundException {
            ResultSet rst = CrudUtil.execute("select Branch_Id from branch");

            ArrayList<String> branchIds = new ArrayList<>();

            while (rst.next()) {
                branchIds.add(rst.getString(1));
            }

            return branchIds;
        }


}




