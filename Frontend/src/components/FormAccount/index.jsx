import * as React from 'react';
import Box from '@mui/material/Box';
import Button from '@mui/material/Button';
import Typography from '@mui/material/Typography';
import Modal from '@mui/material/Modal';
import TextField from '@mui/material/TextField';
import HighlightOffIcon from '@mui/icons-material/HighlightOff';
import './FormAccount.styles.scss';
import validator from 'validator';
import ArrowBackIosNewIcon from '@mui/icons-material/ArrowBackIosNew';
import CountdownTimer from './CountTime';
const style = {
    position: 'absolute',
    top: '50%',
    left: '50%',
    transform: 'translate(-50%, -50%)',
    width: 600,
    bgcolor: ' #ffffff',
    boxShadow: 24,

};
function Register(props) {
    const [open, setOpen] = React.useState(false);
    const [openModalConfirm, setOpenModalConfirm] = React.useState(false);
    const [email, setEmail] = React.useState('');
    const [error, setError] = React.useState(false);
    const [number, setNumber] = React.useState('');
    const [timeLeft, setTimeLeft] = React.useState(10);

    const handleOpen = () => {
        setOpen(true)
    };
    const handleClose = () => {
        setEmail('')
        setOpen(false)};

    const handleCloseConfirm = () =>{
        setEmail('')
        setOpenModalConfirm(false);
    } 
        

    const handleOpenConfirm = () => setOpenModalConfirm(true);

    const changeEmail = (event) => {
        setError(false)
        setEmail(event.target.value);
    }


    const changeNumber = (event) => {
        setNumber(event.target.value);
    }

    const backPrevious = () => {
        setEmail(email);
        handleOpen();
        setOpenModalConfirm(false);
    }
    const stepContinue = () => {
        setOpen(false);
        handleOpenConfirm();
    };

    const handleSubmit = (e) => {
        e.preventDefault()
        if (validator.isEmail(email)) {
            setError(false);
            stepContinue();
        }
        else {
            setError(true)
        }

    }

    const getTimer = React.useCallback((timeLeft) => {
        setTimeLeft(timeLeft);
    }, [])

    return (
        <div>
            {props.name === false ? (
                <Button variant="outlined" onClick={handleOpen}>Đăng Ký</Button>
            ) : (<Button variant="outlined" onClick={handleOpen}>Đăng Nhập</Button>)}
            <Modal
                open={open}
                onClose={handleClose}
                aria-labelledby="modal-modal-title"
                aria-describedby="modal-modal-description"
            >
                <Box sx={style}>
                    <div className='register-title'>
                        {props.name === false ?
                            <Typography className='register-text' id="modal-modal-title" variant="h6" component="h2">
                                ĐĂNG KÝ TÀI KHOẢN
                            </Typography> :
                            <Typography className='register-text' id="modal-modal-title" variant="h6" component="h2">
                                ĐĂNG NHẬP TÀI KHOẢN
                            </Typography>
                        }
                        <HighlightOffIcon className='register-close' onClick={handleClose}></HighlightOffIcon>
                    </div>
                    <div className='register-body'>
                        <Typography id="modal-modal-description" sx={{ mb: 2 }}>
                            Nhập email của bạn:
                        </Typography>
                        <TextField
                            className='input-email'
                            required
                            id="outlined-required"
                            label="Email"
                            value={email}
                            onChange={changeEmail}
                            error={!!error}
                            helperText={error ? 'Email không hợp lệ' : ''}
                        />
                        {props.name === false ?
                            <Button onClick={handleSubmit} variant="contained" sx={{ mt: 2, ml: 15, width: 300, textAlign: 'center', padding: 1 }}>ĐĂNG KÝ</Button>
                            :
                            <Button onClick={handleSubmit} variant="contained" sx={{ mt: 2, ml: 15, width: 300, textAlign: 'center', padding: 1 }}>ĐĂNG NHẬP</Button>
                        }
                    </div>
                </Box>
            </Modal>

            <div className="modal-confirm">
                <div>
                    <Modal
                        open={openModalConfirm}
                        onClose={handleCloseConfirm}
                        aria-labelledby="modal-modal-title"
                        aria-describedby="modal-modal-description"
                    >
                        <Box sx={style}>
                            <div className='confirm-title'>
                                <ArrowBackIosNewIcon className='confirm-iconback' onClick={backPrevious} fontSize='small'></ArrowBackIosNewIcon>
                                <Typography className='confirm-text' id="modal-modal-title" variant="h6" component="h2">
                                    NHẬP MÃ XÁC NHẬN
                                </Typography>
                                <HighlightOffIcon className='confirm-close' onClick={handleCloseConfirm}></HighlightOffIcon>
                            </div>
                            <div className='register-body'>

                                <TextField
                                    fullWidth
                                    className='input-number'
                                    required
                                    id="outlined-required"
                                    label="Mã xác nhận"
                                    value={number}
                                    onChange={changeNumber}
                                    error={error}
                                    helperText={error ? 'number không hợp lệ' : ''}
                                />
                                <Typography id="modal-modal-description" sx={{ mt: 2, textAlign: 'center', fontStyle: 'italic' }}>
                                    Mã xác nhận đã được gửi đến email của bạn.
                                </Typography>
                                <div >
                                    <CountdownTimer initialTime={timeLeft} getTimer={getTimer} /> 
                                </div>
                                {/* <Typography id="modal-modal-description" sx={{ textAlign: 'center', color: 'red' }}>
                                    Nếu không nhận được mã.
                                </Typography> */}
                                <Button variant="contained" sx={{ mt: 2, ml: 15, width: 300, textAlign: 'center', padding: 1 }}>XÁC NHẬN</Button>
                            </div>
                        </Box>
                    </Modal>
                </div>
            </div>
        </div>
    );
}

export default Register;