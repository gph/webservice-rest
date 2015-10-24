package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import models.HighScore;
import tools.ConnectionFactory;

public class HighScoreDAO extends ConnectionFactory{
	public ArrayList<HighScore> getHighScores() throws ClassNotFoundException{
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		ArrayList<HighScore> highscores = new ArrayList<HighScore>();
		
		conn = createConn();
		
		try {
			
			pstmt = conn.prepareStatement("SELECT * FROM highscore");
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				HighScore highscore = new HighScore();
				highscore.setId(rs.getInt("id"));
				highscore.setName(rs.getString("name"));
				highscore.setScore(rs.getInt("score"));
				
				highscores.add(highscore);
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error while getting all scores!\n File: HighScoreDAO.java\nMethod: getHighScores()");
		}finally {
			closeConn(conn, pstmt, rs);
		}
		return highscores;
	}
	public HighScore getHighScore(int id) throws ClassNotFoundException{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		HighScore highscore = new HighScore();
		conn = createConn();
					
		try{
			pstmt = conn.prepareStatement("SELECT * FROM highscore WHERE id = ?");
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				highscore.setId(rs.getInt("id"));
				highscore.setName(rs.getString("Subject"));
				highscore.setScore(rs.getInt("body"));
			}
			
		}catch(Exception e){
			System.out.println("Error while trying to get score " + id + "!\n File: HighScoreDAO.java\nMethod: getHighScore()" + e);
			e.printStackTrace();
		}finally {
			closeConn(conn, pstmt, rs);
		}
		return highscore;		
	}
	
	public String postHighScore(HighScore highscore) throws ClassNotFoundException{
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		conn = createConn();
		
		try {
			pstmt = conn.prepareStatement("INSERT INTO highscore (name,score) VALUES (?,?)");
			
			pstmt.setString(1, highscore.getName());
			pstmt.setInt(2, highscore.getScore());
			pstmt.execute();
					
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return "Error while sending score!\n File: HighScoreDAO.java\nMethod: postHighScore()\n" + e;
		}finally {
			closeConn(conn, pstmt, rs);
		}
		
		return "OK";
		
	}
	
}
