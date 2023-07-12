import {
  Chart as ChartJS,
  CategoryScale,
  LinearScale,
  BarElement,
  Title,
  Tooltip,
  Legend,
} from "chart.js";
import { Bar } from "react-chartjs-2";

ChartJS.register(
  CategoryScale,
  LinearScale,
  BarElement,
  Title,
  Tooltip,
  Legend
);

// const dataExample = {
//   labels: [2023, 2022, 2021, 2020, 2019],
//   datasets: [
//     {
//       label: "A1",
//       data: [9, 33, 30, 26, 17],
//       backgroundColor: "#4dc9f6",
//     },
//     {
//       label: "A2",
//       data: [0, 8, 13, 17, 6],
//       backgroundColor: "#f67019",
//     },
//     {
//       label: "A3",
//       data: [12, 26, 24, 46, 20],
//       backgroundColor: "#537bc4",
//     },
//     {
//       label: "A4",
//       data: [0, 30, 49, 25, 55],
//       backgroundColor: "#acc236",
//     },
//   ],
// };

const getDataFromStats = (stats, tipo) => {
  if (!stats) return;

  const labels = stats.map((stat) => {
    return stat.ano;
  });

  const statsData = stats.map((stat) => {
    return stat.stats;
  });

  let datasets;

  if (tipo === "periodo") {
    datasets = [
      {
        label: "A1",
        data: [],
        backgroundColor: "#4dc9f6",
      },
      {
        label: "A2",
        data: [],
        backgroundColor: "#f67019",
      },
      {
        label: "A3",
        data: [],
        backgroundColor: "#537bc4",
      },
      {
        label: "A4",
        data: [],
        backgroundColor: "#acc236",
      },
    ];
  } else {
    datasets = [
      {
        label: "B1",
        data: [],
        backgroundColor: "#4dc9f6",
      },
      {
        label: "B2",
        data: [],
        backgroundColor: "#f67019",
      },
      {
        label: "B3",
        data: [],
        backgroundColor: "#537bc4",
      },
      {
        label: "B4",
        data: [],
        backgroundColor: "#acc236",
      },
    ];
  }

  statsData.map((stat) =>
    stat.map((tipo) =>
      // eslint-disable-next-line array-callback-return
      datasets.map((data) => {
        if (data.label === tipo.type) {
          data.data.push(tipo.qtd);
        }
      })
    )
  );

  return {
    labels,
    datasets,
  };
};

const config = {
  responsive: true,
  maintainAspectRatio: false,
  scales: {
    x: {
      stacked: true,
    },

    y: {
      stacked: true,
    },
  },
};

export const DocenteChart = ({ data, isLoading, tipo }) => {
  return (
    <div className="card-body p-5">
      {!data && (
        <div className="d-flex  justify-content-center">
          <div className="spinner-border" role="status">
            <span className="sr-only">Loading...</span>
          </div>
        </div>
      )}

      {data && (
        <div className="chart">
          <Bar options={config} data={getDataFromStats(data, tipo)} />
        </div>
      )}
    </div>
  );
};
