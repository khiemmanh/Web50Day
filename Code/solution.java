import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.PriorityQueue;

public class Solution {

	public class Block implements Comparable<Block>{
		int start;
		int end;
		
		public Block(int start, int end) {
			super();
			this.start = start;
			this.end = end;
		}
		
		public int size() {
			return start - end + 1;
		}

		@Override
		public int compareTo(Solution.Block o) {
			return this.start - o.start;
		}
	}
	
	public static class File{
		int start;
		int end;
		int size;
		ArrayList<Block> arrBlocks = new ArrayList<>();
	}
	
	PriorityQueue<Block> queueEmptyBlock;
	HashMap<Integer, File> hashFiles;
	int n;
	
	public void init(int N) {
		int n = N;
		hashFiles = new HashMap<>();
		queueEmptyBlock = new PriorityQueue<>();
		queueEmptyBlock.add(new Block(0, N));
	}
	
	public int  add(int mID, int mSize) {
		if(this.n < mSize) return -1;
		this.n -= mSize;
		File file = new File();
		file.size = mSize;
		
		this.hashFiles.put(mID, file);
		
		while (mSize > 0) {
			Block block = queueEmptyBlock.poll();
			
			// lay ra tat ca cac EmptyBlock lien ke nhau
			while (!queueEmptyBlock.isEmpty()) {
				if(block.end + 1 == queueEmptyBlock.peek().start) {
					block.end = queueEmptyBlock.poll().end;
				}else {
					break;
				}
			}
			
			if(block.size() < mSize) {
				// add new empty block
				queueEmptyBlock.add(new Block(block.start + mSize, block.end));
				block.end = block.start + mSize - 1;
			}
			int sizeBlock = block.end - block.start + 1;
			mSize -= sizeBlock;
			file.arrBlocks.add(block);
		}
		file.start = file.arrBlocks.get(0).start;
		file.end = file.arrBlocks.get(file.arrBlocks.size() - 1).end;
		return file.start;
	}
	
	public int remove(int mId) {
		File file = this.hashFiles.remove(mId);
		this.n += file.size;
		this.queueEmptyBlock.addAll(file.arrBlocks);
		return file.arrBlocks.size();
	}
	
	public int count(int mStart, int mEnd) {
		Block block = new Block(mStart, mEnd);
		int count = 0;
		for (File file : this.hashFiles.values()) {
			if(file.end < mStart || file.start > mEnd) 
				continue;
			if(Collections.binarySearch(file.arrBlocks, block) > 0) 
				++count;
		}
		return count;
	}

}
