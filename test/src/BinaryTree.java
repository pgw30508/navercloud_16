public class BinaryTree {
    private Node root;

    public Node getRoot() {
        return root;
    }

    void insert(int key) {
        root = insertRec(root, key);
    }

    Node insertRec(Node root, int key) {

        if (root == null) {
            root = new Node(key);
            return root;
        }

        if (key < root.key) {
            root.left = insertRec(root.left, key);
        }
        else if (key > root.key) {
            root.right = insertRec(root.right, key);
        }

        return root;
    }

    void delete(int key) {
        root = deleteRec(root, key);
    }

    Node deleteRec(Node root, int key) {
        if (root == null) {
            return root;
        }

        if (key < root.key) {
            root.left = deleteRec(root.left, key);
        } else if (key > root.key) {
            root.right = deleteRec(root.right, key);
        } else {
            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            }

            root.key = minValue(root.right);

            root.right = deleteRec(root.right, root.key);
        }
        return root;
    }

    int minValue(Node root) {
        int minValue = root.key;
        while (root.left != null) {
            minValue = root.left.key;
            root = root.left;
        }
        return minValue;
    }

    void insertAt(Node root, int key, int position) {
        if (root == null) {
            return;
        }

        if (position == 0) {
            Node newNode = new Node(key);
            newNode.left = root.left;
            root.left = newNode;
        } else if (position == 1) {
            Node newNode = new Node(key);
            newNode.right = root.right;
            root.right = newNode;
        }

        insertAt(root.left, key, position - 1);
        insertAt(root.right, key, position - 1);
    }
}
