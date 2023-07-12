export const CardCapes = ({ title, value, icon, color, isLoading }) => {
  return (
    <div className="col-md-3 col-sm-6 col-12">
      <div className="info-box">
        <span className={`info-box-icon bg-${color}`}>
          <i className={`far fa-${icon}`}></i>
        </span>

        <div className="info-box-content">
          <span className="info-box-text">{title}</span>
          <span className="info-box-number">
            {value}
            {(!value || isLoading) && (
              <div className="ml-3 spinner-grow spinner-grow-sm" role="status">
                <span className="sr-only">Loading...</span>
              </div>
            )}
          </span>
        </div>
      </div>
    </div>
  );
};
