/**
 * 
 */
package com.accenture.iot.agent.service;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class RestfulClient {

	public static Header[] assembHead(HashMap<String, Object> hds) {
		Header[] allHeader = new BasicHeader[hds.size()];
		int i = 0;
		for (String str : hds.keySet()) {
			allHeader[i] = new BasicHeader(str, (String) hds.get(str));
			i++;
		}
		return allHeader;
	}

	@SuppressWarnings("finally")
	public static void sendRestReq(String httpType, HashMap<String, Object> params, String url,
			HashMap<String, Object> hds, IHttpListener listener) {
		if (httpType == null || url == null || listener == null) {
			return;
		}
		HttpClient httpClient = HttpClients.createDefault();

		HttpRequestBase hp = null;
		if (httpType.equalsIgnoreCase("POST")) {
			hp = new HttpPost(url);
		} else if (httpType.equalsIgnoreCase("GET")) {
			hp = new HttpGet(url);
		}

		hp.setHeaders(assembHead(hds));

		try {

			if (httpType.equalsIgnoreCase("POST") && params != null) {
				if (hds != null && hds.containsValue("application/x-www-form-urlencoded")) {
					List<NameValuePair> pairs = new ArrayList<NameValuePair>();
					Set<String> set = params.keySet();
					for (Iterator<String> itr = set.iterator(); itr.hasNext();) {
						String key = (String) itr.next();
						String value = (String) params.get(key);
						pairs.add(new BasicNameValuePair(key, value));
					}
					((HttpPost) hp).setEntity(new UrlEncodedFormEntity(pairs, "UTF8"));
					//System.out.println("URLEncoded Pair Param:" + pairs.toString()/* json.toString() */);
				} else if (hds != null && hds.containsValue("application/json")) {
					// JSON params
					String json = new Gson().toJson(params, HashMap.class);
					StringEntity se = new StringEntity(json, "utf-8");
					// se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE,
					// "application/json"));
					// se.setContentType("application/json");
					System.out.println("JSON Param:" + json);
					((HttpPost) hp).setEntity(se);
				}

			}

			System.out.println("Start--------------------------URL is:" + url);
			HttpResponse httpResponse = httpClient.execute(hp);

			int httpCode = httpResponse.getStatusLine().getStatusCode();

			if (httpResponse != null) {

				HttpEntity entity = httpResponse.getEntity();

				InputStream inputStream = entity.getContent();

				InputStreamReader inputStreamReader = new InputStreamReader(inputStream);

				BufferedReader reader = new BufferedReader(inputStreamReader);

				String s;
				StringBuilder result = new StringBuilder();

				while (((s = reader.readLine()) != null)) {

					result.append(s);

				}

				reader.close();
				if (httpCode == HttpURLConnection.HTTP_OK) {
					System.out.println("Successfully to send post request resp:" + result.toString());
					listener.onReqCompleted(result.toString());
				} else {
					System.out.println("Failed to send post request error:" + httpCode);
					listener.onReqError(result.toString());
				}
				return;

			} else {
				System.out.println("Failed to send post request error:" + httpCode);
				listener.onReqError("Http failed no response");
				return;
			}

		} catch (UnsupportedEncodingException e) {
			listener.onReqError("UnsupportedEncodingException");
			e.printStackTrace();

		} catch (ClientProtocolException e) {
			listener.onReqError("ClientProtocolException");
			e.printStackTrace();
		} catch (IOException e) {
			listener.onReqError("IOException");
			e.printStackTrace();
		} finally {
			if (httpClient != null) {
				httpClient = null;
			}
			System.out.println("End ++++++++++++++++++++++++++++");
			return;
		}
	}
}
