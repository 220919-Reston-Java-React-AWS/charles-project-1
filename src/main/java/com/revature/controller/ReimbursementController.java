
package com.revature.controller;

        import com.revature.exception.ReimbursementReviewedException;
        import com.revature.exception.ReimbursementNotFoundException;
        import com.revature.model.Reimbursement;
        import com.revature.model.User;
        import com.revature.service.ReimbursementService;
        import io.javalin.Javalin;

        import javax.servlet.http.HttpSession;
        import java.util.List;

public class ReimbursementController {

    private ReimbursementService reimbursementService = new ReimbursementService();

    public void mapEndpoints(Javalin app) {

        // GET /assignments works is different depending on whether we're logged in as a trainer or a student
        // If we are logged in as a trainer, then it will retrieve all assignments from the system
        // If we are logged in as a student, then it will retrieve assignments belonging to us
        app.get("/assignments", (ctx) -> {
            // We must be logged in as a trainer
            HttpSession httpSession = ctx.req.getSession();

            // retrieve the user object from user attribute
            User user = (User) httpSession.getAttribute("user");

            if (user != null) { // Check if logged in
                if (user.getRoleId() == 2) { // if they are a trainer
                    List<Reimbursement> assignments = reimbursementService.getAllAssignments();

                    ctx.json(assignments);
                } else if (user.getRoleId() == 1) { // if they are a student
                    int studentId = user.getId();

                    List<Reimbursement> assignments = reimbursementService.getAllAssignmentsForStudent(studentId);

                    ctx.json(assignments);
                } else {
                    ctx.result("You are logged in, but you're not a trainer or student");
                    ctx.status(401);
                }
            } else {
                ctx.result("You are not logged in!");
                ctx.status(401);
            }
        });

        app.patch("/reimbursements", (ctx) -> {

            HttpSession httpSession = ctx.req.getSession();
            User user = (User) httpSession.getAttribute("user");

            if (user != null) { // Check if logged in
                // Check if user is a trainer


                    Reimbursement ticket = ctx.bodyAsClass(Reimbursement.class);
                    reimbursementService.makereimbursement(ticket.getAmount(), ticket.getLast_name(), ticket.getDescription());

                    ctx.result("Ticket successfully graded!");


            } else {
                ctx.result("You are not logged in!");
                ctx.status(401);
            }

        });

    }

}
