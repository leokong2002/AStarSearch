package AStarSearch;

import java.util.PriorityQueue;
import java.util.HashSet;
import java.util.Set;
import java.util.List;
import java.util.Comparator;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

//Written by Ashwin Rajendran and Chi Wai Kong 

class Node {

    int package_array[][] = new int[3][3];
    String RobotPosition;
    Node parent_node;
    String ActionFromPreviousState;
    int Nheuristic;
    int Ncost;
    int NFcost;

    Node(int WAS, int WAM, int WAL, int TS, int TM, int TL, int WBS, int WBM, int WBL, String position, Node current_node, String action, int cost) {
        package_array[0][0] = WAS;
        package_array[0][1] = WAM;
        package_array[0][2] = WAL;
        package_array[1][0] = TS;
        package_array[1][1] = TM;
        package_array[1][2] = TL;
        package_array[2][0] = WBS;
        package_array[2][1] = WBM;
        package_array[2][2] = WBL;
        RobotPosition = position;
        parent_node = current_node;
        ActionFromPreviousState = action;
        Ncost = cost;
        Nheuristic = (package_array[0][1] + package_array[0][2] + package_array[1][0] + package_array[1][2] + package_array[2][0] + package_array[2][1]);
       // Nheuristic = (package_array[0][1] + package_array[0][2] + package_array[1][0] + package_array[1][2] + package_array[2][0] + package_array[2][1])*8;
        NFcost = Ncost + Nheuristic;
    }

    int getFCost() {
        return NFcost;
    }
}

public class AStarSearch {

    public static boolean found = false;
    public static Node final_node = null;

    public static void createChildren(List<Node> openlist, List<Node> closedlist, Node node) {

        openlist.remove(node);
        closedlist.add(node);

        Node newnode;

        if (node.package_array[0][1] == 0 && node.package_array[0][2] == 0
                && node.package_array[1][0] == 0 && node.package_array[1][2] == 0 && node.package_array[2][0] == 0
                && node.package_array[2][1] == 0) {

            found = true;
            final_node = node;

        } else {
            if (node.RobotPosition == "T") {

                newnode = new Node(node.package_array[0][0], node.package_array[0][1], node.package_array[0][2],
                        node.package_array[1][0], node.package_array[1][1], node.package_array[1][2],
                        node.package_array[2][0], node.package_array[2][1], node.package_array[2][2],
                        "B", node, "Moved from T to B", node.Ncost + 2);

                if (closedlist.contains(newnode)) {
                    //ignore
                } else {
                    openlist.add(newnode);
                }

                newnode = new Node(node.package_array[0][0], node.package_array[0][1], node.package_array[0][2],
                        node.package_array[1][0], node.package_array[1][1], node.package_array[1][2],
                        node.package_array[2][0], node.package_array[2][1], node.package_array[2][2],
                        "A", node, "Moved from T to A", node.Ncost + 2);
                if (closedlist.contains(newnode)) {
                    //ignore
                } else {
                    openlist.add(newnode);
                }

                if (node.package_array[1][0] > 0) {
                    newnode = new Node(node.package_array[0][0] + 1, node.package_array[0][1], node.package_array[0][2],
                            node.package_array[1][0] - 1, node.package_array[1][1], node.package_array[1][2],
                            node.package_array[2][0], node.package_array[2][1], node.package_array[2][2],
                            "A", node, "Moved small package from T to A", node.Ncost + 1);
                    if (closedlist.contains(newnode)) {
                        //ignore
                    } else {
                        openlist.add(newnode);
                    }
                }

                if (node.package_array[1][2] > 0) {
                    newnode = new Node(node.package_array[0][0], node.package_array[0][1], node.package_array[0][2],
                            node.package_array[1][0], node.package_array[1][1], node.package_array[1][2] - 1,
                            node.package_array[2][0], node.package_array[2][1], node.package_array[2][2] + 1,
                            "B", node, "Moved large package from T to B", node.Ncost + 1);
                    if (closedlist.contains(newnode)) {
                        //ignore
                    } else {
                        openlist.add(newnode);
                    }
                }

            } else if (node.RobotPosition == "A") {
                newnode = new Node(node.package_array[0][0], node.package_array[0][1], node.package_array[0][2],
                        node.package_array[1][0], node.package_array[1][1], node.package_array[1][2],
                        node.package_array[2][0], node.package_array[2][1], node.package_array[2][2],
                        "T", node, "Moved from A to T", node.Ncost + 2);

                if (closedlist.contains(newnode)) {
                    //ignore
                } else {
                    openlist.add(newnode);
                }

                newnode = new Node(node.package_array[0][0], node.package_array[0][1], node.package_array[0][2],
                        node.package_array[1][0], node.package_array[1][1], node.package_array[1][2],
                        node.package_array[2][0], node.package_array[2][1], node.package_array[2][2],
                        "B", node, "Moved from A to B", node.Ncost + 2);

                if (closedlist.contains(newnode)) {
                    //ignore
                } else {
                    openlist.add(newnode);
                }

                if (node.package_array[0][1] > 0) {
                    newnode = new Node(node.package_array[0][0], node.package_array[0][1] - 1, node.package_array[0][2],
                            node.package_array[1][0], node.package_array[1][1] + 1, node.package_array[1][2],
                            node.package_array[2][0], node.package_array[2][1], node.package_array[2][2],
                            "T", node, "Moved medium package from A to T", node.Ncost + 1);

                    if (closedlist.contains(newnode)) {
                        //ignore
                    } else {
                        openlist.add(newnode);
                    }
                }
            } else if (node.RobotPosition == "B") {

                newnode = new Node(node.package_array[0][0], node.package_array[0][1], node.package_array[0][2],
                        node.package_array[1][0], node.package_array[1][1], node.package_array[1][2],
                        node.package_array[2][0], node.package_array[2][1], node.package_array[2][2],
                        "A", node, "Moved from B to A", node.Ncost + 2);

                if (closedlist.contains(newnode)) {
                    //ignore
                } else {
                    openlist.add(newnode);
                }

                newnode = new Node(node.package_array[0][0], node.package_array[0][1], node.package_array[0][2],
                        node.package_array[1][0], node.package_array[1][1], node.package_array[1][2],
                        node.package_array[2][0], node.package_array[2][1], node.package_array[2][2],
                        "T", node, "Moved from B to T", node.Ncost + 2);

                if (closedlist.contains(newnode)) {
                    //ignore
                } else {
                    openlist.add(newnode);
                }

                if (node.package_array[2][1] > 0) {
                    newnode = new Node(node.package_array[0][0], node.package_array[0][1], node.package_array[0][2],
                            node.package_array[1][0], node.package_array[1][1] + 1, node.package_array[1][2],
                            node.package_array[2][0], node.package_array[2][1] - 1, node.package_array[2][2],
                            "T", node, "Moved medium package from B to T", node.Ncost + 1);

                    if (closedlist.contains(newnode)) {
                        //ignore
                    } else {
                        openlist.add(newnode);
                    }
                }

            }

        }
    }

