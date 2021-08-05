package com.kevin_leader.app;

import com.kevin_leader.controllers.LogInController;
import com.kevin_leader.controllers.RequestFormController;
import com.kevin_leader.controllers.ReviewRequestController;
import com.kevin_leader.controllers.UpdateRequestController;
import com.kevin_leader.models.Attachment;
import com.kevin_leader.models.Employee;
import com.kevin_leader.models.Event;
import com.kevin_leader.models.EventType;
import com.kevin_leader.models.GradingFormat;
import com.kevin_leader.models.Message;
import com.kevin_leader.models.Reimbursement;
import com.kevin_leader.repositories.GenericRepo;
import com.kevin_leader.repositories.GenericRepoImpl;
import com.kevin_leader.services.LogInService;
import com.kevin_leader.services.LogInServiceImpl;
import com.kevin_leader.services.RequestFormService;
import com.kevin_leader.services.RequestFormServiceImpl;
import com.kevin_leader.services.ReviewRequestService;
import com.kevin_leader.services.ReviewRequestServiceImpl;
import com.kevin_leader.services.UpdateRequestService;
import com.kevin_leader.services.UpdateRequestServiceImpl;

import io.javalin.Javalin;

public class App {

    public static void main(String[] args) {

        Javalin app = Javalin
                .create(config -> config.enableCorsForAllOrigins());

        establishRoutes(app);

        app.start(7000);

    }

    private static void establishRoutes(Javalin app) {

        GenericRepo<Attachment> aDao = new GenericRepoImpl<>(Attachment.class);
        GenericRepo<Employee> empDao = new GenericRepoImpl<>(Employee.class);
        GenericRepo<Event> evDao = new GenericRepoImpl<>(Event.class);
        GenericRepo<EventType> etDao = new GenericRepoImpl<>(EventType.class);
        GenericRepo<GradingFormat> gfDao = new GenericRepoImpl<>(
                GradingFormat.class);
        GenericRepo<Message> mDao = new GenericRepoImpl<>(Message.class);
        GenericRepo<Reimbursement> rDao = new GenericRepoImpl<>(
                Reimbursement.class);

        RequestFormService rfServ = new RequestFormServiceImpl(empDao, evDao,
                etDao, gfDao, rDao);
        LogInService liServ = new LogInServiceImpl(empDao);
        UpdateRequestService urServ = new UpdateRequestServiceImpl(aDao, empDao,
                mDao, rDao);
        ReviewRequestService rrServ = new ReviewRequestServiceImpl(aDao, empDao,
                mDao, rDao);

        RequestFormController rfCon = new RequestFormController(rfServ);
        LogInController liCon = new LogInController(liServ);
        UpdateRequestController urCon = new UpdateRequestController(urServ);
        ReviewRequestController rrCon = new ReviewRequestController(rrServ);

        etDao.getById(0); // start up hibernate

        app.get("/hello", (ctx) -> ctx.result("Hello World!"));

        app.post("/login", liCon.processLogInAttempt);

        app.get("/future_events", rfCon.getFutureEvents);
        app.get("/grading_formats", rfCon.getAllGradingFormats);
        app.get("/event_types", rfCon.getAllEventTypes);
        app.post("/reimbursements", rfCon.processReimbursementRequest);

        app.get("/employees/:empId/reimbursements",
                urCon.getReimbursementsByEmployeeId);
        app.get("/employees/:empId/attachments",
                urCon.getAttachmentsByEmployeeId);
        app.get("/employees/:empId/messages", urCon.getMessagesForEmployee);
        app.post("/attachments", urCon.addAttachment);

        app.put("/reimbursements", urCon.updateReimbursement);

        app.get("/reviewers/:empId/reimbursements",
                rrCon.getReimbursementsForReviewer);
        app.get("/reimbursements/:rId/messages",
                rrCon.getMessagesForReimbursement);
        app.get("/reimbursements/:rId/attachments",
                rrCon.getAttachmentsForReimbursement);
        app.post("/messages", rrCon.incrementStepAndAddMessage);

    }

}
