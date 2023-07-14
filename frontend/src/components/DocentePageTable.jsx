/* eslint-disable array-callback-return */
import { useState } from "react";
import { OrientacaoModal } from "./OrientacaoModal";

export const DocentePageTable = ({ title, data, tipo, isLoading }) => {
  const [page, setPage] = useState(0);
  const [limit, setLimit] = useState(5);
  const [openModal, setOpenModal] = useState(false);
  const [changeDiscente, setChangeDiscente] = useState(null);

  const handlePreviusPage = () => {
    if (!data) return;

    if (page !== 0) {
      setPage(page - 5);
      setLimit(limit - 5);
    }
  };

  const handleNextPage = () => {
    if (!data) return;

    if (limit < data.length) {
      setPage(page + 5);
      setLimit(limit + 5);
    }
  };

  return (
    <>
      {changeDiscente && openModal && (
        <OrientacaoModal
          orientacao={changeDiscente}
          show={openModal}
          onShow={setOpenModal}
          setChangeDiscente={setChangeDiscente}
        />
      )}

      <div className="card">
        <div className="card-header">
          <h3 className="card-title">{title}</h3>
        </div>

        {!isLoading && (
          <div className="card-body">
            {tipo === "ori" && (
              <table
                id="example1"
                className="table table-bordered table-striped"
              >
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
                    data.map((d, key) => {
                      if (key >= page && key < limit) {
                        return (
                          <tr key={key}>
                            <td
                              style={{ cursor: "pointer" }}
                              onClick={() => {
                                setChangeDiscente(d);
                                setOpenModal(true);
                              }}
                            >
                              {d.discente}
                            </td>
                            <td>{d.titulo}</td>
                            <td>{d.tipo}</td>
                            <td>{d.ano}</td>
                          </tr>
                        );
                      }
                    })}
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
            )}

            {tipo === "tec" && (
              <table
                id="example3"
                className="table table-bordered table-striped"
              >
                <thead>
                  <tr>
                    <th>Título</th>
                    <th>Tipo</th>
                    <th>Ano</th>
                  </tr>
                </thead>

                <tbody>
                  {data &&
                    data.map((d, key) => {
                      if (key >= page && key < limit) {
                        return (
                          <tr key={key}>
                            <td>{d.titulo}</td>
                            <td>{d.tipo}</td>
                            <td>{d.ano}</td>
                          </tr>
                        );
                      }
                    })}
                </tbody>

                <tfoot>
                  <tr>
                    <th>Título</th>
                    <th>Tipo</th>
                    <th>Ano</th>
                  </tr>
                </tfoot>
              </table>
            )}

            {tipo === "prod" && (
              <table
                id="example2"
                className="table table-bordered table-striped"
              >
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
                    data.map((d, key) => {
                      if (key >= page && key < limit) {
                        return (
                          <tr key={key}>
                            <td>{d.titulo}</td>
                            <td>{d.nomeLocal}</td>
                            <td>{d.tipo}</td>
                            <td>{d.qualis}</td>
                            <td>{d.ano}</td>
                          </tr>
                        );
                      }
                    })}
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
            )}
          </div>
        )}

        {isLoading && (
          <div className="d-flex p-5 justify-content-center">
            <div className="spinner-border" role="status">
              <span className="sr-only">Loading...</span>
            </div>
          </div>
        )}

        <div className="card-footer d-flex justify-content-center">
          <ul className="pagination">
            <li className="page-item">
              <button
                className="page-link text-dark"
                onClick={handlePreviusPage}
              >
                Anterior
              </button>
            </li>
            <li className="page-item">
              <span className="page-link text-dark">{page / 5}</span>
            </li>
            <li className="page-item">
              <button className="page-link text-dark" onClick={handleNextPage}>
                Próximo
              </button>
            </li>
          </ul>
        </div>
      </div>
    </>
  );
};
