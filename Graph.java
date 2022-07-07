import java.util.*;

/**
 * Esta clase representa un grafo por medio de una lista de adyacencia.
 */

public class Graph{
    private int V; // Numero de vertices
    private LinkedList<String> adj[];  // Lista de adyacencia para guardar los valores de los vertices
    private LinkedList<Integer> adj_code[];  // Lista de adyacencia del identificador de vertices

    // Constructor
    Graph(int numVertices){
        V = numVertices;
        adj = new LinkedList[numVertices];
        for(int i=0; i< numVertices; i++){
            adj[i] = new LinkedList();
        }

        adj_code = new LinkedList[numVertices];
        for(int i=0; i< numVertices; i++){
            adj_code[i] = new LinkedList();
        }

    }

    // Metodos para añadir vertice e identificador de vertices
    void addVertex(int i, String n){
        adj[i].add(n);
    }
    void addVertex_adj(int i, int n){
        adj_code[i].add(n);
    }
    //Funcion para agregar una nueva arista e identificador de arista
    void addEdge( int i , String w){
        adj[i].add(w);
    }
    void addEdge_code( int i , int w){
        adj_code[i].add(w);
    }

    // Metodo complementario para controlar los vertices visitados
    // Este metodo trabaja con la lista de adyacencia de identificadores
    // Almacena el orden topologico en el stack
    void topologicalSortUtil(int v, boolean visit[], Stack stack)
    {
        // Marca el vertice actual o inicial como visitado
        visit[v] = true;
        Integer i;

        // Visita todos los vertices adyacentes a un identificador de vertice elegido
        // Elemntos de una linked list.
        Iterator<Integer> it = adj_code[v].iterator();
        while(it.hasNext()){
            i = it.next();
            if (!visit[i]){
                topologicalSortUtil(i, visit, stack);
            }
        }

        // Lo almacena en un stack
        stack.push(v);
    }

    // Metodo de ordanamiento topologico
    Object[] topologicalSort()
    {
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


    public static void main(String[] args) {

        // Instancia de objetos
        CSVReader CSVObject = new CSVReader(";", "src/malla_curricular.csv");
        CSVObject.updateBuffer();
        ArrayList<Malla> Data = CSVObject.DataIntoArray();
        Graph g = new Graph(CSVObject.DataSize);


        // Añade vertice e identificador de vertices
        for(int i=1; i<=CSVObject.DataSize ; i++){
           g.addVertex(i-1, Data.get(i).materia);
           g.addVertex_adj(i-1, Integer.parseInt(Data.get(i).indice));
        }


        // Tratamiendo de prerequisitos
        String prerequisto;
        ArrayList<String> tokenPrq = new ArrayList<String>();
        String[] strSplit;
        for(int i=1; i<=CSVObject.DataSize ; i++){
            prerequisto = Data.get(i).prerequisito; // String de prerequisitos
            if(!prerequisto.equals("N/A")){ // Verifican que existan prerequisitos
                // Tokeniza el String de prerequisitos
                prerequisto = prerequisto.replace("\"", "");
                strSplit = prerequisto.split("-");
                tokenPrq = new ArrayList<String>(Arrays.asList(strSplit));



                // Toma el string tokenizado de prerequisitos y lo añade al grafo
                for(String s: tokenPrq){
                    for(int j=1; j<=CSVObject.DataSize ; j++){
                        if(Data.get(j).materia.equals(s.trim())){
                            System.out.println("Indice: " + Data.get(j).indice + "(" + Data.get(j).materia + ")" + " ---> " + " Materia: " + Data.get(i).materia);
                            g.addEdge(Integer.parseInt(Data.get(j).indice), Data.get(i).materia);
                            g.addEdge_code(Integer.parseInt(Data.get(j).indice), Integer.parseInt(Data.get(i).indice) );
                        }
                    }
                }
            }
        }


        // Imprime el ordenamiento topologico
        System.out.println();
        System.out.println();
        Object[] topologicalSort =  g.topologicalSort();
        int counter = CSVObject.DataSize-1;
        while(counter != 0){
            for(int i=1; i<=CSVObject.DataSize ; i++){
                if(Data.get(i).indice.equals(topologicalSort[counter].toString())){
                    System.out.print(Data.get(i).materia + " ~~> ");
                }
            }
            counter--;
        }

    }
}

