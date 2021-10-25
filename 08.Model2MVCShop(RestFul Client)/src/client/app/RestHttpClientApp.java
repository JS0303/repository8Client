package client.app;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.domain.Product;

public class RestHttpClientApp {

	// main Method
	public static void main(String[] args) throws Exception {

		////////////////////////////////////////////////////////////////////////////////////////////
		// 주석을 하나씩 처리해가며 실습
		////////////////////////////////////////////////////////////////////////////////////////////

//		System.out.println("\n====================================\n");
//		// 1.1 Http Get 방식 Request : JsonSimple lib 사용
//		RestHttpClientApp.getUserTest_JsonSimple();

//		System.out.println("\n====================================\n");
//		// 1.2 Http Get 방식 Request : CodeHaus lib 사용
//		RestHttpClientApp.getUserTest_Codehaus();

//		System.out.println("\n====================================\n");
//		// 2.1 Http Post 방식 Request : JsonSimple lib 사용
//		RestHttpClientApp.LoginTest_JsonSimple();

//		System.out.println("\n====================================\n");
//		// 2.2 Http Post 방식 Request : CodeHaus lib 사용
//		RestHttpClientApp.LoginTest_Codehaus();		

//		System.out.println("\n====================================\n"); 성공
//		// 3.1 Http Get 방식 Request : JsonSimple lib 사용
//		RestHttpClientApp.updateUserTest_JsonSimple();

//		System.out.println("\n====================================\n");
//		// 3.2 Http Get 방식 Request : CodeHaus lib 사용
//		RestHttpClientApp.updateUserTest_Codehaus();

//		System.out.println("\n====================================\n");
//		// 4.1 Http Post 방식 Request : JsonSimple lib 사용
		RestHttpClientApp.updateUserTest_JsonSimple();

//		System.out.println("\n====================================\n");
//		// 4.2 Http Post 방식 Request : CodeHaus lib 사용
//		RestHttpClientApp.updateUserTest_Codehaus();		

//		System.out.println("\n====================================\n"); 미구현
//		// 7.1 Http Get 방식 Request : JsonSimple lib 사용
//		RestHttpClientApp.getProductTest_JsonSimple();

//		System.out.println("\n====================================\n"); 미구현
//		// 7.2 Http Get 방식 Request : CodeHaus lib 사용
//		RestHttpClientApp.getProductTest_Codehaus();

//		System.out.println("\n====================================\n"); 미구현
//		// 8.1 Http Post 방식 Request : JsonSimple lib 사용
//		RestHttpClientApp.insertProductTest_JsonSimple();

//		System.out.println("\n====================================\n"); 미구현
//		// 8.2 Http Post 방식 Request : CodeHaus lib 사용
//		RestHttpClientApp.insertProductTest_Codehaus();

	}

//================================================================//
	// 1.1 Http Protocol GET Request : JsonSimple 3rd party lib 사용
	public static void getUserTest_JsonSimple() throws Exception {

		// HttpClient : Http Protocol 의 client 추상화
		HttpClient httpClient = new DefaultHttpClient();

		String url = "http://127.0.0.1:8080/user/json/getUser/admin";

		// HttpGet : Http Protocol 의 GET 방식 Request
		HttpGet httpGet = new HttpGet(url);
		httpGet.setHeader("Accept", "application/json");
		httpGet.setHeader("Content-Type", "application/json");

		// HttpResponse : Http Protocol 응답 Message 추상화
		HttpResponse httpResponse = httpClient.execute(httpGet);

		// ==> Response 확인
		System.out.println(httpResponse);
		System.out.println();

		// ==> Response 중 entity(DATA) 확인
		HttpEntity httpEntity = httpResponse.getEntity();

		// ==> InputStream 생성
		InputStream is = httpEntity.getContent();
		BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));

		System.out.println("[ Server 에서 받은 Data 확인 ] ");
		String serverData = br.readLine();
		System.out.println(serverData);

		// ==> 내용읽기(JSON Value 확인)
		JSONObject jsonobj = (JSONObject) JSONValue.parse(serverData);
		System.out.println(jsonobj);
	}

	// 1.2 Http Protocol GET Request : JsonSimple + codehaus 3rd party lib 사용
	public static void getUserTest_Codehaus() throws Exception {

		// HttpClient : Http Protocol 의 client 추상화
		HttpClient httpClient = new DefaultHttpClient();

		String url = "http://127.0.0.1:8080/user/json/getUser/admin";

		// HttpGet : Http Protocol 의 GET 방식 Request
		HttpGet httpGet = new HttpGet(url);
		httpGet.setHeader("Accept", "application/json");
		httpGet.setHeader("Content-Type", "application/json");

		// HttpResponse : Http Protocol 응답 Message 추상화
		HttpResponse httpResponse = httpClient.execute(httpGet);

		// ==> Response 확인
		System.out.println(httpResponse);
		System.out.println();

		// ==> Response 중 entity(DATA) 확인
		HttpEntity httpEntity = httpResponse.getEntity();

		// ==> InputStream 생성
		InputStream is = httpEntity.getContent();
		BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));

		// ==> 다른 방법으로 serverData 처리
		// System.out.println("[ Server 에서 받은 Data 확인 ] ");
		// String serverData = br.readLine();
		// System.out.println(serverData);

		// ==> API 확인 : Stream 객체를 직접 전달
		JSONObject jsonobj = (JSONObject) JSONValue.parse(br);
		System.out.println(jsonobj);

		ObjectMapper objectMapper = new ObjectMapper();
		User user = objectMapper.readValue(jsonobj.toString(), User.class);
		System.out.println(user);
	}
