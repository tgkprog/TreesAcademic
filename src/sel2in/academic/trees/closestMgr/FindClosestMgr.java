package sel2in.academic.trees.closestMgr;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FindClosestMgr {
	/*
	 * could use Person it self but in this contrived example we know name is
	 * unique, using that as key
	 */
	Map<String, List<Person>> chains = new HashMap<>();
	boolean useCache = true;

	// closestCommonManager(Milton, Nina) = Peter
	public Person closestCommonManager(Person e1, Person e2) {
		// no need to process or maintain list of mgrs of CEO, short circuit
		// here
		if (e1.isCeo())
			return e1;
		if (e2.isCeo())
			return e2;

		List<Person> mgrsE1 = getMgrChain(e1, false);
		List<Person> mgrsE1a = mgrsE1;
		List<Person> mgrsE2 = getMgrChain(e2, false);
		List<Person> tmpMgrs = null;

		int from1 = 0;// imediate mgr to CEO
		int from2 = 0;
		int till1 = mgrsE1.size() - 1;
		int till2 = mgrsE2.size() - 1;
		if (till1 > till2) {
			tmpMgrs = mgrsE1;
			mgrsE1 = mgrsE2;
			mgrsE2 = tmpMgrs;
			till1 = mgrsE1.size() - 1;
		}
		for (Person p : mgrsE1) {
			if (mgrsE2.contains(p)) {
				return p;
			}
		}
		//return CEO
		if(mgrsE1a.size() > 0){
			return mgrsE1a.get(mgrsE1a.size() - 1).getMgr();
		}
		return e1.getMgr();
	}

	private List<Person> getMgrChain(Person e, boolean includeCeo) {

		if (useCache && chains.containsKey(e.getName())) {
			return chains.get(e.getName());
		}
		List<Person> mgrs = new ArrayList<>();
		if (e.isMgr())
			mgrs.add(e);
		Person m = e.getMgr();
		do {
			if ((m.isCeo() && includeCeo == false) == false) {
				mgrs.add(m);
			}
			if (m.isCeo())
				break;
			m = m.getMgr();
		} while (true);
		if (useCache) {
			chains.put(e.getName(), mgrs);
		}
		return mgrs;
	}

}
