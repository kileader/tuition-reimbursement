package com.kevin_leader.repositories;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import com.kevin_leader.models.GradingFormat;

public class GradingFormatDaoTests {

    private static GenericRepo<GradingFormat> gfDao;

    @BeforeClass
    public static void setUpBeforeClass() {
        gfDao = new GenericRepoImpl<>(GradingFormat.class);
//		Database database = Database.getInstance();
//		database.runSQL("database.sql");
    }

    @Test
    public void addSuccess() {
        GradingFormat gradingFormat = new GradingFormat("No Ds",
                "Grades that are typically Ds are Fs", "C");
        int id = gfDao.add(gradingFormat);
        assertNotEquals(0, id);
        assertNotEquals(-1, id);
    }

    @Test
    public void getAllSuccess() {
        List<GradingFormat> gradingFormats = gfDao.getAll();
        assertTrue(1 < gradingFormats.size());
    }

    @Test
    public void getByIdSuccess() {
        GradingFormat gradingFormat = gfDao.getById(2);
        GradingFormat expectedFormat = new GradingFormat(2,
                "Typical Numeric Grade",
                "Grades are from 0 to 100. Less than 60 is fail.", "60");
        assertEquals(expectedFormat.toString(), gradingFormat.toString());
    }

    @Test
    public void updateSuccess() {
        GradingFormat gradingFormat = new GradingFormat(3, "You Lose",
                "Good day sir!", "not possible");
        GradingFormat updatedFormat = gfDao.update(gradingFormat);
        assertEquals(gradingFormat.toString(), updatedFormat.toString());
    }

    @Test
    public void deleteSuccess() {
        GradingFormat formatToDelete = gfDao.getById(1);
        GradingFormat deletedFormat = gfDao.delete(formatToDelete);
        assertEquals(deletedFormat.toString(), formatToDelete.toString());
    }
}
