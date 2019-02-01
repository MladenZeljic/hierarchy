package program.classes;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Comparator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import program.object.models.PlayerObject;

public class JsonClass 
{
	private String _totalScore;
	private PlayerObject _player;
	
	public JsonClass(String totalScore, PlayerObject player) {
		_totalScore = totalScore;
		_player = player;
	}
	
	public JsonClass() {}
	
	@SuppressWarnings("unchecked")
	public JSONArray readFromFile() {
		JSONParser parser = new JSONParser();
		try {
			FileReader fReader = new FileReader("json_data/data.json");
			 Object obj = parser.parse(fReader); 
			 if(obj != null) { 
				 JSONObject jsonObject = (JSONObject) obj;
				 JSONArray jsonArray = (JSONArray) jsonObject.get("scores");
				 // sorting array by greater score
				 jsonArray.sort(new Comparator<JSONObject>() {
					 public int compare(JSONObject a, JSONObject b){ 
						 int valA=  Integer.parseInt((String) a.get("score"));
						 int valB = Integer.parseInt((String) b.get("score"));
						 return valB - valA;
					 }
				 });
				 fReader.close();
				 return jsonArray;
			 }
			 else 
				 fReader.close();
				 return new JSONArray();
		}
		catch (IOException | ParseException e) {
			e.printStackTrace();
        }
		return new JSONArray();
	}
	
	public int getMaxScore() {
		JSONArray scores = readFromFile();
		if(!scores.isEmpty()) {
			JSONObject e = (JSONObject)scores.get(0);
			return Integer.parseInt((String) e.get("score"));
		}
		return 0;
	}
	
	@SuppressWarnings("unchecked")
	public void writeInFile() {
		JSONObject jsonObject = new JSONObject();
		JSONArray scores = readFromFile();
		JSONObject playerScore = new JSONObject();

		try {
			playerScore.put("player", this._player.getPlayerName());
			playerScore.put("score",_totalScore);
			scores.add(playerScore);
			
			jsonObject.put("scores",scores);
			FileWriter fWriter = new FileWriter("json_data/data.json");
			fWriter.write(jsonObject.toString());
			fWriter.flush();
			fWriter.close();
		}
	    catch (IOException e) {
	    	e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	public void clearFile() {
		JSONObject jsonObject = new JSONObject();
		JSONArray scores = new JSONArray();

		try {
			
			jsonObject.put("scores",scores);
			FileWriter fWriter = new FileWriter("json_data/data.json");
			fWriter.write(jsonObject.toString());
			fWriter.flush();
			fWriter.close();
		}
	    catch (IOException e) {
	    	e.printStackTrace();
		}

	}
}
