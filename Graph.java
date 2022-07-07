import java.util.*;

/**
 * Esta clase representa un grafo por medio de una lista de adyacencia.
 */

public class Graph {
    private int V; // Numero de vertices
    private LinkedList<String> adj[];  // Lista de adyacencia para guardar los valores de los vertices
    private LinkedList<Integer> adj_code[];  // Lista de adyacencia del identificador de vertices

    // Constructor
    Graph(int numVertices) {
        V = numVertices;
        adj = new LinkedList[numVertices];
        for (int i = 0; i < numVertices; i++) {
            adj[i] = new LinkedList();
        }

        adj_code = new LinkedList[numVertices];
        for (int i = 0; i < numVertices; i++) {
            adj_code[i] = new LinkedList();
        }

    }

    // Metodos para añadir vertice e identificador de vertices
    void addVertex(int i, String n) {
        adj[i].add(n);
    }

    void addVertex_adj(int i, int n) {
        adj_code[i].add(n);
    }

    //Funcion para agregar una nueva arista e identificador de arista
    void addEdge(int i, String w) {
        adj[i].add(w);
    }

    void addEdge_code(int i, int w) {
        adj_code[i].add(w);
    }

    // Metodo complementario para controlar los vertices visitados
    // Este metodo trabaja con la lista de adyacencia de identificadores
    // Almacena el orden topologico en el stack
    void topologicalSortUtil(int v, boolean visit[], Stack stack) {
        // Marca el vertice actual o inicial como visitado
        visit[v] = true;
        Integer i;

        // Visita todos los vertices adyacentes a un identificador de vertice elegido
        // Elemntos de una linked list.
        Iterator<Integer> it = adj_code[v].iterator();
        while (it.hasNext()) {
            i = it.next();
            if (!visit[i]) {
                topologicalSortUtil(i, visit, stack);
            }
        }

        // Lo almacena en un stack
        stack.push(v);
    }

    // Metodo de ordanamiento topologico
    Object[] topologicalSort() {
        ArrayList<Integer> ordering = new ArrayList<Integer>();
        Stack stack = new Stack();

        // Marco todos los vertices como no visitados
        boolean visited[] = new boolean[V];
        for (int i = 0; i < V; i++)
            visited[i] = false;

        // Llama a la funcion complementaria y empieza a guardar el orden topologico
        for (int i = 0; i < V; i++)
            if (!visited[i])
                topologicalSortUtil(i, visited, stack);


        Object[] arr = stack.toArray();
        return arr;
    }
}


