package com.jotsons.dynamic_route_optimizer.Service;

import java.util.*;

public class RoutingAlgorithms {
    static class Node implements Comparable<Node> {
        int id;
        double distance;
        List<Node> path;

        Node(int id, double distance, List<Node> path) {
            this.id = id;
            this.distance = distance;
            this.path = path;
        }

        @Override
        public int compareTo(Node other) {
            return Double.compare(this.distance, other.distance);
        }
    }

    public static List<Integer> dijkstra(double[][] graph, int start, int end) {
        int n = graph.length;
        double[] distances = new double[n];
        boolean[] visited = new boolean[n];
        int[] previous = new int[n];

        Arrays.fill(distances, Double.POSITIVE_INFINITY);
        distances[start] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start, 0, new ArrayList<>(Collections.singletonList(new Node(start, 0, null)))));

        while (!pq.isEmpty()) {
            Node current = pq.poll();
            int currentId = current.id;

            if (currentId == end) {
                return reconstructPath(previous, start, end);
            }

            if (visited[currentId]) continue;
            visited[currentId] = true;

            for (int neighbor = 0; neighbor < n; neighbor++) {
                if (graph[currentId][neighbor] > 0) {
                    double newDist = distances[currentId] + graph[currentId][neighbor];

                    if (newDist < distances[neighbor]) {
                        distances[neighbor] = newDist;
                        previous[neighbor] = currentId;
                        List<Node> newPath = new ArrayList<>(current.path);
                        newPath.add(new Node(neighbor, newDist, null));
                        pq.offer(new Node(neighbor, newDist, newPath));
                    }
                }
            }
        }

        return new ArrayList<>();
    }

    public static List<Integer> aStar(double[][] graph, int start, int end, double[] heuristic) {
        int n = graph.length;
        double[] gScore = new double[n];
        double[] fScore = new double[n];
        boolean[] visited = new boolean[n];
        int[] previous = new int[n];

        Arrays.fill(gScore, Double.POSITIVE_INFINITY);
        Arrays.fill(fScore, Double.POSITIVE_INFINITY);
        gScore[start] = 0;
        fScore[start] = heuristic[start];

        PriorityQueue<Node> openSet = new PriorityQueue<>();
        openSet.offer(new Node(start, fScore[start], new ArrayList<>()));

        while (!openSet.isEmpty()) {
            Node current = openSet.poll();
            int currentId = current.id;

            if (currentId == end) {
                return reconstructPath(previous, start, end);
            }

            if (visited[currentId]) continue;
            visited[currentId] = true;

            for (int neighbor = 0; neighbor < n; neighbor++) {
                if (graph[currentId][neighbor] > 0) {
                    double tentativeGScore = gScore[currentId] + graph[currentId][neighbor];

                    if (tentativeGScore < gScore[neighbor]) {
                        previous[neighbor] = currentId;
                        gScore[neighbor] = tentativeGScore;
                        fScore[neighbor] = gScore[neighbor] + heuristic[neighbor];
                        openSet.offer(new Node(neighbor, fScore[neighbor], new ArrayList<>()));
                    }
                }
            }
        }

        return new ArrayList<>();
    }

    private static List<Integer> reconstructPath(int[] previous, int start, int end) {
        List<Integer> path = new ArrayList<>();
        for (int at = end; at != start; at = previous[at]) {
            path.add(at);
        }
        path.add(start);
        Collections.reverse(path);
        return path;
    }
}
