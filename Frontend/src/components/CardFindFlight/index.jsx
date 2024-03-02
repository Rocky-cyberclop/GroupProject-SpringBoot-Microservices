import FlightTakeoffIcon from '@mui/icons-material/FlightTakeoff';
import './cardfindflight.styles.scss';
import TextField from '@mui/material/TextField';
import TrendingFlatIcon from '@mui/icons-material/TrendingFlat';
import { LocalizationProvider } from '@mui/x-date-pickers/LocalizationProvider';
import { AdapterDateFns } from '@mui/x-date-pickers/AdapterDateFns';
// or for dayjs
import { DatePicker } from '@mui/x-date-pickers';
import viLocale from 'date-fns/locale/vi'
import Button from '@mui/material/Button';
import { useState } from 'react';
import ListAirPort from '../ListAirPort/ListAirPort'
import FormControl from '@mui/material/FormControl';
import InputAdornment from '@mui/material/InputAdornment';
function CartFindFlight() {

    const [selectedDate, setSelectedDate] = useState(null);
    const handleDateChange = (newDate) => {
        setSelectedDate(newDate);
    };
    return (
        <div className="card findflight">
            <div className="card-header d-flex">
                <h5>Mua vé</h5>
                <FlightTakeoffIcon></FlightTakeoffIcon>
            </div>
            <div className="card-body">
                <p className="card-text">Chuyến bay một chiều</p>
                <FormControl >
                    <ListAirPort></ListAirPort>

                </FormControl>
                <TrendingFlatIcon className='arrow-icon' ></TrendingFlatIcon>
                <TextField
                    className='input-item'
                    required
                    id="outlined-required"
                    label="Điểm đến"
                    value="Chọn điểm đến"
                />
                <LocalizationProvider dateAdapter={AdapterDateFns} locale={viLocale}>
                    <DatePicker
                        className='dayDeparture'
                        label="Chọn ngày"
                        value={selectedDate}
                        disablePast
                        onChange={handleDateChange}
                        inputFormat="dd/MM/YYYY"
                    />
                </LocalizationProvider>

                <Button variant="contained" size="large">Tìm chuyến bay</Button>
            </div>
        </div >
    )
}

export default CartFindFlight;