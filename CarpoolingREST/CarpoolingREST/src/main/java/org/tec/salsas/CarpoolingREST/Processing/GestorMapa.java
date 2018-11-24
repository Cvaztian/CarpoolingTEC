package org.tec.salsas.CarpoolingREST.Processing;

import org.graphstream.algorithm.APSP;
import org.graphstream.graph.*;
import java.util.LinkedList;
import java.util.List;


/**
 * @class GestorMapa
 * Se encarga de identificar, analizar y optimizar una ruta entre dos puntos, considerando si
 * en el recorrido debe pasar por amigos
 */
public class GestorMapa {

    //FUNCION PRINCIPAL
    /**
     * @param graph Grafo: Contiene la relacion entre puntos del mapa
     * @param ruta LinkedList<NodoMapa> que contiene los puntos inicial y final y amigos a recoger
     * @return retorna una lista con la ruta optima entre dos puntos considerando amigos que deba recoger
     */
    public static LinkedList<NodoMapa> rutaOptima(Graph graph, LinkedList<NodoMapa> ruta){

        int origen = ruta.get(0).getiD();
        int destino = ruta.get(1).getiD();
        int listSize = ruta.size();
        LinkedList<NodoMapa> rutaFinalOptima = new LinkedList<>();

        if (listSize < 2){
            return null;
        }

        else if(listSize == 2){
            rutaFinalOptima = convertPathToLinkedList(rutaOptimaAux(graph, origen,destino));
        }

        else if (listSize>2){

            boolean flag = verificadorVecinosEnRutaOptima(graph,ruta);

            if (flag == false){
                rutaFinalOptima = convertPathToLinkedList(rutaOptimaAux(graph, origen, destino));

                System.out.println("Vecinos (TODOS En ruta optima): ");
                for (int i = 2; i < listSize; i++){
                    System.out.println(ruta.get(i).getiD());
                }

                Path path= rutaOptimaAux(graph, origen, destino);
                LinkedList<NodoMapa> rutaOptima = convertPathToLinkedList(path);

                System.out.println("Ruta optima");
                System.out.println(path);

                System.out.println("Lista enlazada con vecinos y valores del tiempo: ");

                for (NodoMapa nodo:rutaFinalOptima){
                    System.out.println("nodo " + nodo.getiD() + ": " + nodo.getTiempo());
                }
            }

            else{
                int cRutasPorEnlazar = listSize - 2;
                int cPosiblesRutas = 0;
                Path posiblePath;
                LinkedList<NodoMapa> rutaProvisional = new LinkedList<>(); //ruta utilizada para iterar

                for (int i = 2; i<listSize; i++){
                    NodoMapa nodo = new NodoMapa(ruta.get(i).getiD(),false,false,ruta.get(i).getTiempo());
                    rutaProvisional.add(nodo);
                }

                rutaFinalOptima.add(ruta.getFirst()); //ANADE PRIMER ELEMENTO DE RUTA A RUTA FINAL

                LinkedList<LinkedList<NodoMapa>> listaListas = new LinkedList<>();
                int origenProvisional = ruta.get(0).getiD();

                while(cRutasPorEnlazar>0){

                    for (int i = 0; i<cRutasPorEnlazar; i++){ //Añade posibles cominos a lista de Listas

                        posiblePath = rutaOptimaAux(graph,origenProvisional, rutaProvisional.get(i).getiD());
                        listaListas.add(convertPathToLinkedList(posiblePath));
                    }

                    int idListaMenor = 0;
                    int sumador = 0; //para sumar tiempo total de la ruta
                    int comparador = 0;
                    for (int i = 0; i<cRutasPorEnlazar; i++){ //Evalua cual es el camino mas corto

                        LinkedList<NodoMapa> listaEvaluada = listaListas.get(i);

                        for (NodoMapa n: listaEvaluada){ sumador += n.getTiempo(); }

                        if (i==0){
                            comparador = sumador;
                            idListaMenor = 0;
                        }

                        else if (sumador<comparador){
                            comparador = sumador;
                            idListaMenor = i;
                        }
                        sumador = 0;
                    }
                    // OBTIENE LISTA MAS CORTA
                    LinkedList<NodoMapa> listaMasCorta = listaListas.get(idListaMenor);

                    //CAMBIA EL ORIGEN AL ULTIMO NODO DE ESTA RUTA CORTA
                    origenProvisional = listaMasCorta.getLast().getiD();

                    //ELIMINA EL NODO COMO POSIBLE RUTA
                    rutaProvisional.remove(idListaMenor);

                    //ANADE LISTA CORTA A LISTA FINAL (excepto primer elemento)
                    int sizeRutaCorta = listaMasCorta.size();
                    for (int i = 1; i<sizeRutaCorta; i++){
                        rutaFinalOptima.add(listaMasCorta.get(i));
                    }

                    listaListas.clear();
                    cRutasPorEnlazar-=1;
                }

                origenProvisional = rutaFinalOptima.getLast().getiD();

                posiblePath = rutaOptimaAux(graph,origenProvisional,ruta.get(1).getiD());
                rutaProvisional = convertPathToLinkedList(posiblePath);

                int sizeRutaCorta = rutaProvisional.size();
                for (int i = 1; i<sizeRutaCorta; i++){
                    rutaFinalOptima.add(rutaProvisional.get(i));
                }

                System.out.println("Vecinos (NO TODOS En ruta optima): ");
                for (int i = 2; i < listSize; i++){
                    System.out.println(ruta.get(i).getiD());
                }

                Path path= rutaOptimaAux(graph, origen, destino);
                LinkedList<NodoMapa> rutaOptima = convertPathToLinkedList(path);

                System.out.println("Ruta optima");
                System.out.println(path);

                System.out.println("Lista enlazada con vecinos y valores del tiempo: ");


            }
        }

        asignaBooleanParada(ruta,rutaFinalOptima);
        asignaBooleanVuelta(rutaFinalOptima);

        for (NodoMapa nodo:rutaFinalOptima){
            System.out.println("nodo " + nodo.getiD() + ": " + nodo.isVuelta());
        }
        return rutaFinalOptima;
    }

