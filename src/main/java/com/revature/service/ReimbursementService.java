package com.revature.service;

        import com.revature.exception.ReimbursementReviewedException;
        import com.revature.exception.ReimbursementNotFoundException;
        import com.revature.model.Reimbursement;
        import com.revature.repository.ReimbursementRepository;

        import java.sql.SQLException;
        import java.util.List;

public class ReimbursementService {

    private ReimbursementRepository assignmentRepository = new ReimbursementRepository();

    public List<Reimbursement> getAllAssignments() throws SQLException {
        return assignmentRepository.getAllReimbursements();
    }

    public List<Reimbursement> getAllAssignmentsForStudent(int studentId) throws SQLException {
        return assignmentRepository.getAllAssignmentsForStudent(studentId);
    }

    public boolean gradeAssignment(int assignmentId, String last_name, int graderId) throws SQLException, ReimbursementNotFoundException, ReimbursementReviewedException {
        // Check if grade is negative
        if (graderId < 0) {
            throw new IllegalArgumentException("Grades assigned must be 0 or higher"); // Built-in unchecked exception
        }

        // Check if assignment does not exist
        Reimbursement assignment = assignmentRepository.getAssignmentById(assignmentId);
        if (assignment == null) {
            throw new ReimbursementNotFoundException("Assignment with id " + assignmentId + " was not found");
        }

        // Already graded
        if (graderId != 0) {

            throw new ReimbursementReviewedException("Assignment with id " + assignmentId + " has already been graded");
        }

        // Grading assignment
        return assignmentRepository.gradeAssignment(assignmentId, last_name, graderId);
    }

    public String makereimbursement(String amount, String last_name, String description) {
//            throws ReimbursementNotFoundException, ReimbursementReviewedException {
         return "Hello";
    }






}
