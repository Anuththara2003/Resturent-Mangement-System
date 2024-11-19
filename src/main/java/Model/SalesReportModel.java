package Model;

import com.assignment.resturentmanagementsystem.Crud.CrudUtil;
import com.assignment.resturentmanagementsystem.Dto.ResevationDTO;
import com.assignment.resturentmanagementsystem.Dto.SalesReportDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SalesReportModel {
    public ArrayList<SalesReportDTO> getAllSalesReport() throws SQLException, ClassNotFoundException {
    ResultSet rst = CrudUtil.execute("select * from salesreport");

    ArrayList<SalesReportDTO> salesReportDTOS = new ArrayList<>();

    while (rst.next()) {
        SalesReportDTO salesReportDTO = new SalesReportDTO(
                rst.getInt(1),
                rst.getInt(2)



        );
        salesReportDTOS.add(salesReportDTO);
    }
    return salesReportDTOS;
}





    public String getNextSale() throws SQLException, ClassNotFoundException {
        ResultSet rst =  CrudUtil.execute("select Sale_Id from salesreport order by Sale_Id desc limit 1");

        if (rst.next()){
            int lastId = rst.getInt(1);
            int substring = lastId;
            int i = Integer.parseInt(String.valueOf(substring));
            int newIdIndex = i+1;

            return String.format("C%03d",newIdIndex);
        }
        return  "1";

    }

    public boolean onclickedSave(SalesReportDTO salesReportDTO) throws SQLException, ClassNotFoundException {

        boolean isSaved =  CrudUtil.execute(
                "insert into salesreport values (?,?)",

                salesReportDTO.getSaleId(),
                salesReportDTO.getBranchId()

        );

        return isSaved;
    }

    public static boolean updateOnAction(SalesReportDTO salesReportDTO) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute(
                "update salesreport set Branch_Id=?  where Sale_Id=?",


                salesReportDTO.getBranchId(),
                salesReportDTO.getSaleId()


        );
    }

    public static boolean onDelete(int Sale_Id ) throws SQLException, ClassNotFoundException {

        return CrudUtil.execute("delete from salesreport where Sale_Id=?", Sale_Id);
    }


    public ArrayList<String> getAllSalesids() throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("select Sale_Id from salesreport");

        ArrayList<String> Saleids = new ArrayList<>();

        while (rst.next()) {
            Saleids.add(rst.getString(1));
        }

        return Saleids;
    }


}
