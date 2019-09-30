package modelo;

/**
 * .: ZUP - Teste Desenvolvedor JR :.
 * Autor: Sérgio Guimarães Costa Filho
 * Data: 25/09/2019
 */
public class POI {
    
    private int id;
    private String nome;
    private int coordenadaX;
    private int coordenadaY;
    
    public int getId()
    {
        return id;
    }
    
    public void setId(int id)
    {
        this.id = id;
    }
        
    public String getNome()
    {
        return nome;
    }
    
    public void setNome(String nome)
    {
      this.nome = nome;
    }
    
    public int getCoordenadaX()
    {
        return coordenadaX;
    }
    
    public void setCoordenadaX(int coordenadaX)
    {
        this.coordenadaX = coordenadaX;
    }
    
    public int getCoordenadaY()
    {
        return coordenadaY;
    }
    
    public void setCoordenadaY(int coordenadaY)
    {
        this.coordenadaY = coordenadaY;
    }
    
}