import { React, useState, useEffect, use } from "react";
import {
  Flex,
  Spacer,
  Button,
  Heading,
  Stack,
  Text,
  Spinner,
  Table,
  Thead,
  Tr,
  Th,
  Tbody,
  Td,
} from "@chakra-ui/react";
import UserService from "../../freeServices/FreeUser";
import { Pie, Line, Bar } from "react-chartjs-2";
import dynamic from "next/dynamic";
import {
  Chart,
  ArcElement,
  Chart as ChartJS,
  CategoryScale,
  LinearScale,
  PointElement,
  LineElement,
  BarElement,
  Title,
  SubTitle,
  Tooltip,
  Legend,
  Ticks,
} from "chart.js";
import { useParams } from "react-router";
import { faSleigh } from "@fortawesome/free-solid-svg-icons";
Chart.register(
  ArcElement,
  CategoryScale,
  LinearScale,
  PointElement,
  LineElement,
  BarElement,
  Title,
  SubTitle,
  Tooltip,
  Legend
);
// Labels for past 7 days date
function pastSevenDays(numOfDays, date = new Date()) {
  date.setDate(date.getDate() - numOfDays);
  var finalDate = date.getDate();
  return finalDate;
}
// Return real time
function lastUpdated() {
  var date = new Date();
  date.setDate(date.getDate());
  var update =
    date.getDate() -
    1 +
    "-" +
    date.getMonth() +
    "-" +
    date.getFullYear() +
    "," +
    " 23" +
    ":" +
    "00";
  return update;
}
function pastWeek(thisWeek, date = new Date()) {
  date.setDate(date.getDate() - thisWeek);
  var firstDate = date.getDate() + "/" + date.getMonth();

  date.setDate(date.getDate() - 6);
  var secondDate = date.getDate() + "/" + date.getMonth();
  return secondDate + " - " + firstDate;
}
let dateObj = new Date();
let month = dateObj.getUTCMonth() + 1;
let day = dateObj.getUTCDate() - 1;
let year = dateObj.getUTCFullYear();

