import org.graphstream.graph.*;
import org.graphstream.algorithm.generator.*;
import java.util.Random;


/**
 * Mapa
 * @Author Fabian Crawford
 * Clase de genera grafo estatico o aleatorio
 */
public class Mapa {

    private Graph graph;

    /**
     * Constructor
     * @param graph Grafo: Contiene la relacion entre puntos del mapa
     */
    public Mapa(Graph graph){
        this.graph = graph;
    }

    /**
     * Establece relaciones fijas para el Single graph
     */
    public void staticMap(){

        Random random = new Random();
        int aleatorio = random.nextInt(20);

        graph.addNode("A");
        graph.addNode("B");
        graph.addNode("C");
        graph.addNode("D");
        graph.addNode("E");
        graph.addNode("F");
        graph.addNode("G");
        graph.addNode("H");
        graph.addNode("I");
        graph.addEdge("AB", "A", "B",false);
        graph.addEdge("BC", "B", "C",false);
        graph.addEdge("CF", "C", "F",false);
        graph.addEdge("BE", "B", "E",false);
        graph.addEdge("CE", "C", "E",false);
        graph.addEdge("ED", "E", "D",false);
        graph.addEdge("DG", "D", "G",false);
        graph.addEdge("DH", "D", "H",false);
        graph.addEdge("GH", "G", "H",false);
        graph.addEdge("EH", "E", "H",false);
        graph.addEdge("HI", "H", "I",false);
        graph.addEdge("EI", "E", "I",false);

        int[] valores = new int[12];
        valores[0] = 1;
        valores[1] = 1;
        valores[2] = 3;
        valores[3] = 1;
        valores[4] = 5;
        valores[5] = 1;
        valores[6] = 1;
        valores[7] = 1;
        valores[8] = 1;
        valores[9] = 1;
        valores[10] = 1;
        valores[11] = 3;

        int i = 0;
        for(Edge e:graph.getEachEdge()) {
            e.addAttribute("time",valores[i]);
            System.out.println(e.getId() + ": " + e.getAttribute("time"));
            i++;
        }

        System.out.println("ID de nodos");
        for (Node n:graph.getEachNode()){
            System.out.println(n.getId() + ": " + n.getIndex());
        }

    }

    /**
     * Contiene la relacion entre puntos del mapa
     */
    public void generateMap() throws Exception{
        Generator gen = new LobsterGenerator(10);
        Random random = new Random();
        int aleatorio;

        gen.addSink(graph);
        gen.begin();
        for(int i=0; i<30; i++) {
            gen.nextEvents();
        }
        gen.end();
        graph.display();

        for(Edge e:graph.getEachEdge()) {
            aleatorio = random.nextInt(20);

            while(aleatorio==0){
                aleatorio = random.nextInt(20);
            }

            e.addAttribute("time",aleatorio);
            System.out.println(e.getId() + ": " + e.getAttribute("time"));
        }

        System.out.println("ID de nodos");
        for (Node n:graph.getEachNode()){
            System.out.println(n.getIndex());
        }
    }

}
