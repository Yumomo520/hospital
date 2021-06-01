package com.goodmap.hospital;

import com.goodmap.hospital.common.result.EntityResult;
import com.goodmap.hospital.pojo.Hospital;
import com.goodmap.hospital.service.hospital.HospitalService;
import org.jasypt.encryption.StringEncryptor;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import redis.clients.jedis.Jedis;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

@SpringBootTest
class HospitalApplicationTests {
    @Autowired
    HospitalService hospitalService;
    @Test
    void contextLoads() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/hospital?useUnicode=true&characterEncoding=UTF-8&autoReconnect=true&failOverReadOnly=false" ;
            String username = "root" ;
            String password = "123" ;
            Connection connection = DriverManager.getConnection(url, username, password);
            System.out.println(connection);
        } catch (ClassNotFoundException e) {

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Test
    void test(){
        EntityResult<List<Hospital>> listEntityResult = hospitalService.selectByName(null);
        System.out.println(listEntityResult);
    }
    @Test
    void redisConnection(){
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        System.out.println(jedis);
    }
    @Autowired
    StringEncryptor encryptor;
//    数据库密码加密
    @Test
    public void getPassage(){
        String url = encryptor.encrypt("jdbc:mysql://127.0.0.1:3306/hospital?useUnicode=true&characterEncoding=utf8");
        String name = encryptor.encrypt("root");
        String password = encryptor.encrypt("gis832");
        String password1 = encryptor.encrypt("123456");
        System.out.println(url+"----------------");
        System.out.println(name+"----------------");
        System.out.println(password+"----------------");
        System.out.println(password1+"----------------");
        Assert.assertTrue(name.length() > 0);
        Assert.assertTrue(password.length() > 0);
    }
    @Test
    public void getConnectionSqlServer() {

        String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
        String dbURL = "jdbc:sqlserver://122.112.220.66:1433;databasename=indoormap"; // 1433是端口，"test"是数据库名称
        String userName = "sa"; // 用户名
        String userPwd = "Zhdy12345@"; // 密码

        Connection dbConn = null;
        try {

            Class.forName(driverName).newInstance();
        } catch (Exception ex) {
            System.out.println("驱动加载失败");
            ex.printStackTrace();
        }
        try {
            dbConn = DriverManager.getConnection(dbURL, userName, userPwd);
            System.out.println("成功连接数据库！");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            try {
                if (dbConn != null)
                    dbConn.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}
