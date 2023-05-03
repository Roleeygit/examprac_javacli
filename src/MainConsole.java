import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class MainConsole 
{
    ArrayList<Employee> employeeList;
    ArrayList<Employee> smallSalary;

    public MainConsole() 
    {
        this.employeeList = new ArrayList<>();
        this.smallSalary = new ArrayList<>();
        readContent();
        selectSmallSalary();
        writeFile();
    }

    public void readContent()
    {
        try {
            tryreadContent();
        } catch (FileNotFoundException e) {
            System.err.println("A fájl nem található!");
        }
    }
    public void tryreadContent() throws FileNotFoundException
    {
        File file = new File("szinthum_dolgozok.csv");
        Scanner scanner = new Scanner(file, "utf-8");
        String firstLine = scanner.nextLine();
        while(scanner.hasNext()) 
        {
            String line = scanner.nextLine();
            // System.out.println(line);
            String[] lineArray = line.split(",");
            Employee employee = new Employee(Integer.parseInt(lineArray[0]), lineArray[1], lineArray[2], Double.parseDouble((lineArray[3])), LocalDate.parse(lineArray[4]));
            this.employeeList.add(employee);
        }
        
    }

    public void selectSmallSalary()
    {
        for(Employee employee : this.employeeList)
        {
            if(employee.salary < 200000)
            {
                this.smallSalary.add(employee);
            }
        }
    }

    public void writeFile()
    {
        try 
        {
            trywriteFile();
        } 

        catch (IOException e) 
        {
            System.err.println("Nem sikerült a fájlt írni!");
            System.err.println(e.getMessage());
        }
    }

    public void trywriteFile() throws IOException
    {   
        FileWriter fw = new FileWriter("keves.csv");
        
        for(Employee employee : this.smallSalary)
        {
            fw.write(employee.id.toString());
            fw.write(",");
            fw.write(employee.name);
            fw.write(",");
            fw.write(employee.city);
            fw.write(",");
            fw.write(employee.salary.toString());
            fw.write(",");
            fw.write(employee.birthdate.toString());
            fw.write("\n");
        }
        fw.close();
    }
}
