import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class SQLOperations {
	
	static String homeDir = System.getProperty("user.dir");
	static String url = "jdbc:sqlite:" + homeDir + "/skola.db";
	static String prihlasovacieMeno = "";
	static String prihlasovacieHeslo = "";
	
	public void loginFromFile()
	{
		try {
			Scanner sc = new Scanner(new File("login.txt"));
			while (sc.hasNext()) {
				prihlasovacieMeno = sc.next();
				prihlasovacieHeslo = sc.next();
			}
			sc.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void createTeacherTable()
	{
			String login = "student";
			String password = "student";
			if(prihlasovacieMeno.equals(login) && prihlasovacieHeslo.equals(password))
			{
				try {
				Connection conn = DriverManager.getConnection(url, login, password);
				Statement myStmt = conn.createStatement();
				
				String sql = "CREATE TABLE teachers " +
				"(id INTEGER not NULL, " +
				" first_name VARCHAR(255), " +
				" last_name VARCHAR(255), " +
				" year INTEGER not NULL, " +
				" salary DOUBLE not NULL, " +
				" PRIMARY KEY (id))";

				myStmt.executeUpdate(sql);
				}
				
				catch(SQLException e){
					System.out.println(e.toString());
				}
			}
			else
			{
				System.out.println("Prihlasovacie udaje sa nezhoduju.");
			}
	}
	public void addTeacher(int id, String first_name, String last_name, int year, double salary)
	{

		String login = "student";
		String password = "student";
		if(prihlasovacieMeno.equals(login) && prihlasovacieHeslo.equals(password))
		{
			try {
				Connection conn = DriverManager.getConnection(url, "student", "student");
			
				PreparedStatement myStmt = conn.prepareStatement("INSERT INTO teachers"
															   + "(id, first_name, last_name, year, salary) VALUES"
															   + "(?, ?, ?, ?, ?);");
				
				myStmt.setInt(1, id);
				myStmt.setString(2, first_name);
				myStmt.setString(3, last_name);
				myStmt.setInt(4, year);
				myStmt.setDouble(5, salary);
			
				myStmt.executeUpdate();
				}
				catch(SQLException e){
					System.out.println(e.toString());
				}
		}
		else
		{
			System.out.println("Prihlasovacie udaje sa nezhoduju.");
		}
	}	

public void listTeacherTable()
{
	
	String login = "student";
	String password = "student";
	
	if(prihlasovacieMeno.equals(login) && prihlasovacieHeslo.equals(password))
	{
		try {
			Connection conn = DriverManager.getConnection(url, "student", "student");
			Statement myStmt = conn.createStatement();
			ResultSet myRs = myStmt.executeQuery("select * from teachers");	
		
    		while(myRs.next())
    		{
    			System.out.println(myRs.getInt("id") + " "
  							 	+ myRs.getString("first_name") + " "
  						 	 	+ myRs.getString("last_name") + ", "
  						 	 	+ myRs.getInt("year") + ", "
  						 	 	+ myRs.getDouble("salary"));
    		}
		}
		catch(SQLException e){
			System.out.println(e.toString());
		}
	}
	else
	{
		System.out.println("Prihlasovacie udaje sa nezhoduju.");
	}
}
public void deleteTeacher(int cislo)
{
	String login = "student";
	String password = "student";
	if(prihlasovacieMeno.equals(login) && prihlasovacieHeslo.equals(password))
	{
		try {
			Connection conn = DriverManager.getConnection(url, "student", "student");
			PreparedStatement statement = conn.prepareStatement("DELETE FROM teachers where id = ?;");
		
			statement.setInt(1, cislo);

			statement.executeUpdate();
			}
			catch(SQLException e){
				System.out.println(e.toString());
			}
	}	
	else
	{
		System.out.println("Prihlasovacie udaje sa nezhoduju.");
	}
}
public void createStudentTable()
{
		String login = "student";
		String password = "student";
		if(prihlasovacieMeno.equals(login) && prihlasovacieHeslo.equals(password))
		{
			try {
			Connection conn = DriverManager.getConnection(url, login, password);
			Statement myStmt = conn.createStatement();
			
			String sql = "CREATE TABLE students " +
			"(id INTEGER not NULL, " +
			" first_name VARCHAR(255), " +
			" last_name VARCHAR(255), " +
			" year INTEGER not NULL, " +
			" average DOUBLE not NULL, " +
			" PRIMARY KEY (id))";

			myStmt.executeUpdate(sql);
			}
			
			catch(SQLException e){
				System.out.println(e.toString());
			}
		}
		else
		{
			System.out.println("Prihlasovacie udaje sa nezhoduju.");
		}
}
public void addStudent(int id, String first_name, String last_name, int year, double average)
{

	String login = "student";
	String password = "student";
	if(prihlasovacieMeno.equals(login) && prihlasovacieHeslo.equals(password))
	{
		try {
			Connection conn = DriverManager.getConnection(url, "student", "student");
		
			PreparedStatement myStmt = conn.prepareStatement("INSERT INTO students"
														   + "(id, first_name, last_name, year, average) VALUES"
														   + "(?, ?, ?, ?, ?);");
			
			myStmt.setInt(1, id);
			myStmt.setString(2, first_name);
			myStmt.setString(3, last_name);
			myStmt.setInt(4, year);
			myStmt.setDouble(5, average);
		
			myStmt.executeUpdate();
			}
			catch(SQLException e){
				System.out.println(e.toString());
			}
	}
	else
	{
		System.out.println("Prihlasovacie udaje sa nezhoduju.");
	}
}	
public void deleteStudent(int cislo)
{
	String login = "student";
	String password = "student";
	if(prihlasovacieMeno.equals(login) && prihlasovacieHeslo.equals(password))
	{
		try {
			Connection conn = DriverManager.getConnection(url, "student", "student");
			PreparedStatement statement = conn.prepareStatement("DELETE FROM students where id = ?;");
		
			statement.setInt(1, cislo);

			statement.executeUpdate();
			}
			catch(SQLException e){
				System.out.println(e.toString());
			}
	}	
	else
	{
		System.out.println("Prihlasovacie udaje sa nezhoduju.");
	}
}
public void listStudentTable()
{
	
	String login = "student";
	String password = "student";
	
	if(prihlasovacieMeno.equals(login) && prihlasovacieHeslo.equals(password))
	{
		try {
			Connection conn = DriverManager.getConnection(url, "student", "student");
			Statement myStmt = conn.createStatement();
			ResultSet myRs = myStmt.executeQuery("select * from students");	
		
    		while(myRs.next())
    		{
    			System.out.println(myRs.getInt("id") + " "
  							 	+ myRs.getString("first_name") + " "
  						 	 	+ myRs.getString("last_name") + ", "
  						 	 	+ myRs.getInt("year") + ", "
  						 	 	+ myRs.getDouble("average"));
    		}
		}
		catch(SQLException e){
			System.out.println(e.toString());
		}
	}
	else
	{
		System.out.println("Prihlasovacie udaje sa nezhoduju.");
	}
}
public void findTeacher(int cislo)
{
	String login = "student";
	String password = "student";
	if(prihlasovacieMeno.equals(login) && prihlasovacieHeslo.equals(password))
	{
		try {
			String selectSQL = "SELECT * FROM teachers WHERE id = ?";
			Connection conn = DriverManager.getConnection(url, "student", "student");
			PreparedStatement myStmt = conn.prepareStatement(selectSQL);
			myStmt.setInt(1, cislo);
			ResultSet myRs = myStmt.executeQuery();	
			
			
			
    		while(myRs.next())
    		{
    			System.out.println("==========NAJDENY UCITEL==========");
    			System.out.println("==================================");
    			System.out.println(myRs.getInt("id") + " "
  							 	+ myRs.getString("first_name") + " "
  						 	 	+ myRs.getString("last_name") + ", "
  						 	 	+ myRs.getInt("year") + ", "
  						 	 	+ myRs.getDouble("salary"));
    		}
		}
		catch(SQLException e){
			System.out.println(e.toString());
		}	
	}
	else
	{
		System.out.println("Prihlasovacie udaje sa nezhoduju.");
	}
}
public void findStudent(int cislo)
{
	String login = "student";
	String password = "student";
	if(prihlasovacieMeno.equals(login) && prihlasovacieHeslo.equals(password))
	{
		try {
			String selectSQL = "SELECT * FROM students WHERE id = ?";
			Connection conn = DriverManager.getConnection(url, "student", "student");
			PreparedStatement myStmt = conn.prepareStatement(selectSQL);
			myStmt.setInt(1, cislo);
			ResultSet myRs = myStmt.executeQuery();	
			
			
			
    		while(myRs.next())
    		{
    			System.out.println("==========NAJDENY STUDENT=========");
    			System.out.println("==================================");
    			System.out.println(myRs.getInt("id") + " "
  							 	+ myRs.getString("first_name") + " "
  						 	 	+ myRs.getString("last_name") + ", "
  						 	 	+ myRs.getInt("year") + ", "
  						 	 	+ myRs.getDouble("average"));
    		}
		}
		catch(SQLException e){
			System.out.println(e.toString());
		}	
	}
	else
	{
		System.out.println("Prihlasovacie udaje sa nezhoduju.");
	}
}

}
