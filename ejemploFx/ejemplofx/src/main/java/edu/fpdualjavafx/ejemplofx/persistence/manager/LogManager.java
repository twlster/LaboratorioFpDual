package edu.fpdualjavafx.ejemplofx.persistence.manager;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

import java.util.ArrayList;
import java.util.List;

import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;

import edu.fpdualjavafx.ejemplofx.persistence.conector.Conector;
import edu.fpdualjavafx.ejemplofx.persistence.model.Log;

/**
 * Manages the query to a the mongo collection Logs
 * @author jose.m.prieto.villar
 *
 */
public class LogManager {

	/**
	 * Finds all the data from the collection.
	 * @return
	 */
	public List<Log> findAll() {
		List<Log> logs = new ArrayList<>();
		//Creates DB Client.
		try (MongoClient cliente = new Conector().getMongoDBClient()) {
			//Obtains db to query.
			MongoDatabase database = cliente.getDatabase("applogs");
			//Create codec registry
			CodecRegistry pojoCodecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(),
					fromProviders(PojoCodecProvider.builder().automatic(true).build()));
			database = database.withCodecRegistry(pojoCodecRegistry);
			database.getCollection("logs", Log.class).find().forEach(logs::add);
			
			return logs;
		}
	}

}
