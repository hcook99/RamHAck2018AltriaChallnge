
import com.google.api.gax.paging.Page;
import com.google.cloud.language.v1.AnalyzeSentimentResponse;
import com.google.cloud.language.v1.Document;
import com.google.cloud.language.v1.LanguageServiceClient;
import com.google.cloud.language.v1.Sentiment;
import com.google.cloud.storage.Bucket;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import io.grpc.StatusRuntimeException;

import java.lang.reflect.Field;
import java.util.Map;


public class SentimentAnalysis {
    public SentimentAnalysis(){
        setEnv("GOOGLE_APPLICATION_CREDENTIALS", "/Users/harrisoncook/altriaapphackath-**********************.json");
        authImplicit();
    }
    public float getSentiment(String text) throws Exception {
        LanguageServiceClient language = LanguageServiceClient.create();
        Document doc = Document.newBuilder().setContent(text).setType(Document.Type.PLAIN_TEXT).build();
        AnalyzeSentimentResponse response=null;
        try {
            response = language.analyzeSentiment(doc);
        }catch(StatusRuntimeException e){
            e.getStatus();
        }
        Sentiment sentiment = response.getDocumentSentiment();
        System.out.println(sentiment.getScore());
        language.close();
        return sentiment.getScore();

    }
    static void authImplicit() {
        // If you don't specify credentials when constructing the client, the client library will
        // look for credentials via the environment variable GOOGLE_APPLICATION_CREDENTIALS.
        Storage storage = StorageOptions.getDefaultInstance().getService();


    }
    public static void setEnv(String key, String value) {
        try {
            Map<String, String> env = System.getenv();
            Class<?> cl = env.getClass();
            Field field = cl.getDeclaredField("m");
            field.setAccessible(true);
            Map<String, String> writableEnv = (Map<String, String>) field.get(env);
            writableEnv.put(key, value);
        } catch (Exception e) {
            throw new IllegalStateException("Failed to set environment variable", e);
        }
    }
}
