import { useCallback, useEffect, useState } from "react";
import {
  CardCapes,
  DocenteTable,
  GraficoCard,
  PageComponent,
} from "../components";
import { useNavigate } from "react-router-dom";
import ProgramaService from "../service/ProgramaService";

const ppgValues = [
  { name: "PPGCC", code: 15 },
  { name: "DCCMAPI", code: 0 },
];

const service = new ProgramaService();

export const Programa = () => {
  const [formData, setFormData] = useState({
    ppg: ppgValues[0].name,
    anoIni: 2019,
    anoFim: 2023,
  });
  const [chartData, setChartData] = useState(null);
  const [tableData, setTableData] = useState(null);
  const [isLoadingProg, setIsLoadingProg] = useState(true);
  const [isLoadingDoc, setIsLoadingDoc] = useState(true);
  const [totalProd, setTotalProd] = useState(null);

  const navigate = useNavigate();

  const filterHandler = (e) => {
    const { name, value } = e.target;

    // tentando impedir o user de fazer requisições erradas
    if (name === "anoIni" && value > formData.anoFim)
      alert("Ano inicial maior que ano final.");

    if (name === "anoFim" && value < formData.anoIni && value > 1500)
      alert("Ano final menor que ano inicial.");

    setFormData({ ...formData, [name]: value });
  };

  const filtrarPpg = (e) => {
    e.preventDefault();
    const idProg = formData.ppg === ppgValues[0].name ? 15 : 0;

    getDataFromApi(idProg, formData.anoIni, formData.anoFim);
  };

  const getStatsFromApi = useCallback(
    (idProg, anoIni, anoFim) => {
      setIsLoadingProg(true);

      service
        .getStatsByYear(idProg, anoIni, anoFim)
        .then(({ data }) => {
          setChartData(data);

          const qtd = data.reduce((total, stat) => total + stat.total, 0);
          setTotalProd(qtd);

          setIsLoadingProg(false);
        })
        .catch((er) => {
          console.error(er);
          if ([400])
            alert("Não foi possível atualizar valores. Verifique o filtro.");
          setIsLoadingProg(false);
        });
    },
    [setChartData]
  );

  const getDocenteStatsFromApi = useCallback(
    (idProg, anoIni, anoFim) => {
      setIsLoadingDoc(true);

      service
        .getDocentesStatsByYear(idProg, anoIni, anoFim)
        .then(({ data }) => {
          setTableData(data);
          setIsLoadingDoc(false);
        })
        .catch((er) => {
          console.error(er);
          alert("Não foi possível atualizar valores. Verifique o filtro.");
          setIsLoadingDoc(false);
        });
    },
    [setTableData]
  );

  useEffect(() => {
    document.body.classList = "hold-transition layout-top-nav";

    const userToken = localStorage.getItem("token");

    if (!userToken) navigate("/login");
  }, [navigate]);

  // REQUISIÇÕES

  const getDataFromApi = useCallback(
    (idProg = 15, anoIni = 2019, anoFim = 2023) => {
      getStatsFromApi(idProg, anoIni, anoFim);
      getDocenteStatsFromApi(idProg, anoIni, anoFim);
    },
    [getStatsFromApi, getDocenteStatsFromApi]
  );

  useEffect(getDataFromApi, [getDataFromApi]);

  return (
    <PageComponent>
      <div className="content-wrapper">
        <div className="content-header">
          <div className="container">
            <div className="row mb-2">
              <div className="col-sm-6">
                <h1 className="m-0"> Programa </h1>
              </div>
            </div>
          </div>
        </div>

        <div className="content">
          <div className="container">
            <div className="container-fluid">
              <h5 className="mb-2">Filtros</h5>

              <form action="#" onSubmit={filtrarPpg}>
                <div className="row">
                  <div className="col-md-10">
                    <div className="row">
                      <div className="col-3">
                        <div className="form-group">
                          <label>Programa:</label>
                          <select
                            className="form-control"
                            style={{ width: "100%" }}
                            defaultValue={formData.ppg}
                            name="ppg"
                            onChange={filterHandler}
                          >
                            <option value="PPGCC">PPGCC</option>
                            <option value="DCCMAPI">DCCMAPI</option>
                          </select>
                        </div>
                      </div>
                      <div className="col-3">
                        <div className="form-group">
                          <label>Ano inicial:</label>
                          <input
                            className="form-control"
                            type="number"
                            name="anoIni"
                            defaultValue={formData.anoIni}
                            onChange={filterHandler}
                          />
                        </div>
                      </div>
                      <div className="col-3">
                        <div className="form-group">
                          <label>Ano Final:</label>
                          <input
                            className="form-control"
                            type="number"
                            name="anoFim"
                            defaultValue={formData.anoFim}
                            onChange={filterHandler}
                          />
                        </div>
                      </div>
                      <div className="col-3">
                        <br />
                        <button type="submit" className="btn btn-primary mt-2">
                          Filtrar
                        </button>
                      </div>
                    </div>
                  </div>
                </div>
              </form>

              <h5 className="mb-2">Indicadores Capes</h5>

              <div className="row">
                <CardCapes
                  title="Total Produção"
                  value={totalProd}
                  isLoading={isLoadingProg}
                  color="gray"
                  icon="copy"
                />
                <CardCapes
                  title="I Geral"
                  value="17,43"
                  color="info"
                  icon="envelope"
                />
                <CardCapes
                  title="I Restrito"
                  value="16,45"
                  color="success"
                  icon="flag"
                />
                <CardCapes
                  title="I Não Restrito"
                  value="0,99"
                  color="warning"
                  icon="copy"
                />
              </div>

              <GraficoCard data={chartData} isLoading={isLoadingProg} />

              <DocenteTable data={tableData} isLoading={isLoadingDoc} />
            </div>
          </div>
        </div>
      </div>
    </PageComponent>
  );
};
