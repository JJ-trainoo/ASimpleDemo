package com.trainoo.crawler.novel;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhoutao on 2018/6/28 18:03
 */

public class ChapterDB {

    private static Logger LOG = LoggerFactory.getLogger(ChapterDB.class);

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
            LOG.error("获取JDBC连接出现异常：{}", e);
        }
        return conn;
    }

    public static int insert(Chapter chapter) {
        Connection conn = getConn();
        int i = 0;
        String sql = "insert into chapter (title, content, title_num) values(?,?,?)";
        PreparedStatement pstmt = null;
        try {
            pstmt = (PreparedStatement) conn.prepareStatement(sql);
            pstmt.setString(1, chapter.getTitle());
            pstmt.setString(2, chapter.getContent());
            pstmt.setInt(3, chapter.getTitleNum());
            i = pstmt.executeUpdate();

        } catch (MySQLIntegrityConstraintViolationException e1){
            LOG.error("检测到重复数据：{}，已舍弃", chapter.getTitle());
        } catch (SQLException e2) {
            LOG.error("插入数据到数据库出现异常：{}", e2);
        } finally {
            try {
                if (pstmt != null)
                    pstmt.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return i;
    }

    public static Chapter select(int chapterNum) {
        Connection conn = getConn();
        String sql = "SELECT t.title_num, t.title, t.content from chapter t where t.title_num = ?;";
        PreparedStatement pstmt = null;
        try {
            pstmt = (PreparedStatement) conn.prepareStatement(sql);
            pstmt.setInt(1, chapterNum);
            ResultSet resultSet = pstmt.executeQuery();
            if (resultSet.next()){
                Chapter chapter = new Chapter();
                chapter.setTitle(resultSet.getString("title"));
                chapter.setContent(resultSet.getString("content"));
                chapter.setTitleNum(resultSet.getInt("title_num"));
                return chapter;
            }

        } catch (SQLException e2) {
            LOG.error("查询章节数据出现异常，参数：{}，异常信息：{}", chapterNum, e2);
        } finally {
            try {
                if (pstmt != null)
                    pstmt.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static List<Chapter> selectAll() {
        List<Chapter> chapterList = new ArrayList<>();
        Connection conn = getConn();
        String sql = "SELECT t.title_num, t.title, t.content from chapter t ORDER BY t.title_num ASC;";
        PreparedStatement pstmt = null;
        try {
            pstmt = (PreparedStatement) conn.prepareStatement(sql);
            ResultSet resultSet = pstmt.executeQuery();
            while (resultSet.next()){
                Chapter chapter = new Chapter();
                chapter.setTitle(resultSet.getString("title"));
                chapter.setContent(resultSet.getString("content"));
                chapter.setTitleNum(resultSet.getInt("title_num"));
                chapterList.add(chapter);
            }

        } catch (SQLException e2) {
            LOG.error("查询章节数据出现异常，异常信息：{}", e2);
        } finally {
            try {
                if (pstmt != null)
                    pstmt.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return chapterList;
    }
}
