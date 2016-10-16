import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class NeedleInAHaystack {

	public static void main(String[] args) {
		JSONObject obj = new JSONObject();
		String token = "ebe0fe990f4cd813990b2b98ded442b5";
		JSONObject given;
		obj.put("token", "{\"token\":\"" + token + "\"}");
		try {
			URL url1 = new URL("http://challenge.code2040.org/api/haystack");
			HttpURLConnection conn = (HttpURLConnection) url1.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/json");
			OutputStream os = conn.getOutputStream();
			os.write(obj.get("token").toString().getBytes());
			os.flush();
			BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			given = new JSONObject(in.readLine());
			in.close();
			JSONArray stacks = given.getJSONArray("haystack");
			String needle = given.getString("needle");
			int n = 0;
			boolean found = false;
			while (!found && n < stacks.length()) {
				if (stacks.getString(n).equals(needle))
					found = true;
				else
					n++;
			}
			obj = new JSONObject();
			obj.put("token", token);
			obj.put("needle", n);

			URL url2 = new URL("http://challenge.code2040.org/api/haystack/validate");
			HttpURLConnection conn2 = (HttpURLConnection) url2.openConnection();
			conn2.setDoOutput(true);
			conn2.setRequestMethod("POST");
			conn2.setRequestProperty("Content-Type", "application/json");
			OutputStream os2 = conn2.getOutputStream();
			String compound = "{\"token\":\"" + token + "\",\"needle\":\"" + n + "\"}";
			System.out.println(compound);
			os2.write(compound.getBytes());
			os2.flush();
			in = new BufferedReader(new InputStreamReader(conn2.getInputStream()));
			String res;
			while ((res = in.readLine()) != null) {
				System.out.println(res);
			}
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
}