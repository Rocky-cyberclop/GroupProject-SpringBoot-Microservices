import HomePage from "../pages/HomePage/HomePage";
import Profile from "../pages/Profile";
import SeatBooking from "../pages/Seat/Seat";
const routes = [
    { path: '/', page: HomePage},
    { path: '/seatbooking', page: SeatBooking},
    {path: '/profile', page: Profile}
]

export default routes;