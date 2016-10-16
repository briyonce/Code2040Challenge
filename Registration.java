

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Registration {	

	public static void main(String args[]) {	

		HttpURLConnection conn;
		OutputStream os;
		BufferedReader in;

		try {

			URL url = new URL("http://challenge.code2040.org/api/register");
			
			conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/json");
			String input = "{\"token\":\"ebe0fe990f4cd813990b2b98ded442b5\",\"github\":\"https://github.com/briyonce/Code2040Challenge\"}";
			//System.out.println(input);
			os = conn.getOutputStream();
			os.write(input.getBytes());
			os.flush();
			in = new BufferedReader(new InputStreamReader((conn.getInputStream())));
			String output;
			while((output = in.readLine()) != null) {
				System.out.println(output);
			}
			conn.disconnect();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}