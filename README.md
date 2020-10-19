## Hierholzer's Algorithm!
Given a directed Eulerian graph, print an Euler circuit. Euler circuit is a path that traverses every edge of a graph, and the path ends on the starting vertex.
he same problem can be solved using  Fleury’s Algorithm,however, its complexity is O(E*E). Using Heirholzer’s Algorithm, we can find the circuit/path in O(E).

Given graph has a Eulerian cycle if the following conditions are true 
1. All vertices with nonzero degrees belong to a single strongly connected 			   component.
 2. In degree and out-degree of every vertex is the same. The algorithm assumes that the given graph has a Eulerian Circuit.

Choose any starting vertex v, and follow a trail of edges from that vertex until returning to v. It is not possible to get stuck at any vertex other than v, because indegree and outdegree of every vertex must be same, when the trail enters another vertex w there must be an unused edge leaving w.  
    The tour formed in this way is a closed tour, but may not cover all the vertices and edges of the initial graph.
-   As long as there exists a vertex u that belongs to the current tour, but that has adjacent edges not part of the tour, start another trail from u, following unused edges until returning to u, and join the tour formed in this way to the previous tour.
- Thus the idea is to keep following unused edges and removing them until we get stuck. Once we get stuck, we backtrack to the nearest vertex in our current path that has unused edges, and we repeat the process until all the edges have been used. We can use another container to maintain the final path.

This algorithm is not so easy and obvious so I found very useful video with better
explanation what is going on under the hood. It is a little long but definitely worth watching.
https://www.youtube.com/watch?v=8MpoO2zA2l4
Time Complexity  O(V+E).
My implementation is based on a graph which is generated I a way it always has a Euler's circle. So the result always will be successful.
Fell free to download the code and try it yourself. !
