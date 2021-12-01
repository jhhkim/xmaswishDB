package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import comf.Util;
import dto.XmasDto;

 
public class XmasDao {
	
    private static Connection conn; //DB 커넥션 연결 객체
    static String[] code =Util.readLineFile("C:/dev/DBcode.txt").split("\\n");
    private static final String USERNAME = code[0];//DBMS접속 시 아이디
    private static final String PASSWORD = code[1];//DBMS접속 시 비밀번호
    private static final String URL = code[2];//DBMS접속할 DB명
    
    public XmasDao() {
        try {
            System.out.println("생성자");
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("연결 성공");
        } catch (Exception e) {
            System.out.println("연결 실패 ");
            e.printStackTrace();
            try {
                conn.close();
            } catch (SQLException e1) {    }
        }
        
        
    }
    
    
    //MainClass에서 받은 데이터 DB 삽입 메소드
    public void insertXmasInfo(XmasDto dto){
        //쿼리문 준비
        String sql = "INSERT INTO XmasWish (name, age, gift) VALUES(?,?,?)";
        
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, dto.getName());
            pstmt.setInt(2, dto.getAge());
            pstmt.setString(3, dto.getGift());

            
            int result = pstmt.executeUpdate();
            if(result==1) {
                System.out.println("데이터 삽입 성공!");
                
            }
            
        } catch (Exception e) {
            System.out.println("naver_info 데이터 삽입 실패!");
        }    finally {
            try {
                if(pstmt!=null && !pstmt.isClosed()) {
                    pstmt.close();
                }
            } catch (Exception e2) {}
        }
        
        
    }   
    
    
   
  //DB에서 모든 행 가져오는 메소드
    public void selectAll() {
        String sql = "SELECT * FROM XmasWish";
        PreparedStatement pstmt = null;
        
    	
        try {
        	pstmt = conn.prepareStatement(sql);  
            ResultSet rs = pstmt.executeQuery();
            
            while(rs.next()) {
                System.out.println("name: " + rs.getString("name"));
                System.out.println("age: " + rs.getInt("age"));
                System.out.println("gift: " + rs.getString("gift"));
           }
        } catch (Exception e) {
            System.out.println("select 메서드 예외발생");
        } finally {
            try {
                if(pstmt!=null && !pstmt.isClosed()) {
                    pstmt.close();
                }
            } catch (Exception e2) {}
        }
        
    }    

}
