import java.util.ArrayList;

public class Visit {
	
	private City c;
	private ArrayList<City> nonVisited = new ArrayList<City>();
	
	public Visit(City c) {
		this.c = c;
		this.nonVisited = new ArrayList<City>(City.listCity);
		if (!this.c.equals(City.listCity.get(0)))
			this.nonVisited.remove(this.c);
	}
	
	public Visit(City c, ArrayList<City> nonVisited) {
		this.c = c;
		this.nonVisited = nonVisited;
	}
	
	public City getC() {
		return this.c;
	}
	
	public ArrayList<City> getNV() {
		return this.nonVisited;
	}
	
	public Visit goTo(City c) {
		if ((this.nonVisited.size() != 1 && c.equals(City.listCity.get(0))) || !this.nonVisited.contains(c) || this.c.equals(c))
			return null;
		ArrayList<City> nonVisited = new ArrayList<City>(this.nonVisited);
		nonVisited.remove(c);
		return new Visit(c, nonVisited);
	}
	
	public boolean isSolved() {
		return (this.c.equals(City.listCity.get(0)) && this.nonVisited.isEmpty());
	}
	
	public double heuristic() {
		ArrayList<City> lcity = new ArrayList<City>(this.nonVisited);
		ArrayList<City> prim = new ArrayList<City>();
		prim.add(this.c);
		if (lcity.get(0).equals(City.listCity.get(0)))
			lcity.remove(0);
		double somme = 0;
		while(!lcity.isEmpty()) {
			double min;
			int imin = 0;
			min = prim.get(0).distance(lcity.get(0));
			for (int i = 0; i < prim.size(); i++) {
				for (int j = 0; j < lcity.size(); j++) {
					double d = prim.get(i).distance(lcity.get(j));
					if (d < min) {
						min = d;
						imin = j;
					}
				}
			}
			prim.add(lcity.get(imin));
			lcity.remove(imin);
			somme += min;
		}
		return somme;
	}
	
	@Override
	public Object clone() {
		Visit copy = new Visit((City) this.c.clone(), new ArrayList<City>(this.nonVisited));
		return copy;
	}
	
	@Override
	public String toString() {
		String s = this.c.toString() + " {";
		for (City c : this.nonVisited) {
			s += c.toString() + ", ";
		}
		if (!this.nonVisited.isEmpty())
			s = s.substring(0,s.length() - 2);
		s += "}";
		return s;
	}
	
}
