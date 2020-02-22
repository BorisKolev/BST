package Ex2;

class BST
{
    private BTNode<Integer> root;

    public BST()
    {
        root = null;
    }

    public boolean insert(Integer i)
    {

        BTNode<Integer> parent = root;
        BTNode<Integer> child = root;
        boolean goneLeft = false;

        while (child!=null && i.compareTo(child.data)!=0)
        {
            parent = child;
            if (i.compareTo(child.data)<0)
            {
                child = child.left;
                goneLeft = true;
            }
            else
            {
                child = child.right;
                goneLeft = false;
            }
        }

        if (child!=null)
        {
            return false;
        }

        else
        {
            BTNode<Integer> leaf = new BTNode<>(i);

            if (parent==null)
            {
                root = leaf;
            }
            else if (goneLeft)
            {
                parent.left = leaf;
            }
            else
            {
                parent.right = leaf;
            }

            return true;
        }
    }

    public boolean find(Integer i)
    {

        BTNode<Integer> n = root;
        boolean found = false;

        while (n!=null && !found)
        {

            int comp = i.compareTo(n.data);

            if (comp==0)
            {
                found = true;
            }

            else if (comp<0)
            {
                n = n.left;
            }

            else
            {
                n = n.right;
            }
        }

        return found;
    }

    /*
     This method is counting each non leaves through a recursive return
    */
    private int countNonLeaves(BTNode<Integer> root)
    {
        // If the root == null it will return 0 since it will be a leaf
        // If the root != null but root.left nor root.right has any data it will return 0 since it will be a leaf
        // In any other case the root will not be a null and it will have at least one child e.g. it will be a non-leaf

        if(root != null && (root.left != null || root.right != null))
        {
            // Every time when the code reaches here it means there is a non-leaf
            // add 1 to the return and keep calling the next root.left and the next root.right
            return 1 + countNonLeaves(root.left) + countNonLeaves(root.right);
        }

        else // stop the recursion
        {
            return 0;
        }

    }

    /*
        This method is counting the depth of the given BTNode going left and right through a recursive call
        then simply compares them and return the maximumDepth
     */
    private int maximumDepth(BTNode<Integer> n)
    {
        // If the passed BTNode<Integer> n == null return 0
        if(n != null)
        {
            int LeftDepth = maximumDepth(n.left);
            int RightDepth = maximumDepth(n.right);

            if(RightDepth > LeftDepth)
            {
                return (RightDepth + 1);
            }
            else
            {
                return (LeftDepth + 1);
            }
        }

        else // stop the recursion
        {
            return 0;
        }

    }

    /*
    This method is counting how many of the values within the Nodes are between the min and max variables
    using recursion
    */
    private int countInRange(BTNode<Integer> root, int min, int max)
    {
        if(root != null) // Check whether the node is null to escape nullPointerException
        {
            if(root.data >= min && root.data <= max) // Check whether we should count the value
            {
                return 1 + countInRange(root.left, min, max) + countInRange(root.right, min, max);
            }
            else // otherwise just recurse it without adding anything
            {
                return countInRange(root.left, min, max) + countInRange(root.right, min, max);
            }
        }

        return 0;
    }

    private int nonleaves()
    {
        // passing the BTNode of the current tree into countNonLeaves and returning the Call
        return this.countNonLeaves(root);
    }

    private int depth()
    {
        // passing the BTNode of the current tree into maximumDepth and returning the Call
        return this.maximumDepth(root);
    }

    private int range(int min, int max)
    {
        // passing the BTNode of the current tree with min and max and returning the call
        return this.countInRange(root,min,max);
    }

}
