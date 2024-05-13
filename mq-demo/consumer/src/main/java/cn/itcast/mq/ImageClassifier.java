package cn.itcast.mq;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author : Valor
 * @create 2024/5/13 11:48
 */
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;

public class ImageClassifier {

    private static final String API_KEY = "IvRfvfFqgYZoJC54lRvyAm9W";
    private static final String SECRET_KEY = "3iB47MSPFzcve2dlRBWRSLotFsTibzUi";
    private static final String TOKEN_URL = "https://aip.baidubce.com/oauth/2.0/token";
//    private static final String IMAGE_URL = "https://aip.baidubce.com/rest/2.0/image-classify/v1/animal";//动物
    private static final String IMAGE_URL = "https://aip.baidubce.com/rest/2.0/image-classify/v2/advanced_general";

    public static void main(String[] args) throws IOException, JSONException {
        String accessToken = fetchToken();
//        String imageBase64 = encodeImageToBase64("./text.jpg");
//        String imageBase64 = encodeImageToBase64("/Users/ivalor/Downloads/项目脚手架/project/mq-demo/consumer/src/main/java/cn/itcast/mq/text.jpg");
        String imageBase64 = encodeImageToBase64("/Users/ivalor/Downloads/项目脚手架/project/mq-demo/consumer/src/main/java/cn/itcast/mq/tt.jpg");
        JSONObject result = requestAnimalRecognition(accessToken, imageBase64);
        System.out.println(result.toString(2));
    }

    private static String fetchToken() throws IOException, JSONException {
        HttpClient client = HttpClients.createDefault();
        HttpPost post = new HttpPost(TOKEN_URL);

        String body = "grant_type=client_credentials&client_id=" + API_KEY + "&client_secret=" + SECRET_KEY;
        post.setEntity(new StringEntity(body));
        HttpResponse response = client.execute(post);
        String resultStr = EntityUtils.toString(response.getEntity());

        JSONObject result = new JSONObject(resultStr);
        if (result.has("access_token") && result.has("scope")) {
            if (!result.getString("scope").contains("brain_all_scope")) {
                throw new RuntimeException("The API does not have brain_all_scope enabled.");
            }
            return result.getString("access_token");
        } else {
            throw new RuntimeException("Invalid API_KEY or SECRET_KEY");
        }
    }

    private static String encodeImageToBase64(String imagePath) throws IOException {
        byte[] fileContent = Files.readAllBytes(Paths.get(imagePath));
        return Base64.getEncoder().encodeToString(fileContent);
    }

    private static JSONObject requestAnimalRecognition(String accessToken, String imageBase64) throws IOException, JSONException {
        String url = IMAGE_URL + "?access_token=" + accessToken;
        HttpClient client = HttpClients.createDefault();
        HttpPost post = new HttpPost(url);
        String body = "image=" + imageBase64;
        post.setEntity(new StringEntity(body));
        HttpResponse response = client.execute(post);
        String resultStr = EntityUtils.toString(response.getEntity());
        return new JSONObject(resultStr);
    }
}
