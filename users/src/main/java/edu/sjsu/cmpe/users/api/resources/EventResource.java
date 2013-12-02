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
import edu.sjsu.cmpe.users.domain.EventDetails;

@Path("/v1")
@Produces(value = MediaType.APPLICATION_JSON)
@Consumes(value = MediaType.APPLICATION_JSON)

public class EventResource {
	private int authenticated = 400;
		
		//client = new MongoDao();
		JacksonDBCollection<EventDetails, String> events;
		public EventResource(JacksonDBCollection<EventDetails, String> events)
		{
			this.events = events;
		}
		
			@Path("/events")
		 	@GET
		    @Produces(value = MediaType.APPLICATION_JSON)
		    @Timed
		    public List<EventDetails> index() {
		        DBCursor<EventDetails> dbCursor = events.find();
		        List<EventDetails> events = new ArrayList<EventDetails>();
		        while (dbCursor.hasNext()) {
		        	EventDetails event = dbCursor.next();
		            events.add(event);
		        }
		        return events;
		    }
		 	
			@Path("/events")
		 	@OPTIONS
		 	    public Response getOptions() {
		 	    return Response.ok()
		 	      .header("Access-Control-Allow-Origin", "*")
		 	      .header("Access-Control-Allow-Methods", "POST, GET, PUT, UPDATE, OPTIONS")
		 	      .header("Access-Control-Allow-Headers", "Origin,Content-Type, Accept, X-Requested-With").build();
		 	}
		 	
			
			@Path("/events")
		 	@POST
		    @Timed
		    public Response publishNewBlog(@Valid EventDetails event) {
		        events.insert(event);
		        return Response.noContent().build();
		    }
    /*
	@Path("/users")
	@Produces(MediaType.APPLICATION_JSON)
    @GET
    public Response getAllUsers() throws Exception
   {
		//MongoDao client = new MongoDao();
		//Iterable<UserDetails> userDetails = null;
      	//userDetails = client.find("usercollection").as(UserDetails.class);
      	return Response.status(200).build();
		
	    
   }
   
 
	@Path("/users")
	//@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@POST
	public Response createUser(UserDetails user) throws FileNotFoundException, IOException 
	{
		//MongoDao client = new MongoDao();
		//client.saveData("usercollection", user);
        return Response.status(201).build();
	}
	
	
	@POST
	@Path("users/authenticate")
	//@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response authenticateUser(UserDetails user) throws FileNotFoundException, IOException
	{
		//MongoDao client = new MongoDao();
		BasicDBObject query = new BasicDBObject();
        List<BasicDBObject> queryList = new ArrayList<BasicDBObject>();
        queryList.add(new BasicDBObject("username", user.getSjsuId()));
        queryList.add(new BasicDBObject("password", user.getPassword()));
        query.put("$and", queryList);
        Find result = client.findData("usercollection", query.toString());
        if(!(result.toString()==null)) authenticated = 200;
		return Response.status(authenticated).build();
	}
	
	
	
	@Path("/events")
	@Produces(MediaType.APPLICATION_JSON)
    @GET
    @Timed(name="get-all-events")
    public Response getAllEvents() throws Exception
   {
	
		//MongoDao client = new MongoDao();
		//Iterable<EventDetails> eventDetails = null;
      	//eventDetails = client.find("eventcollection").as(EventDetails.class);
		return Response.status(200).build();
		
	    
   }
   
 
	@Path("/events")
	//@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@POST
	@Timed(name="create-event")
	public Response CreateEvent(EventDetails event) throws FileNotFoundException, IOException 
	{
		//MongoDao client = new MongoDao();
		//client.saveData("eventcollection", event);
        return Response.status(201).build();
	}
	
	*/
}
 