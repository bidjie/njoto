package ci_agriculture;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import java.io.IOException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static final String TEST_URL = "http://api.hara.ci-agriculture.com/fieldacquisition/territoryManagement/territoryPlan/organizationUnit/availableDesa/find";

    public static void main ( String[] args ) throws Exception
    {
        System.out.println( "Hello World!" );
//        getSomething();

        ListComparator tes = new ListComparator();
        tes.test();
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
}
