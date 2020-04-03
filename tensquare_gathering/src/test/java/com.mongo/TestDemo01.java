package com.mongo;


import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;


public class TestDemo01 {

    public static void main(String[] args) {
        //创建连接
        MongoClient client = new MongoClient("192.168.203.131");
        //打开数据库
        MongoDatabase spitdb = client.getDatabase("spitdb");
        //获取集合
        MongoCollection<Document> spit=spitdb.getCollection("spit");
        // 构建查询条件
        BasicDBObject bson=new BasicDBObject("userid","1013");
        //查询记录获取结集
        FindIterable<Document> documents =spit.find(bson);

        for (Document document : documents) {
            System.out.println("内容："+document.getString("content"));
            System.out.println(document);

        }
    }
}
