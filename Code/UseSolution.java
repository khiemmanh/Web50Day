import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.TreeSet;

public class UseSolution {
	
	class Block {
		int start;
		int end;
		public Block(int start, int end) {
			super();
			this.start = start;
			this.end = end;
		}
	}
	
	HashMap<Integer, ArrayList<Block>> hashFiles;
	TreeSet<Block> emtryBlocks;
	int n;
	int coundEmtryMemory;
	
	public void init(int N) {
		coundEmtryMemory = n = N;
		hashFiles = new HashMap<Integer, ArrayList<Block>>();
		emtryBlocks = new TreeSet<UseSolution.Block>(new Comparator<Block>() {

			@Override
			public int compare(UseSolution.Block o1, UseSolution.Block o2) {
				return o1.start - o2.start;
			}
		});
		emtryBlocks.add(new Block(1, N));
	}
	
	public int add(int mId, int mSize) {
		if (coundEmtryMemory >= mSize) {
			coundEmtryMemory -= mSize;
			
			ArrayList<Block> listEmptyOfFileID = hashFiles.get(mId);
			if(listEmptyOfFileID == null) {
				listEmptyOfFileID = new ArrayList<UseSolution.Block>();
				hashFiles.put(mId, listEmptyOfFileID);
			}
			
			while (mSize > 0) {
				Block currblock = emtryBlocks.pollFirst();
				if(currblock.end - currblock.start + 1 > mSize) {
					listEmptyOfFileID.add(new Block(currblock.start, currblock.start + mSize - 1));

					emtryBlocks.add(new Block(currblock.start + mSize, currblock.end));
					mSize = 0;
					break;
				}
				mSize -= currblock.end - currblock.start + 1;
				listEmptyOfFileID.add(currblock);
			}
			return listEmptyOfFileID.get(0).start;
		}
		return -1; // return lowest address
	}
	
	public int remove(int mId) {
		ArrayList<Block> listBlocksOfFileID = hashFiles.get(mId);
		if(listBlocksOfFileID == null || listBlocksOfFileID.size() < 0) {
			return -1;
		}
		
		hashFiles.remove(mId);
		for (Block block : listBlocksOfFileID) {
			coundEmtryMemory += block.end - block.start + 1;
			
			Block lower = emtryBlocks.lower(block);
			Block higher = emtryBlocks.higher(block);
			
			if(lower != null && lower.end + 1 == block.start) {
				block.start = lower.start;
				emtryBlocks.remove(block);
			}
			if(higher != null && higher.start - 1 == block.end) {
				block.end = higher.end;
				emtryBlocks.remove(higher);
			}
			emtryBlocks.add(block);
		}
		return listBlocksOfFileID.size();
	}
	
	public int count(int mSatrt, int mEnd) {
		int result = 0;
		for (ArrayList<Block> listBlockByFile : hashFiles.values()) {
			if(listBlockByFile != null) {
				for (Block block : listBlockByFile) {
					if(block.start >= mSatrt && block.end <= mEnd) {
						result++;
						break;
					}
				}
			}
		}
		return result;
	}

}
