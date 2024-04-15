import java.util.LinkedList;
import java.util.PriorityQueue;

class UseSolution {

	class Cell {
		int x;
		int y;
		public Cell(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
		@Override
		public String toString() {
			return "Cell [x=" + x + ", y=" + y + "]";
		}
	}
	
	class Gate implements Comparable<Gate>{
		int mGateID;
		int deriction;
		public Gate(int mGateID, int deriction) {
			super();
			this.mGateID = mGateID;
			this.deriction = deriction;
		}
		@Override
		public int compareTo(UseSolution.Gate o) {
			return this.deriction - o.deriction;
		}
		@Override
		public String toString() {
			return "Gate [mGateID=" + mGateID + ", deriction=" + deriction + "]";
		}
	}
	
	int[] dx = {0, 0, 1, -1};
	int[] dy = {1, -1, 0, 0};
	LinkedList<Gate>[] nearGates;
	int[][] map;
	int[][] mapGate;
	int mMaxStamina;
	int N;
	
	void init(int N, int mMaxStamina, int mMap[][]) {
		this.N = N;
		this.map = new int[351][351];
		this.mapGate = new int[351][351];
		this.mMaxStamina = mMaxStamina;
		this.nearGates = new LinkedList[201];
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				this.map[i][j] = mMap[i][j];
			}
		}
	}
	
	void addGate(int mGateID, int mRow, int mCol) {
		int[][] visit = new int[351][351];
		LinkedList<Cell> queue = new LinkedList<UseSolution.Cell>();
		
		visit[mRow][mCol] = 1;
		queue.add(new Cell(mRow, mCol));
		
		while (!queue.isEmpty()) {
			Cell currentCell = queue.pop();
			int x = currentCell.x;
			int y = currentCell.y;
			
			if(mapGate[x][y] > 0) { // Check Gate
				int nextGateID = mapGate[x][y];
				nearGates[nextGateID].add(new Gate(mGateID, visit[x][y] - 1));
				nearGates[mGateID].add(new Gate(nextGateID, visit[x][y] - 1));
			}
			
			if(visit[x][y] == mMaxStamina + 1) {
				continue;
			}
			
			for (int i = 0; i < 4; i++) {
				int x1 = x + dx[i];
				int y1 = y + dy[i];
				
				if(x1 >= 0 && y1 >= 0 && x1 < N && y1 < N && map[x1][y1] == 0 && visit[x1][y1] == 0) {
					visit[x1][y1] = visit[x][y] + 1;
					queue.add(new Cell(x1, y1));
				}
			}
		}
	}
	
	void removeGate(int mGateID) {
		LinkedList<Gate> listGates = nearGates[mGateID];
		for (Gate gate : listGates) {
			LinkedList<Gate> list = nearGates[gate.mGateID];
			for (int i = 0; i < list.size(); i++) {
				if(list.get(i).mGateID == mGateID) {
					list.remove(i);
				}
			}
		}
		//remove gateID in mapGate
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(mapGate[i][j] == mGateID) {
					mapGate[i][j] = 0;
				}
			}
		}
	}
	
	int getMinTime(int mStartGateID, int mEndGateID) {
		PriorityQueue<Gate> queue = new PriorityQueue<UseSolution.Gate>();
		int[] checkDerictions = new int[201];
		
		checkDerictions[mStartGateID] = 1;
		
		queue.add(new Gate(mStartGateID, 0));
		checkDerictions[mStartGateID] = 1;
		
		while (!queue.isEmpty()) {
			Gate curGate = queue.poll();
			LinkedList<Gate> listGatesOfCurGate = nearGates[curGate.mGateID];
			if(curGate.mGateID == mEndGateID) {
				return curGate.deriction;
			}
			for (Gate nextGate : listGatesOfCurGate) {
				int nextGateID = nextGate.mGateID;
				if(checkDerictions[nextGateID] == 0 || checkDerictions[nextGateID] > curGate.deriction + nextGate.deriction) {
					checkDerictions[nextGateID] = curGate.deriction + nextGate.deriction;
					queue.add(new Gate(nextGate.mGateID, checkDerictions[nextGateID]));
				}
			}
		}
		return 0;
	}
	
	
}
