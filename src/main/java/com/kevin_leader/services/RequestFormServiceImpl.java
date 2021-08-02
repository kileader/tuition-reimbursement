package com.kevin_leader.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.kevin_leader.models.Employee;
import com.kevin_leader.models.Event;
import com.kevin_leader.models.EventType;
import com.kevin_leader.models.GradingFormat;
import com.kevin_leader.models.Reimbursement;
import com.kevin_leader.models.RequestForm;
import com.kevin_leader.repositories.GenericRepo;

public class RequestFormServiceImpl implements RequestFormService {

    private static final Logger log = Logger
            .getLogger(RequestFormServiceImpl.class);
    private GenericRepo<Employee> empDao;
    private GenericRepo<Event> evDao;
    private GenericRepo<EventType> etDao;
    private GenericRepo<GradingFormat> gfDao;
    private GenericRepo<Reimbursement> rDao;
    private static List<EventType> allEventTypes;

    public RequestFormServiceImpl(GenericRepo<Employee> empDao,
            GenericRepo<Event> evDao, GenericRepo<EventType> etDao,
            GenericRepo<GradingFormat> gfDao, GenericRepo<Reimbursement> rDao) {
        log.info("Instantiate RequestFormServiceImpl");
        this.empDao = empDao;
        this.evDao = evDao;
        this.etDao = etDao;
        this.gfDao = gfDao;
        this.rDao = rDao;
    }

    @Override
    public List<Event> getAllEvents() {
        return evDao.getAll();
    }

    @Override
    // Check for events at least one week and half a day in the future
    public List<Event> getFutureEvents() {

        List<Event> allEvents = evDao.getAll();

        Date dateTime = new Date();
        long currentTime = dateTime.getTime();

        List<Event> futureEvents = new ArrayList<>();
        for (Event event : allEvents) {
            if (currentTime + 648000000L < event.getStartTime()) {
                futureEvents.add(event);
            }
        }
        return futureEvents;
    }

    @Override
    public List<EventType> getAllEventTypes() {
        if (allEventTypes == null) {
            allEventTypes = etDao.getAll();
        }
        return allEventTypes;
    }

    @Override
    public List<GradingFormat> getAllGradingFormats() {
        return gfDao.getAll();
    }

    @Override
    public Reimbursement processRequestForm(RequestForm reqForm) {

        Reimbursement reimbursement = null;

        log.info("reqForm empId: " + reqForm.getEmployeeId());
        Employee reimbursee = empDao.getById(reqForm.getEmployeeId());

        Date dateTime = new Date();
        long currentTime = dateTime.getTime();

        Event event;
        if (reqForm.getEventId() != null) {
            event = evDao.getById(reqForm.getEventId());
        } else {
            Long startTime = convertToEpoch(reqForm.getStartDate(),
                    reqForm.getStartTime());
            Long endTime = convertToEpoch(reqForm.getEndDate(),
                    reqForm.getEndTime());

            GradingFormat format;
            if (reqForm.getFormatId() != null) {
                format = gfDao.getById(reqForm.getFormatId());
            } else {
                format = new GradingFormat(reqForm.getFormatName(),
                        reqForm.getFormatDescription(),
                        reqForm.getPassingGradeCutoff());
                int id = gfDao.add(format);
                format.setId(id);
            }

            event = new Event(reqForm.getEventName(), startTime,
                    reqForm.getLocation(), reqForm.getTuition(),
                    etDao.getById(reqForm.getTypeId()), format, endTime);

            int id = evDao.add(event);
            event.setId(id);
        }

        reimbursement = new Reimbursement(reimbursee, event,
                reqForm.getDescription(), currentTime, reqForm.getHoursMissed(),
                0, null, null);

        int id = rDao.add(reimbursement);
        reimbursement.setId(id);

        return reimbursement;
    }

    @Override
    public Long convertToEpoch(String date, String time) {
        String dateAndTime = date + " " + time + " EST";
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-DD HH:mm zzz");

        Date dateTime = null;
        try {
            dateTime = sdf.parse(dateAndTime);
        } catch (ParseException e) {
            log.warn(e);
        }

        Long epoch = null;
        if (dateTime != null) {
            epoch = dateTime.getTime();
        }

        return epoch;
    }

    @Override
    public Reimbursement getReimbursementById(int id) {
        return rDao.getById(id);
    }

    @Override
    public Event getEventById(int id) {
        return evDao.getById(id);
    }

    @Override
    public GradingFormat getGradingFormatById(int id) {
        return gfDao.getById(id);
    }

}
