import DefaultAdminLayout from '../layouts/AdminLayout/DefaultAdminLayout/DefaultAdminLayout';
import Dashboard from '../pages/Admin/dashboard';
import AirportTable from '../pages/Admin/table/airportTable';
import Users from '../pages/Admin/users';
import HomePage from '../pages/HomePage/HomePage';
import Profile from '../pages/Profile';
import SeatBooking from '../pages/Seat/Seat';
const routes = [
    { path: '/', page: HomePage },
    { path: '/seatbooking', page: SeatBooking },
    { path: '/profile', page: Profile },
    { path: '/admin/dashboard', page: Dashboard, layout: DefaultAdminLayout },
    { path: '/admin/users', page: Users, layout: DefaultAdminLayout },
    { path: '/admin/table/airport', page: AirportTable, layout: DefaultAdminLayout },
];

export default routes;
