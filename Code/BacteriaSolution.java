import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.TreeSet;

public class BacteriaSolution {
	
	class TypeBacteria{
		int mHalfTime;
		int mCnt;
		
		public TypeBacteria(int mHalfTime, int mCnt) {
			super();
			this.mHalfTime = mHalfTime;
			this.mCnt = mCnt;
		}

		@Override
		public String toString() {
			return "TypeBacteria [mHalfTime=" + mHalfTime + ", mCnt=" + mCnt + "]";
		}
	}
	
	class Bacteria{
		int startTime;
		int endTime;
		String name;
		int mLife;
		int mCnt;
		public Bacteria(int startTime, int endTime, String name, int mLife, int mCnt) {
			super();
			this.startTime = startTime;
			this.endTime = endTime;
			this.name = name;
			this.mLife = mLife;
			this.mCnt = mCnt;
		}
		@Override
		public String toString() {
			return "Bacteria [startTime=" + startTime + ", endTime=" + endTime + ", name=" + name + ", mLife=" + mLife
					+ ", mCnt=" + mCnt + "]";
		}
	}
	
	PriorityQueue<Bacteria> heap = new PriorityQueue<BacteriaSolution.Bacteria>(new Comparator<Bacteria>() {

		@Override
		public int compare(BacteriaSolution.Bacteria o1, BacteriaSolution.Bacteria o2) {
			if(o1.endTime == o1.endTime) {
				return o1.startTime - o2.startTime;
			}
			return o1.endTime - o2.endTime;
		}
	});
	
	TreeSet<Bacteria> treeLifeForce = new TreeSet<BacteriaSolution.Bacteria>(new Comparator<Bacteria>() {

		@Override
		public int compare(BacteriaSolution.Bacteria o1, BacteriaSolution.Bacteria o2) {
			if(o1.mLife == o2.mLife) {
				return o1.startTime - o2.startTime;
			}
			return o1.mLife - o2.mLife;
		}
	});
	
	HashMap<String, TypeBacteria> hashType;
	
	void init(int N, char bNameList[][], int mHalfTime[]) {
		heap.clear();
		treeLifeForce.clear();
		hashType = new HashMap<>();
		
		for (int i = 0; i < N; i++) {
			String name = String.valueOf(bNameList[i]);
			TypeBacteria typeBacteria = new TypeBacteria(mHalfTime[i], 0);
			hashType.put(name, typeBacteria);
		}
		
	}
	
	void update(int tStamp) {
		while (!heap.isEmpty()) {
			Bacteria bacteria = heap.peek();
			if(bacteria.mCnt < 0) {
				treeLifeForce.remove(bacteria);
				heap.poll();
				continue;
			}
			
			if(bacteria.endTime > tStamp) {
				break;
			}
			
			bacteria = heap.poll();
			TypeBacteria type = hashType.get(bacteria.name);
			type.mCnt -= bacteria.mCnt;
			treeLifeForce.remove(bacteria);
			
			//update
			bacteria.mLife = bacteria.mLife / 2;
			if(bacteria.mLife >= 10) {
				bacteria.endTime += type.mHalfTime;
				treeLifeForce.add(bacteria);
				heap.add(bacteria);
				type.mCnt += bacteria.mCnt;
			}
		}
	}
	
	void addBacteria(int tStamp, char bName[], int mLife, int mCnt) {
		String name = String.valueOf(bName);
		TypeBacteria type = hashType.get(name);
		
		update(tStamp);
		
		Bacteria bacteria = new Bacteria(tStamp, tStamp + type.mHalfTime, name, mLife, mCnt);
		heap.add(bacteria);
		treeLifeForce.add(bacteria);
		type.mCnt += mCnt;
	
	}
	
	int takeOut(int tStamp, int mCnt) {
		update(tStamp);
		int result = 0;
		// remove du mCnt, nen can while
		while (!treeLifeForce.isEmpty()) {
			Bacteria bacteria = treeLifeForce.first();
			if(bacteria.mCnt < 0) {
				treeLifeForce.remove(bacteria);
				heap.remove(bacteria);
				continue;
			}
			
			TypeBacteria type = hashType.get(bacteria.name);
			if(bacteria.mCnt <= mCnt) {
				heap.remove(bacteria);
				treeLifeForce.remove(bacteria);
				mCnt -= bacteria.mCnt;
				type.mCnt -= bacteria.mCnt;
				result += bacteria.mCnt*bacteria.mLife;
				if(mCnt == 0) break;
			}else {
				bacteria.mCnt -= mCnt;
				type.mCnt -= mCnt;
				result += mCnt*bacteria.mCnt;
				break;
			}
		}
		
		return result;
	}
	
	int checkBacteria(int tStamp, char bName[]) {
		update(tStamp);
		String name = String.valueOf(bName);
		return hashType.get(name).mCnt;
	}

}
