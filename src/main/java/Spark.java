import static spark.Spark.*;
import service.ComputadorService;

public class Spark {

	private static ComputadorService produtoService = new ComputadorService();
	
	
	public static void main(String[] args) {
		
		port(4578);
		
        staticFiles.location("/public");
        
        post("/produto/insert", (request, response) -> produtoService.insert(request, response));

        get("/produto/:id", (request, response) -> produtoService.get(request, response));
        
        get("/produto/list/:orderby", (request, response) -> produtoService.getAll(request, response));

        get("/produto/update/:id", (request, response) -> produtoService.getToUpdate(request, response));
        
        post("/produto/update/:id", (request, response) -> produtoService.update(request, response));
           
        get("/produto/delete/:id", (request, response) -> produtoService.delete(request, response));
	}
}
