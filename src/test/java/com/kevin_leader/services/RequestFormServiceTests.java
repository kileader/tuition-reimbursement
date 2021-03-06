package com.kevin_leader.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import com.kevin_leader.models.Employee;
import com.kevin_leader.models.Event;
import com.kevin_leader.models.EventType;
import com.kevin_leader.models.GradingFormat;
import com.kevin_leader.models.Reimbursement;
import com.kevin_leader.models.RequestForm;
import com.kevin_leader.repositories.GenericRepo;
import com.kevin_leader.repositories.GenericRepoImpl;

public class RequestFormServiceTests {

    private static RequestFormService rfServ;
    private static GenericRepo<Employee> empDao;
    private static GenericRepo<Event> evDao;
    private static GenericRepo<EventType> etDao;
    private static GenericRepo<GradingFormat> gfDao;
    private static GenericRepo<Reimbursement> rDao;

    @BeforeClass
    public static void setUpBeforeClass() {
        empDao = new GenericRepoImpl<>(Employee.class);
        evDao = new GenericRepoImpl<>(Event.class);
        etDao = new GenericRepoImpl<>(EventType.class);
        gfDao = new GenericRepoImpl<>(GradingFormat.class);
        rDao = new GenericRepoImpl<>(Reimbursement.class);
        rfServ = new RequestFormServiceImpl(empDao, evDao, etDao, gfDao, rDao);
    }

    @Test
    public void getAllEventsPass() {
        List<Event> events = rfServ.getAllEvents();
        assertTrue(4 < events.size());
    }

    @Test
    public void getFutureEventsSuccess() {
        List<Event> futureEvents = rfServ.getFutureEvents();
        assertTrue(futureEvents.size() > 0);
    }

    @Test
    public void getAllEventTypesPass() {
        List<EventType> eventTypes = rfServ.getAllEventTypes();
        assertTrue(5 < eventTypes.size());
    }

    @Test
    public void getAllGradingFormatsPass() {
        List<GradingFormat> gradingFormats = rfServ.getAllGradingFormats();
        assertTrue(2 < gradingFormats.size());
    }

    @Test
    public void processRequestFormChosenEventPass() {
        RequestForm reqForm = new RequestForm(14, // employeeId
                5, // eventId
                "I would like reimbursement for this.", // description
                7.5 // hoursMissed
        );
        Reimbursement processedReimb = rfServ.processRequestForm(reqForm);
        Reimbursement addedReimb = rfServ
                .getReimbursementById(processedReimb.getId());
        assertEquals(processedReimb.toString(), addedReimb.toString());
    }

    @Test
    public void processRequestFormChosenFormatPass() {
        RequestForm reqForm = new RequestForm(14, // employeeId
                null, // eventId
                "How to Run in Place", // eventName
                "2022-04-20", // startDate
                "08:00", // startTime
                "51 East Blv. Kevin Crater, Moon 51356", // location
                400.0, // tuition
                3, // typeId
                3, // formatId
                null, // formatName
                null, // formatDescription
                null, // passingGradeCutoff
                "2022-04-20", // endDate
                "04:30", // endTime
                "I would like reimbursement for this.", // description
                8.5); // hoursMissed

        rfServ.processRequestForm(reqForm);

        Reimbursement processedReimb = rfServ.processRequestForm(reqForm);
        Reimbursement addedReimb = rfServ
                .getReimbursementById(processedReimb.getId());
        assertEquals(processedReimb.toString(), addedReimb.toString());

        Event addedEvent = rfServ.getEventById(addedReimb.getEvent().getId());
        assertEquals(addedReimb.getEvent().toString(), addedEvent.toString());
    }

    @Test
    public void processRequestFormFullPass() {
        RequestForm reqForm = new RequestForm(14, // employeeId
                null, // eventId
                "How to Run in Place", // eventName
                "2022-04-20", // startDate
                "08:00", // startTime
                "51 East Blv. Kevin Crater, Moon 51356", // location
                400.0, // tuition
                3, // typeId
                null, // formatId
                "Smile or Fail", // formatName
                "All you have to do is smile.", // formatDescription
                "Smile", // passingGradeCutoff
                "2022-04-20", // endDate
                "04:30", // endTime
                "I would like reimbursement for this.", // description
                8.5); // hoursMissed

        Reimbursement processedReimb = rfServ.processRequestForm(reqForm);
        Reimbursement addedReimb = rfServ
                .getReimbursementById(processedReimb.getId());
        assertEquals(processedReimb.toString(), addedReimb.toString());

        Event addedEvent = rfServ.getEventById(addedReimb.getEvent().getId());
        assertEquals(addedReimb.getEvent().toString(), addedEvent.toString());

        GradingFormat addedFormat = rfServ.getGradingFormatById(
                addedReimb.getEvent().getGradingFormat().getId());
        assertEquals(addedReimb.getEvent().getGradingFormat().toString(),
                addedFormat.toString());
    }

    @Test
    public void convertToEpochPass() {
        String date = "2050-06-15";
        String time = "10:00";
        long epoch = rfServ.convertToEpoch(date, time);
        assertEquals(2524143600000L, epoch);
    }

    @Test
    public void getReimbursementByIdPass() {
        String expectedString = "Reimbursement [id=2, employeeId=4, eventId=2, "
                + "description=I am getting some supervisoral training., submis"
                + "sionTime=1442682000000, hoursMissed=40.0, approvalStep=5, finalGrade=91, act"
                + "ualClaim=899.2]";
        Reimbursement reimbursement = rfServ.getReimbursementById(2);
        assertEquals(expectedString, reimbursement.toString());
    }

    @Test
    public void getEventByIdPass() {
        String expectedString = "Event [id=3, eventName=Public Speaking, startTime=1456673400000, location=1337 Road Street Ln. Litte Rock, AR 72002, tuition=200.0, eventTypeId=1, gradingFormatId=3, endTime=null]";
        Event event = rfServ.getEventById(3);
        assertEquals(expectedString, event.toString());
    }

    @Test
    public void getGradingFormatByIdPass() {
        String expectedString = "GradingFormat [id=1, formatName=Typical Letter Grade, description=Grades are A, B, C, D, and F. F is fail, D is lowest passing, A is highest passing, passingGradeCutoff=D]";
        GradingFormat format = rfServ.getGradingFormatById(1);
        assertEquals(expectedString, format.toString());
    }

}
