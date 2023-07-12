import { useCallback, useEffect, useState } from "react";
import {
  PageComponent,
  CardCapes,
  DocentePageTable,
  DocenteChart,
} from "../components";
import { useNavigate, useParams } from "react-router-dom";
import DocenteService from "../service/DocenteService";

const service = new DocenteService();

export const Docente = () => {
  const navigate = useNavigate();
  const { idDocente } = useParams();

  const [oriData, setOriData] = useState(null);
  const [prodData, setProdData] = useState(null);
  const [tecData, setTecData] = useState(null);
  const [docStatsData, setDocStatsData] = useState(null);
  const [name, setName] = useState(null);

  const [formData, setFormData] = useState({
    anoIni: 2019,
    anoFim: 2023,
  });
  const [isLoadingOri, setIsLoadingOri] = useState(true);
  const [isLoadingProd, setIsLoadingProd] = useState(true);
  const [isLoadingTec, setIsLoadingTec] = useState(true);
  const [isLoadingStats, setIsLoadingStats] = useState(true);
  const [isLoadingName, setIsLoadingName] = useState(true);
  const [total, setTotal] = useState(0);

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

    getDataFromApi(idDocente, formData.anoIni, formData.anoFim);
  };

  const getDataFromApi = useCallback(
    (anoIni = 2019, anoFim = 2023) => {
      setIsLoadingProd(true);
      setIsLoadingTec(true);
      setIsLoadingOri(true);
      setIsLoadingStats(true);
      setIsLoadingName(true);

      service.getName(idDocente).then(({ data }) => {
        setName(data.nome);
        setIsLoadingName(false);
      });

      service
        .getAllDocenteStats(idDocente, anoIni, anoFim)
        .then(({ data }) => {
          setDocStatsData(data);

          setIsLoadingStats(false);
        })
        .catch((err) => {
          console.error(err);
        });

      service
        .getDocenteProds(idDocente, anoIni, anoFim)
        .then(({ data }) => {
          setProdData(data);
          setIsLoadingProd(false);
        })
        .catch((er) => {
          console.log(er);
          alert("Erro na req");
          setIsLoadingOri(false);
        });

      service
        .getOrientacoesDocente(idDocente, anoIni, anoFim)
        .then(({ data }) => {
          setOriData(data);
          setIsLoadingOri(false);
        })
        .catch((er) => {
          console.log(er);
          alert("Erro na req");
          setIsLoadingOri(false);
        });

      service
        .getDocenteTecnica(idDocente, anoIni, anoFim)
        .then(({ data }) => {
          setTecData(data);
          setIsLoadingTec(false);
        })
        .catch((er) => {
          console.log(er);
          alert("Erro na req");
          setIsLoadingOri(false);
        });
    },
    [idDocente]
  );

  useEffect(() => {
    document.body.classList = "hold-transition layout-top-nav";

    const userToken = localStorage.getItem("token");

    if (!userToken) navigate("/login");
  }, [navigate]);

  useEffect(getDataFromApi, [getDataFromApi]);

  return (
    <PageComponent>
      <div className="content-wrapper">
        <div className="content-header">
          <div className="container">
            <div className="row mb-2">
              <div className="col-sm-6">
                {isLoadingName && (
                  <div className="spinner-border" role="status">
                    <span className="sr-only">Loading...</span>
                  </div>
                )}
                {!isLoadingName && (
                  <h1 className="m-0"> {`Docente: ${name} `}</h1>
                )}
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
                          <label>Ano inicial:</label>
                          <input
                            className="form-control"
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
                  value={total}
                  isLoading={isLoadingProd}
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

              <div className="card card-gray">
                <div className="card-header">
                  <h3 className="card-title">
                    Produção em Periódicos vs Qualis
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
                <div className="card-body">
                  <div className="chart">
                    <DocenteChart
                      data={docStatsData}
                      isLoading={isLoadingStats}
                      tipo="periodo"
                    />
                  </div>
                </div>
              </div>

              <div className="card card-gray">
                <div className="card-header">
                  <h3 className="card-title">
                    Produção em Congressos vs Qualis
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
                <div className="card-body">
                  <div className="chart">
                    <DocenteChart
                      data={docStatsData}
                      isLoading={isLoadingStats}
                      tipo="congresso"
                    />
                  </div>
                </div>
              </div>

              <div className="card">
                <div className="card-header">
                  <h3 className="card-title">Orientações</h3>
                </div>

                {!isLoadingOri && (
                  <div className="card-body">
                    <DocentePageTable tipo="ori" data={oriData} />
                  </div>
                )}

                {isLoadingOri && (
                  <div className="d-flex p-5 justify-content-center">
                    <div className="spinner-border" role="status">
                      <span className="sr-only">Loading...</span>
                    </div>
                  </div>
                )}
              </div>

              <div className="card">
                <div className="card-header">
                  <h3 className="card-title">Artigos</h3>
                </div>

                {!isLoadingProd && (
                  <div className="card-body">
                    <DocentePageTable tipo="prod" data={prodData} />
                  </div>
                )}

                {isLoadingProd && (
                  <div className="d-flex p-5 justify-content-center">
                    <div className="spinner-border" role="status">
                      <span className="sr-only">Loading...</span>
                    </div>
                  </div>
                )}
              </div>

              <div className="card">
                <div className="card-header">
                  <h3 className="card-title">Técnicas</h3>
                </div>

                {!isLoadingTec && (
                  <div className="card-body">
                    <DocentePageTable tipo="tec" data={tecData} />
                  </div>
                )}

                {isLoadingTec && (
                  <div className="d-flex p-5 justify-content-center">
                    <div className="spinner-border" role="status">
                      <span className="sr-only">Loading...</span>
                    </div>
                  </div>
                )}
              </div>
            </div>
          </div>
        </div>
      </div>
    </PageComponent>
  );
};