//================================================================//	

	// 2.1 Http Protocol POST Request : FromData 전달 / JsonSimple 3rd party lib 사용
	public static void LoginTest_JsonSimple() throws Exception {

		// HttpClient : Http Protocol 의 client 추상화
		HttpClient httpClient = new DefaultHttpClient();

		String url = "http://127.0.0.1:8080/user/json/login";
		HttpPost httpPost = new HttpPost(url);
		httpPost.setHeader("Accept", "application/json");
		httpPost.setHeader("Content-Type", "application/json");

		// [ 방법 1 : String 사용]
//			String data =  "{\"userId\":\"admin\",\"password\":\"1234\"}";
//			HttpEntity httpEntity01 = new StringEntity(data,"utf-8");

		// [ 방법 2 : JSONObject 사용]
		JSONObject json = new JSONObject();
		json.put("userId", "admin");
		json.put("password", "1234");
		HttpEntity httpEntity01 = new StringEntity(json.toString(), "utf-8");

		httpPost.setEntity(httpEntity01);
		HttpResponse httpResponse = httpClient.execute(httpPost);

		// ==> Response 확인
		System.out.println(httpResponse);
		System.out.println();

		// ==> Response 중 entity(DATA) 확인
		HttpEntity httpEntity = httpResponse.getEntity();

		// ==> InputStream 생성
		InputStream is = httpEntity.getContent();
		BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));

		System.out.println("[ Server 에서 받은 Data 확인 ] ");
		String serverData = br.readLine();
		System.out.println(serverData);

		// ==> 내용읽기(JSON Value 확인)
		JSONObject jsonobj = (JSONObject) JSONValue.parse(serverData);
		System.out.println(jsonobj);

	}

	// 2.2 Http Protocol POST 방식 Request : FromData전달
	// ==> JsonSimple + codehaus 3rd party lib 사용
	public static void LoginTest_Codehaus() throws Exception {

		// HttpClient : Http Protocol 의 client 추상화
		HttpClient httpClient = new DefaultHttpClient();

		String url = "http://127.0.0.1:8080/user/json/login";
		HttpPost httpPost = new HttpPost(url);
		httpPost.setHeader("Accept", "application/json");
		httpPost.setHeader("Content-Type", "application/json");

//		//[ 방법 1 : String 사용]
//		String data =  "{\"userId\":\"admin\",\"password\":\"1234\"}";
//		HttpEntity httpEntity01 = new StringEntity(data,"utf-8");

//		//[ 방법 2 : JSONObject 사용]
//		JSONObject json = new JSONObject();
//		json.put("userId", "admin");
//		json.put("password", "1234");
//		HttpEntity httpEntity01 = new StringEntity(json.toString(),"utf-8");

		// [ 방법 3 : codehaus 사용]
		User user01 = new User();
		user01.setUserId("admin");
		user01.setPassword("1234");
		ObjectMapper objectMapper01 = new ObjectMapper();
		// Object ==> JSON Value 로 변환
		String jsonValue = objectMapper01.writeValueAsString(user01);
		HttpEntity httpEntity01 = new StringEntity(jsonValue, "utf-8");

		httpPost.setEntity(httpEntity01);
		HttpResponse httpResponse = httpClient.execute(httpPost);

		// ==> Response 확인
		System.out.println(httpResponse);
		System.out.println();

		// ==> Response 중 entity(DATA) 확인
		HttpEntity httpEntity = httpResponse.getEntity();

		// ==> InputStream 생성
		InputStream is = httpEntity.getContent();
		BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));

		// ==> 다른 방법으로 serverData 처리
		// System.out.println("[ Server 에서 받은 Data 확인 ] ");
		// String serverData = br.readLine();
		// System.out.println(serverData);

		// ==> API 확인 : Stream 객체를 직접 전달
		JSONObject jsonobj = (JSONObject) JSONValue.parse(br);
		System.out.println(jsonobj);

		ObjectMapper objectMapper = new ObjectMapper();
		User user = objectMapper.readValue(jsonobj.toString(), User.class);
		System.out.println(user);
	}

	// ================================================================//

	// 3.1 Http Protocol GET Request : JsonSimple 3rd party lib 사용
