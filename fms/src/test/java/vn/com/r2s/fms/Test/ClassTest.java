package vn.com.r2s.fms.Test;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import vn.com.r2s.fms.repository.ClassRepository;
import vn.com.r2s.fms.service.ClassService;
import vn.com.r2s.fms.entity.Class;

@SpringBootTest
public class ClassTest {
	@Autowired
	private ClassRepository classRepository;

	@Autowired
	private ClassService classService;
	
	@Test
	public void TestDelete() {	
		classRepository.deleteById(5);
	}
	
	@Test
	public void TestInsert() {
		Class class1 = new Class();
	
		class1.setEndTime(LocalDate.parse("2018-12-27"));
		class1.setIsDeleted(false);
		class1.setClassName("WWW");
		class1.setStartTime(LocalDate.parse("2018-12-27"));
		class1.setCapacity(10);
		classService.saveClass(class1);
	}
	
	@Test
	public void TestUpdate() {
		Class classEdit = new Class();
		
		classEdit.setClassID(7);		
		classEdit.setEndTime(LocalDate.parse("2018-12-27"));
		classEdit.setIsDeleted(false);
		classEdit.setClassName("WWW2");
		classEdit.setStartTime(LocalDate.parse("2018-12-27"));
		classEdit.setCapacity(10);
		classService.saveClass(classEdit);
	}
}
