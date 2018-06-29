package com.trainoo.crawler.novel;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by zhoutao on 2018/6/28 18:03
 */

public class ChapterDB {

    private static String driver = "com.mysql.jdbc.Driver";
    private static String url = "jdbc:mysql://localhost:3306/spring_boot_demo?useUnicode=true&characterEncoding=utf8&characterSetResults=utf8";
    private static String username = "root";
    private static String password = "root";

    public static Connection getConn() {
        Connection conn = null;
        try {
            Class.forName(driver); //classLoader,加载对应驱动
            conn = (Connection) DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    public static int insert(Chapter chapter) {
        Connection conn = getConn();
        int i = 0;
        String sql = "insert into chapter (id, title, content) values(?,?,?)";
        PreparedStatement pstmt;
        try {
            pstmt = (PreparedStatement) conn.prepareStatement(sql);
            pstmt.setInt(1, chapter.getId());
            pstmt.setString(2, chapter.getTitle());
            pstmt.setString(3, chapter.getContent());
            i = pstmt.executeUpdate();
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return i;
    }
}
