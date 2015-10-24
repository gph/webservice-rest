package resources;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.google.gson.Gson;

import dao.HighScoreDAO;
import models.HighScore;
@Path("/highscores")
public class HighScoreResources {
	@GET
	@Produces("application/json")
	public ArrayList<HighScore> getHighScores() throws ClassNotFoundException{
		HighScoreDAO highscore = new HighScoreDAO();
		return highscore.getHighScores();
	}
	@Path("{id}")
	@GET
	@Produces("application/json")
	public HighScore getHighScore(@PathParam("id") int id) throws ClassNotFoundException{
		HighScoreDAO highscore = new HighScoreDAO();
		return highscore.getHighScore(id);
	}
	
	@POST
	@Consumes("application/json")
	public String dataFromPost(InputStream incomingData) throws ClassNotFoundException, IOException{
		StringBuilder data = new StringBuilder();
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(incomingData));
			String line = null;
			while((line = in.readLine()) != null){
				data.append(line);
			}
			in.close();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error getting data from POST!" + e);
			e.printStackTrace();
		}
		
		Gson gson = new Gson();
		HighScore highscore = gson.fromJson(data.toString(), HighScore.class);
		HighScoreDAO highscoreDao = new HighScoreDAO();
		
		incomingData.close();
		
		return highscoreDao.postHighScore(highscore);
		
		// It'll return the same data received... (to test :p)
		//return data.toString();
	}
}
