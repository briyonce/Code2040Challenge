import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
public class Reverse {

	public static void main(String args[]) {
		JSONObject obj = new JSONObject();
		String token = "ebe0fe990f4cd813990b2b98ded442b5";
		obj.put("token", "{\"token\":\"" + token + "\"}");
		try {
			URL url1 = new URL("http://challenge.code2040.org/api/reverse");
			HttpURLConnection conn = (HttpURLConnection) url1.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/json");
			OutputStream os = conn.getOutputStream();
			os.write(obj.get("token").toString().getBytes());
			os.flush();
			BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String output;
			System.out.println("Output from server.... \n");
			while((output = in.readLine()) != null) {
				System.out.println(output);
				String reversed = new StringBuffer(output).reverse().toString();
				//System.out.println(reversed);
				obj.put("string", "{\"string\":\"" + reversed + "\"}");
				URL url2 = new URL("http://challenge.code2040.org/api/reverse/validate");
				HttpURLConnection conn2 = (HttpURLConnection) url2.openConnection();
				conn2.setDoOutput(true);
				conn2.setRequestMethod("POST");
				conn2.setRequestProperty("Content-Type", "application/json");
				OutputStream os2 = conn2.getOutputStream();
				String compound = "{\"token\":\"" + token + "\",\"string\":\"" + reversed + "\"}";
				//System.out.println(compound);
				os2.write(compound.getBytes());
				os2.flush();
				BufferedReader br2 = new BufferedReader(new InputStreamReader((conn2.getInputStream())));
				String output2;
				System.out.println("Output from server.... \n");
				while((output2 = br2.readLine()) != null) {
					System.out.println(output2);
				}
				conn2.disconnect();
			}
			conn.disconnect();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}