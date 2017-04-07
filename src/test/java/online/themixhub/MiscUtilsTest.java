package online.themixhub;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import online.themixhub.demo.utils.MiscUtils;
import org.junit.Test;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLParameters;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.UnknownHostException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class MiscUtilsTest {

       @Test
       public void connectToPayPalWithTLSSucceeds() {
           boolean result = false;

            try {
			SSLContext sslContext = SSLContext.getInstance("TLS");
			sslContext.init(null, null, null);
			HttpsURLConnection.setDefaultSSLSocketFactory(sslContext.getSocketFactory());

			URL url = new URL("https://tlstest.paypal.com");
			HttpsURLConnection httpsConnection = (HttpsURLConnection) url.openConnection();

			httpsConnection.connect();
			BufferedReader reader = new BufferedReader(new InputStreamReader(httpsConnection.getInputStream()));
			StringBuilder body = new StringBuilder();
			while (reader.ready()) {
				body.append(reader.readLine());
			}
			httpsConnection.disconnect();
			if (body.toString().equals("PayPal_Connection_OK")) {
				result = true;
			}

		} catch (NoSuchAlgorithmException e) {
		} catch (UnknownHostException e) {
		} catch (IOException e) {
		} catch (KeyManagementException e) {
		}

           assertTrue(result);
       }


	@Test
	public void exceptionToStringIsNotNull() {
	   String result = null;
	   Exception e = new Exception("Test Exception");
	   result = MiscUtils.exceptionToString(e);
	   assertNotNull(result);
	}


	@Test
	public void stringSizeLengthFileIsNotNull() {
	    String result = null;
	    long l = 1L;
	    result = MiscUtils.getStringSizeLengthFile(l);
	    assertNotNull(result);
	    System.out.println(result);
	}



}


