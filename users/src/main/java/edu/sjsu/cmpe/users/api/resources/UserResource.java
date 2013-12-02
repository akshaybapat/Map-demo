package edu.sjsu.cmpe.users.api.resources;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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
import javax.ws.rs.QueryParam;
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

import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.resource.factory.MessageFactory;
import com.twilio.sdk.resource.factory.SmsFactory;
import com.twilio.sdk.resource.instance.Message;
import com.twilio.sdk.resource.list.MessageList;
import com.twilio.sdk.verbs.Sms;

import java.util.ArrayList;
import java.util.List;

@Path("/v1/users")
@Produces(value = MediaType.APPLICATION_JSON)
@Consumes(value = MediaType.APPLICATION_JSON)

public class UserResource {
	private int authenticated = 400;
		
		//client = new MongoDao();
		JacksonDBCollection<UserDetails, String> users;
		public UserResource(JacksonDBCollection<UserDetails, String> users)
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
		 	
			
			
		 	@POST
		    @Timed
		    public Response publishNewBlog(@Valid UserDetails User) {
		        users.insert(User);
		        return Response.noContent().build();
	
											}
			
			@Path("/{sjsuId}")
		 	@GET
		    @Produces(value = MediaType.APPLICATION_JSON)
		    @Timed
		    public List<UserDetails> getUserById(@PathParam("sjsuId")String id) {
				BasicDBObject query = new BasicDBObject();
				query.put("sjsuId", id);
		        DBCursor<UserDetails> dbCursor = users.find(query);
		        List<UserDetails> Users = new ArrayList<UserDetails>();
		        while (dbCursor.hasNext()) {
		        	UserDetails User = dbCursor.next();
		            Users.add(User);
		        }
		        return Users;
		    }
			
			@Path("/{sjsuId}")
		 	@POST
		    @Produces(value = MediaType.APPLICATION_JSON)
		    @Timed
		    public Response authenticateUser(@PathParam("sjsuId")String id,@QueryParam("password")String password) {
				authenticated = 400;
				BasicDBObject query = new BasicDBObject();
                List<BasicDBObject> queryList = new ArrayList<BasicDBObject>();
                queryList.add(new BasicDBObject("sjsuId", id));
                queryList.add(new BasicDBObject("password", password));
                query.put("$and", queryList);
		        DBCursor<UserDetails> dbCursor = users.find(query);
		        List<UserDetails> Users = new ArrayList<UserDetails>();
		        while (dbCursor.hasNext()) {
		        	UserDetails User = dbCursor.next();
		            Users.add(User);
		            authenticated = 200;
		        }
		        return Response.status(authenticated).build();
		    }
			
			@Path("/message")
		 	@POST
		    @Produces(value = MediaType.APPLICATION_JSON)
		    @Timed
		    public Response sendSMS(@QueryParam("mess")String mess) {
				 try {
					 TwilioRestClient client = new TwilioRestClient("AC877a45598fb7259ea0e5cfd927d31dfd", "eb7c434dc900c43211dbbc0acca09f0b");
					 // Build a filter for the SmsList
					 Map<String, String> params = new HashMap<String, String>();
					 params.put("Body", mess);
					 params.put("To", "+14087979314");
					 params.put("From", "+19717173230");
					 SmsFactory messageFactory = client.getAccount().getSmsFactory();
					 com.twilio.sdk.resource.instance.Sms message = messageFactory.create(params);
					 System.out.println(message.getSid());
					 } catch (TwilioRestException e) {
					 System.out.println(e.getErrorMessage());
					 }
		        
		        return Response.noContent().build();
		    }
			
}			
 

