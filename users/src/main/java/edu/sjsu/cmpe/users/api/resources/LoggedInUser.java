package edu.sjsu.cmpe.users.api.resources;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.OPTIONS;
//import javax.ws.rs.DELETE;
//import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.GET;
//import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
//import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import net.vz.mongodb.jackson.JacksonDBCollection;
import net.vz.mongodb.jackson.DBCursor;

import org.eclipse.jetty.util.ajax.JSON;
import org.jongo.Find;

import com.google.common.collect.Lists;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
//import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
//import com.yammer.dropwizard.jersey.params.IntParam;
import com.yammer.metrics.annotation.Timed;

import edu.sjsu.cmpe.mongo.config.MongoConfig;
import edu.sjsu.cmpe.users.dao.MongoDao;
import edu.sjsu.cmpe.users.domain.UserDetails;
import edu.sjsu.cmpe.users.domain.UserDetails;

@Path("/v1/loggedin")
@Produces(value = MediaType.APPLICATION_JSON)
@Consumes(value = MediaType.APPLICATION_JSON)

public class LoggedInUser {
	private int authenticated = 400;
		
		//client = new MongoDao();
		JacksonDBCollection<UserDetails, String> users;
		public LoggedInUser(JacksonDBCollection<UserDetails, String> users)
		{
			this.users = users;
		}
		
			
		 	@GET
		    @Produces(value = MediaType.APPLICATION_JSON)
		    @Timed
		    public List<UserDetails> index() {
		        DBCursor<UserDetails> dbCursor = users.find();
		        List<UserDetails> Users = new ArrayList<UserDetails>();
		        while (dbCursor.hasNext()) {
		        	UserDetails User = dbCursor.next();
		            Users.add(User);
		        }
		        return Users;
		    }
		 	
			
		 	@OPTIONS
		 	    public Response getOptions() {
		 	    return Response.ok()
		 	      .header("Access-Control-Allow-Origin", "*")
		 	      .header("Access-Control-Allow-Methods", "POST, GET, PUT, UPDATE, OPTIONS")
		 	      .header("Access-Control-Allow-Headers", "Origin,Content-Type, Accept, X-Requested-With").build();
		 	}
		 	
			
		 	@Path("/{sjsuId}")
		 	@POST
		    @Timed
		    public Response publishNewLocation(UserDetails User,@PathParam("sjsuId")String id) {
		 		
		 		BasicDBObject query = new BasicDBObject();
				query.put("sjsuId", id);
				System.out.println(id);
		        DBCursor<UserDetails> dbCursor = users.find(query);
		        System.out.println(User.getLatitude()+","+User.getLongitude());
		        users.remove(query);
		        //System.out.println(dbCursor.curr() != null);
		       /* if (dbCursor.curr() != null) 
		        {
		        	System.out.println(dbCursor.next().getFirstName().toString());
		        	BasicDBObject newInfo = new BasicDBObject();
		        	newInfo.put("latitude", User.getLatitude());
		        	newInfo.put("longitude", User.getLongitude());
		        	users.update(query, newInfo);
		        //}
		        //else
		       */
		        users.insert(User);
		        
		        return Response.noContent().build();
	
											}
			
			
}
 

