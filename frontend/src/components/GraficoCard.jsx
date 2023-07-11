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

const config = {
  responsive: true,
  maintainAspectRatio: false,
  scales: {
    x: 
      {
        stacked: true,
      },
    
    y: 
      {
        stacked: true,
      },
    
  },
};

export const GraficoCard = ({ data }) => {
  return (
    <div className="card card-gray">
      <div className="card-header">
        <h3 className="card-title">Produção vs Qualis</h3>

        <div className="card-tools">
          <button
            type="button"
            className="btn btn-tool"
            data-card-widget="collapse"
          >
            <i className="fas fa-minus"></i>
          </button>
          <button type="button" className="btn btn-tool" data-card-widget="remove">
            <i className="fas fa-times"></i>
          </button>
        </div>
      </div>
      <div className="card-body">
        <div className="chart">
          <Bar options={config} data={data} />
        </div>
      </div>
    </div>
  );
};
