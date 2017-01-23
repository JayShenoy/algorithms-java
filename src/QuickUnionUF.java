public class QuickUnionUF {
  private int[] id;

  public QuickUnionUF(int N) {
    id = new int[N];

    for(int i = 0; i < N; i++)
      id[i] = i;
  }

  private int root(int i) {
    // Traverse up the tree until root is found
    while(id[i] != i)
      i = id[i];

    return i;
  }

  public boolean connected(int p, int q) {
    return root(p) == root(q);
  }

  public void union(int p, int q) {
    int proot = root(p);
    int qroot = root(q);

    // Join the components by setting p's root's parent to be q's root
    id[proot] = qroot;
  }

  public static void main(String[] args) {
    int N = StdIn.readInt();
    QuickFindUF uf = new QuickFindUF(N);
    while(!StdIn.isEmpty()) {
      int p = StdIn.readInt();
      int q = StdIn.readInt();
      if(!uf.connected(p, q)) {
        uf.union(p, q);
        StdOut.println(p + " " + q);
      }
    }
  }
}