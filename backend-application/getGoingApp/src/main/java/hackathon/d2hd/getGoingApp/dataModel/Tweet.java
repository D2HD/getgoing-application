package hackathon.d2hd.getGoingApp.dataModel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tweet")
public class Tweet {

    //url
    @Id
    @Column(name = "value1")
    private String value1;

    //topic
    @Column(name = "value2")
    private String value2;

    //profile_name
    @Column(name = "value3")
    private String value3;

    //tweet_id
    @Column(name = "value4")
    private String value4;

    //username
    @Column(name = "value5")
    private String value5;

    //profile_picture_url
    @Column(name = "value6")
    private String value6;

    //tweet_content
    @Column(name = "value7")
    private String value7;

    //tweet_like_count
    @Column(name = "value8")
    private String value8;


    //tweet_retweet_count
    @Column(name = "value9")
    private String value9;

    //tweet_reply_count
    @Column(name = "value10")
    private String value10;


    //tweet_quote_tweet_count
    @Column(name = "value11")
    private String value11;

    //photo_urls
    @Column(name = "value12")
    private String value12;

    //photo_url_number
    @Column(name = "value13")
    private String value13;

    //localDateTime
    @Column(name = "value14")
    private String value14;

    //tweet_json
    @Column(name = "value15")
    private String value15;

    public Tweet() {}

    public Tweet(String value1, String value2, String value3, String value4, String value5, String value6, String value7, String value8, String value9, String value10, String value11, String value12, String value13, String value14, String value15) {
        this.value1 = value1;
        this.value2 = value2;
        this.value3 = value3;
        this.value4 = value4;
        this.value5 = value5;
        this.value6 = value6;
        this.value7 = value7;
        this.value8 = value8;
        this.value9 = value9;
        this.value10 = value10;
        this.value11 = value11;
        this.value12 = value12;
        this.value13 = value13;
        this.value14 = value14;
        this.value15 = value15;
    }

    public String getValue1() {
        return value1;
    }

    public void setValue1(String value1) {
        this.value1 = value1;
    }

    public String getValue2() {
        return value2;
    }

    public void setValue2(String value2) {
        this.value2 = value2;
    }

    public String getValue3() {
        return value3;
    }

    public void setValue3(String value3) {
        this.value3 = value3;
    }

    public String getValue4() {
        return value4;
    }

    public void setValue4(String value4) {
        this.value4 = value4;
    }

    public String getValue5() {
        return value5;
    }

    public void setValue5(String value5) {
        this.value5 = value5;
    }

    public String getValue6() {
        return value6;
    }

    public void setValue6(String value6) {
        this.value6 = value6;
    }

    public String getValue7() {
        return value7;
    }

    public void setValue7(String value7) {
        this.value7 = value7;
    }

    public String getValue8() {
        return value8;
    }

    public void setValue8(String value8) {
        this.value8 = value8;
    }

    public String getValue9() {
        return value9;
    }

    public void setValue9(String value9) {
        this.value9 = value9;
    }

    public String getValue10() {
        return value10;
    }

    public void setValue10(String value10) {
        this.value10 = value10;
    }

    public String getValue11() {
        return value11;
    }

    public void setValue11(String value11) {
        this.value11 = value11;
    }

    public String getValue12() {
        return value12;
    }

    public void setValue12(String value12) {
        this.value12 = value12;
    }

    public String getValue13() {
        return value13;
    }

    public void setValue13(String value13) {
        this.value13 = value13;
    }

    public String getValue14() {
        return value14;
    }

    public void setValue14(String value14) {
        this.value14 = value14;
    }

    public String getValue15() {
        return value15;
    }

    public void setValue15(String value15) {
        this.value15 = value15;
    }

    @Override
    public String toString() {
        return "Tweet{" +
                "value1='" + value1 + '\'' +
                ", value2='" + value2 + '\'' +
                ", value3='" + value3 + '\'' +
                ", value4='" + value4 + '\'' +
                ", value5='" + value5 + '\'' +
                ", value6='" + value6 + '\'' +
                ", value7='" + value7 + '\'' +
                ", value8='" + value8 + '\'' +
                ", value9='" + value9 + '\'' +
                ", value10='" + value10 + '\'' +
                ", value11='" + value11 + '\'' +
                ", value12='" + value12 + '\'' +
                ", value13='" + value13 + '\'' +
                ", value14='" + value14 + '\'' +
                ", value15='" + value15 + '\'' +
                '}';
    }
}
