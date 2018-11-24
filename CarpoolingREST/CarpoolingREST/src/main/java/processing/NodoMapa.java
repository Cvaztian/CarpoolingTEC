package processing;

/**
 * @class NodoMapa
 * Nodo utilizado para la graficacion y comunicacion cliente-servidor
 */
public class NodoMapa {

    private int iD;
    private boolean parada;
    private boolean vuelta;
    private int tiempo;

    /**
     * Constructor por defecto
     */
    public NodoMapa(){
        this.iD = 0;
        this.parada = false;
        this.vuelta = false;
        this.tiempo = 0;
    }

    /**
     * Constructor
     * @param iD index del nodo
     * @param parada indica True si en este nodo se debe hacer una parada
     * @param vuelta indica True si en este nodo debe voltear el carro
     * @param tiempo tiempo para llegar a este nodo desde el edge anterior
     */
    public NodoMapa(int iD, boolean parada, boolean vuelta, int tiempo){
        this.iD = iD;
        this.parada = parada;
        this.vuelta = vuelta;
        this.tiempo = tiempo;
    }

    //GETTERS

    public int getiD() { return iD;}

    public boolean isParada() { return parada; }

    public boolean isVuelta() { return vuelta; }

    public int getTiempo() { return tiempo; }

    //SETTERS

    public void setiD(int iD) { this.iD = iD; }

    public void setParada(boolean parada) { this.parada = parada; }

    public void setVuelta(boolean vuelta) { this.vuelta = vuelta; }

    public void setTiempo(int tiempo) { this.tiempo = tiempo; }
}