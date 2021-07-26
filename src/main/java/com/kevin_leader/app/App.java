package com.kevin_leader.app;

import com.kevin_leader.controllers.RequestFormController;
import com.kevin_leader.models.Attachment;
import com.kevin_leader.models.Department;
import com.kevin_leader.models.Employee;
import com.kevin_leader.models.Event;
import com.kevin_leader.models.EventType;
import com.kevin_leader.models.GradingFormat;
import com.kevin_leader.models.Message;
import com.kevin_leader.models.Reimbursement;
import com.kevin_leader.repositories.GenericRepo;
import com.kevin_leader.repositories.GenericRepoImpl;
import com.kevin_leader.services.RequestFormService;
import com.kevin_leader.services.RequestFormServiceImpl;

import io.javalin.Javalin;

public class App {
	
	public static void main(String[] args) {
		
		Javalin app = Javalin.create(
				config -> config.enableCorsForAllOrigins());
		
		establishRoutes(app);
		
		app.start(7000);
		
	}
	
	private static void establishRoutes(Javalin app) {
		
		GenericRepo<Attachment> aDao = new GenericRepoImpl<>(Attachment.class);
		GenericRepo<Department> dDao = new GenericRepoImpl<>(Department.class);
		GenericRepo<Employee> empDao = new GenericRepoImpl<>(Employee.class);
		GenericRepo<Event> evDao = new GenericRepoImpl<>(Event.class);
		GenericRepo<EventType> etDao = new GenericRepoImpl<>(EventType.class);
		GenericRepo<GradingFormat> gfDao = new GenericRepoImpl<>(GradingFormat.class);
		GenericRepo<Message> mDao = new GenericRepoImpl<>(Message.class);
		GenericRepo<Reimbursement> rDao = new GenericRepoImpl<>(Reimbursement.class);
		
		RequestFormService rfServ = new RequestFormServiceImpl(
				empDao, evDao, etDao, gfDao, rDao);
		
		etDao.getById(0);
		
		RequestFormController rfCon = new RequestFormController(rfServ);
		
		app.get("/hello", (ctx) -> ctx.result("Hello World!"));
		
		app.get("/future_events", rfCon.getFutureEvents);
		app.get("/grading_formats", rfCon.getAllGradingFormats);
		app.get("/event_types", rfCon.getAllEventTypes);
		
		app.post("/reimbursements", rfCon.processReimbursementRequest);
	}
	
}
