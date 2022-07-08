import java.util.ArrayList;
import java.util.Arrays;

public class DAG {
    CSVReader CSVObject;
    Graph G;
    ArrayList<Malla> Data;

    DAG(CSVReader CSVObject, Graph G){
        this.CSVObject = CSVObject;
        this.G = G;
    }

    void BuildGraph(){
        ArrayList<Malla> Data = this.CSVObject.DataIntoArray();
        for(int i=1; i<=this.CSVObject.DataSize; i++){
            this.G.addVertex(i-1, Data.get(i).materia);
            this.G.addVertex_adj(i-1, Integer.parseInt(Data.get(i).indice));
        }

        String prerequisto;
        ArrayList<String> tokenPrq = new ArrayList<>();
        String[] strSplit;


        for(int i=1; i<= this.CSVObject.DataSize; i++){
            prerequisto = Data.get(i).prerequisito;
            if(!prerequisto.equals("N/A")){
                prerequisto = prerequisto.replace("\"", "");
                strSplit = prerequisto.split("-");
                tokenPrq = new ArrayList<String>(Arrays.asList(strSplit));

                for(String s: tokenPrq){
                    for(int j=1; j<=this.CSVObject.DataSize; j++){
                        if(Data.get(j).materia.equals(s.trim())) {
                            this.G.addEdge(Integer.parseInt(Data.get(j).indice), Data.get(i).materia);
                            this.G.addEdge_code(Integer.parseInt(Data.get(j).indice), Integer.parseInt(Data.get(i).indice));
                        }
                    }
                }
            }
        }

        this.Data =  Data;
    }

    String TopologicalSort(){

        String order = "";

        Object[] topologicalSort =  this.G.topologicalSort();
        int counter = this.CSVObject.DataSize-1;
        while(counter != 0){
            for(int i=1; i<=CSVObject.DataSize ; i++){
                if(this.Data.get(i).indice.equals(topologicalSort[counter].toString())){
                    order += this.Data.get(i).materia + "\n" ;
                }
            }
            counter--;
        }
        return order;
    }




    public static void main(String[] args) {

        // Load data into CSV java object
        CSVReader CSVObject = new CSVReader(";", "src/malla_curricular.csv");
        // Load CSV Java object into Graph object
        CSVObject.updateBuffer();
        Graph g = new Graph(CSVObject.DataSize);
        // Create a Directed Acyclic Graph with all of its functionalities.
        DAG dag = new DAG(CSVObject, g);
        // Build the graph from CSVObject and Graph class.
        dag.BuildGraph();

        // Print topological order
        System.out.println(dag.TopologicalSort());





    }
}
