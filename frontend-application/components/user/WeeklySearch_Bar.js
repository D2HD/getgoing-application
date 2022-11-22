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

// Labels for past 4 weeks days date (ie today - last 7 days)
function pastWeek(thisWeek, date = new Date()) {
  date.setDate(date.getDate() - thisWeek);
  var firstDate = date.getDate() + "/" + date.getMonth();

  date.setDate(date.getDate() - 6);
  var secondDate = date.getDate() + "/" + date.getMonth();
  return secondDate + " - " + firstDate;
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

const WeeklySearch_Bar = (props) => {
  let { height, width } = props;
  return (
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
                label: "#FTT",
                data: [1650, 1000, 2120, 4170],
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

export default WeeklySearch_Bar;
