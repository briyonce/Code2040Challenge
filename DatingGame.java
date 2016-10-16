import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
public class DatingGame {

	public static void main(String[] args) {
		JSONObject obj = new JSONObject();
		String token = "ebe0fe990f4cd813990b2b98ded442b5";
		JSONObject given;
		obj.put("token", "{\"token\":\"" + token + "\"}");
		try {
			URL url1 = new URL("http://challenge.code2040.org/api/dating");
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
			String dateStamp = given.getString("datestamp");
			int secs = given.getInt("interval");
			System.out.println(dateStamp + ", " + secs);
			int minutes = secs / 60;
			int hours = minutes / 60;
			int days = hours / 24;
			System.out.println("Minutes: " + minutes);
			System.out.println("Hours: " + hours);
			System.out.println("Days: " + days);
			secs = secs - (minutes * 60);
			minutes = minutes - (hours * 60);
			hours = hours - (days * 24);
			System.out.println();
			System.out.println("Seconds: " + secs);
			System.out.println("Minutes: " + minutes);
			System.out.println("Hours: " + hours);
			System.out.println("Days: " + days);
			String ess = dateStamp.substring(8, dateStamp.length());
			System.out.println(ess);
			String month = dateStamp.substring(5,7);
			String year = dateStamp.substring(0,4);
			String day = ess.substring(0,2);
			String hour = ess.substring(3,5);
			String min = ess.substring(6,8);
			String sec = ess.substring(9,11);
			int modMonth = Integer.parseInt(month);
			int modYear = Integer.parseInt(year);
			int modDay = Integer.parseInt(day) + days;
			int modHr = Integer.parseInt(hour) + hours;
			int modMin = Integer.parseInt(min) + minutes;
			int modSec = Integer.parseInt(sec) + secs;
			System.out.println();
			System.out.println("Day Int: " + modDay);
			System.out.println("Hours Int: " + modHr);
			System.out.println("Min Int: " + modMin);
			System.out.println("Sec Int: " + modSec);
			System.out.println("\n\n");
			if (modSec > 59) {
				modMin ++;
				modSec -= 59;
			} if (modMin > 59) {
				modHr ++;
				modMin -= 59;
			} if (modHr > 24) {
				if ((modHr == 24) && (modMin != 0) && (modSec != 0)) 
				{
					modDay++;
				} else {
					modDay ++;
				}
				modHr -= 24;
			} if (modDay > 28) {
				if (modMonth == 2) {
					if (modYear % 4 != 0) { //not a leapYear
						modDay -= 28;
						modMonth++;
					} else if (modDay > 29) {
						modDay -= 29;
						modMonth++;
					}
				} else if (modDay > 30) {
					if ((modMonth == 4) || (modMonth == 6) || (modMonth == 9) || (modMonth == 11)) {
						modDay -= 30;
						modMonth++;
					} else if (modDay > 31) {
						modDay -= 31;
						modMonth++;
					}
				}
			} if (modMonth > 12) {
				modMonth -= 12;
				modYear++;
			}
			System.out.println("Year Int: " + modYear);
			System.out.println("Month Int: " + modMonth);
			System.out.println("Day Int: " + modDay);
			System.out.println("Hours Int: " + modHr);
			System.out.println("Min Int: " + modMin);
			System.out.println("Sec Int: " + modSec);
			System.out.println("\n\n");
			if (modSec < 10) {
				sec = "0" + modSec;
			}	else {
				sec = "" + modSec;
			} if (modMin < 10) {
				min = "0" + modMin;
			} else {
				min = "" + modMin;
			} if (modHr < 10) {
				hour = "0" + modHr;
			} else {
				hour = "" + modHr;
			} if (modDay < 10) {
				day = "0" + modDay;
			} else {
				day = "" + modDay;
			} if (modMonth < 10) {
				month = "0" + modMonth;
			} else {
				month = "" + modMonth;
			} if (modYear < 1000) {
				if (modYear < 10) {
						year = "000" + modYear;
				} else if (modYear < 100) {
					year = "00" + modYear;
				} else {
					year = "0" + modYear;
				}
			} else {
				year = "" + modYear;
			}
			String result = year + "-" + month + "-" + day + "T" + hour + ":" + min + ":" + sec + "Z";
			System.out.println(result);
			JSONObject val = new JSONObject();
			val.put("token", token);
			val.put("datestamp", result);
			System.out.println(val.toString());
			URL url2 = new URL("http://challenge.code2040.org/api/dating/validate");
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