//	public static void updateUserTest_JsonSimple() throws Exception {
//
//		// HttpClient : Http Protocol 의 client 추상화
//		HttpClient httpClient = new DefaultHttpClient();
//
//		String url = "http://127.0.0.1:8080/user/json/updateUser/testUserId";
//
//		// HttpGet : Http Protocol 의 GET 방식 Request
//		HttpGet httpGet = new HttpGet(url);
//		httpGet.setHeader("Accept", "application/json");
//		httpGet.setHeader("Content-Type", "application/json");
//
//		// [ 방법 2 : JSONObject 사용]
//		JSONObject json = new JSONObject();
//		json.put("userId", "testUserId");
//		json.put("password", "testPasswd");
//		HttpEntity httpEntity01 = new StringEntity(json.toString(), "utf-8");
//
//		// HttpResponse : Http Protocol 응답 Message 추상화
//		HttpResponse httpResponse = httpClient.execute(httpGet);
//
//		// ==> Response 확인
//		System.out.println(httpResponse);
//		System.out.println();
//
//		// ==> Response 중 entity(DATA) 확인
//		HttpEntity httpEntity = httpResponse.getEntity();
//
//		// ==> InputStream 생성
//		InputStream is = httpEntity.getContent();
//		BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
//
//		System.out.println("[ Server 에서 받은 Data 확인 ] ");
//		String serverData = br.readLine();
//		System.out.println(serverData);
//
//		// ==> 내용읽기(JSON Value 확인)
//		JSONObject jsonobj = (JSONObject) JSONValue.parse(serverData);
//		System.out.println(jsonobj);
//	}

	// 3.2 Http Protocol GET Request : JsonSimple + codehaus 3rd party lib 사용
