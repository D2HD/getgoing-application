import React from "react";
import { Line } from "react-chartjs-2";
import {
  Chart,
  ArcElement,
  Chart as ChartJS,
  CategoryScale,
  LinearScale,
  PointElement,
  LineElement,
  Title,
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
  Title,
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

const WeeklyCS_Line = (props) => {
  let { height, width } = props;
  return (
    <Flex className="WeeklyCS_Line" id="WeeklyCS_Line" padding="10px">
      <Flex bgColor="#111C44" borderRadius="10px" padding="10px">
        <Line
          data={{
            labels: [pastWeek(22), pastWeek(15), pastWeek(8), pastWeek(1)],
            datasets: [
              {
                label: "Positive (+1)",
                data: [5000, 2000, 300, 200],
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
                data: [100, 1400, 5000, 10000],
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
          width={width}
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
  );
};

export default WeeklyCS_Line;
