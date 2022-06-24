package todolist;

/***
 * Tododemo Reads the inputfile text file. It Adds new Todo Task details to Arraylist. Edit 
 * Delete the existing Task details. Also Write back the details to text file
 * AUTHOR IR ON T3500 WORKSTATION
 */
import java.io.BufferedWriter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import javax.swing.*;
import javax.swing.filechooser.*;
import java.io.File;

public class Tododemo {
	private ArrayList<Task> inp = new ArrayList<>();
  private int option,taskNo,statusOpen = 0,statusClosed = 0;
  private boolean setExit = false;
  private String inputText3,inputText1,inputText2;
  String filename = null;
  
  DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

  /** Display the To do list data from ArrayList
   * 
   */
  
 public void displayInput()
{
  int counter = 0;
  int choice;
 
  System.out.println("Please Enter your choice - for sorting");
  System.out.println("1.Sort based on Date");
  System.out.println("2.Sort based on Project");
  
  choice = scanInput();
  
  if (choice == 1)
  {
	  inp.sort((Task d1,Task d2)->d1.getTaskDate().compareTo(d2.getTaskDate()));
  }
  else if (choice == 2)
  {
	  inp.sort((Task d1,Task d2)->d1.getProjectName().compareTo(d2.getProjectName()));
  }
  String format1 = "%-9s %-40s %-43s %-12s %-15s";
  System.out.println(String.format(format1,"Task No","Task Name","ProjectName" , "Status", " Date"));
  System.out.println("----------------------------------------------------------------------------------------------------------------------");
  
  for(Task file : inp)
	{
    counter = counter + 1;
    
	 System.out.println(String.format(format1,counter,file.getTaskName(),file.getProjectName(),file.getSatus(),formatter.format(file.getTaskDate())));
	 String changeCase = file.getSatus().toUpperCase();
	 if (changeCase.equals("OPEN"))
	 	{
		 statusOpen = statusOpen + 1;
	 	}
	 else if(changeCase.equals("CLOSED"))
	 	{
		 statusClosed = statusClosed + 1;
	 	}
	}
  	System.out.println("Number of Tasks open : "+statusOpen+" Number of Tasks Closed " + statusClosed);
  	 statusOpen = 0;
  	statusClosed = 0;
  	 
}
 /*
  *@Read the input Text file and Store the details into Arraylist of Task
  */

public void inputReader() throws IOException
{
    Date date = null;
    	JFileChooser chooser = new JFileChooser();
    	
    	File file = null;
    	int returnValue = chooser.showOpenDialog( null ) ;    	 
    	if( returnValue == JFileChooser.APPROVE_OPTION )
    	{
	        file = chooser.getSelectedFile() ;
    	}
    	if(file != null)
    	{
	      filename = file.getPath();
    	}   

		BufferedReader inpFile = new BufferedReader(new FileReader(filename));
	    String str;
      try{
	    while((str = inpFile.readLine()) != null)
	    {
	    	String str1 = str.toString();
	    	String[] arr = str1.split(";");
        try{
            date = formatter.parse(arr[3]);
      }catch(ParseException p){
          System.out.println("Error while reading the Date field");
      }

	        inp.add(new Task(arr[0],arr[1],arr[2],date));

	    }
	}catch(IOException e) {
    e.printStackTrace();
	}

}

/*
 * Write back to the file which should be used when you open the Todo list task next time.
 */

public void outputWriter() throws IOException
{
	BufferedWriter writer = new  BufferedWriter(new FileWriter(filename));

		for (Task out: inp)
		{
		writer.write(out.getTaskName() + ";"+out.getProjectName()+";"+out.getSatus()+ ";"+ formatter.format(out.getTaskDate())+"\n");
		}

	writer.close();
}
 /*
  * Add new Todo task.
  */

public void addRecord(String taskName,String projectName,String taskStatus,Date taskDate) throws IOException, ParseException
{
	DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	
//* Get the current date. Consider only the Date portion for comparison
	
	Date today = new Date();
	Date todayWithZeroTime = formatter.parse(formatter.format(today));
	Date inputDateWithZeroTime = formatter.parse(formatter.format(taskDate));
	
	if (inputDateWithZeroTime.compareTo(todayWithZeroTime)<0)
    {
    	System.out.println("Date Entered should be greater than today");
    }
    else
    {
	inp.add(new Task(taskName,projectName,taskStatus,taskDate));
    }
    
}

/*
 * Edit the already existing Todo list
 */

public void editRecord(int editIndex,String inputField,int changeField) throws IOException, ParseException

{
  Date date = null;
  
  if (changeField == 1)
  {
    inp.set(editIndex,new Task(inputField,inp.get(editIndex).getProjectName(),inp.get(editIndex).getSatus(),inp.get(editIndex).getTaskDate()));
  }
  if (changeField == 2)
  {
    inp.set(editIndex,new Task(inp.get(editIndex).getTaskName(),inputField,inp.get(editIndex).getSatus(),inp.get(editIndex).getTaskDate()));
  }
  if (changeField == 3)
  {
    inp.set(editIndex,new Task(inp.get(editIndex).getTaskName(),inp.get(editIndex).getProjectName(),inputField,inp.get(editIndex).getTaskDate()));
  }
  if (changeField == 4)
  {

    try{

    	
       date = formatter.parse(inputField);

  	  
    }catch(ParseException p){
      System.out.println(" Error occured while editing the arraylist record");
    }
 
    DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	
  //* Get the current date. Consider only the Date portion for comparison
  	
  	Date today = new Date();
  	Date todayWithZeroTime = formatter.parse(formatter.format(today));
  	Date inputDateWithZeroTime = formatter.parse(formatter.format(date));
  	
  	if (inputDateWithZeroTime.compareTo(todayWithZeroTime)<0)
    {
      	System.out.println("Date Entered should be greater than today");
    }
    else
    {
    inp.set(editIndex,new Task(inp.get(editIndex).getTaskName(),inp.get(editIndex).getProjectName(),inp.get(editIndex).getSatus(),date));
    }
  }
}

/*
 * Delete the Todo task which are done or which you don't want to maintain in the TOdo list
 */

public void deleteRecord(int removeindex) throws IOException
{
	inp.remove(removeindex);
	
  System.out.println("Record deleted sucessfully ");
}

/*
 * Get the choice from the user.
 */

public int scanInput()
{
  Scanner sc = new Scanner(System.in);
  int i = sc.nextInt();
  return i;
}

}

