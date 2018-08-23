import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.lang.System.*;

public class Tree {
    private Node root;
    /*//leafs are maintained to allow users to add elements to specific tree as the given
    input is not in sorted order
     */
     private List<Node> leafs= new ArrayList<>();
    public Tree(){
        root = null;
    }
    //this is just to allow the user to add data without specifying direction
    public Node add(Integer data){
      Node x= leafs.get(0);
      if(x != null){
          x.setLeft(new Node(data));
          leafs.set(0, x.getLeft());
          return x.getLeft();
      }else{
          x=new Node(data);
          this.root = x;
          leafs.add(x);
          return x;
      }

    }
    /*//this is to add the data just like how in the question,
    as this binary tree doesn't follow the left side having lower value and right having greated
    value that root*/
    public Node add(Node node, Integer data,  Direction direction){
        if(node == null){
            //first insert will become root
            this.root= new Node(data);
            leafs.add(root);
            return root;
        }else{
            boolean index = leafs.remove(node);
            if(index){
                if(direction == Direction.LEFT && node.getLeft() == null ){
                    node.setLeft(new Node(data));
                    leafs.add( node.getLeft());
                    if(node.getRight()== null)leafs.add(node);
                    return  node.getLeft();
                }else if(direction == Direction.RIGHT && node.getRight() == null ){
                    node.setRight(new Node(data));
                    leafs.add( node.getRight());
                    if(node.getLeft()== null)leafs.add(node);
                    return node.getRight();
                }
            }

        }
       return null;
    }
    public boolean search (Integer data){
        return search(this.root, data);
    }
    public boolean search (Node node, Integer data){
        if(node != null){
            if(node.getData().equals(data)){
                return true;
            }
                if (node.getLeft()!= null){
                    return search(node.getLeft(), data);
                }
                if (node.getRight()!= null){
                    return search(node.getRight(),data);
                }

        }
        return false;
    }
    public static void main(String[] args) throws IOException {
        Tree tree = new Tree();
        //adding input as per the question
        Node n2 = tree.add(null, 2,null );
        Node n7_6= tree.add(n2, 7, Direction.LEFT);
        Node n5_9= tree.add(n2,5, Direction.RIGHT);
        n7_6 = tree.add(n7_6, 2, Direction.LEFT);
        tree.add(n7_6, 5, Direction.LEFT);
        tree.add(n7_6, 11, Direction.RIGHT);
        n5_9 = tree.add(n5_9, 9, Direction.RIGHT);
        tree.add(n5_9, 4, Direction.LEFT);

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        out.println("Enter value to search or exist with -1");
        int searchVal = Integer.parseInt(reader.readLine());
        System.out.println("Entered value:"+searchVal);
        if (searchVal == -1) exit(0);
        if(tree.search(searchVal)){
            out.println("Search value exist");
        }else{
            out.println( " Search value does not exists");
        }


    }
}

class Node implements Comparable<Node>{
    private Node left;
    private Node right;
    private Integer data;

    Node (){
       setLeft(null);
       setRight(null);
       setData(null);
    }
    Node(Integer n){
        setLeft(null);
        setRight(null);
        this.setData(n);
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public Integer getData() {
        return data;
    }

    public void setData(Integer data) {
        this.data = data;
    }

    @Override
    public int compareTo(Node o) {
        return this.getData().compareTo(o.getData());
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Node) {
            return this.getData().equals(((Node) obj).getData());
        }else{
            return false;
        }
    }
}
enum Direction {
    LEFT, RIGHT;
        }