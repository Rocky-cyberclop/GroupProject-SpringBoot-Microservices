import "./Seat.styles.scss";
import React, { useState, useEffect } from "react";
import SeatComponent from "../../components/SeatComponent";
import { getListSeatBySchedule } from "../../service/SeatServices";
function SeatBooking() {
  const [listSeat, setListSeat] = useState([]);
  const idSchedule = "65a4b49b9bab87676502e19f";

  useEffect(() => {
    async function fetchData() {
      const data = await getListSeatBySchedule(idSchedule);
      setListSeat(data);
      console.log(data);
    }
    fetchData();
  }, []);

  const windows = function () {
    return (
      <div className="windows">
        <div className="windowUp ">
          <i class="fa-solid fa-arrow-left left"></i>
          <div></div>
          <p>Cửa lên tàu bay</p>
        </div>
        <div className="windowUp ">
          <p>Cửa lên tàu bay</p>
          <div></div>
          <i class="fa-solid fa-arrow-right right"></i>
        </div>
      </div>
    );
  };
  return (
    <div className="container ">
      <div className="mycontainer">
        <div className="noteSeatClass">
          <div className="noteSeatClass-header">
            <h6>Hạng ghế dựa trên màu tương ứng</h6>
          </div>
          <div className="noteSeatClass-body">
            <ul>
              <li>
                <button className="businessSeat"></button>
                <span>Ghế cao cấp</span>
              </li>
              <li>
                <button className="deluxeSeat"></button>
                <span>Ghế thương gia</span>
              </li>
              <li>
                <button className="basicSeat"></button>
                <span>Ghế tiêu chuẩn</span>
              </li>
              <li>
                <button className="seating"></button>
                <span>Ghế đang chọn</span>
              </li>
              <li>
                <button className="seated"></button>
                <span>Ghế đã đặt</span>
              </li>
            </ul>
          </div>
        </div>
        <div className="plane">
          <div className="airplaneHead ">
            <div>
              <svg width="21" height="45" viewBox="0 0 21 45" fill="none">
                <mask
                  id="mask0_599_36433"
                  maskUnits="userSpaceOnUse"
                  x="0"
                  y="0"
                  width="21"
                  height="45"
                >
                  <rect width="21" height="45" fill="#D9D9D9"></rect>
                </mask>
                <g mask="url(#mask0_599_36433)">
                  <mask id="path-2-inside-1_599_36433" fill="white">
                    <path
                      fill-rule="evenodd"
                      clip-rule="evenodd"
                      d="M482 45C482 45 476.28 -20.5905 416.126 -55.3814C370.605 -81.7091 271.509 -117.077 241 -114.905C210.491 -117.077 111.395 -81.7091 65.8741 -55.3814C5.72041 -20.5905 0 45 0 45H238.984H243.016H482Z"
                    ></path>
                  </mask>
                  <path
                    d="M416.126 -55.3814L419.13 -60.5752L419.13 -60.5752L416.126 -55.3814ZM482 45V51H488.546L487.977 44.4787L482 45ZM241 -114.905L240.574 -108.92L241 -108.889L241.426 -108.92L241 -114.905ZM65.8741 -55.3814L62.8702 -60.5752L65.8741 -55.3814ZM0 45L-5.97731 44.4787L-6.54606 51H0V45ZM413.122 -50.1875C441.788 -33.608 457.519 -9.68624 466.111 10.2324C470.407 20.1916 472.896 29.1036 474.307 35.5002C475.012 38.6952 475.445 41.2528 475.701 42.9872C475.828 43.8541 475.911 44.5144 475.961 44.9447C475.986 45.1599 476.002 45.3174 476.012 45.4145C476.017 45.4631 476.02 45.4965 476.022 45.5144C476.023 45.5234 476.023 45.5285 476.023 45.5296C476.023 45.5302 476.023 45.5298 476.023 45.5284C476.023 45.5277 476.023 45.5259 476.023 45.5256C476.023 45.5236 476.023 45.5213 482 45C487.977 44.4787 487.977 44.4759 487.977 44.4729C487.977 44.4716 487.976 44.4683 487.976 44.4656C487.976 44.4602 487.975 44.4538 487.974 44.4464C487.973 44.4317 487.971 44.4129 487.969 44.3902C487.965 44.3448 487.959 44.2836 487.951 44.207C487.936 44.0538 487.913 43.8388 487.881 43.5651C487.818 43.0176 487.719 42.2348 487.573 41.2402C487.28 39.2518 486.797 36.4132 486.025 32.9155C484.484 25.9268 481.783 16.2666 477.13 5.47937C467.823 -16.095 450.617 -42.3639 419.13 -60.5752L413.122 -50.1875ZM241.426 -108.92C248.057 -109.392 259.189 -107.799 273.436 -104.388C287.484 -101.025 303.958 -96.048 320.971 -90.1606C355.064 -78.3631 390.823 -63.0845 413.122 -50.1875L419.13 -60.5752C395.908 -74.006 359.358 -89.5754 324.896 -101.501C307.631 -107.475 290.766 -112.578 276.23 -116.058C261.893 -119.491 249.197 -121.504 240.574 -120.889L241.426 -108.92ZM241.426 -120.889C232.803 -121.504 220.107 -119.491 205.77 -116.058C191.234 -112.578 174.368 -107.475 157.104 -101.501C122.642 -89.5754 86.092 -74.006 62.8702 -60.5752L68.8781 -50.1875C91.1772 -63.0845 126.936 -78.3631 161.029 -90.1606C178.042 -96.048 194.516 -101.025 208.564 -104.388C222.811 -107.799 233.943 -109.392 240.574 -108.92L241.426 -120.889ZM62.8702 -60.5752C31.3827 -42.3639 14.1766 -16.095 4.87014 5.47937C0.216881 16.2666 -2.48396 25.9268 -4.02545 32.9155C-4.79693 36.4132 -5.2802 39.2518 -5.57279 41.2402C-5.71914 42.2348 -5.81793 43.0176 -5.88128 43.5651C-5.91297 43.8388 -5.9358 44.0538 -5.9513 44.207C-5.95905 44.2836 -5.96497 44.3448 -5.96924 44.3902C-5.97137 44.4129 -5.9731 44.4317 -5.97444 44.4464C-5.9751 44.4538 -5.97568 44.4602 -5.97615 44.4656C-5.97639 44.4683 -5.97668 44.4716 -5.9768 44.4729C-5.97707 44.4759 -5.97731 44.4787 0 45C5.97731 45.5213 5.97711 45.5236 5.97693 45.5256C5.9769 45.526 5.97675 45.5277 5.97668 45.5284C5.97656 45.5298 5.97652 45.5302 5.97658 45.5296C5.97668 45.5285 5.97715 45.5234 5.97799 45.5144C5.97967 45.4965 5.98288 45.4631 5.98779 45.4145C5.99761 45.3174 6.01424 45.1599 6.03914 44.9447C6.08895 44.5144 6.17181 43.8541 6.29937 42.9872C6.55458 41.2528 6.98817 38.6952 7.69288 35.5002C9.10377 29.1036 11.5926 20.1916 15.8887 10.2324C24.4809 -9.68624 40.2119 -33.608 68.8781 -50.1875L62.8702 -60.5752ZM0 51H238.984V39H0V51ZM238.984 51H243.016V39H238.984V51ZM482 39H243.016V51H482V39Z"
                    fill="url(#paint0_linear_599_36433)"
                    mask="url(#path-2-inside-1_599_36433)"
                  ></path>
                  <path
                    d="M7.06841 39H475.033L476 45H6L7.06841 39Z"
                    fill="white"
                  ></path>
                </g>
                <defs>
                  <linearGradient
                    id="paint0_linear_599_36433"
                    x1="241"
                    y1="-115"
                    x2="241"
                    y2="45"
                    gradientUnits="userSpaceOnUse"
                  >
                    <stop
                      offset="0.565554"
                      stop-color="#D1D1D1"
                      stop-opacity="0"
                    ></stop>
                    <stop offset="1" stop-color="#D1D1D1"></stop>
                  </linearGradient>
                </defs>
              </svg>
            </div>
            <div>
              <svg width="21" height="45" viewBox="0 0 21 45" fill="none">
                <mask
                  id="mask0_599_36434"
                  maskUnits="userSpaceOnUse"
                  x="0"
                  y="0"
                  width="21"
                  height="45"
                >
                  <rect
                    width="21"
                    height="45"
                    transform="matrix(-1 0 0 1 21 0)"
                    fill="#D9D9D9"
                  ></rect>
                </mask>
                <g mask="url(#mask0_599_36434)">
                  <mask id="path-2-inside-1_599_36434" fill="white">
                    <path
                      fill-rule="evenodd"
                      clip-rule="evenodd"
                      d="M-461 45C-461 45 -455.28 -20.5905 -395.126 -55.3814C-349.605 -81.7091 -250.509 -117.077 -220 -114.905C-189.491 -117.077 -90.395 -81.7091 -44.8741 -55.3814C15.2796 -20.5905 21 45 21 45H-217.984H-222.016H-461Z"
                    ></path>
                  </mask>
                  <path
                    d="M-395.126 -55.3814L-398.13 -60.5752L-398.13 -60.5752L-395.126 -55.3814ZM-461 45V51H-467.546L-466.977 44.4787L-461 45ZM-220 -114.905L-219.574 -108.92L-220 -108.889L-220.426 -108.92L-220 -114.905ZM-44.8741 -55.3814L-41.8702 -60.5752L-44.8741 -55.3814ZM21 45L26.9773 44.4787L27.5461 51H21V45ZM-392.122 -50.1875C-420.788 -33.608 -436.519 -9.68624 -445.111 10.2324C-449.407 20.1916 -451.896 29.1036 -453.307 35.5002C-454.012 38.6952 -454.445 41.2528 -454.701 42.9872C-454.828 43.8541 -454.911 44.5144 -454.961 44.9447C-454.986 45.1599 -455.002 45.3174 -455.012 45.4145C-455.017 45.4631 -455.02 45.4965 -455.022 45.5144C-455.023 45.5234 -455.023 45.5285 -455.023 45.5296C-455.023 45.5302 -455.023 45.5298 -455.023 45.5284C-455.023 45.5277 -455.023 45.5259 -455.023 45.5256C-455.023 45.5236 -455.023 45.5213 -461 45C-466.977 44.4787 -466.977 44.4759 -466.977 44.4729C-466.977 44.4716 -466.976 44.4683 -466.976 44.4656C-466.976 44.4602 -466.975 44.4538 -466.974 44.4464C-466.973 44.4317 -466.971 44.4129 -466.969 44.3902C-466.965 44.3448 -466.959 44.2836 -466.951 44.207C-466.936 44.0538 -466.913 43.8388 -466.881 43.5651C-466.818 43.0176 -466.719 42.2348 -466.573 41.2402C-466.28 39.2518 -465.797 36.4132 -465.025 32.9155C-463.484 25.9268 -460.783 16.2666 -456.13 5.47937C-446.823 -16.095 -429.617 -42.3639 -398.13 -60.5752L-392.122 -50.1875ZM-220.426 -108.92C-227.057 -109.392 -238.189 -107.799 -252.436 -104.388C-266.484 -101.025 -282.958 -96.048 -299.971 -90.1606C-334.064 -78.3631 -369.823 -63.0845 -392.122 -50.1875L-398.13 -60.5752C-374.908 -74.006 -338.358 -89.5754 -303.896 -101.501C-286.631 -107.475 -269.766 -112.578 -255.23 -116.058C-240.893 -119.491 -228.197 -121.504 -219.574 -120.889L-220.426 -108.92ZM-220.426 -120.889C-211.803 -121.504 -199.107 -119.491 -184.77 -116.058C-170.234 -112.578 -153.368 -107.475 -136.104 -101.501C-101.642 -89.5754 -65.092 -74.006 -41.8702 -60.5752L-47.8781 -50.1875C-70.1772 -63.0845 -105.936 -78.3631 -140.029 -90.1606C-157.042 -96.048 -173.516 -101.025 -187.564 -104.388C-201.811 -107.799 -212.943 -109.392 -219.574 -108.92L-220.426 -120.889ZM-41.8702 -60.5752C-10.3827 -42.3639 6.82344 -16.095 16.1299 5.47937C20.7831 16.2666 23.484 25.9268 25.0255 32.9155C25.7969 36.4132 26.2802 39.2518 26.5728 41.2402C26.7191 42.2348 26.8179 43.0176 26.8813 43.5651C26.913 43.8388 26.9358 44.0538 26.9513 44.207C26.959 44.2836 26.965 44.3448 26.9692 44.3902C26.9714 44.4129 26.9731 44.4317 26.9744 44.4464C26.9751 44.4538 26.9757 44.4602 26.9762 44.4656C26.9764 44.4683 26.9767 44.4716 26.9768 44.4729C26.9771 44.4759 26.9773 44.4787 21 45C15.0227 45.5213 15.0229 45.5236 15.0231 45.5256C15.0231 45.526 15.0233 45.5277 15.0233 45.5284C15.0234 45.5298 15.0235 45.5302 15.0234 45.5296C15.0233 45.5285 15.0229 45.5234 15.022 45.5144C15.0203 45.4965 15.0171 45.4631 15.0122 45.4145C15.0024 45.3174 14.9858 45.1599 14.9609 44.9447C14.911 44.5144 14.8282 43.8541 14.7006 42.9872C14.4454 41.2528 14.0118 38.6952 13.3071 35.5002C11.8962 29.1036 9.40736 20.1916 5.1113 10.2324C-3.48092 -9.68624 -19.2119 -33.608 -47.8781 -50.1875L-41.8702 -60.5752ZM21 51H-217.984V39H21V51ZM-217.984 51H-222.016V39H-217.984V51ZM-461 39H-222.016V51H-461V39Z"
                    fill="url(#paint0_linear_599_36434)"
                    mask="url(#path-2-inside-1_599_36434)"
                  ></path>
                  <path
                    d="M13.9316 39H-454.033L-455 45H15L13.9316 39Z"
                    fill="white"
                  ></path>
                </g>
                <defs>
                  <linearGradient
                    id="paint0_linear_599_36434"
                    x1="-220"
                    y1="-115"
                    x2="-220"
                    y2="45"
                    gradientUnits="userSpaceOnUse"
                  >
                    <stop
                      offset="0.565554"
                      stop-color="#D1D1D1"
                      stop-opacity="0"
                    ></stop>
                    <stop offset="1" stop-color="#D1D1D1"></stop>
                  </linearGradient>
                </defs>
              </svg>
            </div>
          </div>

          <div className="airplaneBody">
            {windows()}
            <div className="wall">
              <div className="wall-left">
                <div></div>
              </div>
              <div className="wall-right">
                <div></div>
              </div>
            </div>
            <div className="leterSeat">
              <div className="leterSeat-left">
                <ul>
                  <li>A</li>
                  <li>B</li>
                  <li className="leterSeatC">C</li>
                  <li>D</li>
                  <li>E</li>
                  <li>F</li>
                </ul>
              </div>
            </div>
            <div className="planeWing">
              <div className="planeWing-left">
                <svg width="255" height="900" viewBox="0 0 255 900" fill="none">
                  <mask
                    id="mask0_599_36421"
                    maskUnits="userSpaceOnUse"
                    x="0"
                    y="0"
                    width="255"
                    height="900"
                  >
                    <rect width="255" height="900" fill="#D9D9D9"></rect>
                  </mask>
                  <g mask="url(#mask0_599_36421)">
                    <path
                      d="M-836.736 825.437V1135.9L-453.313 1010.72L-453.276 1010.71L-453.239 1010.7L256.67 837.687V2.00495L-836.736 825.437Z"
                      fill="#F8F8F8"
                      stroke="#E6E6E6"
                      stroke-width="2"
                    ></path>
                    <path
                      d="M256.67 837.687V670.115L-476.582 890.109L-452.228 1010.45L256.67 837.687Z"
                      fill="#F2F2F2"
                      stroke="#D1D1D1"
                      stroke-width="2"
                    ></path>
                  </g>
                </svg>
              </div>
              <div className="planeWing-right">
                <svg width="255" height="900" viewBox="0 0 255 900" fill="none">
                  <mask
                    id="mask0_599_36422"
                    maskUnits="userSpaceOnUse"
                    x="0"
                    y="0"
                    width="255"
                    height="900"
                  >
                    <rect
                      width="255"
                      height="900"
                      transform="matrix(-1 0 0 1 255 0)"
                      fill="#D9D9D9"
                    ></rect>
                  </mask>
                  <g mask="url(#mask0_599_36422)">
                    <path
                      d="M1091.74 825.437V1135.9L708.313 1010.72L708.276 1010.71L708.239 1010.7L-1.67017 837.687V2.00495L1091.74 825.437Z"
                      fill="#F8F8F8"
                      stroke="#E6E6E6"
                      stroke-width="2"
                    ></path>
                    <path
                      d="M-1.67017 837.687V670.115L731.582 890.109L707.228 1010.45L-1.67017 837.687Z"
                      fill="#F2F2F2"
                      stroke="#D1D1D1"
                      stroke-width="2"
                    ></path>
                  </g>
                </svg>
              </div>
            </div>
            <div className="seat">
              <div className="list-seat">
                <ul>
                  {listSeat.map((s, index) => {
                    const i = index + 1;
                    if (i % 3 === 0) {
                      return (
                        <li className="leterSeatC">
                          <SeatComponent
                            level={s.aclass}
                            id={s._id}
                            idSchedule={idSchedule}
                            status={s.status}
                          ></SeatComponent>
                        </li>
                      );
                    } else {
                      return (
                        <li>
                          <SeatComponent
                            level={s.aclass}
                            id={s._id}
                            idSchedule={idSchedule}
                            status={s.status}
                          ></SeatComponent>
                        </li>
                      );
                    }
                  })}
                </ul>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}

export default SeatBooking;