//	public static void updateUserTest_Codehaus() throws Exception {
//
//		// HttpClient : Http Protocol 의 client 추상화
//		HttpClient httpClient = new DefaultHttpClient();
//
//		String url = "http://127.0.0.1:8080/user/json/updateUser/testUserId";
//
//		// HttpGet : Http Protocol 의 GET 방식 Request
//		HttpGet httpGet = new HttpGet(url);
//		httpGet.setHeader("Accept", "application/json");
//		httpGet.setHeader("Content-Type", "application/json");
//
//		// [ 방법 2 : JSONObject 사용]
//		JSONObject json = new JSONObject();
//		json.put("userId", "testUserId");
//		json.put("password", "testPasswd");
//		HttpEntity httpEntity01 = new StringEntity(json.toString(), "utf-8");
//
//		// HttpResponse : Http Protocol 응답 Message 추상화
//		HttpResponse httpResponse = httpClient.execute(httpGet);
//
//		// ==> Response 확인
//		System.out.println(httpResponse);
//		System.out.println();
//
//		// ==> Response 중 entity(DATA) 확인
//		HttpEntity httpEntity = httpResponse.getEntity();
//
//		// ==> InputStream 생성
//		InputStream is = httpEntity.getContent();
//		BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
//
//		// ==> 다른 방법으로 serverData 처리
//		// System.out.println("[ Server 에서 받은 Data 확인 ] ");
//		// String serverData = br.readLine();
//		// System.out.println(serverData);
//
//		// ==> API 확인 : Stream 객체를 직접 전달
//		JSONObject jsonobj = (JSONObject) JSONValue.parse(br);
//		System.out.println(jsonobj);
//
//		ObjectMapper objectMapper = new ObjectMapper();
//		User user = objectMapper.readValue(jsonobj.toString(), User.class);
//		System.out.println(user);
//	}
	// ================================================================//

	// 4.1 Http Protocol POST Request : FromData 전달 / JsonSimple 3rd party lib 사용
	public static void updateUserTest_JsonSimple() throws Exception {

		// HttpClient : Http Protocol 의 client 추상화
		HttpClient httpClient = new DefaultHttpClient();

		String url = "http://127.0.0.1:8080/user/json/updateUser/testUserId";
		HttpPost httpPost = new HttpPost(url);
		httpPost.setHeader("Accept", "application/json");
		httpPost.setHeader("Content-Type", "application/json");

		// [ 방법 1 : String 사용]
//				String data =  "{\"userId\":\"admin\",\"password\":\"1234\"}";
//				HttpEntity httpEntity01 = new StringEntity(data,"utf-8");

		// [ 방법 2 : JSONObject 사용]
		JSONObject json = new JSONObject();
		json.put("userId", "testUserId");
		json.put("password", "testPasswd");
		HttpEntity httpEntity01 = new StringEntity(json.toString(), "utf-8");

		httpPost.setEntity(httpEntity01);
		HttpResponse httpResponse = httpClient.execute(httpPost);

		// ==> Response 확인
		System.out.println(httpResponse);
		System.out.println();

		// ==> Response 중 entity(DATA) 확인
		HttpEntity httpEntity = httpResponse.getEntity();

		// ==> InputStream 생성
		InputStream is = httpEntity.getContent();
		BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));

		System.out.println("[ Server 에서 받은 Data 확인 ] ");
		String serverData = br.readLine();
		System.out.println(serverData);

		// ==> 내용읽기(JSON Value 확인)
		JSONObject jsonobj = (JSONObject) JSONValue.parse(serverData);
		System.out.println(jsonobj);

	}

	// 4.2 Http Protocol POST 방식 Request : FromData전달
	// ==> JsonSimple + codehaus 3rd party lib 사용
	public static void updateUserTest_Codehaus() throws Exception {

		// HttpClient : Http Protocol 의 client 추상화
		HttpClient httpClient = new DefaultHttpClient();

		String url = "http://127.0.0.1:8080/user/json/updateUser/testUserId";
		HttpPost httpPost = new HttpPost(url);
		httpPost.setHeader("Accept", "application/json");
		httpPost.setHeader("Content-Type", "application/json");

//			//[ 방법 1 : String 사용]
//			String data =  "{\"userId\":\"admin\",\"password\":\"1234\"}";
//			HttpEntity httpEntity01 = new StringEntity(data,"utf-8");

//			//[ 방법 2 : JSONObject 사용]
//			JSONObject json = new JSONObject();
//			json.put("userId", "admin");
//			json.put("password", "1234");
//			HttpEntity httpEntity01 = new StringEntity(json.toString(),"utf-8");

		// [ 방법 3 : codehaus 사용]
		User user01 = new User();
		user01.setUserId("testUserId");
		user01.setPassword("testPasswd");
		ObjectMapper objectMapper01 = new ObjectMapper();
		// Object ==> JSON Value 로 변환
		String jsonValue = objectMapper01.writeValueAsString(user01);
		HttpEntity httpEntity01 = new StringEntity(jsonValue, "utf-8");

		httpPost.setEntity(httpEntity01);
		HttpResponse httpResponse = httpClient.execute(httpPost);

		// ==> Response 확인
		System.out.println(httpResponse);
		System.out.println();

		// ==> Response 중 entity(DATA) 확인
		HttpEntity httpEntity = httpResponse.getEntity();

		// ==> InputStream 생성
		InputStream is = httpEntity.getContent();
		BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));

		// ==> 다른 방법으로 serverData 처리
		// System.out.println("[ Server 에서 받은 Data 확인 ] ");
		// String serverData = br.readLine();
		// System.out.println(serverData);

		// ==> API 확인 : Stream 객체를 직접 전달
		JSONObject jsonobj = (JSONObject) JSONValue.parse(br);
		System.out.println(jsonobj);

		ObjectMapper objectMapper = new ObjectMapper();
		User user = objectMapper.readValue(jsonobj.toString(), User.class);
		System.out.println(user);
	}

