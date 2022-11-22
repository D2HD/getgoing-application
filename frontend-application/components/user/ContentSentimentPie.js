import React from "react";
import { Pie } from "react-chartjs-2";
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

const CSPie = (props) => {
  let { width, height } = props;
  return (
    <Flex
      className="CSPie"
      id="CSPie"
      padding="10px"
      flexDir="row"
      height={height}>
      <Flex bgColor="#111C44" borderRadius="10px" padding="20px" width={width}>
        <Pie
          data={{
            labels: ["Positve (+1)", "Negative(-1)"],
            datasets: [
              {
                label: "CS for weekly",
                data: [200, 10000], // <---- insert data here
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
  );
};

export default CSPie;
