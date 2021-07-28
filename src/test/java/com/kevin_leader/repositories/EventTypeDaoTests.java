package com.kevin_leader.repositories;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import com.kevin_leader.models.EventType;

public class EventTypeDaoTests {

    private static GenericRepo<EventType> etDao;

    @BeforeClass
    public static void setUpBeforeClass() {
        etDao = new GenericRepoImpl<>(EventType.class);
    }

    @Test
    public void addSuccess() {
        EventType eventType = new EventType("Corporate Party", 95);
        int id = etDao.add(eventType);
        assertNotEquals(0, id);
        assertNotEquals(-1, id);
    }

    @Test
    public void getAllSuccess() {
        List<EventType> eventTypes = etDao.getAll();
        assertTrue(4 < eventTypes.size() && eventTypes.size() < 8);
    }

    @Test
    public void getByIdSuccess() {
        EventType eventType = etDao.getById(2);
        EventType expectedType = new EventType(2, "seminar", 60);
        assertEquals(expectedType.toString(), eventType.toString());
    }

    @Test
    public void updateSuccess() {
        EventType eventType = new EventType(3, "dragon training", 10);
        EventType updatedType = etDao.update(eventType);
        assertEquals(eventType.toString(), updatedType.toString());
    }

    @Test
    public void deleteSuccess() {
        EventType typeToDelete = etDao.getById(4);
        EventType deletedType = etDao.delete(typeToDelete);
        assertEquals(deletedType.toString(), typeToDelete.toString());
    }
}
