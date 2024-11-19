package Model;

import com.assignment.resturentmanagementsystem.Crud.CrudUtil;
import com.assignment.resturentmanagementsystem.Dto.CategoryDTO;
import com.assignment.resturentmanagementsystem.Dto.InventoryDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CategoryModel {

    public ArrayList<CategoryDTO> getAllCategory() throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("select * from category");

        ArrayList<CategoryDTO> categoryDTOS = new ArrayList<>();

        while (rst.next()) {
            CategoryDTO categoryDTO = new CategoryDTO(
                    rst.getInt(1),
                    rst.getString(2)




            );
            categoryDTOS.add(categoryDTO);
        }
        return categoryDTOS;
    }





    public String getNextCategory() throws SQLException, ClassNotFoundException {
        ResultSet rst =  CrudUtil.execute("select Cat_Id from category order by Cat_Id desc limit 1");

        if (rst.next()){
            int lastId = rst.getInt(1);
            int substring = lastId;
            int i = Integer.parseInt(String.valueOf(substring));
            int newIdIndex = i+1;

            return String.format("C%03d",newIdIndex);
        }
        return  "1";

    }

    public boolean onclickedSave(CategoryDTO categoryDTO) throws SQLException, ClassNotFoundException {

        boolean isSaved =  CrudUtil.execute(
                "insert into category values (?,?)",

                categoryDTO.getCatID(),
                categoryDTO.getCatName()
        );

        return isSaved;
    }

    public static boolean updateOnAction(CategoryDTO categoryDTO) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute(
                "update category set  Name=?  where Cat_Id=?",


                categoryDTO.getCatName(),
                categoryDTO.getCatID()

        );
    }

    public static boolean onDelete(int Cat_Id ) throws SQLException, ClassNotFoundException {

        return CrudUtil.execute("delete from category where Cat_Id=?", Cat_Id);
    }


    public ArrayList<String> getAllCategoryids() throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("select Cat_Id from category");

        ArrayList<String> categoryids = new ArrayList<>();

        while (rst.next()) {
            categoryids.add(rst.getString(1));
        }

        return categoryids;
    }
}
