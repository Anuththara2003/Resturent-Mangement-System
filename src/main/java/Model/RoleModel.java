package Model;

import com.assignment.resturentmanagementsystem.Crud.CrudUtil;
import com.assignment.resturentmanagementsystem.Dto.CategoryDTO;
import com.assignment.resturentmanagementsystem.Dto.RoleDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RoleModel {

    public ArrayList<RoleDTO> getAllRoles() throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("select * from role");

        ArrayList<RoleDTO> roleDTOS = new ArrayList<>();

        while (rst.next()) {
            RoleDTO roleDTO = new RoleDTO(
                    rst.getInt(1),
                    rst.getString(2),
                    rst.getInt(3)




            );
            roleDTOS.add(roleDTO);
        }
        return roleDTOS;
    }





    public String getNextCategory() throws SQLException, ClassNotFoundException {
        ResultSet rst =  CrudUtil.execute("select Role_Id from role order by Role_Id desc limit 1");

        if (rst.next()){
            int lastId = rst.getInt(1);
            int substring = lastId;
            int i = Integer.parseInt(String.valueOf(substring));
            int newIdIndex = i+1;

            return String.format("C%03d",newIdIndex);
        }
        return  "1";

    }

    public boolean onclickedSave(RoleDTO roleDTO) throws SQLException, ClassNotFoundException {

        boolean isSaved =  CrudUtil.execute(
                "insert into role values (?,?,?)",

                roleDTO.getRoleId(),
                roleDTO.getRoleName(),
                roleDTO.getEmployeeId()

        );

        return isSaved;
    }
//
//    public static boolean updateOnAction(RoleDTO roleDTO) throws SQLException, ClassNotFoundException {
//        return CrudUtil.execute(
//                "update role set  Name=?, Emp_Id=?  where Role_Id=?",
//
//
//                roleDTO.getRoleName(),
//                roleDTO.getEmployeeId(),
//                roleDTO.getRoleId()
//        );
//    }

    public static boolean updateOnAction(RoleDTO roleDTO) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute(
                "UPDATE role SET  Name=?, Emp_Id=? WHERE Role_Id=?",

                roleDTO.getRoleName(),
                roleDTO.getEmployeeId(),
                roleDTO.getRoleId()
        );
    }


    public static boolean onDelete(int Role_Id ) throws SQLException, ClassNotFoundException {

        return CrudUtil.execute("delete from role where Role_Id=?", Role_Id);
    }


    public ArrayList<String> getAllRoleids() throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("select Role_Id from role");

        ArrayList<String> roleids = new ArrayList<>();

        while (rst.next()) {
            roleids.add(rst.getString(1));
        }

        return roleids;
    }
}
