package Model;

import com.assignment.resturentmanagementsystem.Crud.CrudUtil;
import com.assignment.resturentmanagementsystem.Dto.BranchDto;
import com.assignment.resturentmanagementsystem.Dto.SupplierDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SupplierModel {

    public ArrayList<SupplierDTO> getAllSpplier() throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("select * from supplier");

        ArrayList<SupplierDTO> supplierDTOS = new ArrayList<>();

        while (rst.next()) {
            SupplierDTO supplierDTO = new SupplierDTO(
                    rst.getInt(1),
                    rst.getString(2)


            );
            supplierDTOS.add(supplierDTO);
        }
        return supplierDTOS;
    }





    public String getNextSupplierID() throws SQLException, ClassNotFoundException {
        ResultSet rst =  CrudUtil.execute("select Sup_Id from supplier order by Sup_Id desc limit 1");

        if (rst.next()){
            int lastId = rst.getInt(1);
            int substring = lastId;
            int i = Integer.parseInt(String.valueOf(substring));
            int newIdIndex = i+1;

            return String.format("C%03d",newIdIndex);
        }
        return  "1";

    }

    public boolean onclickedSave(SupplierDTO supplierDTO) throws SQLException, ClassNotFoundException {

        boolean isSaved =  CrudUtil.execute(
                "insert into supplier values (?,?)",

                supplierDTO.getSupplierId(),
                supplierDTO.getSupplierName()

        );

        return isSaved;
    }

    public static boolean updateOnAction(SupplierDTO supplierDTO) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute(
                "update supplier set Name=? where Sup_Id=?",

                supplierDTO.getSupplierName(),
                supplierDTO.getSupplierId()


        );
    }

    public static boolean onDelete(int Sup_Id ) throws SQLException, ClassNotFoundException {

        return CrudUtil.execute("delete from supplier where Sup_Id=?", Sup_Id);
    }


    public ArrayList<String> getAllSupplierids() throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("select Sup_Id from supplier");

        ArrayList<String> supplierids = new ArrayList<>();

        while (rst.next()) {
            supplierids.add(rst.getString(1));
        }

        return supplierids;
    }
}
