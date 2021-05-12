package edu.fpdualjavafx.ejemplofx.persistence.conector;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

import java.io.IOException;
import java.util.Properties;

import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

import lombok.Getter;
import lombok.Setter;

/**
 * Class responsible for creation DDBB connections.
 * 
 * @author jose.m.prieto.villar
 *
 */
public class Conector {

	@Setter
	@Getter
	Properties prop = new Properties();

	public Conector() {
		try {
			// Loads all the properties of file "config.properties".
			prop.load(getClass().getClassLoader().getResourceAsStream("config.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Creates the connection object for a MongoDB
	 * 
	 * @return a {@link MongoDatabase}
	 */
	public MongoDatabase getMongoDBDatabase() {
		CodecRegistry pojoCodecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(),
                fromProviders(PojoCodecProvider.builder().automatic(true).build()));
		MongoClientSettings settings = MongoClientSettings.builder()
		        .codecRegistry(pojoCodecRegistry)
		        .build();
		MongoClient mongoClient = MongoClients.create(getURI());
		MongoDatabase database = mongoClient.getDatabase("applog");
		database = database.withCodecRegistry(pojoCodecRegistry);

		return database;

	}

	/**
	 * Obtains the URI to connect to a MySQL DDBB.
	 * 
	 * @return an URI
	 */
	private String getURI() {
		// mongodb+srv://<username>:<password>@<cluster-address>/test
		return new StringBuilder().append(prop.getProperty(MongoDBConstants.URL_PREFIX))
				.append(prop.getProperty(MongoDBConstants.URL_HOST)).append(":")
				.append(prop.getProperty(MongoDBConstants.URL_PORT)).append("/")
				.append(prop.getProperty(MongoDBConstants.URL_SCHEMA)).toString();
	}

}
