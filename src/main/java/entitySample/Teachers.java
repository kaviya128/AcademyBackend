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
@Table(name = "Teachers")
public class Teachers {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Teacher_Id")
	private int teacherId;
	
	@Column(name ="name")
	private String name;
	
	@Column(name = "experience")
	private int experience;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="Teacher_Id", nullable = false)
	private List<Subjects> subject = new ArrayList<Subjects>();

	public Teachers() {
		super();
	}
	

	public Teachers(int teacherId, String name, int experience) {
		super();
		this.teacherId = teacherId;
		this.name = name;
		this.experience = experience;
	}


	public Teachers(String name, int experience) {
		super();
		this.name = name;
		this.experience = experience;
	}


	public Teachers(String name, int experience, List<Subjects> subject) {
		super();
		this.name = name;
		this.experience = experience;
	    this.subject = subject;
	}

	public int getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(int teacherId) {
		this.teacherId = teacherId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getExperience() {
		return experience;
	}

	public void setExperience(int experience) {
		this.experience = experience;
	}

	

	public List<Subjects> getSubject() {
		return subject;
	}

	public void setSubject(List<Subjects> subject) {
		this.subject = subject;
	}

	@Override
	public String toString() {
		return "Teachers [teacherId=" + teacherId + ", name=" + name + ", experience=" + experience + ","
				+ ", subject=" + subject + "]";
	}
	
	
	

}
