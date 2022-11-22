import React from "react";
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
import { Flex, Spacer } from "@chakra-ui/react";

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

const DailySearches = (props) => {
  let { width, height, topic } = props;
  return (
    <Flex
      className="DailySearches"
      id="DailySearches"
      padding="10px"
      flexDir="row"
      height={height}>
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
                label: "#hotGirls",
                data: [12, 5, 47, 6, 3, 8, 24],
                backgroundColor: "#4481EB",
                borderColor: "#4481EB",
                borderWidth: 3,
                pointBorderColor: "white",
                pointBackgroundColor: "white",
              },
            ],
          }}
          height={height}
          width={width} //420px
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
  );
};

export default DailySearches;
