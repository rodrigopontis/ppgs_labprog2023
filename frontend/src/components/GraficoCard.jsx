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

const getDataFromStats = (stats) => {
  if (!stats) return;

  const labels = stats.map((stat) => {
    return stat.ano;
  });

  const statsData = stats.map((stat) => {
    return stat.stats;
  });

  let datasets = [
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

export const GraficoCard = ({ data, isLoading }) => {
  return (
    <div className="card card-gray">
      <div className="card-header">
        <h3 className="card-title">
          Produção vs Qualis
          {isLoading && (
            <div className="ml-3 spinner-grow spinner-grow-sm" role="status">
              <span className="sr-only">Loading...</span>
            </div>
          )}
        </h3>

        <div className="card-tools">
          <button
            type="button"
            className="btn btn-tool"
            data-card-widget="collapse"
          >
            <i className="fas fa-minus"></i>
          </button>
          <button
            type="button"
            className="btn btn-tool"
            data-card-widget="remove"
          >
            <i className="fas fa-times"></i>
          </button>
        </div>
      </div>
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
            <Bar options={config} data={getDataFromStats(data)} />
          </div>
        )}
      </div>
    </div>
  );
};
