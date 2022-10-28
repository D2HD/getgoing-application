package hackathon.d2hd.getGoingApp.dataModel;

import javax.persistence.Entity;
import java.util.ArrayList;

public class TweetTrend {
    private String main_content;
    private Long num_of_occurrences;
    private ArrayList<Integer> sevenDaySearch;

    public TweetTrend(String main_content, Long num_of_occurrences, ArrayList<Integer> sevenDaySearch) {
        this.main_content = main_content;
        this.num_of_occurrences = num_of_occurrences;
        this.sevenDaySearch = sevenDaySearch;
    }

    public TweetTrend() {
    }

    public String getMain_content() {
        return main_content;
    }

    public void setMain_content(String main_content) {
        this.main_content = main_content;
    }

    public Long num_of_occurrences() {
        return num_of_occurrences;
    }

    public void setNum_of_occurrences(Long num_of_occurrences) {
        this.num_of_occurrences = num_of_occurrences;
    }

    public ArrayList<Integer> getSevenDaySearch() {
        return sevenDaySearch;
    }

    public void setSevenDaySearch(ArrayList<Integer> sevenDaySearch) {
        this.sevenDaySearch = sevenDaySearch;
    }
}
