import twitter4j.*;

import java.util.ArrayList;
import java.util.List;

public class GetUserTweets {
    public static void main(String[] args) {
        Twitter twitter = TwitterFactory.getSingleton();
        Query query = new Query("(I) AND (Nike) AND ((Hate) OR (Love))"+ " -filter:retweets");

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
        System.out.println(tweetsContent.size());
        SentimentAnalysis sentimentAnalysis = new SentimentAnalysis();
        for(String tweetContent: tweetsContent){
            System.out.println(tweetContent);
            //System.out.println(sentimentAnalysis.getSentiment(tweetContent));
        }

     }
}
