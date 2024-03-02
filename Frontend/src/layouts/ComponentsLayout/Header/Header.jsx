import * as React from 'react';
import './style.scss'
import Container from '@mui/material/Container';
import { Link } from "react-router-dom";
import Stack from '@mui/material/Stack';
// import Button from '@mui/material/Button';
import FormAccount from '../../../components/FormAccount';
export default function Header() {
    const islogin = false;
    return (
        <Container>
            <div className='header'>
                <div className="logo">
                    <img src="/assets/image/logo.png" alt="" />
                </div>
                <div className="navigaton">
                    <div className='navbar-account'>
                        <Stack spacing={2} direction="row">
                            <FormAccount name={islogin}></FormAccount>
                            <FormAccount name={!islogin}></FormAccount>
                        </Stack>
                    </div>
                    <div className='navbar-list'>
                        <Stack spacing={2} direction="row">
                            <Link to='/' className='navbar-item'>Trang Chủ</Link>
                            <Link to='' className='navbar-item'>Chuyến Bay Của Tôi</Link>
                            <Link to='' className='navbar-item'>Dịch Vụ Chuyến Bay</Link>
                            <Link to='' className='navbar-item'>Dịch Vụ Khác</Link>
                        </Stack>
                    </div>
                </div>
            </div>
        </Container>
    );
}