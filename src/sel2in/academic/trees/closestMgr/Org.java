package sel2in.academic.trees.closestMgr;

import java.util.ArrayList;
import java.util.List;

public class Org {
	private List<Person> emps = null;
	public static void main(String[] args) {
		Org app = new Org();
	}
	
	/*CEO Bill has 3 employees reporting to him: {Dom, Samir, Michael}
Dom has three reports { Peter, Bob, Porter}
Samir has no reports {}
Michael has no reports {}
Peter has 2 reports {Milton, Nina}
Bob has no reports {}
Porter has no reports {}
Milton has no reports {}
Nina has no reports {}*/
	
	public Org(){
		this.emps = new ArrayList<>();
		
		Person bill = add("Bill", null);
		
		Person dom = add("Dom", bill);
		Person samir = add("Samir", bill);
		Person michael = add("Michael", bill);		
		
		Person peter = add("Peter", dom);
		Person bob = add("Bob", dom);		
		Person porter = add("Porter", dom);
		
		Person milton = add("Milton", peter);		
		Person nina = add("Nina", peter);
		
		FindClosestMgr f = new FindClosestMgr();
		
		verify(f, milton, nina, peter);
		verify(f, nina, porter, dom);
		
		verify(f, nina, samir, bill);		
		verify(f, peter, nina, peter);
		
		System.out.println("Ran : " + cnt + " checks, errors : " + errs);
		
	
		
	}
	
	int errs,cnt;
	private void verify(FindClosestMgr f, Person e1, Person e2, Person cm) {
		cnt++;
		Person can = null;
		try {
			can = f.closestCommonManager(e1, e2);
		} catch (Exception e) {
			System.out.println("EWErr " + e1 + " & " + e2 + ", "  + e);
			e.printStackTrace();
		}
		if(can != null && cm.equals(can)){
			//okay
		}else{
			errs++;
			System.out.println("For " + e1 + " & " + e2   + ", Got " + can   + ", expectng :" + cm + ".");
		}
		
	}

	Person add(String name, Person mgr){
		Person p = new Person(name, mgr);
		this.emps.add(p);
		if(mgr != null){
			mgr.addReportee(p);
		}
		return p;
	}

}