const PremiumTrends = (props) => {
  let { searchTopic, printSearch, searchObj } = props;
  let index = 0;
  const [state, setState] = useState(0);
  const [showList, setShowList] = useState(false);
  useEffect(() => {
    UserService.currentTop5HashtagDtoList().then((Response) => {
      setState({ users: Response.data });
    });
  }, []);
  useEffect(() => {
    setReturnedObj(searchObj);
  });
  const [topic, setTopic] = useState({
    name: "",
    dailyCount: "",
    weeklyCount: [],
    weeklyPos: [],
    weeklyNeg: [],
    dailySentPos: 0.0,
    dailySentNeg: 0.0,
    weeklySentPos: 0.0,
    weeklySentNeg: 0.0,
    dailyRTcount: [],
  });
  const [returnedObj, setReturnedObj] = useState({});
  if (searchTopic != null && printSearch === true) {
    console.log("success");
    console.log(searchTopic);
    console.log(searchObj); //search Obj
    console.log(printSearch);
  }

  return (
    <Flex
      flexBasis="75%"
      className="right"
      flexDir="column"
      alignItems="left"
      bgColor="#fff">
      <Flex
        className="List"
        id="List"
        padding="10px"
        display={printSearch ? "flex" : "none"}
        flexDir="row"
        height="400px"
        align="right">
        <Flex
          bgColor="#111C44"
          borderRadius="10px"
          padding="10px"
          height="400px"
          width="890px"
          flexDir="column">
          <Heading
            color="white"
            fontSize="20px"
            fontWeight="normal"
            padding="20px"
            textAlign="center">
            Top 10 Tweets- Based on retweets
          </Heading>
          <Spinner
            justifySelf="center"
            alignSelf="center"
            display={searchTopic.users ? "none" : "flex"}
            thickness="4px"
            speed="1.25s"
            emptyColor="gray.200"
            color="blue.500"
            size="xl"
          />
          <Flex overflowY="auto" maxHeight="450px">
            <Table colorScheme="teal">
              <Thead position="sticky" top={0} bgColor="#111C44">
                <Tr>
                  <Th>Rank</Th>
                  <Th>Tweet</Th>
                  <Th>Retweets</Th>
                  <Th>Content Sentiments</Th>
                </Tr>
              </Thead>

              <Tbody color="#6484AA">
                {searchTopic.users?.map((topic, index) => (
                  <Tr>
                    <Td>{(index += 1)}</Td>
                    <Td>{topic.tweet_content}</Td>
                    <Td>{topic.tweet_retweet_count}</Td>
                    <Td>{topic.general_sentiment}</Td>
                  </Tr>
                ))}
              </Tbody>
            </Table>
          </Flex>
          <Flex justifyContent="center" textColor="white" height="10px">
            <Text font>Last updated: {lastUpdated()}</Text>
          </Flex>
        </Flex>
      </Flex>
      <Flex height="20px" w="100%"></Flex>
      <Flex
        className="Top5Trends"
        id="Top5Trends"
        padding="10px"
        flexDir="row"
        height="520px"
        align="right"
        display={printSearch ? "none" : "flex"}>
        <Flex
          borderRadius="10px"
          flexDir="column"
          bgColor="#111C44"
          padding="10px"
          width="890px"
          alignItems="center">
          <Heading
            fontSize="20px"
            fontWeight="normal"
            textAlign="center"
            paddingTop="20px"
            color="white">
            Latest top 5 trends
          </Heading>

          <Flex
            justify="space-evenly"
            flexDir="column"
            height="450px"
            width="360px"
            margin="10px">
            <Spinner
              justifySelf="center"
              alignSelf="center"
              display={state.users ? "none" : "flex"}
              thickness="4px"
              speed="1.25s"
              emptyColor="gray.200"
              color="blue.500"
              size="xl"
            />
            {state.users?.map((topic) => (
              <Button
                bgColor="white"
                size="lg"
                _hover={{ opacity: "25%" }}
                ml="35px"
                mr="35px"
                justifyContent="flex-start"
                onClick={() => {
                  setTopic({
                    name: topic?.hashtag_name,
                    dailyCount: topic?.daily_hashtag_count,
                    weeklyCount: topic?.weekly_hashtag_count,
                    weeklyPos: topic?.weekly_general_sentiment.map(
                      (sentiment) => sentiment.positive_sentiment
                    ),
                    weeklyNeg: topic?.weekly_general_sentiment.map(
                      (sentiment) => sentiment.negative_sentiment
                    ),
                    dailySentPos:
                      topic?.general_sentiment_of_the_day.positive_sentiment,
                    dailySentNeg:
                      topic?.general_sentiment_of_the_day.negative_sentiment,
                    weeklySentPos:
                      topic?.general_sentiment_of_the_week.positive_sentiment,
                    weeklySentNeg:
                      topic?.general_sentiment_of_the_week.negative_sentiment,
                    dailyRTcount: topic?.daily_retweet_count,
                  });
                }}>
                <Flex
                  alignItems="center"
                  justifyContent="space-between"
                  w="100%">
                  <Text fontSize="20px" flexBasis="50%">
                    {topic.hashtag_name}
                  </Text>
                  <Text fontSize="10px" flexBasis="25%" textAlign="left">
                    Likes:{topic.like_count}
                  </Text>
                </Flex>
              </Button>
            ))}

            <Flex justifyContent="center" textColor="white" height="10px">
              <Text>Last updated: {lastUpdated()}</Text>
            </Flex>
            <Button
              colorScheme="red"
              size="lg"
              _hover={{ opacity: "25%" }}
              ml="35px"
              mr="35px"
              justifyContent="flex-start"
              onClick={() =>
                setTopic({
                  name: "",
                  dailyCount: "",
                  weeklyCount: [],
                  weeklyPos: [],
                  weeklyNeg: [],
                  dailySentPos: 0.0,
                  dailySentNeg: 0.0,
                  weeklySentPos: 0.0,
                  weeklySentNeg: 0.0,
                  dailyRTcount: [],
                })
              }>
              <Flex alignItems="center" justifyContent="space-between" w="100%">
                <Text fontSize="20px">Reset</Text>
              </Flex>
            </Button>
          </Flex>
        </Flex>
      </Flex>
      <Flex>
        <Flex
          className="DailySearches"
          id="DailySearches"
          padding="10px"
          flexDir="row">
          <Flex
            bgColor="#111C44"
            borderRadius="10px"
            padding="20px"
            height="440px">
            <Bar
              data={{
                labels: [
                  pastSevenDays(6),
                  pastSevenDays(5),
                  pastSevenDays(4),
                  pastSevenDays(3),
                  pastSevenDays(2),
                  pastSevenDays(1),
                  pastSevenDays(0),
                ],
                datasets: [
                  {
                    label: topic.name
                      ? topic.name
                      : returnedObj.users?.hashtag_name,
                    data: topic.dailyCount
                      ? topic.dailyCount
                      : returnedObj.users?.daily_hashtag_count,
                    backgroundColor: "#4481EB",
                    borderColor: "#4481EB",
                    borderWidth: 3,
                    pointBorderColor: "white",
                    pointBackgroundColor: "white",
                  },
                ],
              }}
              height="440px"
              width="450px"
              options={{
                maintainAspectRatio: false,
                responsive: true,
                plugins: {
                  legend: {
                    labels: {
                      color: "white",
                      font: {
                        size: "13px",
                      },
                    },
                  },
                  title: {
                    display: true,
                    text: "Daily Search",
                    color: "white",
                    padding: {
                      top: 10,
                      bottom: 10,
                    },
                    font: {
                      size: "20px",
                      weight: "normal",
                    },
                  },
                  subtitle: {
                    display: true,
                    text: "Last updated: " + lastUpdated(),
                    color: "white",
                    font: {
                      size: "13px",
                      family: "sans-serif",
                    },
                    position: "bottom",
                  },
                },
                scales: {
                  y: {
                    grid: {
                      color: "#5F6A8A",
                    },
                    ticks: {
                      color: "white",
                    },
                  },
                  x: {
                    grid: {
                      color: "#5F6A8A",
                    },
                    ticks: {
                      color: "white",
                    },
                  },
                },
              }}
            />
          </Flex>
        </Flex>
        {/* WEEKLY SEARCH BAR -----------------------------------------------------*/}
        <Flex
          className="WeeklySearch_Bar"
          id="WeeklySearch_Bar"
          padding="10px"
          flexDir="row">
          <Flex bgColor="#111C44" borderRadius="10px" padding="20px">
            <Bar
              data={{
                labels: [pastWeek(22), pastWeek(15), pastWeek(8), pastWeek(1)],
                datasets: [
                  {
                    label: topic.name
                      ? topic.name
                      : returnedObj.users?.hashtag_name,
                    data:
                      topic.weeklyCount.length === 0
                        ? returnedObj.users?.weekly_hashtag_count
                        : topic.weeklyCount,
                    backgroundColor: "#4481EB",
                    borderColor: "#4481EB",
                    pointBorderColor: "white",
                    pointBackgroundColor: "white",
                    borderRadius: 120,
                    borderSkipped: "bottom",
                    maxBarThickness: 30,
                  },
                ],
              }}
              height={400}
              width={"380px"}
              options={{
                maintainAspectRatio: false,
                responsive: true,
                plugins: {
                  legend: {
                    labels: {
                      color: "white",
                      font: {
                        size: "13px",
                      },
                    },
                  },
                  title: {
                    display: true,
                    text: "Weekly Searches",
                    color: "white",
                    padding: {
                      top: 10,
                      bottom: 10,
                    },
                    font: {
                      size: "20px",
                      weight: "normal",
                    },
                  },
                },
                scales: {
                  y: {
                    grid: {
                      color: "#5F6A8A",
                    },
                    ticks: {
                      color: "white",
                    },
                  },
                  x: {
                    grid: {
                      color: "#5F6A8A",
                    },
                    ticks: {
                      color: "white",
                    },
                  },
                },
              }}
            />
          </Flex>
        </Flex>
      </Flex>
      {/* CONTENT SENTIMENT-----------------------------------------------------*/}
      <Heading p="10px">Content Sentiment</Heading>
      <Flex className="WeeklyCS_Line" id="WeeklyCS_Line" padding="10px">
        <Flex bgColor="#111C44" borderRadius="10px" padding="10px" w="890px">
          <Line
            data={{
              labels: [pastWeek(22), pastWeek(15), pastWeek(8), pastWeek(1)],
              datasets: [
                {
                  label: "Positive (+1)",
                  data:
                    topic.weeklyPos.length === 0
                      ? returnedObj.users?.weekly_general_sentiment.map(
                          (sentiment) => sentiment.positive_sentiment
                        )
                      : topic.weeklyPos,
                  //topic.name.positive
                  backgroundColor: "#4481EB",
                  borderColor: "#4481EB",
                  borderWidth: 5,
                  pointBorderColor: "white",
                  pointBackgroundColor: "white",
                  pointHoverBackgroundColor: "#4481EB",
                  pointBackgroundColor: "#4481EB",
                  pointBorderWidth: 8,
                },
                {
                  label: "Negative (-1)",
                  data:
                    topic.weeklyNeg.length === 0
                      ? returnedObj.users?.weekly_general_sentiment.map(
                          (sentiment) => sentiment.negative_sentiment
                        )
                      : topic.weeklyNeg, //topic.name.negative
                  backgroundColor: "red",
                  borderColor: "red",
                  borderWidth: 5,
                  pointBorderColor: "white",
                  pointBackgroundColor: "white",
                  pointHoverBackgroundColor: "red",
                  pointBackgroundColor: "red",
                  pointBorderWidth: 8,
                },
              ],
            }}
            height={400}
            width={"880px"}
            options={{
              maintainAspectRatio: false,
              responsive: true,
              plugins: {
                legend: {
                  labels: {
                    color: "white",
                    font: {
                      size: "15px",
                      weight: "normal",
                    },
                  },
                },
                title: {
                  display: true,
                  text: "Weekly Content Sentiments",
                  color: "white",
                  padding: {
                    top: 10,
                    bottom: 10,
                  },
                  font: {
                    size: "20px",
                    weight: "normal",
                  },
                },
              },
              scales: {
                y: {
                  grid: {
                    color: "#5F6A8A",
                  },
                  ticks: {
                    color: "white",
                  },
                },
                x: {
                  grid: {
                    color: "#5F6A8A",
                  },
                  ticks: {
                    color: "white",
                  },
                },
              },
            }}
          />
        </Flex>
      </Flex>
      {/* CONTENT SENTIMENT PIE -----------------------------------------------------*/}
      <Flex>
        <Flex
          className="DailyCSPie"
          id="CSPie"
          padding="10px"
          flexDir="row"
          height={"460px"}>
          <Flex
            bgColor="#111C44"
            borderRadius="10px"
            padding="20px"
            width={"435px"}>
            <Pie
              data={{
                labels: ["Positve (+1)", "Negative(-1)"],
                datasets: [
                  {
                    label: "CS for daily",
                    data: [
                      topic.dailySentPos
                        ? topic.dailySentPos
                        : returnedObj.users?.general_sentiment_of_the_day
                            .positive_sentiment,
                      topic.dailySentNeg
                        ? topic.dailySentNeg
                        : returnedObj.users?.general_sentiment_of_the_day
                            .negative_sentiment,
                    ], // <---- insert data here
                    backgroundColor: ["#4481EB", "Red"],
                    borderColor: "white",
                    borderWidth: 1,
                  },
                ],
              }}
              options={{
                maintainAspectRatio: false,
                responsive: true,
                plugins: {
                  legend: {
                    labels: {
                      color: "white",
                      font: {
                        size: "13px",
                      },
                    },
                  },
                  title: {
                    display: true,
                    text: "Daily Content Sentiments",
                    color: "white",
                    padding: {
                      top: 10,
                      bottom: 10,
                    },
                    font: {
                      size: "20px",
                      weight: "normal",
                    },
                  },
                  subtitle: {
                    display: true,
                    text: "Last updated: " + lastUpdated(),
                    color: "white",
                    font: {
                      size: "13px",
                      family: "sans-serif",
                    },
                    position: "bottom",
                    padding: {
                      top: "10",
                    },
                  },
                },
              }}
            />
          </Flex>
        </Flex>
        <Flex
          className="WeeklyCSPie"
          id="CSPie"
          padding="10px"
          flexDir="row"
          height={"460px"}>
          <Flex
            bgColor="#111C44"
            borderRadius="10px"
            padding="20px"
            width={"435px"}>
            <Pie
              data={{
                labels: ["Positve (+1)", "Negative(-1)"],
                datasets: [
                  {
                    label: "CS for weekly",
                    data: [
                      topic.weeklySentPos
                        ? topic.weeklySentPos
                        : returnedObj.users?.general_sentiment_of_the_week
                            .positive_sentiment,
                      topic.weeklySentNeg
                        ? topic.weeklySentNeg
                        : returnedObj.users?.general_sentiment_of_the_week
                            .positive_sentiment,
                    ], // <---- insert data here
                    backgroundColor: ["#4481EB", "Red"],
                    borderColor: "white",
                    borderWidth: 1,
                  },
                ],
              }}
              options={{
                maintainAspectRatio: false,
                responsive: true,
                plugins: {
                  legend: {
                    labels: {
                      color: "white",
                      font: {
                        size: "13px",
                      },
                    },
                  },
                  title: {
                    display: true,
                    text: "Past Week Content Sentiments",
                    color: "white",
                    padding: {
                      top: 10,
                      bottom: 10,
                    },
                    font: {
                      size: "20px",
                      weight: "normal",
                    },
                  },
                  subtitle: {
                    display: true,
                    text: "Last updated: " + lastUpdated(),
                    color: "white",
                    font: {
                      size: "13px",
                      family: "sans-serif",
                    },
                    position: "bottom",
                    padding: {
                      top: "10",
                    },
                  },
                },
              }}
            />
          </Flex>
        </Flex>
      </Flex>
      {/* QUOTE SEARCH BAR -----------------------------------------------------*/}
      <Heading p="10px">Quote Retweets</Heading>
      <Flex
        className="DailyRetweets_Bar"
        id="DailyRetweets_Bar"
        padding="10px"
        flexDir="row">
        <Flex
          bgColor="#111C44"
          borderRadius="10px"
          padding="20px"
          width={"890px"}>
          <Bar
            data={{
              labels: [
                pastSevenDays(7),
                pastSevenDays(6),
                pastSevenDays(5),
                pastSevenDays(4),
                pastSevenDays(3),
                pastSevenDays(2),
                pastSevenDays(1),
              ],
              datasets: [
                {
                  label: topic.name,
                  data:
                    topic.dailyRTcount.length === 0
                      ? returnedObj.users?.daily_retweet_count
                      : topic.dailyRTcount,
                  backgroundColor: "#4481EB",
                  borderColor: "#4481EB",
                  pointBorderColor: "white",
                  pointBackgroundColor: "white",
                  borderRadius: 120,
                  borderSkipped: false,
                  maxBarThickness: 30,
                },
              ],
            }}
            height={350}
            width={"890px"}
            options={{
              maintainAspectRatio: false,
              responsive: true,
              plugins: {
                legend: {
                  labels: {
                    color: "white",
                    font: {
                      size: "13px",
                    },
                  },
                },
                title: {
                  display: true,
                  text: "Daily Retweets - Past 7 days",
                  color: "white",
                  padding: {
                    top: 10,
                    bottom: 10,
                  },
                  font: {
                    size: "20px",
                    weight: "normal",
                  },
                },
                subtitle: {
                  display: true,
                  text: "Last updated: " + lastUpdated(),
                  color: "white",
                  font: {
                    size: "13px",
                    family: "sans-serif",
                  },
                  position: "bottom",
                },
              },
              scales: {
                y: {
                  grid: {
                    color: "#5F6A8A",
                  },
                  ticks: {
                    color: "white",
                  },
                },
                x: {
                  grid: {
                    color: "#5F6A8A",
                  },
                  ticks: {
                    color: "white",
                  },
                },
              },
            }}
          />
        </Flex>
      </Flex>
    </Flex>
  );
};
export default PremiumTrends;

// Return real time
