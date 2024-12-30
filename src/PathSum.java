import java.util.ArrayList;

class TreeNode {
    int val;
    TreeNode left, right;

    TreeNode(int val) {
        this.val = val;
        left = right = null;
    }
}

public class PathSum {
    public static void printPaths(TreeNode root, int sum, ArrayList<Integer> path) {
        if (root == null)
            return;

        path.add(root.val);

        if (root.left == null && root.right == null && sum == root.val) {
            System.out.println(path);
        }

        printPaths(root.left, sum - root.val, new ArrayList<>(path));
        printPaths(root.right, sum - root.val, new ArrayList<>(path));
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(4);
        root.right = new TreeNode(8);
        root.left.left = new TreeNode(11);
        root.right.left = new TreeNode(13);
        root.right.right = new TreeNode(4);

        int sum = 17;
        printPaths(root, sum, new ArrayList<>());
    }
}
