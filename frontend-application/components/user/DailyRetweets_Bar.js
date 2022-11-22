import React from "react";
import { Bar } from "react-chartjs-2";
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
} from "chart.js";
import { Flex } from "@chakra-ui/react";

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
    " " +
    date.getHours() +
    ":" +
    (date.getMinutes() < 10 ? "0" : "") +
    date.getMinutes();
  return update;
}

const DailyRetweets_Bar = (props) => {
  let { width, height } = props;
  return (
    <Flex
      className="DailyRetweets_Bar"
      id="DailyRetweets_Bar"
      padding="10px"
      flexDir="row">
      <Flex bgColor="#111C44" borderRadius="10px" padding="20px">
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
                label: "#FTT",
                data: [156, 162, 347, 320, 400, 500, 1000],
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
          height={height}
          width={width}
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
  );
};

export default DailyRetweets_Bar;
