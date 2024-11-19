package Model;

import com.assignment.resturentmanagementsystem.Crud.CrudUtil;
import com.assignment.resturentmanagementsystem.Dto.BranchDto;
import com.assignment.resturentmanagementsystem.Dto.FeedbackDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class FeedbackModel {

    public ArrayList<FeedbackDTO> getAllFeedback() throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("select * from feedback");

        ArrayList<FeedbackDTO> feedbackDTOS = new ArrayList<>();

        while (rst.next()) {
            FeedbackDTO feedbackDTO = new FeedbackDTO(
                    rst.getInt(1),
                    rst.getString(2),
                    rst.getInt(3)


            );
            feedbackDTOS.add(feedbackDTO);
        }
        return feedbackDTOS;
    }





    public String getNextFeedbackId() throws SQLException, ClassNotFoundException {
        ResultSet rst =  CrudUtil.execute("select Fee_Id from feedback order by Fee_Id desc limit 1");

        if (rst.next()){
            int lastId = rst.getInt(1);
            int substring = lastId;
            int i = Integer.parseInt(String.valueOf(substring));
            int newIdIndex = i+1;

            return String.format("C%03d",newIdIndex);
        }
        return  "1";

    }

    public boolean onclickedSave(FeedbackDTO feedbackDTO) throws SQLException, ClassNotFoundException {

        boolean isSaved =  CrudUtil.execute(
                "insert into feedback values (?,?,?)",

                feedbackDTO.getFeedbackID(),
                feedbackDTO.getDescription(),
                feedbackDTO.getFeedbackID()

        );

        return isSaved;
    }

    public static boolean updateOnAction(FeedbackDTO feedbackDTO) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute(
                "update feedback set Fee_Id=?, Description=? where Fee_Id=?",

                feedbackDTO.getFeedbackID(),
                feedbackDTO.getDescription(),
                feedbackDTO.getFeedbackID()

        );
    }

    public static boolean onDelete(int Fee_Id ) throws SQLException, ClassNotFoundException {

        return CrudUtil.execute("delete from feedback where Fee_Id=?", Fee_Id);
    }


    public ArrayList<String> getAllFeedbackIds() throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("select Branch_Id from branch");

        ArrayList<String> feedbackids = new ArrayList<>();

        while (rst.next()) {
            feedbackids.add(rst.getString(1));
        }

        return feedbackids;
    }



}
