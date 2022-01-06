package Com.sanjeet.remoteControlAPIs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import sun.net.www.http.HttpClient;

public class apiRequest {
	public static apiRequest shared = new apiRequest();
	
	private apiRequest() {
		// TODO Auto-generated constructor stub
	}
	private String ipadress = "192.168.0.38:8080";
	public void TurnOnEmergyPlan() throws IOException{
		MakeConnection("http://"+ipadress+"/~pi/remoteAPIs/rasbarryCircuitOperations.py?isEmergency=emerON");
	}
	
	public void TurnOffEmergyPlan() throws IOException{
		MakeConnection("http://"+ipadress+"/~pi/remoteAPIs/rasbarryCircuitOperations.py?isEmergency=emerOFF");
	}
	
	public void TurnOnNightMode() throws IOException{
		MakeConnection("http://"+ipadress+"/~pi/remoteAPIs/rasbarryCircuitOperations.py?IsNightModeOn=True");
	}
	
	public void TurnOffNightMode() throws IOException{
		MakeConnection("http://"+ipadress+"/~pi/remoteAPIs/rasbarryCircuitOperations.py?IsNightModeOn=False");
	}
	
	void MakeConnection(String urlStr) throws IOException{
		URL url = new URL(urlStr);
		HttpURLConnection con = (HttpURLConnection)url.openConnection();
		con.setRequestMethod("GET");
		con.setRequestProperty("User-Agent", "Mozilla/5.0");
		int responseCode = con.getResponseCode();
		if (responseCode == HttpURLConnection.HTTP_OK) { // success
			BufferedReader in = new BufferedReader(new InputStreamReader(
					con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			// print result
			//System.out.println(response.toString());
		}

		//var responseFuture = client.sendAsync(request, new JsonBodyHandler<>(APOD.class));
		//HttpRequest crunchifyRequest = HttpRequest.newBuilder()
	      
	}
}
