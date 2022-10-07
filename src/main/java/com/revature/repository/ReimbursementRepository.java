
package com.revature.repository;

        import com.revature.model.Reimbursement;
        import java.sql.*;
        import java.util.ArrayList;
        import java.util.List;

public class ReimbursementRepository {
     // Manager can see all reimbursement
    public List<Reimbursement> getAllReimbursements() throws SQLException {
        try (Connection connectionObject = ConnectionFactory.createConnection()) {

            List<Reimbursement> reimbursements = new ArrayList<>();

            String sql = "SELECT * FROM reimbursements";

            Statement stmt = connectionObject.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            // ResultSet represents the temporary table of query results

            // Iterating through the ResultSet
            while (rs.next()) {
                int id = rs.getInt("id");
                String amount = rs.getString("amount"); //Should be int, but java said String
                String last_name = rs.getString("last_name");
                String description = rs.getString("description");
                String status = rs.getString("status");

                Reimbursement reimbursement = new Reimbursement(id, amount, last_name, description, status);

                reimbursements.add(reimbursement); // add assignment to List
            }

            return reimbursements;
        }
    }

    public List<Reimbursement> getAllAssignmentsForStudent(int studentId) throws SQLException {
        try (Connection connectionObject = ConnectionFactory.createConnection()) {

            List<Reimbursement> assignments = new ArrayList<>();

            String sql = "SELECT * FROM assignments WHERE student_id = ?";

            PreparedStatement pstmt = connectionObject.prepareStatement(sql);

            pstmt.setInt(1, studentId);

            ResultSet rs = pstmt.executeQuery();
            // ResultSet represents the temporary table of query results

            // Iterating through the ResultSet
            while (rs.next()) {
                int id = rs.getInt("id");
                String amount = rs.getString("amount");
                String last_name = rs.getString("last_name");
                String sId = rs.getString("student_id");
                String status = rs.getString("status");

                Reimbursement assignment = new Reimbursement(id, amount, last_name, sId, status);

               assignments.add(assignment); // add assignment to List
           }

           return assignments;
       }
   }

    public boolean gradeAssignment(int assignmentId, String last_name, int status) throws SQLException {
        try (Connection connectionObj = ConnectionFactory.createConnection()) {
            String sql = "UPDATE assignments SET grade = ?, grader_id = ? WHERE id = ?";

            PreparedStatement pstmt = connectionObj.prepareStatement(sql);
            pstmt.setString(1, last_name);
            pstmt.setInt(2, status);
            pstmt.setInt(3, assignmentId);

            int numberOfRecordsUpdated = pstmt.executeUpdate();

            return numberOfRecordsUpdated == 1;
        }
    }

    // Returns either
    // 1. An assignment object
    // 2. null
    public Reimbursement getAssignmentById(int id) throws SQLException {
        try (Connection connectionObj = ConnectionFactory.createConnection()) {
            String sql = "SELECT * FROM assignments WHERE id = ?";

            PreparedStatement pstmt = connectionObj.prepareStatement(sql);

            pstmt.setInt(1, id);

            ResultSet rs = pstmt.executeQuery(); // 0 rows or 1 row

            if (rs.next()) {
                int reimbursementId = rs.getInt("id");
                String amount = rs.getString("amount");
                String last_name = rs.getString("grade");
                String  description = rs.getString("description");
                String status = rs.getString("status");

                return new Reimbursement(reimbursementId, amount, last_name, description, status);
            } else {
                return null;
           }
        }
    }
}