    //FUNCIONES SECUNDARIAS
    /**
     * @param listaVecinos ruta inicial que contiene los puntos inicial y final y amigos a recoger
     * @param rutaOptimaFinal LinkedList<NodoMapa> que contiene la ruta optima final
     */
    public static void asignaBooleanParada(LinkedList<NodoMapa> listaVecinos, LinkedList<NodoMapa> rutaOptimaFinal){

        int listSize = listaVecinos.size();

        for (int i = 2; i<listSize; i++){
            NodoMapa amigo = listaVecinos.get(i);
            for(NodoMapa n : rutaOptimaFinal){
                if (n.getiD() == amigo.getiD()){
                    n.setParada(true);
                    break;
                }
            }
        }
    }

    /**
     * @param rutaOptimaFinal LinkedList<NodoMapa> que contiene la ruta optima final
     */
    public static void asignaBooleanVuelta(LinkedList<NodoMapa> rutaOptimaFinal){

        int listSize = rutaOptimaFinal.size()-1;
        int anterior = 0;
        int posterior = 0;
        for(int i=1;i<listSize;i++){

            anterior = rutaOptimaFinal.get(i-1).getiD();
            posterior = rutaOptimaFinal.get(i+1).getiD();

            if (anterior == posterior){
                rutaOptimaFinal.get(i).setVuelta(true);
            }

        }

    }

    /**
     * @param graph Grafo: Contiene la relacion entre puntos del mapa
     * @param origen index del nodo origen
     * @param destino index del nodo destino
     * @return retorna instancia tipo Path con la ruta mas corta entre dos puntos
     */
    public static Path rutaOptimaAux(Graph graph, int origen, int destino){
        APSP apsp = new APSP();
        apsp.init(graph); // registering apsp as a sink for the graph
        apsp.setDirected(false); // undirected graph
        apsp.setWeightAttributeName("time"); // ensure that the attribute name used is "weight"
        apsp.compute(); // the method that actually computes shortest paths
        APSP.APSPInfo info = graph.getNode(origen).getAttribute(APSP.APSPInfo.ATTRIBUTE_NAME);

        String idDestino = graph.getNode(destino).getId();

        Path camino = info.getShortestPathTo(idDestino);

        return camino;
    }

    public static LinkedList<NodoMapa> convertPathToLinkedList(Path path){

        LinkedList<NodoMapa> rutaOptima = new LinkedList<>();

        assignIdPathToList(rutaOptima,path);
        assignTiempoToList(rutaOptima,path);

        return rutaOptima;
    }

    /**
     * @param lista lista que contiene los nodos a los que se les modifica el atributo tiempo
     * @param path Path del que se obtiene el tiempo (peso) de las aristas entre nodos
     */
    public static void assignTiempoToList(LinkedList<NodoMapa> lista, Path path){

        List<Edge> pathEdge = path.getEdgePath();
        int i=1;
        int suma = 0;

        for(Edge e:pathEdge){
            suma = (int) e.getAttribute("time") ;
            lista.get(i).setTiempo(suma);
            i++;
        }
    }

    /**
     * @param lista lista a la que se le agregan los nodos tipo NodoMapa
     * @param ruta Path del que se obtiene el iD de los Nodos
     */
    public static void assignIdPathToList(LinkedList<NodoMapa> lista, Path ruta){
        for (Node n : ruta.getEachNode()){
            lista.add(new NodoMapa(n.getIndex(),false,false,0));
        }
    }

    /**
     * @param graph Grafo: Contiene la relacion entre puntos del mapa
     * @param ruta LinkedList<NodoMapa> que contiene los puntos inicial y final y amigos a recoger
     * @return retorna True si hay nodos vecinos (amigos) fuera de la rutaOptima calculada
     */
    public static boolean verificadorVecinosEnRutaOptima(Graph graph, LinkedList<NodoMapa> ruta){
        int origen = ruta.get(0).getiD();
        int destino = ruta.get(1).getiD();
        int listSize = ruta.size();
        boolean verificadorVecinoEnRutaOpt = false;
        boolean flag = false;
        Path rutaOptima = rutaOptimaAux(graph, origen, destino);//ruta optima

        for (int i = 2; i < listSize; i++) { //revise que el elmento de la ruta

            for (Node n : rutaOptima.getNodeSet()) {
                if (n.getIndex() != origen && n.getIndex() != destino) { //si el elemento de la ruta es distinto al origen o destino
                    if (ruta.get(i).getiD()==n.getIndex()){
                        verificadorVecinoEnRutaOpt = true;
                    }
                }
            }
            if (!verificadorVecinoEnRutaOpt){ //si hay un vecino fuera de la rutaOptima
                flag = true;
            }
            verificadorVecinoEnRutaOpt = false;
        }
        return flag;
    }
}