//package com.kevin_leader.repositories;
//
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertNotEquals;
//import static org.junit.Assert.assertTrue;
//
//import java.util.List;
//
//import org.junit.BeforeClass;
//import org.junit.Test;
//
//import com.kevin_leader.models.Department;
//import com.kevin_leader.models.Employee;
//
//public class DepartmentDaoTests {
//
//    private static GenericRepo<Department> dDao;
//
//    @BeforeClass
//    public static void setUpBeforeClass() {
//        dDao = new GenericRepoImpl<>(Department.class);
//    }
//
//    @Test
//    public void addSuccess() {
//        Employee employee = new Employee("Super", "Saiyan", "eleven@gohan.com",
//                "3j1o5ino1!", null, null, null, null);
//        Department department = new Department("Underwater Basket Weaving",
//                "The most honorable department in the galaxy.", employee);
//        int id = dDao.add(department);
//        assertNotEquals(0, id);
//        assertNotEquals(-1, id);
//    }
//
//    @Test
//    public void getAllSuccess() {
//        List<Department> departments = dDao.getAll();
//        assertTrue(1 < departments.size());
//    }
//
//    @Test
//    public void getByIdSuccess() {
//        Department department = dDao.getById(2);
//        String expected = "Department [id=2, name=Engineering, description=Bulk"
//                + " of this IT company, depHeadEmpId=3]";
//        assertEquals(expected, department.toString());
//    }
//
//    @Test
//    public void updateSuccess() {
//        Employee employee = new Employee("Super", "Saiyan", "rotund@vegeta.com",
//                "3j1o5ino1!", null, null, null, null);
//        Department department = new Department(3, "Underwater Basket Weaving",
//                "The most honorable department in the galaxy.", employee);
//        Department updatedDepartment = dDao.update(department);
//        assertEquals(department.toString(), updatedDepartment.toString());
//    }
//
////    @Test
////    public void deleteSuccess() {
////        Department departmentToDelete = dDao.getById(1);
////        Department nulledDepartment = new Department(1,
////                departmentToDelete.getName(), null, null);
////        Department updatedDepartment = dDao.update(nulledDepartment);
////        Department deletedDepartment = dDao.delete(nulledDepartment);
////        assertEquals(deletedDepartment.toString(),
////                updatedDepartment.toString());
////    }
//
//}
