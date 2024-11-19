package Model;

import com.assignment.resturentmanagementsystem.Crud.CrudUtil;
import com.assignment.resturentmanagementsystem.Dto.BranchDto;
import com.assignment.resturentmanagementsystem.Dto.ResevationDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ReservationModel {


    public ArrayList<ResevationDTO> getAllReservation() throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("select * from reservation");

        ArrayList<ResevationDTO> resevationDTOS = new ArrayList<>();

        while (rst.next()) {
            ResevationDTO resevationDTO = new ResevationDTO(
                    rst.getInt(1),
                    rst.getInt(2),
                    rst.getString(3)



            );
            resevationDTOS.add(resevationDTO);
        }
        return resevationDTOS;
    }





    public String getNextreservation() throws SQLException, ClassNotFoundException {
        ResultSet rst =  CrudUtil.execute("select Res_Id from reservation order by Res_Id desc limit 1");

        if (rst.next()){
            int lastId = rst.getInt(1);
            int substring = lastId;
            int i = Integer.parseInt(String.valueOf(substring));
            int newIdIndex = i+1;

            return String.format("C%03d",newIdIndex);
        }
        return  "1";

    }

    public boolean onclickedSave(ResevationDTO resevationDTO) throws SQLException, ClassNotFoundException {

        boolean isSaved =  CrudUtil.execute(
                "insert into reservation values (?,?,?)",

                resevationDTO.getReservationId(),
                resevationDTO.getCustomerId(),
                resevationDTO.getDescription()
        );

        return isSaved;
    }

    public static boolean updateOnAction(ResevationDTO resevationDTO) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute(
                "update reservation set Cust_Id=?, Description=?  where Res_Id=?",


                resevationDTO.getCustomerId(),
                resevationDTO.getDescription(),
                resevationDTO.getReservationId()

        );
    }

    public static boolean onDelete(int Res_Id ) throws SQLException, ClassNotFoundException {

        return CrudUtil.execute("delete from reservation where Res_Id=?", Res_Id);
    }


    public ArrayList<String> getAllreservationids() throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("select Res_Id from reservation");

        ArrayList<String> reservationids = new ArrayList<>();

        while (rst.next()) {
            reservationids.add(rst.getString(1));
        }

        return reservationids;
    }

}
