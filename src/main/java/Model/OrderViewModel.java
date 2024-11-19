package Model;

import com.assignment.resturentmanagementsystem.Crud.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderViewModel {

    public String getNextOrderId() throws SQLException, ClassNotFoundException {
        ResultSet rst =  CrudUtil.execute("SELECT order_id FROM orders ORDER BY order_id DESC LIMIT 1");

        if (rst.next()){
            // Assuming `order_id` is stored as an integer in the database
            int lastOrderId = rst.getInt("order_id");  // Properly fetch the `order_id` column value
            String newIdIndex = String.valueOf(lastOrderId + 1);  // Increment and convert to string
            return newIdIndex;
        }
        return "1";  // If no orders exist, start with ID 1
    }
}
