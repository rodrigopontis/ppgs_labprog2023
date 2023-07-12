import { useEffect, useState } from "react";
import { Link } from "react-router-dom";

const formartData = (data) => {
  if (!data) return;

  console.log(data);

  // estou codando as 12:19 e com fome
  // perdão pela falta de criatividade
  const docentes = data.map((d) => {
    return d.docente;
  });

  const docId = data.map((d) => {
    return d.docenteId;
  });

  const stats = data.map((d) => {
    return d.stats;
  });

  const docStats = stats.map((stat) => {
    return stat.map((s) => {
      return s.qtd;
    });
  });

  const res = docentes.map((doc, i) => {
    return { id: docId[i], arr: [docentes[i], ...docStats[i]] };
  });

  return res;
};

export const DocenteTable = ({ data, isLoading = false }) => {
  const [tableData, setTableData] = useState(null);

  useEffect(() => {
    setTableData(formartData(data));
  }, [data]);

  return (
    <div className="card">
      <div className="card-header">
        <h3 className="card-title">
          Docentes
          {isLoading && (
            <div className="ml-3 spinner-grow spinner-grow-sm" role="status">
              <span className="sr-only">Loading...</span>
            </div>
          )}
        </h3>
      </div>
      <div className="card-body">
        {!data && (
          <div className="d-flex  justify-content-center">
            <div className="spinner-border" role="status">
              <span className="sr-only">Loading...</span>
            </div>
          </div>
        )}

        {data && (
          <table id="example1" className="table table-bordered table-striped">
            <thead>
              <tr>
                <th>Docente</th>
                <th>A1</th>
                <th>A2</th>
                <th>A3</th>
                <th>A4</th>
                <th>B1</th>
                <th>B2</th>
                <th>B3</th>
                <th>B4</th>
                <th>Detalhar</th>
              </tr>
            </thead>
            <tbody>
              {tableData &&
                tableData.map((data, key) => (
                  <tr key={key}>
                    {data.arr.map((res, key) => (
                      <td key={key}>{res}</td>
                    ))}
                    <td>
                      <Link to={`/docente/${data.id}`}>Mais</Link>
                    </td>
                  </tr>
                ))}
            </tbody>
            <tfoot>
              <tr>
                <th>Docente</th>
                <th>A1</th>
                <th>A2</th>
                <th>A3</th>
                <th>A4</th>
                <th>B1</th>
                <th>B2</th>
                <th>B3</th>
                <th>B4</th>
                <th>Detalhar</th>
              </tr>
            </tfoot>
          </table>
        )}
      </div>
    </div>
  );
};
