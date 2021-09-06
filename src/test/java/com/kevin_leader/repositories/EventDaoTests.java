package com.kevin_leader.repositories;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import com.kevin_leader.models.Event;
import com.kevin_leader.models.EventType;
import com.kevin_leader.models.GradingFormat;

public class EventDaoTests {

    private static GenericRepo<Event> evDao;
    private static GenericRepo<EventType> etDao;
    private static GenericRepo<GradingFormat> gfDao;

    @BeforeClass
    public static void setUpBeforeClass() {
        evDao = new GenericRepoImpl<>(Event.class);
        etDao = new GenericRepoImpl<>(EventType.class);
        gfDao = new GenericRepoImpl<>(GradingFormat.class);
    }

    @Test
    public void addSuccess() {
        EventType newType = new EventType("Battle Arena", 33);
        int typeId = etDao.add(newType);
        newType.setId(typeId);
        GradingFormat newFormat = new GradingFormat("Rock Paper Scissors",
                "Best two out of three", "2 wins");
        int formatId = gfDao.add(newFormat);
        newFormat.setId(formatId);
        Event newEvent = new Event("Gettin Jiggy Wit It", 1626824924000L,
                "Yo Momma's House", 3.50, newType, newFormat, null);
        int id = evDao.add(newEvent);
        assertTrue(id > 0);
    }

    @Test
    public void getAllSuccess() {
        List<Event> events = evDao.getAll();
        assertTrue(3 < events.size());
    }

    @Test
    public void getByIdSuccess() {
        EventType expectedType = new EventType(5, "technical training", 90);
        GradingFormat expectedFormat = new GradingFormat(2,
                "Typical Numeric Grade",
                "Grades are from 0 to 100. Less than 60 is fail.", "60");
        Event expectedEvent = new Event(2, "All About Supervising",
                1443892000000L, "4321 Be Cool Guys Rd. Honolulu, HI 96795",
                999.11, expectedType, expectedFormat, null);
        Event event = evDao.getById(2);
        assertEquals(expectedEvent.toString(), event.toString());
    }

    @Test
    public void updateSuccess() {
        Event eventToUpdate = evDao.getById(3);
        eventToUpdate.setEndTime(1627686024000L);
        Event updatedEvent = evDao.update(eventToUpdate);
        assertEquals(eventToUpdate.toString(), updatedEvent.toString());
    }

    @Test
    public void deleteSuccess() {
        Event eventToDelete = evDao.getById(4);
        Event deletedEvent = evDao.delete(eventToDelete);
        assertEquals(eventToDelete.toString(), deletedEvent.toString());
        assertNull(evDao.getById(4));
    }

}
