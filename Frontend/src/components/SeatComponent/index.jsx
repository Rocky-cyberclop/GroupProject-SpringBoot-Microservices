import { useState } from "react";
import "./seatComponent.style.scss";
import { bookingSeat } from "../../service/SeatServices";
function SeatComponent(props) {
  const [status, setStatus] = useState(false);
  let seat;
  //   switch (props.level) {
  //     case "BUSINESS":
  //       seat = (
  //         <img
  //           src="https://www.vietjetair.com/static/media/favorite-seat_red.d20dbda6.svg"
  //           alt=""
  //         />
  //       );
  //       break;
  //     case "DELUXE":
  //       seat = (
  //         <img
  //           src="https://www.vietjetair.com/static/media/favorite-seat_yellow.0c9d67ed.svg"
  //           alt=""
  //         />
  //       );
  //       break;
  //     case "CLASSIC":
  //       seat = (
  //         <img
  //           src="https://www.vietjetair.com/static/media/favorite-seat_green.803a88c3.svg"
  //           alt=""
  //         />
  //       );
  //     default:
  //       seat = (
  //         <img
  //           src="https://www.vietjetair.com/static/media/favorite-seat_green.803a88c3.svg"
  //           alt=""
  //         />
  //       );
  //   }

  switch (props.status) {
    case "BOOKING":
      seat = (
        <img
          src="https://www.vietjetair.com/static/media/favorite-seat_blue.93440846.svg"
          alt=""
        />
      );
      break;
    case "AVAILABLE":
      if (props.level) {
        switch (props.level) {
          case "BUSINESS":
            seat = (
              <img
                src="https://www.vietjetair.com/static/media/favorite-seat_red.d20dbda6.svg"
                alt=""
              />
            );
            break;
          case "DELUXE":
            seat = (
              <img
                src="https://www.vietjetair.com/static/media/favorite-seat_yellow.0c9d67ed.svg"
                alt=""
              />
            );
            break;
          case "CLASSIC":
            seat = (
              <img
                src="https://www.vietjetair.com/static/media/favorite-seat_green.803a88c3.svg"
                alt=""
              />
            );
          default:
            seat = (
              <img
                src="https://www.vietjetair.com/static/media/favorite-seat_green.803a88c3.svg"
                alt=""
              />
            );
        }
      }
      break;
    default:
      seat = (
        <img
          src="https://www.vietjetair.com/static/media/favorite-seat_green.803a88c3.svg"
          alt=""
        />
      );
  }

  const SeatBooked = function () {
    bookingSeat(props.id, props.idSchedule);
  };
  return (
    <div>
      <button className="seat" onClick={SeatBooked}>
        {seat}
      </button>
    </div>
  );
}

export default SeatComponent;
