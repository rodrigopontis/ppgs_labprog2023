export const DocentePageTable = ({ data, tipo }) => {
  if (tipo === "tec") {
    return (
      <table id="example3" className="table table-bordered table-striped">
        <thead>
          <tr>
            <th>Título</th>
            <th>Tipo</th>
            <th>Ano</th>
          </tr>
        </thead>

        <tbody>
          {data &&
            data.map((d, key) => (
              <tr key={key}>
                <td>{d.titulo}</td>
                <td>{d.tipo}</td>
                <td>{d.ano}</td>
              </tr>
            ))}
        </tbody>

        <tfoot>
          <tr>
            <th>Título</th>
            <th>Tipo</th>
            <th>Ano</th>
          </tr>
        </tfoot>
      </table>
    );
  }

  if (tipo === "prod") {
    return (
      <table id="example2" className="table table-bordered table-striped">
        <thead>
          <tr>
            <th>Título</th>
            <th>Local</th>
            <th>Tipo</th>
            <th>Qualis</th>
            <th>Ano</th>
          </tr>
        </thead>

        <tbody>
          {data &&
            data.map((d, key) => (
              <tr key={key}>
                <td>{d.titulo}</td>
                <td>{d.nomeLocal}</td>
                <td>{d.tipo}</td>
                <td>{d.qualis}</td>
                <td>{d.ano}</td>
              </tr>
            ))}
        </tbody>

        <tfoot>
          <tr>
            <th>Título</th>
            <th>Local</th>
            <th>Tipo</th>
            <th>Qualis</th>
            <th>Ano</th>
          </tr>
        </tfoot>
      </table>
    );
  }

  if (tipo === "ori") {
    return (
      <table id="example1" className="table table-bordered table-striped">
        <thead>
          <tr>
            <th>Discente</th>
            <th>Título</th>
            <th>Tipo</th>
            <th>Ano</th>
          </tr>
        </thead>
        <tbody>
          {data &&
            data.map((d, key) => (
              <tr key={key}>
                <td>{d.discente}</td>
                <td>{d.titulo}</td>
                <td>{d.tipo}</td>
                <td>{d.ano}</td>
              </tr>
            ))}
        </tbody>
        <tfoot>
          <tr>
            <th>Discente</th>
            <th>Título</th>
            <th>Tipo</th>
            <th>Ano</th>
          </tr>
        </tfoot>
      </table>
    );
  }
};
