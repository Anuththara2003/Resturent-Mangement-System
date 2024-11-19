package Model;

import com.assignment.resturentmanagementsystem.Crud.CrudUtil;
import com.assignment.resturentmanagementsystem.Dto.InventoryDTO;
import com.assignment.resturentmanagementsystem.Dto.ResevationDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class InventoryModel {

    public ArrayList<InventoryDTO> getAllInventory() throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("select * from inventory");

        ArrayList<InventoryDTO> inventoryDTOS = new ArrayList<>();

        while (rst.next()) {
            InventoryDTO inventoryDTO = new InventoryDTO(
                    rst.getInt(1),
                    rst.getString(2),
                    rst.getInt(3)




            );
            inventoryDTOS.add(inventoryDTO);
        }
        return inventoryDTOS;
    }





    public String getNextInventory() throws SQLException, ClassNotFoundException {
        ResultSet rst =  CrudUtil.execute("select Inven_Id from inventory order by Inven_Id desc limit 1");

        if (rst.next()){
            int lastId = rst.getInt(1);
            int substring = lastId;
            int i = Integer.parseInt(String.valueOf(substring));
            int newIdIndex = i+1;

            return String.format("C%03d",newIdIndex);
        }
        return  "1";

    }

    public boolean onclickedSave(InventoryDTO inventoryDTO) throws SQLException, ClassNotFoundException {

        boolean isSaved =  CrudUtil.execute(
                "insert into inventory values (?,?,?)",

                inventoryDTO.getInventoryId(),
                inventoryDTO.getDescription(),
                inventoryDTO.getBranchId()
        );

        return isSaved;
    }

    public static boolean updateOnAction(InventoryDTO inventoryDTO) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute(
                "update inventory set Description=?, Branch_Id=?  where Inven_Id=?",



                inventoryDTO.getDescription(),
                inventoryDTO.getBranchId(),
                inventoryDTO.getInventoryId()

        );
    }

    public static boolean onDelete(int Inven_Id ) throws SQLException, ClassNotFoundException {

        return CrudUtil.execute("delete from inventory where Inven_Id=?", Inven_Id);
    }


    public ArrayList<String> getAllInventoryids() throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("select Inven_Id from inventory");

        ArrayList<String> inventoryids = new ArrayList<>();

        while (rst.next()) {
            inventoryids.add(rst.getString(1));
        }

        return inventoryids;
    }
}
