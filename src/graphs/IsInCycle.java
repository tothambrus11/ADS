package graphs;


import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class IsInCycle {

    public static boolean inCycle(List<Map<Integer, Integer>> graph, int vertex) {
        if (graph.isEmpty()) return false;

        var visited = new HashSet<Map<Integer, Integer>>();
        var startVertex = graph.get(vertex);
        visited.add(startVertex);

        return DFS(graph, vertex, visited, (item) -> item == startVertex);
    }

    public static boolean DFS(List<Map<Integer, Integer>> graph, int vertex,
                              HashSet<Map<Integer, Integer>> visited,
                              Predicate<Map<Integer, Integer>> shouldStopAt) {
        var startVertex = graph.get(vertex);
        for (var n : startVertex.keySet()) {
            if (visited.contains(graph.get(n))) {
                if (shouldStopAt.test(graph.get(n))) return true;
                continue;
            }
            visited.add(graph.get(n));
            if (DFS(graph, n, visited, shouldStopAt)) return true;

        }
        return false;
    }


    public static boolean cycleCheckDFS(List<Map<Integer, Integer>> graph, int vertex) {
        var path = new Stack<Map<Integer, Integer>>();
        path.add(graph.get(vertex));
        var visited = new HashSet<>();
        while (!path.empty()) {
            var currentVertex = path.pop();
            visited.add(currentVertex);

            for (var n : currentVertex.keySet()) {
                if (visited.contains(graph.get(n))) {
                    if (n == vertex) {
                        return true;
                    }
                } else {
                    path.add(graph.get(n));
                    break;
                }
            }
        }
        return false;
    }


    public static boolean isDAG(List<Map<Integer, Integer>> graph) {
        for (var v : graph) {
            var visited = new HashSet<Map<Integer, Integer>>();
            var path = new Stack<Map<Integer, Integer>>();
            path.add(v);

            while(!path.empty()){
                var currentVertex = path.pop();
                visited.add(currentVertex);
                for(var neighbourID : currentVertex.keySet()){
                    var neighbour = graph.get(neighbourID);
                    if(visited.contains(neighbour)){
                        if(v == neighbour) return false; // contains cycle
                    }
                    else {
                        path.add(neighbour);
                        break;
                    }
                }
            }
        }
        return true;
    }

    @Test
    public void testSimple() {
        List<Map<Integer, Integer>> map = new ArrayList<>();
        map.add(new HashMap<>());
        map.add(new HashMap<>());
        map.add(new HashMap<>());
        map.add(new HashMap<>());
        map.get(0).put(1, 5);
        map.get(1).put(3, 5);
        map.get(3).put(0, 5);
        map.get(2).put(1, 5);
        assertTrue(IsInCycle.cycleCheckDFS(map, 0));
        assertTrue(IsInCycle.cycleCheckDFS(map, 1));
        assertFalse(IsInCycle.cycleCheckDFS(map, 2));
        assertTrue(IsInCycle.cycleCheckDFS(map, 3));
    }

    public static List<Map<Integer,Integer>> toAdjacencyMap(int[][] matrix) {
        List<Map<Integer, Integer>> list = new ArrayList<>();

        for(var i = 0; i < matrix.length; i++) {
            var vertex = new HashMap<Integer, Integer>();
            list.add(vertex);
            for(var j = 0; j < matrix.length; j++) {
                if(matrix[i][j] != 0) {
                    vertex.put(j, matrix[i][j]);
                }
            }
        }

        return list;
    }

    public static int[][] toAdjacencyMatrix(List<Map<Integer,Integer>> map) {
        int n = map.size();
        int[][] matrix = new int[n][n];

        for(int i = 0; i < n; i++){
            var node = map.get(i);
            for(var neighbourPair : node.entrySet()){
                matrix[i][neighbourPair.getKey()] = neighbourPair.getValue();
            }
        }
        return matrix;
    }
}
