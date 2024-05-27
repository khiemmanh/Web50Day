import java.util.TreeSet;

public class TestTreeSet {
	
	static class Schedule implements Comparable<Schedule>{
		int start;
		int end;
		public Schedule(int start, int end) {
			super();
			this.start = start;
			this.end = end;
		}
		@Override
		public int compareTo(TestTreeSet.Schedule o) {
//			if(o.start > this.end || o.end < this.start)
//				return this.start - o.start;
//			return 0;
			return this.start - o.start;
		}
		@Override
		public String toString() {
			return "Schedule [start=" + start + ", end=" + end + "]";
		}
	}
	
	
	public static void main(String[] args) {
		TreeSet<Schedule> tree = new TreeSet<>();
		tree.add(new Schedule(4, 7));
		tree.add(new Schedule(4, 7));
		tree.add(new Schedule(0, 2));
		tree.add(new Schedule(8, 10));
		
		
		Schedule schedule = new Schedule(5, 7);
		Schedule result = tree.ceiling(schedule);
		System.out.println(tree);
		
	}
}
