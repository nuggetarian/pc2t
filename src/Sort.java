import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Sort {
	
	//static HashMap<String, String> sortPriezviskoUcitel = new HashMap<String, String>();
	HashMap<String, String> sortPriemer = new HashMap<String, String>();
	HashMap<String, String> sortAvgUcitel_Studenta = new HashMap<String, String>();
	HashMap<String, String> sortAvgUcitel_StudentaPriezvisko = new HashMap<String, String>();
	
	//Abecedne, Student
	HashMap<String, String> sortMenoStudent = new HashMap<String, String>();
	HashMap<String, String> sortPriezviskoStudent = new HashMap<String, String>();
	HashMap<String, String> sortRokStudent = new HashMap<String, String>();
	HashMap<String, String> sortPriemerStudent = new HashMap<String, String>();
	
	//Abecedne, Ucitel
	HashMap<String, String> sortMenoUcitel = new HashMap<String, String>();
	HashMap<String, String> sortPriezviskoUcitel = new HashMap<String, String>();
	HashMap<String, String> sortRokUcitel = new HashMap<String, String>();
	HashMap<String, String> sortPlatUcitel = new HashMap<String, String>();
	
	//Pocet Studentov, Ucitel
	HashMap<String, String> sortPocetStudentov = new HashMap<String, String>();

	
	public void sortStudent()
	{
		
		LinkedHashMap<String, String> sorted = new LinkedHashMap<>();
        ArrayList<String> list = new ArrayList<>();
        //ziskame values z sortPriezviskoStudent a vlozime ich do noveho listu
        for (Map.Entry<String, String> entry : sortPriezviskoStudent.entrySet()) {
            list.add(entry.getValue());
        }
        //compareneme cez Collections.sort tento novy list
        Collections.sort(list, new Comparator<String>() {
            public int compare(String str, String str1) {
                return (str).compareTo(str1);
            }
        });
        //for loop kde list str je vzdy to co je v iteracii listu
        for (String str : list) {
        	//vnorene for s entrysetom
            for (Entry<String, String> entry : sortPriezviskoStudent.entrySet()) {
            	//ak sa values entrySetu a sortnuteho stringu zhoduju, ulozia sa spolu so spravnym korespondujucim klucom do novej hashmapy sorted
                if (entry.getValue().equals(str)) {
                    sorted.put(entry.getKey(), str);
                }
            }
        }
        sorted.forEach((k,v)->System.out.println(k +"    "+ sortMenoStudent.get(k) + " " + v + "    " + sortRokStudent.get(k) + "    " + sortPriemerStudent.get(k)));
	}
	
	public void sortStudentPriemer()
	{
		LinkedHashMap<String, String> sorted = new LinkedHashMap<>();
        ArrayList<String> list = new ArrayList<>();
        for (Map.Entry<String, String> entry : sortPriemer.entrySet()) {
            list.add(entry.getValue());
        }
        Collections.sort(list, new Comparator<String>() {
            public int compare(String str, String str1) {
                return (str).compareTo(str1);
            }
        });
        for (String str : list) {
            for (Entry<String, String> entry : sortPriemer.entrySet()) {
                if (entry.getValue().equals(str)) {
                    sorted.put(entry.getKey(), str);
                }
            }
        }
        sorted.forEach((k,v)->System.out.println(k +"  "+ sortPriezviskoStudent.get(k) +"  "+ v));
	}
	
	public void sortTeacher()
	{
		LinkedHashMap<String, String> sorted = new LinkedHashMap<>();
        ArrayList<String> list = new ArrayList<>();
        for (Map.Entry<String, String> entry : sortPriezviskoUcitel.entrySet()) {
            list.add(entry.getValue());
        }
        Collections.sort(list, new Comparator<String>() {
            public int compare(String str, String str1) {
                return (str).compareTo(str1);
            }
        });
        for (String str : list) {
            for (Entry<String, String> entry : sortPriezviskoUcitel.entrySet()) {
                if (entry.getValue().equals(str)) {
                    sorted.put(entry.getKey(), str);
                }
            }
        }
        sorted.forEach((k,v)->System.out.println(k +"    " + sortMenoUcitel.get(k) + " " + v + "    " + sortRokUcitel.get(k) + "    " + sortPlatUcitel.get(k)));
	}
	public void sortAvgUcitelovho_Studenta()
	{
		LinkedHashMap<String, String> sorted = new LinkedHashMap<>();
        ArrayList<String> list = new ArrayList<>();
        for (Map.Entry<String, String> entry : sortAvgUcitel_Studenta.entrySet()) {
            list.add(entry.getValue());
        }
        Collections.sort(list, new Comparator<String>() {
            public int compare(String str, String str1) {
                return (str).compareTo(str1);
            }
        });
        for (String str : list) {
            for (Entry<String, String> entry : sortAvgUcitel_Studenta.entrySet()) {
                if (entry.getValue().equals(str)) {
                    sorted.put(entry.getKey(), str);
                }
            }
        }
        sorted.forEach((k,v)->System.out.println(k +"  "+  sortAvgUcitel_StudentaPriezvisko.get(k) +"  "+ v));
	}
	public void sortPocetStudentov()
	{
		
		LinkedHashMap<String, String> sorted = new LinkedHashMap<>();
        ArrayList<String> list = new ArrayList<>();
        for (Map.Entry<String, String> entry : sortPocetStudentov.entrySet()) {
            list.add(entry.getValue());
        }
        Collections.sort(list, new Comparator<String>() {
            public int compare(String str, String str1) {
                return (str).compareTo(str1);
            }
        });
        for (String str : list) {
            for (Entry<String, String> entry : sortPocetStudentov.entrySet()) {
                if (entry.getValue().equals(str)) {
                    sorted.put(entry.getKey(), str);
                }
            }
        }
        ArrayList<String> numOfStudents = new ArrayList<>();
        sorted.forEach((k,v)-> numOfStudents.add(k + "   " + sortMenoUcitel.get(k) + "  " + sortPriezviskoUcitel.get(k) + "   " + v));
        System.out.println("ID   Meno  Priezvisko  No. Students");
		System.out.println("===================================");
        for (int i=numOfStudents.size()-1; i>=0; i--) {
            System.out.println(numOfStudents.get(i));
        }
	}
}
