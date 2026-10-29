import java.util.ArrayList;
import java.util.List;

public class Q1Test {
     public static void main(String[] args) {

          String result, traversal1, traversal2, traversal3;

          traversal1 = "1-2";
          traversal2 = "2-1-3";
          traversal3 = "1-3-2";
          result = verify(traversal1, traversal2, traversal3);
          System.out.println("Traversal 1 : " + traversal1);
          System.out.println("Traversal 2 : " + traversal2);
          System.out.println("Traversal 3 : " + traversal3);
          System.out.println("Expected Result : Invalid traversals");
          System.out.println("  Actual Result : " + result);
          System.out.println();

          traversal1 = "1-2-3";
          traversal2 = "2-1-3";
          traversal3 = "1-3-2";
          result = verify(traversal1, traversal2, traversal3);
          System.out.println("Traversal 1 : " + traversal1);
          System.out.println("Traversal 2 : " + traversal2);
          System.out.println("Traversal 3 : " + traversal3);
          System.out.println(
                    "Expected Result : Traversal 1 - Inorder, Traversal 2 - Preorder, Traversal 3 - Postorder");
          System.out.println("  Actual Result : " + result);
          System.out.println();

          traversal1 = "1-2-3";
          traversal2 = "3-2-1";
          traversal3 = "2-3-1";
          result = verify(traversal1, traversal2, traversal3);
          System.out.println("Traversal 1 : " + traversal1);
          System.out.println("Traversal 2 : " + traversal2);
          System.out.println("Traversal 3 : " + traversal3);
          System.out.println("Expected Result : Invalid traversals");
          System.out.println("  Actual Result : " + result);
          System.out.println();

          traversal1 = "3-1-2-5-4";
          traversal2 = "2-1-4-5-3";
          traversal3 = "1-2-3-4-5";
          result = verify(traversal1, traversal2, traversal3);
          System.out.println("Traversal 1 : " + traversal1);
          System.out.println("Traversal 2 : " + traversal2);
          System.out.println("Traversal 3 : " + traversal3);
          System.out.println(
                    "Expected Result : Traversal 1 - Preorder, Traversal 2 - Postorder, Traversal 3 - Inorder");
          System.out.println("  Actual Result : " + result);
          System.out.println();

          traversal1 = "10-20-30-40-50";
          traversal2 = "20-10-30-40-50";
          traversal3 = "10-50-40-30-20";
          result = verify(traversal1, traversal2, traversal3);
          System.out.println("Traversal 1 : " + traversal1);
          System.out.println("Traversal 2 : " + traversal2);
          System.out.println("Traversal 3 : " + traversal3);
          System.out.println(
                    "Expected Result : Traversal 1 - Inorder, Traversal 2 - Preorder, Traversal 3 - Postorder");
          System.out.println("  Actual Result : " + result);
          System.out.println();
     } 

     static class Node {
          int value;
          Node left;
          Node right;

          Node(int value) {
               this.value = value;
          }
     }

     public static String verify(String traversal1, String traversal2, String traversal3) {
          List<Integer> first = parseTraversal(traversal1);
          List<Integer> second = parseTraversal(traversal2);
          List<Integer> third = parseTraversal(traversal3);

          if (matches(first, second, third)) {
               return "Traversal 1 - Preorder, Traversal 2 - Inorder, Traversal 3 - Postorder";
          }
          if (matches(first, third, second)) {
               return "Traversal 1 - Preorder, Traversal 2 - Postorder, Traversal 3 - Inorder";
          }
          if (matches(second, first, third)) {
               return "Traversal 1 - Inorder, Traversal 2 - Preorder, Traversal 3 - Postorder";
          }
          if (matches(second, third, first)) {
               return "Traversal 1 - Postorder, Traversal 2 - Preorder, Traversal 3 - Inorder";
          }
          if (matches(third, first, second)) {
               return "Traversal 1 - Inorder, Traversal 2 - Postorder, Traversal 3 - Preorder";
          }
          if (matches(third, second, first)) {
               return "Traversal 1 - Postorder, Traversal 2 - Inorder, Traversal 3 - Preorder";
          }

          return "Invalid traversals";
     }

     public static boolean matches(List<Integer> givenPreorder,
                                   List<Integer> givenInorder,
                                   List<Integer> givenPostorder) {
          Node root = null;
          for (int i = 0; i < givenPreorder.size(); i++) {
               int value = givenPreorder.get(i);
               root = insert(root, value);
          }

          List<Integer> actualPreorder = new ArrayList<>();
          List<Integer> actualInorder = new ArrayList<>();
          List<Integer> actualPostorder = new ArrayList<>();
          preorder(root, actualPreorder);
          inorder(root, actualInorder);
          postorder(root, actualPostorder);

          if (!actualPreorder.equals(givenPreorder)) {
               return false;
          }
          if (!actualInorder.equals(givenInorder)) {
               return false;
          }
          if (!actualPostorder.equals(givenPostorder)) {
               return false;
          }
          return true;
     }

     public static List<Integer> parseTraversal(String traversal) {
          String[] numbers = traversal.split("-");
          List<Integer> values = new ArrayList<>();

          for (int i = 0; i < numbers.length; i++) {
               int value = Integer.parseInt(numbers[i]);
               values.add(value);
          }
          return values;
     }

     public static Node insert(Node node, int value) {
          if (node == null) {
               return new Node(value);
          }

          if (value < node.value) {
               node.left = insert(node.left, value);
          } else if (value > node.value) {
               node.right = insert(node.right, value);
          }
          return node;
     }

     public static void preorder(Node node, List<Integer> result) {
          if (node == null) {
               return;
          }
          result.add(node.value);
          preorder(node.left, result);
          preorder(node.right, result);
     }

     public static void inorder(Node node, List<Integer> result) {
          if (node == null) {
               return;
          }
          inorder(node.left, result);
          result.add(node.value);
          inorder(node.right, result);
     }

     public static void postorder(Node node, List<Integer> result) {
          if (node == null) {
               return;
          }
          postorder(node.left, result);
          postorder(node.right, result);
          result.add(node.value);
     }
}
