import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Graph {

    private int vertices;
    private ArrayList<Integer>[] adj;


    Graph(int numOfVertices) {
        this.vertices = numOfVertices;
        initGraph();
    }

    public void initGraph() {
        adj = new ArrayList[vertices];
        for (int i = 0; i < vertices; i++) {
            adj[i] = new ArrayList<>();
        }
    }

    public void generateGraph(double density) {

        int counter = 1;

        int max = (int) Math.floor((vertices * (vertices - 1) / 2) * density) -2 ;
        for (int i = 0 ; i < vertices - 1; i++){
            if (counter == max ){
                break;
            }
            if(counter +1 == max || counter + 1 == vertices ){
                addEdge(i, 0);

            }else{
                addEdge(i, i+1);
            }
            counter++;
        }

        while(counter != max && ( counter + 3 < max ) ){
            addThreeEdges();
            counter += 3;
        }

    }

    private void addThreeEdges() {

        List<Integer> independentV = new ArrayList<>();
        int i =0;
        for (int k =0 ; k < vertices - 1 ; k++){
            int firsV = i;
            independentV.add(firsV);
            for (int j  =0 ; j < vertices - 1 ; j++){
                if (firsV !=j && !independentV.contains(j) ){
                    if (!isValidNextEdge(firsV, j)){
                        independentV.add(j);
                        firsV = j;
                    }
                    if (independentV.size() == 3){
                        break;
                    }
                }
            }

            if (independentV.size() == 3){
                connectIndependent(independentV);
                break;
            }else{
                independentV.clear();
            }
            i++;
        }
    }

    private void connectIndependent(List<Integer> independentV) {
        addEdge(independentV.get(0) , independentV.get(1));
        addEdge(independentV.get(1) , independentV.get(2));
        addEdge(independentV.get(0) , independentV.get(2));
    }


    public void addEdge(Integer u, Integer v) {
        adj[u].add(v);
        adj[u].sort(Comparator.comparingInt(a -> a));
        adj[v].add(u);
        adj[v].sort(Comparator.comparingInt(a -> a));

    }


    public void removeEdge(Integer u, Integer v) {
        adj[u].remove(v);
        adj[v].remove(u);
    }

    public void printEulerTour() {
        int u = 0;
        for (int i = 0; i < vertices; i++) {
            if (adj[i].size() % 2 == 1) {
                u = i;
                break;
            }
        }
        printEulerUtil(u);
        System.out.println();
    }

    public void printHierholzer(){
        List<Integer>  solution= new ArrayList<>();
        int start =0;
        Lifo lifo = new Lifo();
        lifo.push(start);
        while(!lifo.isEmpty()){
            if (adj[start].size() !=0){
                int next =  adj[start].get(0);
                removeEdge(start, next);
                lifo.push(next);
                start = next;
            }else{
                lifo.pop();
                solution.add(start);
                if (!lifo.isEmpty()){
                    start = lifo.peek();
                }
            }
        }
        System.out.println(solution);
    }



    private void printEulerUtil(Integer u) {

        for (int i = 0; i < adj[u].size(); i++) {
            Integer v = adj[u].get(i);
            if (isValidNextEdge(u, v)) {
                //System.out.print(u + "-" + v + " ");
                removeEdge(u, v);
                printEulerUtil(v);
            }
        }
    }


    public boolean isValidNextEdge(Integer u, Integer v) {
        if (adj[u].size() == 1) {
            return true;
        }
        boolean[] isVisited = new boolean[this.vertices];
        int count1 = dfsCount(u, isVisited);
        removeEdge(u, v);
        isVisited = new boolean[this.vertices];
        int count2 = dfsCount(u, isVisited);
        addEdge(u, v);
        return count1 <= count2;
    }


    public int dfsCount(Integer v, boolean[] isVisited) {
        isVisited[v] = true;
        int count = 1;
        for (int adj : adj[v]) {
            if (!isVisited[adj]) {
                count = count + dfsCount(adj, isVisited);
            }
        }
        return count;
    }
}
