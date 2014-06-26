public class UF {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		QuickUnionIUF uf = new QuickUnionIUF(10);
		uf.displayTree();
		System.out.println("\n");
		uf.union(4, 3);
		uf.displayTree();
		System.out.println("");
		uf.union(3, 8);
		uf.displayTree();
		System.out.println("");
		uf.union(6, 5);
		uf.displayTree();
		System.out.println("");
		uf.union(9, 4);
		uf.displayTree();
		System.out.println("");
		uf.union(2, 1);
		uf.displayTree();
		System.out.println("");
		uf.union(5, 0);
		uf.displayTree();
		System.out.println("");
		uf.union(7, 2);
		uf.displayTree();
		System.out.println("");
		uf.union(6, 1);
		uf.displayTree();
		System.out.println("");
		uf.union(7, 3);
		uf.displayTree();
		
	}

}
