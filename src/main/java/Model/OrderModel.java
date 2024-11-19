package Model;



import com.assignment.resturentmanagementsystem.Crud.CrudUtil;
import com.assignment.resturentmanagementsystem.Dto.BranchDto;
import com.assignment.resturentmanagementsystem.Dto.OrderDTo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderModel {
    public ArrayList<OrderDTo> getAllOrders() throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("select * from orders");

        ArrayList<OrderDTo> orderDTos = new ArrayList<>();

        while (rst.next()) {
            OrderDTo orderDTo = new OrderDTo(
                    rst.getInt(1),
                    rst.getString(2),
                    rst.getInt(4),
                    rst.getInt(3),
                    rst.getInt(7),
                    rst.getDouble(5),
                    rst.getInt(6)


            );
            orderDTos.add(orderDTo);
        }
        return orderDTos;
    }





    public String getNextBranchId() throws SQLException, ClassNotFoundException {
        ResultSet rst =  CrudUtil.execute("select Order_Id from orders order by Order_Id desc limit 1");

        if (rst.next()){
            int lastId = rst.getInt(1);
            int substring = lastId;
            int i = Integer.parseInt(String.valueOf(substring));
            int newIdIndex = i+1;

            return String.format("C%03d",newIdIndex);
        }
        return  "1";

    }

    public boolean onclickedSave(OrderDTo orderDTo) throws SQLException, ClassNotFoundException {

        boolean isSaved =  CrudUtil.execute(
                "insert into orders values (?,?,?,?,?,?,?)",

                orderDTo.getOrderId(),
                orderDTo.getType(),
                orderDTo.getFeedbackID(),
                orderDTo.getItemId(),
                orderDTo.getQuantity(),
                orderDTo.getPrice(),
                orderDTo.getCustomerID()

        );

        return isSaved;
    }

    public static boolean updateOnAction(OrderDTo orderDTo) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute(
                "update orders set Cust_Id=?, Type=?, Fee_Id=?, price=?, qty=?, Item_Id=? where Order_Id=?",

                orderDTo.getCustomerID(),
                orderDTo.getType(),
                orderDTo.getFeedbackID(),
                orderDTo.getPrice(),
                orderDTo.getQuantity(),
                orderDTo.getItemId(),
                orderDTo.getOrderId()

        );
    }

    public static boolean onDelete(int Order_Id ) throws SQLException, ClassNotFoundException {

        return CrudUtil.execute("delete from orders where Order_Id=?", Order_Id);
    }


    public ArrayList<String> getAllCustomerIds() throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("select Order_Id from orders");

        ArrayList<String> orderids = new ArrayList<>();

        while (rst.next()) {
            orderids.add(rst.getString(1));
        }

        return orderids;
    }


}




