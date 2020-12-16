package todolist;

/*
 * Gives option to the user the Action to be performed on Todo List.
 */
import java.io.IOException;
import java.util.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.text.ParseException;

public class Taskinput {

private int option,taskNo;
private boolean setExit = false;
private String inputText3,inputText1,inputText2,inputText4;
private int  changeField;
private Tododemo demo;
private Date date;
DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

public void displayOption() throws ParseException
{
   demo = new Tododemo();
/*
 *  Read the input text File
 */
   
  try{
        demo.inputReader();
      }catch(IOException a)
    {
      System.out.println(" Error Reading inputfile ");
    }

  
  System.out.println("---------------------------------------------------------------------------------------------------------------------");
  System.out.println(String.format("  %55s", "                To do List          "));
  System.out.println("---------------------------------------------------------------------------------------------------------------------");
  System.out.println(String.format("%45s","      1- Display List      "));
  System.out.println(String.format("%45s","      2- Add New Task      "));
  System.out.println(String.format("%45s","      3- Edit Task         "));
  System.out.println(String.format("%45s","      4- Delete Task       "));
  System.out.println(String.format("%45s","      5- Save & Exit       "));
  System.out.println("---------------------------------------------------------------------------------------------------------------------");
  System.out.println(" Enter correct option");
  option = scanInput();

  while(!(setExit))
  {
/*
 *  Display the To do list task
 */
        if(option == 1)
        {
          displayTask();
        }

/*
 *  Add new task to the list
 */
        if(option == 2)
        {
          addTask();
        }

/*
 *  Edit the Todo List
 */
        if(option == 3)
        {
          editTask();
        }

/*
 * Delete the task from the list
 */
        if (option == 4)
        {
          deleteTask();
        }

/*
 *  Save and Exit
 */
        if(option == 5)
         {
           saveExittask();
         }
    }
}
public int scanInput()
    {
      Scanner sc = new Scanner(System.in);
      int i = sc.nextInt();
      return i;
    }
    /*
     * Read the input FileReader
     */
public String scanString()
    {
      Scanner sc = new Scanner(System.in);
      String line = sc.nextLine();
      return line;
    }
/*
 *  Add task
 */
private void addTask() throws ParseException
    {
      System.out.println("Enter the Task name   :  ");
      inputText1 = scanString();
      System.out.println("Enter the ProjectName :  ");
      inputText2 = scanString();
      System.out.println("Enter the Status      :  ");
      inputText3 = scanString();
      System.out.println("Enter the TaskDate(yyyy-MM-dd)      :  ");
      inputText4 = scanString();
  try{
      date = formatter.parse(inputText4); 
      
    }catch(ParseException p)
    {
      System.out.println(" Error occured while trying to convert date ");
    }

  try{
      demo.addRecord(inputText1,inputText2,inputText3,date);
      System.out.println("Enter the option");
      option = scanInput();
  }catch(IOException f)
     {
       System.out.println("Problem occured when trying to add a record ");
     }
    }

  /*
   *  edit task
   */
  private void editTask() throws ParseException
      {
        System.out.println("Enter the Task No you want  to Edit");
        taskNo = scanInput();
        taskNo = taskNo - 1;
        System.out.println("Enter the field you want to Edit");
        System.out.println("1.Task Name  ");
        System.out.println("2.Project Name ");
        System.out.println("3.Status ");
        System.out.println("4.Date");
        option = scanInput();
        if (option ==1)
        {
          System.out.println("Edit the Task Name : " );
          inputText1 = scanString();
           changeField = 1;
           try
           {
               demo.editRecord(taskNo,inputText1,changeField);
           }catch (IOException e) {

             System.out.println("Error occured while trying to edit Task Name");
           }
           System.out.println("Enter the option");
             option = scanInput();

        }
        if (option == 2)
        {
          System.out.println("Edit the Project Name : ");
          inputText2 = scanString();
          changeField = 2;
          try{
            demo.editRecord(taskNo,inputText2,changeField);

          }catch(IOException f)
          {
            System.out.println("Error occured while trying to edit ProjectName");
          }
          System.out.println("Enter the option");
          option = scanInput();

        }
        if (option == 3)
        {
          System.out.println("Edit the status : ");
          inputText3 = scanString();
          changeField = 3;
          try
          {
            demo.editRecord(taskNo,inputText3,changeField);
          }catch (IOException g) {
            System.out.println("Error occured while trying to edit Staus");
        }
          System.out.println("Enter the option");
          option = scanInput();
       }
        if (option == 4)
        {
          System.out.println("Edit the TaskDate(yyyy-MM-dd) : ");
          inputText4 = scanString();
          changeField = 4;
          try
          {
            demo.editRecord(taskNo,inputText4,changeField);
          }catch (IOException g) {
            System.out.println("Error occured while trying to edit Date");
          }
            System.out.println("Enter the option");
            option = scanInput();
        }
      }
/*
 *  delete task
 */
private void deleteTask()
      {
        System.out.println("Enter the Task No you want  to delete ");
        taskNo = scanInput();
        taskNo = taskNo - 1;
        try{
            demo.deleteRecord(taskNo);
            }catch(IOException g)
            {
              System.out.println(" Problem occured when trying to delete a record");
            }
            System.out.println("Enter the option");
              option = scanInput();
          }
/*
 *  Save and Exit task
 */
private void saveExittask()
        {
         try{
             demo.outputWriter();
          }catch(IOException e)
          {
            System.out.println("raised during when trying to write into the file");
          }
          setExit = true;
          }
/*
 *  Display Task
 */
private void displayTask()
        {
          demo.displayInput();
          System.out.println("Enter the option");
          option = scanInput();
        }
  }
