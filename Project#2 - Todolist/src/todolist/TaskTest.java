package todolist;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TaskTest{
	 Date date;
	 DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	 Task tasktest;
	 
	 /*
	  * Load ArrayList with data.
	  */
	 
	@BeforeEach
	void setup() throws ParseException
	{
		date = formatter.parse("2020-10-25");
		Task tasktest = new Task("Test","Test Project","Open",date);
	}
	
	/*
	 * verify whether correct TaskName has been loaded into the ArrayList
	 */
	
	@Test
	void getTaskName() throws ParseException
	{
		Task tasktest = new Task("Test","Test Project","Open",date);
		assertEquals("Test",tasktest.getTaskName());
	}
	@Test
	
	/*
	 * verify for correct projectName in the Arraylist
	 */
	void getProjectName() 
	{
		Task tasktest = new Task("Test","Test Project","Open",date);		
		assertEquals("Test Project",tasktest.getProjectName());
	}
	@Test
	/*
	 * verify for correct Status in the Arraylist
	 */
	
	void getSatus() 
	{
		Task tasktest = new Task("Test","Test Project","Open",date);
		assertEquals("Open",tasktest.getSatus());
	}
	
	/*
	 * Verify for correct Date in the Arraylist
	 */
	
	@Test
	void getTaskDate() 
	{
		Task tasktest = new Task("Test","Test Project","Open",date);
		assertEquals(date,tasktest.getTaskDate());
	}
	
	/*
	 * Check whether the set method for TaskName,ProjectName,Status and Date gives expected result.
	 */
	
	@Test
	void setgetTaskName() throws ParseException
	{
		Task tasktest = new Task("Test","Test Project","Open",date);
		tasktest.setTaskName("test");
		assertEquals("test",tasktest.getTaskName());
		
		tasktest.setProjectName("test project");
		assertEquals("test project",tasktest.getProjectName());
		
		tasktest.setStatus("open");
		assertEquals("open",tasktest.getSatus());
		
		date = formatter.parse("2020-10-21");
		tasktest.setTaskDate(date);
		assertEquals(date,tasktest.getTaskDate());
		
	}
	
	/*
	 * Check whether the set method for TaskName,ProjectName,Status and Date gives expected result.
	 */
	
	@Test
	void setgetTaskNameFailureCheck() throws ParseException
	{
		Task tasktest = new Task("Test","Test Project","Open",date);
		tasktest.setTaskName("test");
		assertEquals("Test",tasktest.getTaskName());
		
		tasktest.setProjectName("test project");
		assertEquals("Test project",tasktest.getProjectName());
		
		tasktest.setStatus("open");
		assertEquals("Open",tasktest.getSatus());
		
		date = formatter.parse("2020-10-01");
		tasktest.setTaskDate(date);
		assertEquals(date,tasktest.getTaskDate());
	}
	
	}

