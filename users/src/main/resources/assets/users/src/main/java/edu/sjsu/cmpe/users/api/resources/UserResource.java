package edu.sjsu.cmpe.users.api.resources;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.ws.rs.Consumes;
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

import org.jongo.Find;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
//import com.yammer.dropwizard.jersey.params.IntParam;
import com.yammer.metrics.annotation.Timed;

import edu.sjsu.cmpe.mongo.config.MongoConfig;
import edu.sjsu.cmpe.users.dao.MongoDao;
import edu.sjsu.cmpe.users.domain.UserDetails;
import edu.sjsu.cmpe.users.domain.EventDetails;

@Path("/v1")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)

public class UserResource {
	private int authenticated = 400;
	MongoDao client;
	DBCursor cursor;
	
	public UserResource() throws UnknownHostException
	{
		client = new MongoDao();
		
	}
	

	@Path("/users")
    @GET
    public Response getAllUsers() throws Exception
   {
	
		Iterable<UserDetails> userDetails = null;
      	userDetails = client.find("usercollection").as(UserDetails.class);
		return Response.status(200).entity(userDetails).build();
		
	    
   }
   
 
	@Path("/users")
	@POST
	public Response createUser(UserDetails user) throws FileNotFoundException, IOException 
	{

		client.saveData("usercollection", user);
        return Response.status(201).build();
	}
	
	
	@POST
	@Path("users/authenticate")
	public Response authenticateUser(UserDetails user) throws FileNotFoundException, IOException
	{
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
    @GET
    @Timed(name="get-all-events")
    public Response getAllEvents() throws Exception
   {
	
		Iterable<EventDetails> eventDetails = null;
      	eventDetails = client.find("eventcollection").as(EventDetails.class);
		return Response.status(200).entity(eventDetails).build();
		
	    
   }
   
 
	@Path("/events")
	@POST
	@Timed(name="create-event")
	public Response CreateEvent(EventDetails event) throws FileNotFoundException, IOException 
	{

		client.saveData("eventcollection", event);
        return Response.status(201).build();
	}
	
}
 