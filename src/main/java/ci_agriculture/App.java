package ci_agriculture;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Hello world!
 *
 */
public class App 
{
    public static final String TEST_URL = "http://api.hara.ci-agriculture.com/fieldacquisition/territoryManagement/territoryPlan/organizationUnit/availableDesa/find";
    public static final String VASHAM_LOGIN_URL = "http://test.vasham.co.id/api-token-auth/";
    public static final String HARA_LOGIN_URL = "http://api.hara.ci-agriculture.com/login";
    public static final String HARA_FARMER_GET_URL = "http://api.hara.ci-agriculture.com/master/farmer/find";
    public static final String VASHAM_FARMER_GET_UTL = "http://test.vasham.co.id/openapi/accountmember";

    public static void main ( String[] args ) throws Exception
    {

        String vitsToken = authVITS();
        String haraToken = authHARA();

        JsonNode allFarmerVits = getVITSFarmerData(vitsToken);
    }

    public static void getSomething () throws UnirestException {
        HttpResponse<JsonNode> jsonResponse = Unirest.get(TEST_URL)
                .header("accept", "application/json")
                .queryString("organization_unit_id", 7)
                .queryString("season_id", 5)
                .queryString("radius", 10)
                .queryString("show_polygon", false)
                .asJson();
        System.out.println(jsonResponse.getBody().toString());
    }

    public static String authHARA() throws  UnirestException {
        JsonNode node = new JsonNode("{'username':'hadi', 'password':'test'}");
        HttpResponse<JsonNode> jsonResponse = Unirest.post(HARA_LOGIN_URL)
                .header("Content-Type", "application/json")
                .header("accept", "application/json")
                .body(node)
                .asJson();
        return jsonResponse.getBody().getObject().get("token").toString();
    }

    public static String authVITS() throws  UnirestException {
        HttpResponse<JsonNode> jsonResponse = Unirest.post(VASHAM_LOGIN_URL)
                .header("accept", "application/json")
                .field("username", "MEDIATRAC")
                .field("password", "ifm")
                .asJson();
        return jsonResponse.getBody().getObject().get("token").toString();
    }

    public static JsonNode getHARAFarmerData(String token) throws UnirestException {
        HttpResponse<JsonNode> jsonResponse = Unirest.get(HARA_FARMER_GET_URL)
                .header("accept", "application/json")
                .header("X-Auth-Token", token)
                .asJson();
        return jsonResponse.getBody();
    }

    public static JsonNode getVITSFarmerData(String token) throws UnirestException {
        HttpResponse<JsonNode> jsonResponse = Unirest.get(VASHAM_FARMER_GET_UTL)
                .header("accept", "application/json")
                .header("Authorization", "JWT " + token)
                .asJson();
        return jsonResponse.getBody();
    }
}
