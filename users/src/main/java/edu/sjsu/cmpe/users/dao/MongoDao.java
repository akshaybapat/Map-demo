package edu.sjsu.cmpe.users.dao;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.bson.types.ObjectId;
import org.jongo.Find;
import org.jongo.FindOne;
import org.jongo.Jongo;
import org.jongo.MongoCollection;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;

import edu.sjsu.cmpe.users.domain.EventDetails;
import edu.sjsu.cmpe.users.domain.UserDetails;

public class MongoDao {
	Mongo mclient = null;
    Jongo jongo;

    public MongoDao() throws UnknownHostException {

        mclient = new Mongo(new ServerAddress("localhost",27017));
        jongo = new Jongo(mclient.getDB("eventdata"));
      
    }
        
        public MongoCollection getDataFromCollection(String collectionName){
        return jongo.getCollection(collectionName);
}


        public <T> void saveData(String collection, List<T> objectToSave){
        MongoCollection coll = jongo.getCollection(collection);
                for(T item : objectToSave ){
                        coll.save(item);
                        }
}

        public <T> void saveData(String collection, T objectToSave){
        MongoCollection coll = jongo.getCollection(collection);
        coll.save(objectToSave);
        }

        // Insert Data in Collection
        /**
        * insert list of data into collection provided
        * @param collection - String
        * @param objectToInsert- List<T>- generic object preferred String
        */
        public <T> void insertData(String collection, List<T> objectToInsert){
        MongoCollection coll = jongo.getCollection(collection);
        
        for(T item : objectToInsert ){
                coll.insert(item);
                }
        
}

	/**
	 * insert data into collection provided
	 * @param collection - String
	 * @param objectToInsert - <T>- generic object preferred String
	 */
	public <T> void insertData(String collection, T objectToInsert){
	        MongoCollection coll = jongo.getCollection(collection);
	        coll.insert(objectToInsert);
	}
	
	
	//Update Data in Collection
	
	/**
	 * update list of data in the collection provided
	 * @param collection - String
	 * @param thingsToUpdate - String
	 * @param withCondition - String
	 */
	public void updateData(String collection, List<String> thingsToUpdate, String withCondition){
	        MongoCollection coll = jongo.getCollection(collection);
	        
	        for(String item : thingsToUpdate ){
	                coll.update(item).with(withCondition);
	                }
	        
	}
	
	/**
	 * update data in the Collection provided
	 * @param collection - String
	 * @param thingsToUpdate - String
	 * @param withCondition - String
 */
	public void updateData(String collection, String thingsToUpdate, String withCondition){
	        MongoCollection coll = jongo.getCollection(collection);
	        coll.update(thingsToUpdate).with(withCondition);
	}

     //Delete data from Collection
	/**
	 * Removes list data from collection provided
	 * @param collection - String
	 * @param objectsToRemove - Collection of String or ObjectId
	 */
        public <T> void removeData(String collection, List<T> objectsToRemove){
        MongoCollection coll = jongo.getCollection(collection);
        
        for( T item : objectsToRemove ){
                if(item instanceof String)
                {
                        coll.remove((String) item);
                }else{
                        
                        coll.remove((ObjectId) item);
                }
        }
        
}

        /**
         * Removes data from collection provided
         * @param collection - String
         * @param objectToRemove - String or ObjectId
         */
        public <T> void removeData(String collection, T objectToRemove){
        MongoCollection coll = jongo.getCollection(collection);
        if(objectToRemove instanceof String)
        {
                coll.remove((String) objectToRemove);
        }else{
                
                coll.remove((ObjectId) objectToRemove);
        }
     }

       /**
       * Find values in collection
       * @param collection - String
       * @param condition - String
       * @return
       */
        public Find findData(String collection, String condition){
        return jongo.getCollection(collection).find(condition);
}

       /**
       * Find one values in collection -- top1
       * @param collection - String
       * @param condition - String
       * @return
       */
        public FindOne findOneData(String collection, String condition){
        return jongo.getCollection(collection).findOne(condition);
        }

        public FindOne findOneData(String collection){
        return jongo.getCollection(collection).findOne();
        }

        public Find find(String collection){
        return jongo.getCollection(collection).find();
        
        }
 

	    
}
