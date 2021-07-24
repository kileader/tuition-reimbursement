package com.kevin_leader.app;

import com.kevin_leader.controllers.AttachmentController;
import com.kevin_leader.controllers.DepartmentController;
import com.kevin_leader.controllers.EmployeeController;
import com.kevin_leader.controllers.EventController;
import com.kevin_leader.controllers.EventTypeController;
import com.kevin_leader.controllers.GradingFormatController;
import com.kevin_leader.controllers.MessageController;
import com.kevin_leader.controllers.ReimbursementController;
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
import com.kevin_leader.services.AttachmentService;
import com.kevin_leader.services.AttachmentServiceImpl;
import com.kevin_leader.services.DepartmentService;
import com.kevin_leader.services.DepartmentServiceImpl;
import com.kevin_leader.services.EmployeeService;
import com.kevin_leader.services.EmployeeServiceImpl;
import com.kevin_leader.services.EventService;
import com.kevin_leader.services.EventServiceImpl;
import com.kevin_leader.services.EventTypeService;
import com.kevin_leader.services.EventTypeServiceImpl;
import com.kevin_leader.services.GradingFormatService;
import com.kevin_leader.services.GradingFormatServiceImpl;
import com.kevin_leader.services.MessageService;
import com.kevin_leader.services.MessageServiceImpl;
import com.kevin_leader.services.ReimbursementService;
import com.kevin_leader.services.ReimbursementServiceImpl;

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
		AttachmentService aServ = new AttachmentServiceImpl(aDao);
		AttachmentController aCon = new AttachmentController(aServ);
		
		GenericRepo<Department> dDao = new GenericRepoImpl<>(Department.class);
		DepartmentService dServ = new DepartmentServiceImpl(dDao);
		DepartmentController dCon = new DepartmentController(dServ);

		GenericRepo<Employee> empDao = new GenericRepoImpl<>(Employee.class);
		EmployeeService empServ = new EmployeeServiceImpl(empDao);
		EmployeeController empCon = new EmployeeController(empServ);
		
		GenericRepo<Event> evDao = new GenericRepoImpl<>(Event.class);
		EventService evServ = new EventServiceImpl(evDao);
		EventController evCon = new EventController(evServ);
		
		GenericRepo<EventType> etDao = new GenericRepoImpl<>(EventType.class);
		EventTypeService etServ = new EventTypeServiceImpl(etDao);
		EventTypeController etCon = new EventTypeController(etServ);
		
		GenericRepo<GradingFormat> gfDao = new GenericRepoImpl<>(GradingFormat.class);
		GradingFormatService gfServ = new GradingFormatServiceImpl(gfDao);
		GradingFormatController gfCon = new GradingFormatController(gfServ);
		
		GenericRepo<Message> mDao = new GenericRepoImpl<>(Message.class);
		MessageService mServ = new MessageServiceImpl(mDao);
		MessageController mCon = new MessageController(mServ);
		
		GenericRepo<Reimbursement> rDao = new GenericRepoImpl<>(Reimbursement.class);
		ReimbursementService rServ = new ReimbursementServiceImpl(rDao);
		ReimbursementController rCon = new ReimbursementController(rServ);
		
		aServ.getById(0);
		
		app.get("/", (ctx) -> ctx.result("This is Our Movie App Home Page!"));
		app.get("/hello", (ctx) -> ctx.result("Hello World!"));
		
		app.get("/events", evCon.getAllEvents);
		
		app.get("/grading_formats", gfCon.getAllGradingFormats);
		
		app.get("/event_types", etCon.getAllEventTypes);
	}
	
}
