
public class Pair {
	int point1;
	int point2;
	
	Pair() {}
	
	Pair(int point1, int point2) {
		this.point1 = point1;
		this.point2 = point2;
	}
	
	public boolean equals(Pair pair) {
		if((this.point1 == pair.point1 && this.point2 == pair.point2) || (this.point1 == pair.point2 && this.point2 == pair.point1)) return true;
		return false;
	}

	@Override
	public String toString() {
		return "Pair [point1=" + point1 + ", point2=" + point2 + "]";
	}
	
	
}
