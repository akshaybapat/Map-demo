package edu.sjsu.cmpe.users;


import org.eclipse.jetty.servlets.CrossOriginFilter;

import net.vz.mongodb.jackson.JacksonDBCollection;

import com.mongodb.DB;
import com.mongodb.Mongo;
import com.sun.jersey.api.client.Client;
import com.yammer.dropwizard.Service;
import com.yammer.dropwizard.assets.AssetsBundle;
import com.yammer.dropwizard.config.Environment;
//import com.yammer.dropwizard.json.Json;


import com.yammer.dropwizard.config.Bootstrap;
import com.yammer.dropwizard.config.FilterBuilder;


//import org.apache.http.NameValuePair;
//import org.apache.http.message.BasicNameValuePair;



//import edu.sjsu.cmpe.users.config.CustomJson;
import edu.sjsu.cmpe.users.config.UserServiceConfiguration;
import edu.sjsu.cmpe.users.dao.MongoHealthCheck;
import edu.sjsu.cmpe.users.dao.MongoManaged;
import edu.sjsu.cmpe.users.domain.EventDetails;
import edu.sjsu.cmpe.users.domain.UserDetails;
import edu.sjsu.cmpe.users.api.resources.EventResource;
import edu.sjsu.cmpe.users.api.resources.LoggedInUser;
import edu.sjsu.cmpe.users.api.resources.UserResource;


public class UsersService extends Service<UserServiceConfiguration> {

	public static void main(String[] args) throws Exception {
	
		new UsersService().run(args);
	}
	
	  @Override
	    public void initialize(Bootstrap<UserServiceConfiguration> bootstrap) {
		bootstrap.setName("users");
		bootstrap.addBundle(new AssetsBundle("/assets", "/"));
	    }
	
	
	@Override
    public void run(UserServiceConfiguration configuration,
	    Environment environment) throws Exception {
		Mongo mongo = new Mongo(configuration.mongohost, configuration.mongoport);
        MongoManaged mongoManaged = new MongoManaged(mongo);
        environment.manage(mongoManaged);
 
        environment.addHealthCheck(new MongoHealthCheck(mongo));
        DB db = mongo.getDB(configuration.mongodb);
        JacksonDBCollection<EventDetails, String> events = JacksonDBCollection.wrap(db.getCollection("events"), EventDetails.class, String.class);
        JacksonDBCollection<UserDetails, String> users = JacksonDBCollection.wrap(db.getCollection("users"), UserDetails.class, String.class);
        JacksonDBCollection<UserDetails, String> loggedIn = JacksonDBCollection.wrap(db.getCollection("loggedIn"), UserDetails.class, String.class);
        
        environment.addResource(new EventResource(events));
        environment.addResource(new UserResource(users));
        environment.addResource(new LoggedInUser(loggedIn));
        FilterBuilder filterConfig = environment.addFilter(CrossOriginFilter.class, "/*");
        filterConfig.setInitParam(CrossOriginFilter.PREFLIGHT_MAX_AGE_PARAM, String.valueOf(60*60*24)); // 1 day - jetty-servlet CrossOriginFilter will convert to Int.
        filterConfig.setInitParam(CrossOriginFilter.ALLOWED_ORIGINS_PARAM, "http://localhost"); // comma separated list of allowed origin domains
        filterConfig.setInitParam(CrossOriginFilter.ACCESS_CONTROL_ALLOW_HEADERS_HEADER, "origin,content-type,accept,authorization,x-requested-with");
        
 
        
    }
	
	}
