import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

import java.util.ArrayList;
import java.util.List;

public class GetUserTweets {
    public ArrayList sentimentValues(String queryText) throws Exception {
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey("*****************************************************")
                .setOAuthConsumerSecret("*****************************************************")
                .setOAuthAccessToken("*****************************************************")
                .setOAuthAccessTokenSecret("**********************************************");
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
