package Model;

import com.assignment.resturentmanagementsystem.Crud.CrudUtil;
import com.assignment.resturentmanagementsystem.Dto.CustomerDto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerModel {
    public ArrayList<CustomerDto> getAllCustomers() throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("select * from customer");

        ArrayList<CustomerDto> customerDTOS = new ArrayList<>();

        while (rst.next()) {
            CustomerDto customerDTO = new CustomerDto(
                    rst.getInt(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getInt(4)


            );
            customerDTOS.add(customerDTO);
        }
        return customerDTOS;
    }

    public String getNextCustomerId() throws SQLException, ClassNotFoundException {

        ResultSet rst = CrudUtil.execute("select Cust_Id from customer order by Cust_Id desc limit 1");

        if (rst.next()) {
            int lastId = rst.getInt(1);
            int substring = lastId;
            int i = Integer.parseInt(String.valueOf(substring));
            int newIdIndex = i + 1;

            return String.format("C%03d", newIdIndex);
        }
        return "1";

    }

    public boolean saveCustomer(CustomerDto customerDTO) throws SQLException, ClassNotFoundException {

        boolean isSaved = CrudUtil.execute(
                "insert into customer values (?,?,?,?)",
                customerDTO.getCustomerId(),
                customerDTO.getCustomerName(),
                customerDTO.getCustomerAddress(),
                customerDTO.getCustomerAge()

        );

        return isSaved;
    }

    public static boolean updateCustomer(CustomerDto customerDTO) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute(
                "update customer set Name=?, Address=?, Age=? where Cust_Id=?",

                customerDTO.getCustomerName(),
                customerDTO.getCustomerAddress(),
                customerDTO.getCustomerAge(),
                customerDTO.getCustomerId()

        );
    }

    public static boolean deleteCustomer(String customerId) throws SQLException, ClassNotFoundException {

        return CrudUtil.execute("delete from customer where Cust_Id=?", customerId);
    }


    public ArrayList<String> getAllCustomerIds() throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("select Cust_Id from customer");

        ArrayList<String> customerIds = new ArrayList<>();

        while (rst.next()) {
            customerIds.add(rst.getString(1));
        }

        return customerIds;
    }


    public CustomerDto findById(String selectedCusId) throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("select * from customer where Cust_Id=?", selectedCusId);

        if (rst.next()) {
            return new CustomerDto(
                    rst.getInt(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getInt(4)
            );
        }
        return null;
    }

}


