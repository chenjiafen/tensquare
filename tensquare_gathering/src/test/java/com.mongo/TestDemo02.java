package com.mongo;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.junit.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class TestDemo02 {

    /**
     * 查询浏览量大于1000的记录visits
     */
    @Test
    public void find1000() {
        //创建连接
        MongoClient client = new MongoClient("192.168.203.131");
        //打开数据库
        MongoDatabase spitdb = client.getDatabase("spitdb");
        //获取集合
        MongoCollection<Document> spit = spitdb.getCollection("spit");
        //构建查询条件
        BasicDBObject bson = new BasicDBObject("visits", new BasicDBObject("$gt", 1000));
        FindIterable<Document> documents = spit.find(bson);
        for (Document document : documents) {
            System.out.println("TestDemo02.find1000：===》》" + document);
            System.out.println("内容：" + document.getString("content"));
            System.out.println("用户ID:" + document.getString("userid"));
            System.out.println("浏览量：" + document.getInteger("visits"));

        }
        //关闭连接
        client.close();
    }

    //插入数据
    @Test
    public  void insert(){
        //创建连接
        MongoClient client=new MongoClient("192.168.203.131");
        //打开数据
       MongoDatabase spitdb= client.getDatabase("spitdb");
       //获取聚合
        MongoCollection<Document> spit = spitdb.getCollection("spit");
        //查询条件
        Map<String,Object> map= new HashMap<>();
        map.put("content","我要吐槽");
        map.put("userid","9999");
        map.put("visits",123);
        map.put("publishtime",new Date());
        Document document=new Document(map);
        spit.insertOne(document);//插入数据
        client.close();
    }
}
