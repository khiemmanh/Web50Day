import java.util.HashMap;
import java.util.TreeSet;

public class ScheduleMananing4 {

	class Schedule implements Comparable<Schedule>{
		String title;
		int start;
		int end;
		
		public Schedule(String title, int start, int end) {
			super();
			this.title = title;
			this.start = start;
			this.end = end;
		}

		@Override
		public String toString() {
			return "Schedule [title=" + title + ", start=" + start + ", end=" + end + "]";
		}

		@Override
		public int compareTo(ScheduleMananing4.Schedule o) {
			if(o.end < this.start || o.start > this.end)
				return this.start - o.start;
			return 0;
		}
	}
	
	class RESULT {
		char[] mTitle;
		int mStartDay;
		int mEndDay;
	}
	
	private RESULT result = new RESULT();
	
	TreeSet<Schedule> treeSchedule;
	HashMap<String, Schedule> hashSchecdule;
	public void init(int N) {
		treeSchedule = new TreeSet<>();
		hashSchecdule = new HashMap<>();
		treeSchedule.add(new Schedule(null, N + 1, N + 1));
	}
	
	public int addSchedule(char[] mTitle, int mStartDay, int mEndDay, int mForce) {
		String title = String.valueOf(mTitle);
		Schedule schedule = new Schedule(title, mStartDay, mEndDay);
		if(mForce == 1) {
			while (true) {
				Schedule delete = treeSchedule.ceiling(schedule);
				if(delete.compareTo(schedule) != 0) break;
				treeSchedule.remove(delete);
				hashSchecdule.remove(delete.title);
			}
		}
		
		if(treeSchedule.add(schedule)) {
			//TreeSet can't add duplicate value
			hashSchecdule.put(title, schedule);
			return 1;
		}
		return 0;
	}
	
	public RESULT getSchedule(int mDay) {
		Schedule schedule = treeSchedule.ceiling(new Schedule(null, mDay, mDay));
		if(mDay > schedule.start) {
			result.mTitle[0] = '\0';
		} else {
			result.mStartDay = schedule.start;
			result.mEndDay = schedule.end;
			for (int i = 0; i < schedule.title.length(); i++) {
				result.mTitle[i] = schedule.title.charAt(i);
			}
		}
		return result;
	}
	
	public int deleteSchedule(char[] mTitle) {
		Schedule schedule = hashSchecdule.remove(String.valueOf(mTitle));
		if(schedule == null) return 0;
		treeSchedule.remove(schedule);
		return 1;
	}
	
	public int findEmtySchedule() {
		// duyet trau
		int last = 0;
		int rs= 0;
		for (Schedule schedule : treeSchedule) {
			if(schedule.start - last - 1  > rs) {
				rs = schedule.start - last - 1;
			}
			last = schedule.end;
		}
		return rs;
	}
	
	
	
}
