import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
public class Prefix {

	public static void main(String[] args) {
		JSONObject obj = new JSONObject();
		String token = "ebe0fe990f4cd813990b2b98ded442b5";
		JSONObject given;
		obj.put("token", "{\"token\":\"" + token + "\"}");
		try {
			URL url1 = new URL("http://challenge.code2040.org/api/prefix");
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
			JSONArray array = given.getJSONArray("array");
			String prefix = given.getString("prefix");
			System.out.println(array);
			System.out.println();
			JSONArray ret = new JSONArray();
			int i = 0;
			while (i < array.length()) {
				String str = array.getString(i);
				String sub = str.substring(0, prefix.length());
				//System.out.println(array.getString(i) +", " + sub + ".");
				if (!(sub.equals(prefix))) {
					ret.put(str);
				}
					i++;
			}
			System.out.println(ret.toString());
			JSONObject val = new JSONObject();
			val.put("token", token);
			val.put("array", ret);
			System.out.println(val.toString());
			URL url2 = new URL("http://challenge.code2040.org/api/prefix/validate");
			HttpURLConnection conn2 = (HttpURLConnection) url2.openConnection();
			conn2.setDoOutput(true);
			conn2.setRequestMethod("POST");
			conn2.setRequestProperty("Content-Type", "application/json");
			OutputStream os2 = conn2.getOutputStream();
			OutputStreamWriter osw = new OutputStreamWriter(os2, "UTF-8");
			osw.write(val.toString());
			osw.flush();
			osw.close();
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