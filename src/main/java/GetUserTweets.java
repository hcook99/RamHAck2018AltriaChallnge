import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

import java.util.ArrayList;
import java.util.List;

public class GetUserTweets {
    public ArrayList sentimentValues(String queryText) throws Exception {
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey("IEY1skT2NUGZnnfxmPYb3Jjxx")
                .setOAuthConsumerSecret("jk2AZRc9Kp9OOBHLRHjYwV8xS2j2QfWAlyr3QUZZ5cDh5FyEG4")
                .setOAuthAccessToken("1013155241003536385-i9R1cMvMBE8pkzMAIHF7nRN8FyGiHv")
                .setOAuthAccessTokenSecret("4xtp8t5FeqA18tHQR2wNVt1R9LWA2Ho18Pg6aFUzHVChK");
        TwitterFactory tf = new TwitterFactory(cb.build());
        Twitter twitter = tf.getInstance();
        Query query = new Query(queryText+ " -filter:retweets");
        query.setLang("en");
        Paging paging = new Paging();
        query.setCount(100);
        QueryResult queryResult=null;
        try {
            queryResult = twitter.search(query);
        }catch(TwitterException e){
            e.printStackTrace();
        }
        ArrayList<String> tweetsContent= new ArrayList();
        for(Status status: queryResult.getTweets()){
            tweetsContent.add(status.getText());
        }
        ArrayList<Float> sentiments = new ArrayList<Float>();
        SentimentAnalysis sentimentAnalysis = new SentimentAnalysis();
        for(String tweetContent: tweetsContent){
            sentiments.add(sentimentAnalysis.getSentiment(tweetContent));
        }
        return sentiments;
     }
}
