package resources;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class Utils {

    RequestSpecification reqSpec;
    JsonPath js;

    public RequestSpecification requestSpecification() throws IOException {
        if(reqSpec==null) {
            //FileOutputStream == Files.newOutputStream
            PrintStream log = new PrintStream(Files.newOutputStream(Paths.get("logging.txt")));
            reqSpec = new RequestSpecBuilder().setBaseUri(getGlobalValue("baseUrl"))
                    .addQueryParam("key", "qaclick123")
                    .addFilter(RequestLoggingFilter.logRequestTo(log))
                    .addFilter(ResponseLoggingFilter.logResponseTo(log))
                    .setContentType(ContentType.JSON).build();

            return reqSpec;
        }

        return reqSpec;
    }

    public ResponseSpecification responseSpecification()
    {
        ResponseSpecification resSpec = (ResponseSpecification) new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON);
        return resSpec;
    }

    public static String getGlobalValue(String key) throws IOException {
        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream("C:\\Users\\aarti\\eclipse-workspace\\APIFramework\\src\\test\\java\\resources\\global.properties");
        prop.load(fis);
        return prop.getProperty(key);
    }

    public String getJsonPath(Response response, String key)
    {
        String responseString = response.asString();
        System.out.println(responseString);
        js = new JsonPath(responseString);
        return js.get(key).toString();
    }
}
