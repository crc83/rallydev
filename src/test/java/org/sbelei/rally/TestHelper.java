package org.sbelei.rally;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.rallydev.rest.RallyRestApi;
import com.rallydev.rest.request.QueryRequest;
import com.rallydev.rest.response.QueryResponse;
import org.apache.commons.io.IOUtils;

import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class TestHelper {

	private static DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

	public static JsonArray getTestData(String resourceName) {
		JsonParser parser =new JsonParser();
		JsonArray array = (JsonArray) parser.parse(TestHelper.getResourceAsReader(resourceName));
		return array;
	}
	
	public static InputStream getResource(String name) {
		TestHelper helper = new TestHelper();
		return helper.getClass().getResourceAsStream(name);
	}

	public static Reader getResourceAsReader(String name) {
		InputStream is = getResource(name);
		if (is == null) {
			throw new IllegalArgumentException();
		}
		return new InputStreamReader(is);
	}
	
	public static String getResourseAsString(String path){
		String result = "";
		try {
			result = IOUtils.toString(getResource(path), "UTF-8");
		} catch (IOException e) {
			//TODO SB : Use JULY
			e.printStackTrace();
		}
		return result;		
	}

	public static RallyRestApi getRestApiWithResponse(String responsePath)
			throws IOException {
		RallyRestApi restApi = mock(RallyRestApi.class);
		String responseMessage =
				getResourseAsString(responsePath);
		QueryResponse stubResponse = new QueryResponse(responseMessage);
		given(restApi.query(any(QueryRequest.class))).willReturn(stubResponse);
		return restApi;
	}
	/**
	 * convert String to date
	 * 
	 * @param date
	 *            "dd/mm/yyyy"
	 * @return Date object
	 */
	public static Date date(String date) {
		Date today = null;
		try {
			today = dateFormat.parse(date);
		} catch (ParseException e) {
			//TODO SB : Use JULY
			e.printStackTrace();
		}
		return today;
	}
}
