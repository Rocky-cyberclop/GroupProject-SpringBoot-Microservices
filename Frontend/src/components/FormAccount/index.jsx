import * as React from 'react';
import Box from '@mui/material/Box';
import Button from '@mui/material/Button';
import Typography from '@mui/material/Typography';
import Modal from '@mui/material/Modal';
import TextField from '@mui/material/TextField';
import HighlightOffIcon from '@mui/icons-material/HighlightOff';
import './FormAccount.styles.scss';
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
    const [email, setEmail] = React.useState('');
    const handleOpen = () => setOpen(true);
    const handleClose = () => setOpen(false);
    const changeEmail = React.useCallback((event) => {
        setEmail(event.target.value);
    }, []);
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
                        />
                        {props.name === false ?
                            <Button variant="contained" sx={{ mt: 2, ml: 15, width: 300, textAlign: 'center', padding: 1 }}>ĐĂNG KÝ</Button>
                            :
                            <Button variant="contained" sx={{ mt: 2, ml: 15, width: 300, textAlign: 'center', padding: 1 }}>ĐĂNG NHẬP</Button>
                        }
                    </div>
                </Box>
            </Modal>
        </div>
    );
}

export default Register;