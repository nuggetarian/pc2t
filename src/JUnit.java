import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class JUnit {

	@Test
	void test() {
		Teacher test = new Teacher("name", "lastname", 0000);
		test.listOfStudents.add(new Student ("name", "lastname", 0000));
		test.listOfStudents.add(new Student ("name", "lastname", 0000));
		int output = test.zarobok();
		assertEquals(1100, output);
	}

}
