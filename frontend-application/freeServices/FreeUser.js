import axios from "axios";

const USERS_REST_API_URL =
  "http://localhost:8080/api/free/currentTop5HashtagDtoList";

class UserService {
  currentTop5HashtagDtoList() {
    return axios.get(USERS_REST_API_URL);
  }
  keywordSearchToTweetDtoList(userInput) {
    return axios.get(
      "http://localhost:8080/api/free/keywordSearchToTweetDtoList/" + userInput
    );
  }
  keywordSearchToHashtagDto(userInput) {
    return axios.get(
      "http://localhost:8080/api/free/keywordSearchToHashtagDto/" + userInput
    );
  }
}

//Export object of class
export default new UserService();
