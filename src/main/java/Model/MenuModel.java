package Model;

import com.assignment.resturentmanagementsystem.Crud.CrudUtil;
import com.assignment.resturentmanagementsystem.Dto.BranchDto;
import com.assignment.resturentmanagementsystem.Dto.MenuDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MenuModel {


    public ArrayList<MenuDTO> getAllMenu() throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("select * from menuitem");

        ArrayList<MenuDTO> menuDTOS = new ArrayList<>();

        while (rst.next()) {
            MenuDTO menuDTO = new MenuDTO(
                    rst.getInt(1),
                    rst.getString(2),
                    rst.getInt(3),
                    rst.getDouble(4),
                    rst.getInt(5)


            );
            menuDTOS.add(menuDTO);
        }
        return menuDTOS;
    }


    public ArrayList<String> getAllItemIds() throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("select Item_Id from menuitem");

        ArrayList<String> itemIds = new ArrayList<>();

        while (rst.next()){
            itemIds.add(rst.getString(1));
        }

        return itemIds;
    }




    public String getNextMenu() throws SQLException, ClassNotFoundException {
        ResultSet rst =  CrudUtil.execute("select Item_Id from menuitem order by Item_Id desc limit 1");

        if (rst.next()){
            int lastId = rst.getInt(1);
            int substring = lastId;
            int i = Integer.parseInt(String.valueOf(substring));
            int newIdIndex = i+1;

            return String.format("C%03d",newIdIndex);
        }
        return  "1";

    }

    public boolean onclickedSave(MenuDTO menuDTO) throws SQLException, ClassNotFoundException {

        boolean isSaved =  CrudUtil.execute(
                "insert into menuitem values (?,?,?,?,?)",

                menuDTO .getMenuId(),
                menuDTO.getMenuName(),
                menuDTO.getCratagoryId(),
                menuDTO.getPrice(),
                menuDTO.getQty()

        );

        return isSaved;
    }

    public static boolean updateOnAction(MenuDTO menuDTO) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute(
                "update menuitem set Name=?, Cat_Id=?, unitprice=?,  qty=? where Item_Id=?",


                menuDTO.getMenuName(),
                menuDTO.getCratagoryId(),
                menuDTO.getPrice(),
                menuDTO.getQty(),
                menuDTO.getMenuId()

        );
    }

    public static boolean onDelete(int Item_Id ) throws SQLException, ClassNotFoundException {

        return CrudUtil.execute("delete from menuitem where Item_Id=?", Item_Id);
    }


    public ArrayList<String> getAllCustomerIds() throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("select Item_Id from menuitem");

        ArrayList<String> menuids = new ArrayList<>();

        while (rst.next()) {
            menuids.add(rst.getString(1));
        }

        return menuids;
    }



    public MenuDTO findById(String selectedItemId) throws SQLException, ClassNotFoundException {
        // Execute SQL query to find the item by ID
        ResultSet rst = CrudUtil.execute("select * from menuitem where Item_Id=?", selectedItemId);

        // If the item is found, create an ItemDTO object with the retrieved data
        if (rst.next()) {
            return new MenuDTO(
                    rst.getInt(1),
                    rst.getString(2),
                    rst.getInt(3),
                    rst.getDouble(4),
                    rst.getInt(5)
            );
        }


        return null;
    }
}
