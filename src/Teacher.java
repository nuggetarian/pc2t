import java.util.ArrayList;
import java.util.List;

public class Teacher implements Person{
	String meno;
	String priezvisko;
	int rokNarodenia;
	List<Student> listOfStudents = new ArrayList<Student>(); 
	List<String> listOfStudentsString = new ArrayList<String>();
	
	public Teacher(String meno, String priezvisko, int rokNarodenia)
	{
		this.priezvisko = priezvisko;
		this.rokNarodenia = rokNarodenia;
		this.meno = meno;
		this.listOfStudents = new ArrayList<Student>();
	}
	public String getMeno()
	{
		return meno;
	}
	public int getRokNarodenia()
	{
		return rokNarodenia;
	}
	public String getPriezvisko()
	{
		return priezvisko;
	}
	public int zarobok()
	{
		if (listOfStudents.size()>=5)
		{
			return 1200;
			
		}
		else if(listOfStudents.size() >= 2)
		{
			return 1100;
		}
		else
		{
			return 1000;
		}
	}
	public int zarobokStipendium()
	{
		int zarobok = 0;
		for(int i = 0; i<listOfStudents.size(); i++)
		{
			if (listOfStudents.get(i).stipendium() == true)
			{
				zarobok = zarobok + 50;
			}
		}
		return zarobok;
	}
	public double zarobokCelkovo()
	{
		return zarobokStipendium()+zarobok();
	}


}
