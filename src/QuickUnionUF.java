public class QuickUnionUF {
  private int[] id;
  // Size of tree under each node
  private int[] size;

  public QuickUnionUF(int N) {
    id = new int[N];

    for(int i = 0; i < N; i++) {
      id[i] = i;
      size[i] = 1;
    }
  }

  private int root(int i) {
    // Traverse up the tree until root is found
    while(id[i] != i) {
      i = id[i];
      // Compress tree so that each node along the way points to its grandparent
      id[i] = id[id[i]];
    }

    return i;
  }

  public boolean connected(int p, int q) {
    return root(p) == root(q);
  }

  public void union(int p, int q) {
    int proot = root(p);
    int qroot = root(q);

    // Do nothing if nodes are the same
    if(proot == qroot) return;
    // Make smaller tree a subtree of larger tree
    if(size[proot] >= size[qroot]) {
      id[qroot] = proot;
      size[proot] += size[qroot];
    }
    else {
      id[proot] = qroot;
      size[qroot] += proot;
    }
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