package edu.sjsu.cmpe.users;


import com.yammer.dropwizard.Service;
import com.yammer.dropwizard.config.Environment;
//import com.yammer.dropwizard.json.Json;

import com.yammer.dropwizard.config.Bootstrap;

//import edu.sjsu.cmpe.users.config.CustomJson;
import edu.sjsu.cmpe.users.config.UserServiceConfiguration;
import edu.sjsu.cmpe.users.api.resources.UserResource;

public class UsersService extends Service<UserServiceConfiguration> {

	public static void main(String[] args) throws Exception {
	
		new UsersService().run(args);
	}
	
	  @Override
	    public void initialize(Bootstrap<UserServiceConfiguration> bootstrap) {
		bootstrap.setName("users");
	    }
	
	
	@Override
    public void run(UserServiceConfiguration configuration,
	    Environment environment) throws Exception {
		environment.addResource(UserResource.class);
    }
	
	}