//	// 4.1 Http Protocol GET Request : JsonSimple 3rd party lib 사용
//	public static void getProductTest_JsonSimple() throws Exception {
//
//		// HttpClient : Http Protocol 의 client 추상화
//		HttpClient httpClient = new DefaultHttpClient();
//
//		String url = "http://127.0.0.1:8080/product/json/getProduct/10008";
//
//		// HttpGet : Http Protocol 의 GET 방식 Request
//		HttpGet httpGet = new HttpGet(url);
//		httpGet.setHeader("Accept", "application/json");
//		httpGet.setHeader("Content-Type", "application/json");
//		System.out.println(httpGet);
//		// HttpResponse : Http Protocol 응답 Message 추상화
//		HttpResponse httpResponse = httpClient.execute(httpGet);
//
//		// ==> Response 확인
//		System.out.println(httpResponse);
//		System.out.println();
//
//		// ==> Response 중 entity(DATA) 확인
//		HttpEntity httpEntity = httpResponse.getEntity();
//		// ==> InputStream 생성
//		InputStream is = httpEntity.getContent();
//		BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
//		System.out.println(br);
//
//		System.out.println("[ Server 에서 받은 Data 확인 ] ");
//		String serverData = br.readLine();
//		System.out.println(serverData);
//
//		// ==> 내용읽기(JSON Value 확인)
//		JSONObject jsonobj = (JSONObject) JSONValue.parse(serverData);
//		System.out.println(jsonobj);
//	}
//
//	// 4.2 Http Protocol GET Request : JsonSimple + codehaus 3rd party lib 사용
//	public static void getProductTest_Codehaus() throws Exception {
//
//		// HttpClient : Http Protocol 의 client 추상화
//		HttpClient httpClient = new DefaultHttpClient();
//
//		String url = "http://127.0.0.1:8080/product/json/getProduct/10008";
//
//		// HttpGet : Http Protocol 의 GET 방식 Request
//		HttpGet httpGet = new HttpGet(url);
//		httpGet.setHeader("Accept", "application/json");
//		httpGet.setHeader("Content-Type", "application/json");
//
//		// HttpResponse : Http Protocol 응답 Message 추상화
//		HttpResponse httpResponse = httpClient.execute(httpGet);
//
//		// ==> Response 확인
//		System.out.println(httpResponse);
//		System.out.println();
//
//		// ==> Response 중 entity(DATA) 확인
//		HttpEntity httpEntity = httpResponse.getEntity();
//
//		// ==> InputStream 생성
//		InputStream is = httpEntity.getContent();
//		BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
//
//		// ==> 다른 방법으로 serverData 처리
//		// System.out.println("[ Server 에서 받은 Data 확인 ] ");
//		// String serverData = br.readLine();
//		// System.out.println(serverData);
//
//		// ==> API 확인 : Stream 객체를 직접 전달
//		JSONObject jsonobj = (JSONObject) JSONValue.parse(br);
//		System.out.println(jsonobj);
//
//		ObjectMapper objectMapper = new ObjectMapper();
//		Product product = objectMapper.readValue(jsonobj.toString(), Product.class);
//		System.out.println(product);
//	}
//
//	// 5.1 Http Protocol POST Request : FromData 전달 / JsonSimple 3rd party lib 사용
//	public static void insertProductTest_JsonSimple() throws Exception {
//
//		// HttpClient : Http Protocol 의 client 추상화
//		HttpClient httpClient = new DefaultHttpClient();
//
//		String url = "http://127.0.0.1:8080/product/json/insertProduct";
//		HttpPost httpPost = new HttpPost(url);
//		httpPost.setHeader("Accept", "application/json");
//		httpPost.setHeader("Content-Type", "application/json");
//
//		// [ 방법 1 : String 사용]
////				String data =  "{\"userId\":\"admin\",\"password\":\"1234\"}";
////				HttpEntity httpEntity01 = new StringEntity(data,"utf-8");
//
//		// [ 방법 2 : JSONObject 사용]
//		JSONObject json = new JSONObject();
//		json.put("userId", "admin");
//		json.put("password", "1234");
//		HttpEntity httpEntity01 = new StringEntity(json.toString(), "utf-8");
//
//		httpPost.setEntity(httpEntity01);
//		HttpResponse httpResponse = httpClient.execute(httpPost);
//
//		// ==> Response 확인
//		System.out.println(httpResponse);
//		System.out.println();
//
//		// ==> Response 중 entity(DATA) 확인
//		HttpEntity httpEntity = httpResponse.getEntity();
//
//		// ==> InputStream 생성
//		InputStream is = httpEntity.getContent();
//		BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
//
//		System.out.println("[ Server 에서 받은 Data 확인 ] ");
//		String serverData = br.readLine();
//		System.out.println(serverData);
//
//		// ==> 내용읽기(JSON Value 확인)
//		JSONObject jsonobj = (JSONObject) JSONValue.parse(serverData);
//		System.out.println(jsonobj);
//
//	}
//
//	// 5.2 Http Protocol POST 방식 Request : FromData전달
//	// ==> JsonSimple + codehaus 3rd party lib 사용
//	public static void insertProductTest_Codehaus() throws Exception {
//
//		// HttpClient : Http Protocol 의 client 추상화
//		HttpClient httpClient = new DefaultHttpClient();
//
//		String url = "http://127.0.0.1:8080/product/json/insertProduct";
//		HttpPost httpPost = new HttpPost(url);
//		httpPost.setHeader("Accept", "application/json");
//		httpPost.setHeader("Content-Type", "application/json");
//
////			//[ 방법 1 : String 사용]
////			String data =  "{\"userId\":\"admin\",\"password\":\"1234\"}";
////			HttpEntity httpEntity01 = new StringEntity(data,"utf-8");
//
////			//[ 방법 2 : JSONObject 사용]
////			JSONObject json = new JSONObject();
////			json.put("userId", "admin");
////			json.put("password", "1234");
////			HttpEntity httpEntity01 = new StringEntity(json.toString(),"utf-8");
//
//		// [ 방법 3 : codehaus 사용]
//		Product product01 = new Product();
//		product01.setProdNo(11111);
//		product01.setProdName("testProd");
//		ObjectMapper objectMapper01 = new ObjectMapper();
//		// Object ==> JSON Value 로 변환
//		String jsonValue = objectMapper01.writeValueAsString(product01);
//		HttpEntity httpEntity01 = new StringEntity(jsonValue, "utf-8");
//
//		httpPost.setEntity(httpEntity01);
//		HttpResponse httpResponse = httpClient.execute(httpPost);
//
//		// ==> Response 확인
//		System.out.println(httpResponse);
//		System.out.println();
//
//		// ==> Response 중 entity(DATA) 확인
//		HttpEntity httpEntity = httpResponse.getEntity();
//
//		// ==> InputStream 생성
//		InputStream is = httpEntity.getContent();
//		BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
//
//		// ==> 다른 방법으로 serverData 처리
//		// System.out.println("[ Server 에서 받은 Data 확인 ] ");
//		// String serverData = br.readLine();
//		// System.out.println(serverData);
//
//		// ==> API 확인 : Stream 객체를 직접 전달
//		JSONObject jsonobj = (JSONObject) JSONValue.parse(br);
//		System.out.println(jsonobj);
//
//		ObjectMapper objectMapper = new ObjectMapper();
//		Product product = objectMapper.readValue(jsonobj.toString(), Product.class);
//		System.out.println(product);
//	}

}
