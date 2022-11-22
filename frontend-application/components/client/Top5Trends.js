import { React, useState, useEffect } from "react";
import {
  Flex,
  Spacer,
  Button,
  Heading,
  Stack,
  Text,
  Spinner,
} from "@chakra-ui/react";
import { isResSent } from "next/dist/shared/lib/utils";
import UserService from "../../freeServices/FreeUser";
import { Pie, Line, Bar } from "react-chartjs-2";
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
    date.getDate() +
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

let dateObj = new Date();
let month = dateObj.getUTCMonth() + 1;
let day = dateObj.getUTCDate() - 1;
let year = dateObj.getUTCFullYear();
const Top5Trends = (props) => {
  const [state, setState] = useState(0);
  useEffect(() => {
    UserService.currentTop5HashtagDtoList().then((Response) => {
      setState({ users: Response.data });
    });
  }, []);
  const [topic, setTopic] = useState({
    name: "",
    dailyCount: [],
    weeklyCount: [],
  });
  return (
    <Flex
      flexBasis="75%"
      className="right"
      flexDir="column"
      alignItems="center"
      bgColor="#fff">
      <Flex>
        <Flex
          className="Top5Trends"
          id="Top5Trends"
          padding="10px"
          flexDir="row"
          height="400px"
          align="right"
          flexBasis="50%">
          <Flex
            borderRadius="10px"
            flexDir="column"
            bgColor="#111C44"
            padding="10px">
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
              height="400px"
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
                <Text font>Last updated: {lastUpdated()}</Text>
              </Flex>
            </Flex>
          </Flex>
        </Flex>
        <Flex
          className="DailySearches"
          id="DailySearches"
          padding="10px"
          flexDir="row"
          height="400px">
          <Flex bgColor="#111C44" borderRadius="10px" padding="20px">
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
                    label: topic.name,
                    data: topic.dailyCount,
                    backgroundColor: "#4481EB",
                    borderColor: "#4481EB",
                    borderWidth: 3,
                    pointBorderColor: "white",
                    pointBackgroundColor: "white",
                  },
                ],
              }}
              height="400"
              width="470px"
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
      </Flex>
      <Flex className="WeeklySearch" padding="10px">
        <Flex bgColor="#111C44" borderRadius="10px" padding="10px">
          <Line
            data={{
              labels: ["1", "2", "3", "4"],
              datasets: [
                {
                  label: topic.name,
                  data: topic.weeklyCount,
                  backgroundColor: "#4481EB",
                  borderColor: "#4481EB",
                  borderWidth: 3,
                  pointBorderColor: "white",
                  pointBackgroundColor: "white",
                },
              ],
            }}
            height="220px"
            width="840"
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
                  text: "Weekly Search",
                  color: "white",
                  padding: {
                    top: 10,
                    bottom: 10,
                  },
                  font: {
                    size: "20px",
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
  );
};
export default Top5Trends;

// Return real time
