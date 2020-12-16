package todolist;

import static org.junit.jupiter.api.Assertions.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class TododemoTest {
	
	 ArrayList<Task> inp = new ArrayList<>();
	 DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	 Date date,date1,date2;
	 Task task;
	 
	 @BeforeEach
	 /** 
	  * 
	  * Load the ArrayList with Date before each method execution.
	  */
	 void setup() throws ParseException
	 {
		 date = formatter.parse("2020-10-08");
		 date1 = formatter.parse("2020-10-25");
		 date2 = formatter.parse("2020-10-21");
		 Task task = new Task("Test","Test Project","Open",date);
		 Task task1 = new Task("First","First Project","Open",date1);
		 Task task2 = new Task("Second","Second Project","Closed",date2);
		 
		 inp.add(task);
		 inp.add(task1);
		 inp.add(task2);
	 }
	 
	@Test
	/**
	 * 
	 * Add element to Arraylist and Test whether added element is present in the list.
	 */
	
	void checkWhetherTheElementisAddedinTheArrayList() throws ParseException {
		 
		 assertEquals("Test",inp.get(0).getTaskName());
		 assertEquals("Test Project",inp.get(0).getProjectName());
		 assertEquals("Open",inp.get(0).getSatus());
		 assertEquals(date,inp.get(0).getTaskDate());
	
	}
	@Test
	/**
	 * 
	 * Add element to Arraylist and Test for the Failure case.
	 */
	
	void checkWhetherTheElementAddedhasFailed() throws ParseException
	{
		
		 assertEquals("test",inp.get(0).getTaskName());
		 assertEquals("test project",inp.get(0).getProjectName());
		 assertEquals("open",inp.get(0).getSatus());
		 assertEquals(date,inp.get(0).getTaskDate());
	}
	@Test
	/*
	 * Edit elements in ArrayList and check whether it gets reflected.
	 */
	
	void TestForEditElementinTheListAndCheckWhetherElementisPresent() throws ParseException
	{
		date = formatter.parse("2020-10-10");
		inp.get(0).setTaskName("Test Edit");
		inp.get(0).setProjectName("Edit Project");
		inp.get(0).setStatus("Closed");
		inp.get(0).setTaskDate(date);
	 		
		inp.set(0,new Task(inp.get(0).getTaskName(),inp.get(0).getProjectName(),inp.get(0).getSatus(),inp.get(0).getTaskDate()));
		assertEquals("Test Edit",inp.get(0).getTaskName());
		assertEquals("Edit Project",inp.get(0).getProjectName());
		assertEquals("Closed",inp.get(0).getSatus());
		assertEquals(date,inp.get(0).getTaskDate());
 }
	@Test
	/*
	 * Check for failure condition of Edit function.
	 */
	
	void TestForEditElementinTheListAndCheckWhetherElementisnotPresent() throws ParseException
	{
		date = formatter.parse("2020-10-10");
		inp.get(0).setTaskName("Test Edit");
		inp.get(0).setProjectName("Edit Project");
		inp.get(0).setStatus("Closed");
		inp.get(0).setTaskDate(date);
	 		
		inp.set(0,new Task(inp.get(0).getTaskName(),inp.get(0).getProjectName(),inp.get(0).getSatus(),inp.get(0).getTaskDate()));
		assertEquals("test edit",inp.get(0).getTaskName());
		assertEquals("edit project",inp.get(0).getProjectName());
		assertEquals("closed",inp.get(0).getSatus());
		assertEquals(date,inp.get(0).getTaskDate());
 }
	@Test
	/*
	 * Check whether element gets successfully deleted from Arraylist
	 */
	
	void checkForDeleteElementFromArrayList()
	{
		inp.remove(2);
		assertEquals(2,inp.size());
	}

	@Test
	/*
	 * Check for failure condition of Delete function.
	 */
	
	void checkForDeleteElementFromArrayListFailure()
	{
		inp.remove(2);
		assertEquals(1,inp.size());
	}
	@Test
	
	/*
	 * Check whether the Arraylist is sorted. Copy the original list into another list  and sorted the list. Compare the sorted list with original list. 
	 */
	
	void checkWhetherArrayListisSorted()
	{
		ArrayList<Task> sorted = new ArrayList<>(inp);
		sorted.sort((Task d1,Task d2)->d1.getTaskDate().compareTo(d2.getTaskDate()));
		assertEquals(sorted.get(0).getTaskDate(),inp.get(0).getTaskDate());
	}

	@Test
	/*
	 * 
	 */
	
	void TestForEditElementinTheListAndChecktheDateInWrongFormat() throws ParseException
	{
		date = formatter.parse("xxxxxx");
		inp.get(0).setTaskName("Test Edit");
		inp.get(0).setProjectName("Edit Project");
		inp.get(0).setStatus("Closed");
		inp.get(0).setTaskDate(date);
	 		
		inp.set(0,new Task(inp.get(0).getTaskName(),inp.get(0).getProjectName(),inp.get(0).getSatus(),inp.get(0).getTaskDate()));
		assertEquals("Test Edit",inp.get(0).getTaskName());
		assertEquals("Edit Project",inp.get(0).getProjectName());
		assertEquals("Closed",inp.get(0).getSatus());
		assertEquals(date,inp.get(0).getTaskDate());
		
 }
	

}

