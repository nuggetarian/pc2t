import java.util.ArrayList;
import java.util.List;

public class Student implements Person{

	double priemer;
	String meno;
	String priezvisko;
	int rokNarodenia;
	List<Integer> listOfGrades = new ArrayList<Integer>();
	List<Teacher> listOfTeachers = new ArrayList<Teacher>(); 
	
	public Student(String meno, String priezvisko, int rokNarodenia)
	{
		this.meno = meno;
		this.priezvisko = priezvisko;
		this.rokNarodenia = rokNarodenia;
		

	}
	public double getPriemer()
	{
		return priemer;
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
	
	public void setPriemer()
	{
		priemer = 0;
		for(int i = 0; i<listOfGrades.size(); i++)
		{
			priemer = priemer + listOfGrades.get(i);
		}
		priemer = priemer / listOfGrades.size();
	}
	public boolean stipendium()
	{
		if(priemer < 1.5 && priemer > 0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	public int stipendiumMoney()
	{
		if(stipendium() == true)
		{
			return 200;
		}
		else
		{
			return 0;
		}
	}

}
