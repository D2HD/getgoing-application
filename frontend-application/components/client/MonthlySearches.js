import React from "react";
import { Pie, Line } from "react-chartjs-2";
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
  Ticks,
} from "chart.js";
import { Flex, Spacer } from "@chakra-ui/react";

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

const MonthlySearches = (props) => {
  let { width, height } = props;
  return (
    <Flex className="MonthlySearch" id="MonthlySearch" padding="10px">
      <Flex bgColor="#111C44" borderRadius="10px" padding="10px">
        <Line
          data={{
            labels: ["APR", "MAY", "JUN", "JUL", "AUG", "SEP"],
            datasets: [
              {
                label: "#hotGirls",
                data: [120, 190, 30, 500, 200, 310],
                backgroundColor: "#4481EB",
                borderColor: "#4481EB",
                borderWidth: 3,
                pointBorderColor: "white",
                pointBackgroundColor: "white",
              },
            ],
          }}
          height={height}
          // height="250px"
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
                text: "Monthly Search",
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
  );
};

export default MonthlySearches;
