package sel2in.academic.trees.closestMgr;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Person {
	
	private List<Person> reportees = null;
	private Person mgr;
	private boolean isCeo = false;
	private String name;
	
	public Person(String name, Person mgr, Person... reps) {
		super();
		this.name = name;
		if(reps.length > 0){
			this.reportees = new ArrayList<>();
			for(Person r : reps){
				this.reportees.add(r);
			}
		}
		this.mgr = mgr;
		if(mgr == null)this.isCeo = true;
	}
	
	public boolean isMgr(){
		return this.reportees != null && this.reportees.size() > 0; 
	}

	public List<Person> getReportees() {
		return this.reportees;
	}

	public void setReportees(List<Person> reportees) {
		this.reportees = reportees;
	}
	
	
	@SuppressWarnings("unqualified-field-access")
	public Person addReportee(Person reportee) {
		if(reportees == null)  reportees = new ArrayList<>();
		 reportees.add(reportee);
		 return this;
	}

	@SuppressWarnings("unqualified-field-access")
	public Person getMgr() {
		return mgr;
	}

	public void setMgr(Person mgr) {
		this.mgr = mgr;
	}

	public boolean isCeo() {
		return this.isCeo;
	}

	public void setCeo(boolean isCeo) {
		this.isCeo = isCeo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return " [" + name + "]";//"Person [name=" + name + "]";
	}
	
	
	
	

}
