import { Modal } from "react-bootstrap";

export const Confirm = ({ show, onHide }) => {
  return (
    <Modal
      show={show}
      onHide={onHide}
      centered
      size="lg"
      aria-labelledby="contained-modal-title-vcenter"
    >
      <Modal.Header closeButton>
        <Modal.Title id="contained-modal-title-vcenter">
          Tem certeza?
        </Modal.Title>
      </Modal.Header>
      <Modal.Body>
        <button className="btn btn-error mt-2">Mudar discente</button>
      </Modal.Body>
    </Modal>
  );
};
