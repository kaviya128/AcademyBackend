package entitySample;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name ="Subjects")
public class Subjects {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Subject_Id")
	private int subjectId;
	
	@Column(name = "name")
	private String name;
	
	
	public Subjects() {
		super();
	}
	
	

	public Subjects(int subjectId, String name) {
		super();
		this.subjectId = subjectId;
		this.name = name;
	}



	public Subjects(String name) {
		super();
		this.name = name;
		
	}

	public int getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Subjects [subjectId=" + subjectId + ", name=" + name + "]";
	}
	
	
	
	

}
