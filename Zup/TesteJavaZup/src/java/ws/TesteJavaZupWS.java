package ws;

import dao.POIDAO;
import modelo.POI;
import com.google.gson.Gson;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;

/**
 * .: ZUP - Teste Desenvolvedor JR :.
 * Autor: Sérgio Guimarães Costa Filho
 * Data: 25/09/2019
 */
@Path("TesteJavaZup")
public class TesteJavaZupWS {

    @Context
    private UriInfo context;

    /** Creates a new instance of TesteJavaZupWS */
    public TesteJavaZupWS() {
    }

    /**
     * Serviço para cadastrar pontos de interesse, com 3 atributos: Nome do POI, coordenada X
     * (inteiro não negativo) e coordenada Y (inteiro não negativo). Os POIs devem ser armazenados
     * em uma base de dados.
     */
    @PUT
    @Consumes("application/json")
    @Path("POI/insert/{nome}/{coordenadaX}/{coordenadaY}")
    public String CadastrarPOI(@PathParam("nome") String nome, @PathParam("coordenadaX") int coordenadaX, @PathParam("coordenadaY") int coordenadaY) {

        POIDAO dao = new POIDAO();
        String retornoExecucao = dao.CadastrarPOI(nome, coordenadaX, coordenadaY);

        Gson g = new Gson();
        return g.toJson(retornoExecucao);
    }

    /**
     * Serviço para listar todos os POIs cadastrados.  
     */
    @GET
    @Produces("application/json")
    @Path("POI/list")
    public String ListarPOI() {

        POIDAO dao = new POIDAO();
        List<POI> listaPOI = dao.ListarPOI();

        Gson g = new Gson();
        return g.toJson(listaPOI);
    }

    /**
     * Serviço para listar POIs por proximidade. Este serviço receberá uma coordenada X e uma coordenada Y,
     * especificando um ponto de referência, em como uma distância máxima (dmax) em metros. O serviço deverá retornar 
     * todos os POIs da base de dados que estejam a uma distância menor ou igual a d-max a partir do ponto de referência.      
     */
    @GET
    @Produces("application/json")
    @Path("POI/get/{coordenadaX}/{coordenadaY}/{distancia}")
    public String ListarPOIProximidade(@PathParam("coordenadaX") int coordenadaX, @PathParam("coordenadaY") int coordenadaY, @PathParam("distancia") int distancia) {

        POIDAO dao = new POIDAO();
        List<String> listaPOI = dao.ListarPOIProximidade(coordenadaX, coordenadaY, distancia);

        Gson g = new Gson();
        return g.toJson(listaPOI);
    }
}