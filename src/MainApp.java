
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

public class MainApp {
	
	static Scanner sc = new Scanner(System.in);
	static HashMap<Integer, Student> databazaStudent = new HashMap<Integer, Student>();
	static HashMap<Integer, Teacher> databazaUcitel = new HashMap<Integer, Teacher>();	
	static int volba;
	
	public static void main(String[] args) throws InputMismatchException {
		
		Sort Sort = new Sort();
		SQLOperations SQLOperations = new SQLOperations();
		int idStu = 1;
		int idUc = 1;
		String id;
		String meno = null;
		String priezvisko;
		int rok = 0;
		System.out.println(""
				+ "  __ \\     \\  __ __|   \\     __ )     \\    __  /     \\    \r\n"
				+ "  |   |   _ \\    |    _ \\    __ \\    _ \\      /     _ \\   \r\n"
				+ "  |   |  ___ \\   |   ___ \\   |   |  ___ \\    /     ___ \\  \r\n"
				+ " ____/ _/    _\\ _| _/    _\\ ____/ _/    _\\ ____| _/    _\\");
		System.out.println();
		
		while(true){
			System.out.println("Zvol akciu:");
			System.out.println("1 - Pridat Osobu");
			System.out.println("2 - Pridat Znamku");
			System.out.println("3 - Odobrat Osobu");
			System.out.println("4 - Vyhladat Osobu");
			System.out.println("5 - Zoradit Osoby");
			System.out.println("6 - Naklady");
			System.out.println("7 - Priradzovacie Operacie");
			System.out.println("8 - SQL Operacie");
			System.out.println("9 - Ukoncenie Programu");

			
			volba = podmienkaCislo(volba);
			switch(volba){
			case 1:
				System.out.println("Zvol akciu:");
				System.out.println("1 - Pridat Studenta");
				System.out.println("2 - Pridat Ucitela");
				volba = podmienkaCislo(volba);
				switch(volba){
				case 1:
					if(databazaUcitel.size() > 0){
						try {
						System.out.println("Zadaj meno");
						meno = sc.next();
						System.out.println("Zadaj priezvisko");
						priezvisko = sc.next();
						System.out.println("Zadaj rok narodenia");
						rok = sc.nextInt();
						System.out.println("Zadaj ID Ucitela: ");
						id = sc.next();
						databazaStudent.put(idStu, new Student(meno, priezvisko, rok));
						
						//HashMapy pre abecedny sort
						Sort.sortMenoStudent.put(String.valueOf(idStu), meno);
						Sort.sortPriezviskoStudent.put(String.valueOf(idStu), priezvisko);
						Sort.sortRokStudent.put(String.valueOf(idStu), String.valueOf(rok));
						//priradenie
						databazaUcitel.get(Integer.valueOf(id)).listOfStudents.add(databazaStudent.get(idStu));
						databazaStudent.get(idStu).listOfTeachers.add(databazaUcitel.get(Integer.valueOf(id)));
						idStu++;
						}
						catch(Exception e)
						{
							System.out.println(e.toString());
						}
						}
					else{
						System.out.println("Najprv pridajte Ucitela");}
					break;
				case 2:
					try {
					System.out.println("Zadaj meno");
					meno = sc.next();
					System.out.println("Zadaj priezvisko");
					priezvisko = sc.next();
					System.out.println("Zadaj rok narodenia");
					rok = sc.nextInt();
					databazaUcitel.put(idUc, new Teacher(meno, priezvisko, rok));
					
					//HashMapy pre abecedny sort
					Sort.sortMenoUcitel.put(String.valueOf(idUc), meno);
					Sort.sortPriezviskoUcitel.put(String.valueOf(idUc), priezvisko);
					Sort.sortRokUcitel.put(String.valueOf(idUc), String.valueOf(rok));
					idUc++;
					}
					catch(Exception e)
					{
						System.out.println(e.toString());
					}
					
					break;}
				System.out.println();
				break;
			case 2:
				try {
				System.out.println("Napis ID na zadanie znamky");
				id = sc.next();
				if(databazaStudent.get(Integer.valueOf(id)) != null) {
					System.out.println("Zadaj znamku");
					int znamka = sc.nextInt();
					databazaStudent.get(Integer.valueOf(id)).listOfGrades.add(znamka);
					databazaStudent.get(Integer.valueOf(id)).setPriemer();
					Sort.sortPriemer.put(id, Double.toString(databazaStudent.get(Integer.valueOf(id)).getPriemer()));
				}
				else
					System.out.println("Student sa v databaze nenachadza");
				}
				catch(Exception e)
				{
					System.out.println(e.toString());
				}
				System.out.println();
				break;
			case 3:
				System.out.println("Zvol akciu:");
				System.out.println("1 - Odober Studenta");
				System.out.println("2 - Odober Ucitela");
				volba = podmienkaCislo(volba);
				switch(volba){
				case 1:
					try {
					System.out.println("Napis ID na odobratie");
					id = sc.next();
					Sort.sortPriezviskoStudent.remove(id);
					Sort.sortPriemer.remove(id);					
					databazaStudent.remove(Integer.valueOf(id));}
					catch(Exception e)
					{
						System.out.println(e.toString());
					}
					break;
				case 2:
					try {
					System.out.println("Napis ID na odobratie");
					id = sc.next();
					Sort.sortMenoUcitel.remove(id);
					Sort.sortPriezviskoUcitel.remove(id);
					Sort.sortPocetStudentov.remove(id);
					Sort.sortRokUcitel.remove(id);
					Sort.sortPlatUcitel.remove(id);
					databazaUcitel.remove(Integer.valueOf(id));
					}
					catch(Exception e)
					{
						System.out.println(e.toString());
					}
					break;}
				System.out.println();
				break;
			case 4:
				System.out.println("Zvol akciu:");
				System.out.println("1 - Najst Studenta");
				System.out.println("2 - Najst Ucitela");
				volba = podmienkaCislo(volba);
				switch(volba){
				case 1:
					System.out.println("Napis ID Studenta");
					try {
					id = sc.next();
					if(databazaStudent.containsKey(Integer.valueOf(id))){
						System.out.println("NAJDENY STUDENT");
						System.out.println("===============");
						System.out.println(databazaStudent.get(Integer.valueOf(id)).getMeno()+" "+
										   databazaStudent.get(Integer.valueOf(id)).getPriezvisko()+" "+
										   databazaStudent.get(Integer.valueOf(id)).getRokNarodenia()+" "+
										   databazaStudent.get(Integer.valueOf(id)).getPriemer());
						if(databazaStudent.get(Integer.valueOf(id)).stipendium() == true) {
							System.out.println("STIPENDIUM: ANO"); }
						else
							System.out.println("STIPENDIUM: NIE"); 
						System.out.println("UCITELIA STUDENTA");
						System.out.println("=================");
						for(int i = 0; i<databazaStudent.get(Integer.valueOf(id)).listOfTeachers.size(); i++) {
							System.out.println(i+1 + " " + databazaStudent.get(Integer.valueOf(id)).listOfTeachers.get(i).getPriezvisko()); }
					}
					else
						System.out.println("Student nenajdeny");
					}
					catch(Exception e)
					{
						System.out.println(e.toString());
					}
					break;
				case 2:
					System.out.println("Napis ID Ucitela");
					try {
					id = sc.next();
					if(databazaUcitel.containsKey(Integer.valueOf(id))){
						System.out.println("NAJDENY UCITEL");
						System.out.println("==============");
						System.out.println(databazaUcitel.get(Integer.valueOf(id)).getMeno()+" "+
										   databazaUcitel.get(Integer.valueOf(id)).getPriezvisko()+" "+
										   databazaUcitel.get(Integer.valueOf(id)).getRokNarodenia()+" "+
										   databazaUcitel.get(Integer.valueOf(id)).zarobokCelkovo()*0.776);}
					else
						System.out.println("Ucitel nenajdeny");
					}
					catch(Exception e)
					{
						System.out.println(e.toString());
					}
					break;}
				System.out.println();
				break;
			case 5:
				System.out.println("ZORADIT:");
				System.out.println("1 - Student -> Priezvisko");
				System.out.println("2 - Ucitel -> Priezvisko");
				System.out.println("3 - Studenti -> Priemer");
				System.out.println("4 - Studenti Ucitela -> Priemer");
				System.out.println("5 - Ucitel -> Pocet Studentov");
				volba = podmienkaCislo(volba);
				switch(volba) {
				case 1:
					System.out.println("ID   Meno  Priezvisko   Rok   Priemer");
					System.out.println("=====================================");
					databazaStudent.forEach((k,v)->Sort.sortPriemerStudent.put(String.valueOf(k), String.valueOf(databazaStudent.get(k).getPriemer())));
					Sort.sortStudent();
					break;
				case 2:
					System.out.println("ID   Meno  Priezvisko   Rok   Plat");
					System.out.println("==================================");
					databazaUcitel.forEach((k,v)->Sort.sortPlatUcitel.put(String.valueOf(k), String.valueOf(databazaUcitel.get(k).zarobokCelkovo() * 0.776)));
					Sort.sortTeacher();
					break;
				case 3:
					System.out.println("ID   Priezvisko   Priemer");
					System.out.println("=========================");
					Sort.sortStudentPriemer();
					break;
				case 4:
					try {
						System.out.println("Zadaj ID ucitela");
						id = sc.next();
						int i = 1;
						Sort.sortAvgUcitel_Studenta.clear();
		            	Sort.sortAvgUcitel_StudentaPriezvisko.clear();
						for (Student temp : databazaUcitel.get(Integer.valueOf(id)).listOfStudents) {
			            	Sort.sortAvgUcitel_Studenta.put(String.valueOf(i), String.valueOf(temp.getPriemer())); 
			            	Sort.sortAvgUcitel_StudentaPriezvisko.put(String.valueOf(i), temp.getPriezvisko());
			            	i++;
			        	}
						System.out.println("No.   Priezvisko   Priemer");
						System.out.println("=========================");
						Sort.sortAvgUcitelovho_Studenta();
					}
					catch(NullPointerException e) {
						System.out.println("Ucitel sa nenachadza v databaze");
					}
					catch(NumberFormatException e) {
						System.out.println("Chyba: Nespravny format ID!");
					}
					break;
				case 5:
					databazaUcitel.forEach((k,v)-> Sort.sortPocetStudentov.put(String.valueOf(k), String.valueOf(v.listOfStudents.size())));
					Sort.sortPocetStudentov();
					break;
				}
				System.out.println();
				break;
			case 6:
				double stipendium = 0;
				double zarobok = 0;
				for (Map.Entry<Integer, Student> pair : databazaStudent.entrySet()) {
				    stipendium = stipendium + databazaStudent.get(pair.getKey()).stipendiumMoney();
				}
				for (Map.Entry<Integer, Teacher> pair : databazaUcitel.entrySet()) {
				    zarobok = zarobok + databazaUcitel.get(pair.getKey()).zarobokCelkovo();
				}
				System.out.println("=======NAKLADY=======");
				System.out.println("Stipendium: " + stipendium);
				System.out.println("Mzdy v hrubom: " + zarobok);
				System.out.println("=====================");
				System.out.println();
				break;
			case 7:
				System.out.println("PRRIDOVACIE OPERACIE:");
				System.out.println("1 - Zobrazit Studentov Ucitela");
				System.out.println("2 - Odobrat Studenta Ucitelovi");
				System.out.println("3 - Priradit Ucitela -> Studentovi");
				volba = podmienkaCislo(volba);
				switch(volba) {
				case 1:
					try {
					System.out.println("Napis ID Ucitela");
					id = sc.next();
					if(databazaUcitel.get(Integer.valueOf(id)) != null) {
						System.out.println("No. Priezvisko");
						System.out.println("==============");
						for(int i = 0; i<databazaUcitel.get(Integer.valueOf(id)).listOfStudents.size(); i++) {
							System.out.println(i+1 + " " + databazaUcitel.get(Integer.valueOf(id)).listOfStudents.get(i).getPriezvisko());
						}
					}
					else {
						System.out.println("Ucitel sa v databaze nenachadza");}
					}
					catch(Exception e)
					{
						System.out.println(e.toString());
					}
					break;
				case 2:
					try {
					System.out.println("Napis ID Ucitela");
					id = sc.next();
					if(databazaUcitel.get(Integer.valueOf(id)) != null) {
						System.out.println("No. Priezvisko");
						System.out.println("==============");
						for(int i = 0; i<databazaUcitel.get(Integer.valueOf(id)).listOfStudents.size(); i++) {
							System.out.println(i+1 + " " + databazaUcitel.get(Integer.valueOf(id)).listOfStudents.get(i).getPriezvisko());
						}
						try {
						System.out.println("Napis poradie studenta na odobratie");
						int idOdober = sc.nextInt();
						databazaUcitel.get(Integer.valueOf(id)).listOfStudents.remove(idOdober-1);
						}
						catch (IndexOutOfBoundsException e){
							System.out.println("Taky Student neexistuje");
						}
					}
					else {
						System.out.println("Ucitel sa v databaze nenachadza");
					}
					}
					catch(Exception e)
					{
						System.out.println(e.toString());
					}
					break;
				case 3:
					try {
					System.out.println("Napis ID Studenta");
					id = sc.next();
					System.out.println("Napis ID Ucitela na priradenie");
					int idPridat = sc.nextInt();
					if(databazaUcitel.get(idPridat) != null && databazaStudent.get(Integer.valueOf(id)) != null) {
					databazaUcitel.get(idPridat).listOfStudents.add(databazaStudent.get(Integer.valueOf(id)));
					databazaStudent.get(Integer.valueOf(id)).listOfTeachers.add(databazaUcitel.get(idPridat));}
					else
						System.out.println("Student alebo Ucitel sa nenachadzaju v databaze");
					}
					catch(Exception e)
					{
						System.out.println(e.toString());
					}
					break;
				}
				System.out.println();
				break;
			case 8:
				System.out.println("SQL OPERACIE:");
				System.out.println("1 - Vytvorit SQL databazu");
				System.out.println("2 - Vlozit data do SQL");
				System.out.println("3 - Nacitat data z SQL");
				System.out.println("4 - Vymazat osobu z SQL");
				System.out.println("5 - Vyhladat osobu z SQL");
				volba = podmienkaCislo(volba);
				switch(volba) {
				case 1:
					SQLOperations.loginFromFile();
					SQLOperations.createStudentTable();
					SQLOperations.createTeacherTable();
					break;
				case 2:
					SQLOperations.loginFromFile();
					for (int i =0; i<100; i++) {
						SQLOperations.deleteTeacher(Integer.valueOf(i));
					}
					for (int i =0; i<100; i++) {
						SQLOperations.deleteStudent(Integer.valueOf(i));
					}
					databazaUcitel.forEach((k,v)->SQLOperations.addTeacher(k, v.getMeno(), v.getPriezvisko(), v.getRokNarodenia(), (v.zarobokCelkovo() * 0.776)));
					databazaStudent.forEach((k,v)->SQLOperations.addStudent(k, v.getMeno(), v.getPriezvisko(), v.getRokNarodenia(), v.getPriemer()));
					break;
				case 3:
					SQLOperations.loginFromFile();
					System.out.println("======UCITELIA======");
					SQLOperations.listTeacherTable();
					System.out.println("======STUDENTI======");
					SQLOperations.listStudentTable();
					break;
				case 4:
					SQLOperations.loginFromFile();
					System.out.println("1 - Vymazat Studenta z SQL");
					System.out.println("2 - Vymazat Ucitela z SQL");
					volba = podmienkaCislo(volba);
					switch(volba) {
					case 1:
						System.out.println("Vyber cislo Studenta na vymazanie");
						id = sc.next();
						SQLOperations.deleteStudent(Integer.valueOf(id));
						break;
					case 2:
						System.out.println("Vyber cislo Ucitela na vymazanie");
						id = sc.next();
						SQLOperations.deleteTeacher(Integer.valueOf(id));
						break;
					}
					break;
				case 5:
					SQLOperations.loginFromFile();
					System.out.println("1 - Vyhladat Studenta z SQL");
					System.out.println("2 - Vyhladat Ucitela z SQL");
					volba = podmienkaCislo(volba);
					switch(volba) {
					case 1:
						System.out.println("Vyber cislo Studenta na vyhladanie");
						id = sc.next();
						SQLOperations.findStudent(Integer.valueOf(id));
						break;
					case 2:
						System.out.println("Vyber cislo Ucitela na vyhladanie");
						id = sc.next();
						SQLOperations.findTeacher(Integer.valueOf(id));
						break;
					}
					break;
				}
				System.out.println();
				break;
			case 9:
				sc.close();
				System.exit(0);
				break;
			case 12:
				SQLOperations.loginFromFile();
				SQLOperations.createTeacherTable();
				SQLOperations.createStudentTable();
				break;
			}
		}
	}
	public static int podmienkaCislo(int cislo)
	{
		try {
			cislo = sc.nextInt();
		}
		catch(Exception e)
		{
			System.out.println("Chyba: Nespravny format!");
			sc.next();	
		}
		return cislo;
	}
}