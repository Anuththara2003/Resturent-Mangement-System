package Model;

import com.assignment.resturentmanagementsystem.Crud.CrudUtil;
import com.assignment.resturentmanagementsystem.Dto.BranchDto;
import com.assignment.resturentmanagementsystem.Dto.EmployeeDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeModel {
    public ArrayList<EmployeeDTO> getAllEmployee() throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("select * from employee");

        ArrayList<EmployeeDTO> employeeDTOS = new ArrayList<>();

        while (rst.next()) {
            EmployeeDTO employeeDTO = new EmployeeDTO(
                    rst.getInt(1),
                    rst.getString(2),
                    rst.getString(3)


            );
            employeeDTOS.add(employeeDTO);
        }
        return employeeDTOS;
    }





    public String getNextEmployeeID() throws SQLException, ClassNotFoundException {
        ResultSet rst =  CrudUtil.execute("select Emp_Id from employee order by Emp_Id desc limit 1");

        if (rst.next()){
            int lastId = rst.getInt(1);
            int substring = lastId;
            int i = Integer.parseInt(String.valueOf(substring));
            int newIdIndex = i+1;

            return String.format("C%03d",newIdIndex);
        }
        return  "1";

    }

    public boolean onclickedSave(EmployeeDTO employeeDTO) throws SQLException, ClassNotFoundException {

        boolean isSaved =  CrudUtil.execute(
                "insert into employee values (?,?,?)",

                employeeDTO.getEmployeeId(),
                employeeDTO.getEmpName(),
                employeeDTO.getEmployeeAddress()


        );

        return isSaved;
    }

    public static boolean updateOnAction(EmployeeDTO employeeDTO) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute(
                "update employee set  Name=?, Address=? where Emp_Id=?",


                employeeDTO.getEmpName(),
                employeeDTO.getEmployeeAddress(),
                employeeDTO.getEmployeeId()

        );
    }

    public static boolean onDelete(int Emp_Id ) throws SQLException, ClassNotFoundException {

        return CrudUtil.execute("delete from employee where Emp_Id=?", Emp_Id);
    }


    public ArrayList<String> getAllEmployeeIDS() throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("select Emp_Id from employee");

        ArrayList<String> employeeids = new ArrayList<>();

        while (rst.next()) {
            employeeids.add(rst.getString(1));
        }

        return employeeids;
    }
}
