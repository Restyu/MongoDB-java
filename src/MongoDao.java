import java.util.Date;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class MongoDao {

	private MongoClient cliente;
	private MongoCollection<Document> col;
	
	public String conexion() {
		
		cliente = new MongoClient("localhost");
		MongoDatabase db = cliente.getDatabase("pais");
		col = db.getCollection("ciudades");
		
		String res = "";
		FindIterable<Document> it = col.find();
			for (Document d : it)
				res += (d.toString() + "\n");
			
			return res;
	}
	
	
	public String insertar(String ciudad , int poblacion , String ultimo_censo , String famosa , String alcalde , String partido){
		
		String res = "Se ha insertado correctamente.\n";
		Document evento = new Document("nombre", ciudad);
		evento.append("poblacion", poblacion);
		evento.append("ultimo_censo", ultimo_censo);
		evento.append("famosa_por", famosa);
		evento.append("alcalde", alcalde );
		col.insertOne(evento);
		return res;
		
	}
	
	
	
	public String salir() {
		cliente.close();
		return "Desconectado...";
	}


	public String consultar(String nombre) {
		
		String res = "";
		
			FindIterable<Document> it = col.find().sort(new Document("famosa_por", 1));
			for (Document d : it)
				res += (d.toString() + "\n");
	
		return res;
		
	}



	public String borrarCiudad(String nombre) {
	
		
		String res = "Se ha borrado correctamente laciudad";
		col.deleteOne(new Document("nombre", nombre));
		return res;
		
	}
	
	
	public String borrarCiudades(String nombre) {
	
		
		String res = "Se ha borrado correctamente laciudad";
		col.deleteMany(new Document("nombre", nombre));
		return res;
		
	}
	
	public String actualizaMuchos(String nombre, Date fecha, int poblacion) {
		
		String res = "Se ha actualizado correctamente";
		col.updateMany(new Document("nombre", nombre), new Document("$set", new Document("poblacion", poblacion)));
		col.updateMany(new Document("nombre", nombre), new Document("$set", new Document("ultimo_censo", fecha)));
		return res;
	}
	
	
}
