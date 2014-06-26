public class QuickUnionIUF {
	// each element has a root pointing to its parent
	// faster because it organizes tree so the smaller tree becomes a child of
	// the bigger one
	private int[] id;
	private int[] sz; // count number of objects in the tree rooted at i

	// constructor set id of each object to itself
	public QuickUnionIUF(int N) {
		id = new int[N];
		sz = new int[N];
		for (int i = 0; i < N; i++) {
			this.id[i] = i;
			this.sz[i] = 1;
		}
	}// end of constructor

	// chase parent pointers until reach root
	public int root(int i) {
		while (i != id[i]) {
			id[i] = id[id[i]];
			i = id[i];
		}
		return i;
	}// end of root method

	// check if p and q have the same root
	public boolean connected(int p, int q) {
		return root(p) == root(q);
	}// end of connected method

	// change root of p to point to root of q
	public void union(int p, int q) {
		int i = root(p);
		int j = root(q);
		if (i == j) {
			return;
		}
		if (sz[i] < sz[j]) {
			id[i] = j;
			sz[j] += sz[i];
		} else {
			id[j] = i;
			sz[i] += sz[j];
		}
	}// end of union method

	public void displayTree() {
		for (int i = 0; i < id.length; i++) {
			System.out.print(id[i] + "\t");
		}
	}// end of displayTree method

}
