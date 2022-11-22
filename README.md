# Appetizer Hackathon 2022

## Instructions
* Ensure that JDK is set to gradle 8
* Run the application by going to java/hackathon/d2hd/getGoingApp/GetGoingApplication.java and running the GetGoingApplication.java file
* Application will run on http://localhost:8080
* Ensure that the frontend application is running as well which should run on http://localhost:3000

## Reference

* API
  * https://www.api-appetizer.com/page/hashscraper2
  * https://www.api-appetizer.com/page/langcode2
* Git
  * https://git-scm.com/docs/git
  * https://docs.github.com/en
  * https://gitforwindows.org/
  * https://code.visualstudio.com/docs/sourcecontrol/overview
  * https://www.jetbrains.com/help/idea/set-up-a-git-repository.html
* FE
  * https://docs.npmjs.com/
  * https://www.figma.com/best-practices/
* BE
  * https://docs.gradle.org/
  * https://docs.spring.io/spring-framework/docs/current/reference/html/
    * https://www.jetbrains.com/help/idea/spring-support.html
  * https://www.postgresql.org/docs/current/index.html
    * https://www.jetbrains.com/help/idea/postgresql.html
  * https://learning.postman.com/docs/getting-started/introduction/
  * https://confluence.atlassian.com/jira

# Code
### Repository Package
#### Hashtag Repository

* Interface extending JPARepository
* The interface uses JPA methods to sort and filter through the database by using different metrics such as time, topics or both.

#### Tweet Repository
* An interface extending JPARepository
* Has no JPA methods to sort or filter through the database as they are not needed


### Data Model
* Specified as an Entity.
* Specifies instance variables for the class.
* The @Table annotation is used to define the table name
* The @Id annotation is used to specify the primary key in the entity.
#### Tweet
* Values:
  * value1: URL
    * ex: https://twitter.com/708060302445826049/status/1589404604017451008
  * value2: Topic
    * ex: #forsale
  * value3: Profile Name
    * ex: FLORIDACOLLECTABLES
  * value4: Tweet ID
    * ex: 708060302445826049
  * value5: Username
    * ex: Floridacollect
  * value6: Profile Picture URL
    * ex: https://pbs.twimg.com/profile_images/708060959038906369/IeELukok_normal.jpg
  * value7: Tweet content
    * ex: #forsale #barbaracrampton Check out Puppet Master: The Littlest Reich, Brand New Sealed Blu-ray Barbara Crampton https://t.co/Y3smlErrkz #eBay via @eBay
  * value8: Tweet Like Count
    * ex: 0
  * value9: Tweet Retweet Count
    * ex: 0
  * value10: Tweet Reply Count
    * ex: 0
  * value11: Tweet Quote Tweet Count
    * ex: 0
  * value12: Photo URLs
    * ex: []
  * value13: Photo URL Number
    * ex: []
  * value14: Time of Scrape
    * ex: 2022-11-07 08:49:21 +0900
  * value15: Tweet JSON with sentiment
    * ex: [{"content":"#forsale #barbaracrampton Check out Puppet Master: The Littlest Reich, Brand New Sealed Blu-ray Barbara Crampton https://t.co/Y3smlErrkz #eBay via @eBay","score":0.0}]
    * Sentiment is broken down per sentence and the score is shown at the very end
#### Hashtag
* Values:
  * hashtag_id: String type identifier
  * hashtag_name: String type value
  * num_of_occurrences: Long type value
  * timestamp: LocalDate type value
  * like_count: Long type value
  * general_sentiment: Double type value

### Data Transfer Object (Dto)
## TweetDto class
* Used to instantiate a TweetDto object with variables of different types so that specific operations (which are not performable with purely string attributes) can be performed
* Constructor: Has 13 variables
  * url: Stored as a String variable
  * topic: Stored as a String variable
  * profile_name: Stored as a String variable
  * tweet_id: Stored as a Long variable
  * username: Stored as a String variable
  * tweet_content: Stored as String variable
  * hashtagList: Stored as a list as it contains each hashtag
  * tweet_like_count: Stored as a Long variable
  * tweet_retweet_count: Stored as a Long variable
  * tweet_reply_count: Stored as a Long variable
  * tweet_quote_tweet_count: Stored as a Long variable
  * localDateTime: Stored as a localDateTime variable
  * general_sentiment: Stored as a Double (for extra precision)

## HashtagDto
* Used to instantiate a HashTagDto object with variables of different types so that specific operations (which are not performable with purely string attributes) can be performed.
* The instance variables of these objects continually change every time a scrape is called.
* Constructor: Has 8 variables
  * hashtag_id: Stored as a String variable
  * hashtag_name: Stored as a String variable
  * like_count: Stores total likes of all tweets having this hashtag. Stored as a long variable
  * daily_hashtag_count: Stores number of times a hashtag has been searched in the last 7 days as an array.
  * weekly_hashtag_count: Stores number of times a hashtag has been searched over the 4 weeks as an array.
  * weekly_general_sentiment: Stores as an array of GeneralSentiment objects. The first index is the sentiment of a hashtag of the oldest week while the last index is the hashtag of the newest week. This variable stores 4 values in the array, one for each week.
  * general_sentiment_of_the_day: Stored as a GeneralSentiment object holding the sentiment of the hashtag on the current day. Changes daily.
  * general_sentiment_of_the_week: Holds one GeneralSentiment object containing the average sentiment of the particular hashtag over the 1 week.
  * daily_retweet_count: Stored as an array of long type variables where each value corresponds to the total retweet count of all the tweets with that hashtag over 1 day. The first index corresponds to the last day.
  * num_of_occurrence: Stores the total occurrences of all tweets having this hashtag. Stored as a Long variable.
  * timestamp: Stores the timestamp of the last scraped tweet with the same hashtag name. Stored as a localDate variable.
  * general_sentiment: Stores the average sentiment of all tweets having the same hashtag. Stored as a Double variable (for better precision)
  * retweet_count: Stores total number of retweets all the tweets with this hashtag have as a long data type.

