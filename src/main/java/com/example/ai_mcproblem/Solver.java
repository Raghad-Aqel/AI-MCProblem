package com.example.ai_mcproblem;

import java.util.*;

public class Solver {

    //BFS Method
    public  String BFS(State start, State goal) {
        Queue<State> queue = new LinkedList<>(); //This creates a queue to store the states that need to be explored.
        Set<State> visited = new HashSet<>(); //states that have already been visited (to avoid revisiting the same state)
        Map<State, State> parentMap = new HashMap<>();

        queue.add(start);
        visited.add(start);

        while (!queue.isEmpty()) {
            State current = queue.poll();

            if (current.equals(goal)) {
                String str = printSolution(parentMap, current);
                return str;
            }

            for (State next : getValidMoves(current)) {
                if (!visited.contains(next)) {
                    queue.add(next);
                    visited.add(next);
                    parentMap.put(next, current);
                }
            }
        }
        return  "";
    }

    //DFS Method
    public  String DFS(State start, State goal) {
        Stack<State> stack = new Stack<>();
        Set<State> visited = new HashSet<>();
        Map<State, State> parentMap = new HashMap<>();

        stack.push(start);
        visited.add(start);

        while (!stack.isEmpty()) {
            State current = stack.pop();

            if (current.equals(goal)) {
                String str = printSolution(parentMap, current);
                return str;
            }

            for (State next : getValidMoves(current)) {
                if (!visited.contains(next)) {
                    stack.push(next);
                    visited.add(next);
                    parentMap.put(next, current);
                }
            }
        }

        return  "";

    }

    public  List<State> getValidMoves(State current) {
        List<State> validMoves = new ArrayList<>();

        int mL = current.missionaries;
        int cL = current.cannibals;

        //Boat on the left(true)
        if (current.boat) {
            addValidMove(validMoves, new State(mL, cL - 2, false));
            addValidMove(validMoves, new State(mL - 2, cL, false));
            addValidMove(validMoves, new State(mL - 1, cL, false));
            addValidMove(validMoves, new State(mL, cL - 1, false));
            addValidMove(validMoves, new State(mL - 1, cL - 1, false));
        } else {
            //Boat on the right(false)
            addValidMove(validMoves, new State(mL, cL + 2, true));
            addValidMove(validMoves, new State(mL + 2, cL, true));
            addValidMove(validMoves, new State(mL + 1, cL, true));
            addValidMove(validMoves, new State(mL, cL + 1, true));
            addValidMove(validMoves, new State(mL + 1, cL + 1, true));
        }
        return validMoves;
    }


    private static void addValidMove(List<State> moves, State nextState) {
        if (isValid(nextState)) {
            moves.add(nextState);
        }
    }

    private static boolean isValid(State state) {
        int mL = state.missionaries;
        int cL = state.cannibals;

        return (mL >= 0 && mL <= 3 && cL >= 0 && cL <= 3 &&
                (mL == 0 || mL >= cL) && (3 - mL == 0 || (3 - mL) >= (3 - cL)));
    }

    public  String printSolution(Map<State, State> parentMap, State current) {
        List<State> path = new ArrayList<>();

        String str = "";
        while (current != null) {
            path.add(current);
            current = parentMap.get(current);
        }

        Collections.reverse(path);

        System.out.println(path.size() + " steps:");

        str+=path.size() + " steps:\n";
        for (int i = 0; i < path.size(); i++) {
            State state = path.get(i);

            System.out.println("Step " + (i+1) + ": " +
                    "Boat in the " + (state.boat ? "left" : "right")
                    + " side of the river with  "+ state.missionaries+
                    " missionaries and "+ state.cannibals +" cannibals, the "+
                    (state.boat ? "right" : "left")+" side of the river has "+
                    (3 - state.missionaries)+" missionaries and "+
                    (3 - state.cannibals) +" cannibals");

            System.out.println("        (" +
                    state.missionaries + ", " + state.cannibals + ", " +
                    (state.boat ? "1" : "0") + ", " +
                    (3 - state.missionaries) + ", " + (3 - state.cannibals) + ")");

            str+="Step " + (i+1) + ": " +
                    "Boat in the " + (state.boat ? "left" : "right")
                    + " side of the river with  "+ state.missionaries+
                    " missionaries and "+ state.cannibals +" cannibals, the "+
                    (state.boat ? "right" : "left")+" side of the river has "+
                    (3 - state.missionaries)+" missionaries and "+
                    (3 - state.cannibals) +" cannibals\n";

            str+="             (" +
                    state.missionaries + ", " + state.cannibals + ", " +(state.boat ? "1" : "0") + ", " +(3 - state.missionaries) + ", " + (3 - state.cannibals) + ")\n";
        }
        return str;
    }
}
