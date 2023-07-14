import { useState } from "react";
import { Modal } from "react-bootstrap";

export const OrientacaoModal = ({
  orientacao,
  show,
  onShow,
  setChangeDiscente,
}) => {
  const [novoDiscente, setNovoDiscente] = useState();

  const handleClose = () => {
    onShow(false);
    setChangeDiscente(false);
  };

  const handleChange = (e) => {
    const { value } = e.target;

    setNovoDiscente(value);
  };

  return (
    <Modal
      show={show}
      onHide={handleClose}
      centered
      size="lg"
      aria-labelledby="contained-modal-title-vcenter"
    >
      <Modal.Header closeButton>
        <Modal.Title id="contained-modal-title-vcenter">
          Mudar discente
        </Modal.Title>
      </Modal.Header>
      <Modal.Body>
        <h4>{orientacao && orientacao.titulo}</h4>
        <p>Atual: {orientacao && orientacao.discente}</p>
        <br />
        <input
          className="form-control"
          placeholder="Insira o nome do aluno"
          onChange={handleChange}
        />
        <button className="btn btn-primary mt-2">Mudar discente</button>
      </Modal.Body>
    </Modal>
  );
};
