package entitySample;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Classes")
public class Classes {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Class_Id")
	private int classId;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "section")
	private String section;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="Class_Id", nullable = false)
	private List<Subjects> subject = new ArrayList<Subjects>();
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="Class_Id", nullable = false)
	private List<Teachers> teacher = new ArrayList<Teachers>();
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="Class_Id", nullable = false)
	private List<Student> student = new ArrayList<Student>();

	public Classes() {
		super();
	}

	public Classes(String name, String section, List<Subjects> subject, List<Teachers> teacher, 
			List<Student> student) {
		super();
		this.name = name;
		this.section = section;
		this.subject = subject;
		this.teacher = teacher;
		this.student = student;
	}

	
	public Classes(int classId, String name, String section) {
		super();
		this.classId = classId;
		this.name = name;
		this.section = section;
	}

	public Classes(String name, String section) {
		super();
		this.name = name;
		this.section = section;
	}

	public int getClassId() {
		return classId;
	}

	public void setClassId(int classId) {
		this.classId = classId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSection() {
		return section;
	}

	public void setSection(String section) {
		this.section = section;
	}

	public List<Subjects> getSubject() {
		return subject;
	}

	public void setSubject(List<Subjects> subject) {
		this.subject = subject;
	}

	public List<Teachers> getTeacher() {
		return teacher;
	}

	public void setTeacher(List<Teachers> teacher) {
		this.teacher = teacher;
	}

	public List<Student> getStudent() {
		return student;
	}

	public void setStudent(List<Student> student) {
		this.student = student;
	}

	@Override
	public String toString() {
		return "Classes [classId=" + classId + ", name=" + name + ", section=" + section + ", subject=" + subject + ", teacher="
				+ teacher + ", student=" + student + "]";
	}
	

	
	
	
	
	
	
	
	

}
