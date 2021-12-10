package Controllers;

import com.mongodb.*;
import com.mongodb.client.*;
import com.mongodb.client.MongoClient;
import com.mongodb.client.model.Filters;

import com.mongodb.client.model.Projections;
import com.mongodb.client.model.Sorts;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.result.*;
import org.bson.BsonDocument;
import org.bson.BsonInt64;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;

import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Updates.*;
import org.bson.conversions.Bson;

public class Database{

        MongoDatabase database;
        MongoCollection<Document> collection;
        public Database() throws FileNotFoundException, IOException {
                BufferedReader test = new BufferedReader(new FileReader("src/privateAccess"));
                String privateKey = test.readLine();
                ConnectionString connectionString = new ConnectionString(privateKey);
                MongoClientSettings settings = MongoClientSettings.builder()
                        .applyConnectionString(connectionString)
                        .build();
                MongoClient mongoClient = MongoClients.create(settings);
                 this.database = mongoClient.getDatabase("DSEncrypt");

                }


                public void add(String[] data){

                        Document document = new Document();
                        document.append("message_id", data[0]);;
                        document.append("privateKey", data[1] );
                        document.append("String", data[2]);
                        document.append("Date",data[3]);
                        document.append("IP",data[4]);
                        document.append("OS",data[5]);
                        database.getCollection("MESSAGE").insertOne(document);
                }

                public String findPrivKey(String messageID){
                        MongoCollection<Document> c = database.getCollection("MESSAGE");
                        Bson projectionFields = Projections.fields(
                                Projections.include("message_id","privateKey"),
                                Projections.excludeId());
                        Document doc = c.find(eq("message_id", messageID))
                                .projection(projectionFields)
                                .sort(Sorts.descending("message_id.privateKey"))
                                .first();
                        return doc.get("privateKey").toString();

                }
        public String findString(String messageID){
                MongoCollection<Document> c = database.getCollection("MESSAGE");
                Bson projectionFields = Projections.fields(
                        Projections.include("message_id", "String"),
                        Projections.excludeId());
                Document doc = c.find(eq("message_id", messageID))
                        .projection(projectionFields)
                        .sort(Sorts.descending("message_id.String"))
                        .first();

                return doc.get("String").toString();

        }
        public String findDate(String messageID){
                MongoCollection<Document> c = database.getCollection("MESSAGE");
                Bson projectionFields = Projections.fields(
                        Projections.include("message_id", "Date"),
                        Projections.excludeId());
                Document doc = c.find(eq("message_id", messageID))
                        .projection(projectionFields)
                        .sort(Sorts.descending("message_id.Date"))
                        .first();

                return doc.get("Date").toString();

        }
        public String findIP(String messageID){
                MongoCollection<Document> c = database.getCollection("MESSAGE");
                Bson projectionFields = Projections.fields(
                        Projections.include("message_id", "IP"),
                        Projections.excludeId());
                Document doc = c.find(eq("message_id", messageID))
                        .projection(projectionFields)
                        .sort(Sorts.descending("message_id.IP"))
                        .first();

                return doc.get("IP").toString();

        }

        public String getOS(String messageID){
                MongoCollection<Document> c = database.getCollection("MESSAGE");
                Bson projectionFields = Projections.fields(
                        Projections.include("message_id", "OS"),
                        Projections.excludeId());
                Document doc = c.find(eq("message_id", messageID))
                        .projection(projectionFields)
                        .sort(Sorts.descending("message_id.OS"))
                        .first();

                return doc.get("OS").toString();

        }



        public boolean verifyCredentials(String messageID, String privateKey){

                if(privateKey.equals(findPrivKey(messageID))){

                        return true;
                }
                        return false;


        }


        public void closeConnection(){

        }


        public static void main(String[] args) throws IOException{
                Database e = new Database();
                String [] a = {"1","234","3","4","5","6"};

                System.out.println(e.findPrivKey("1546849643"));
                e.verifyCredentials("1546849643","[B@60e3b6aa");

        }



}