    public static void printState(Node node) {
        System.out.println("Action: " + node.ActionFromPreviousState);

        System.out.println("      S   M   L");
        for (int i = 0; i <= 2; i++) {
            for (int j = 0; j <= 2; j++) {
                if (i == 0 && j == 0) {
                    System.out.print("WA:   ");
                }
                if (i == 1 && j == 0) {
                    System.out.print("T:    ");
                }
                if (i == 2 && j == 0) {
                    System.out.print("WB:   ");
                }
                System.out.print(node.package_array[i][j] + "   ");
            }
            System.out.println();
        }
        System.out.println("RobotPosition: " + node.RobotPosition);
        System.out.println("Cost So Far: " + node.Ncost);
        System.out.println("Heuristic: " + node.Nheuristic);
        System.out.println("F: " + node.NFcost);

        System.out.println();

    }

    public static void contructPath(Node node, List<Node> path) {

        while (node.parent_node != null) {
            path.add(node);
            node = node.parent_node;
        }

        if (node.parent_node == null) {
            path.add(node);

        }

    }

    public static void printPath(List<Node> path) {

        Collections.reverse(path);
        for (int i = 0; i < path.size(); i++) {
            System.out.println("    STEP: " + (i));
            printState(path.get(i));
        }
    }

    //public static void SortNides(List<Node> nodes,)
    public static void main(String[] args) {

        Node start_node = new Node(0, 2, 0, 2, 0, 2, 0, 3, 0, "T", null, "Start", 0);

        List<Node> openlist = new ArrayList<>();
        List<Node> closedlist = new ArrayList<>();
        List<Node> path = new ArrayList<>();

        openlist.add(start_node);

        while (found != true) {

            Collections.sort(openlist, new Comparator<Node>() {
                @Override
                public int compare(Node p1, Node p2) {
                    return p1.NFcost - p2.NFcost; // Ascending
                }
            });

            createChildren(openlist, closedlist, openlist.get(0));
        }

//        System.out.println("Open List:");
//        for (int i = 0; i < openlist.size(); i++) {
//            System.out.println("    STATE: " + (i + 1));
//            printState(openlist.get(i));
//        }
//        System.out.println("Closed List:");
//        for (int i = 0; i < closedlist.size(); i++) {
//            System.out.println("    STATE: " + (i + 1));
//            printState(closedlist.get(i));
//        }
//   System.out.println("FINAL");
//   printState(openlist.get(0));
        
        contructPath(final_node, path);
        printPath(path);

    }

}
