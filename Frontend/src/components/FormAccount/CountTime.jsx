import React, { useState, useEffect } from 'react';
import CachedIcon from '@mui/icons-material/Cached';
import Button from '@mui/material/Button';

function CountdownTimer({ initialTime}) {
    const [timeLeft, setTimeLeft] = useState(initialTime);
    const [isRunning, setIsRunning] = useState(true);
    useEffect(() => {
        const timer = setInterval(() => {
            if (isRunning && timeLeft > 0) {
                setTimeLeft(prevTime => prevTime - 1);
            } else if (timeLeft === 0) {
                setIsRunning(false);
                clearInterval(timer);
            }
        }, 1000);

        return () => clearInterval(timer);   // Xóa interval khi component unmount hoặc khi đồng hồ dừng
    }, [isRunning, timeLeft]);

    // useEffect(() => {
    //     if (timeLeft === 0) {
    //         getTimer(0);
    //     }
    // }, [timeLeft,]);
    const handleLoadClick = () => {
        setTimeLeft(initialTime); 
        setIsRunning(true);
    };
    // Chuyển đổi thời gian còn lại thành định dạng phù hợp (ví dụ: mm:ss)
    const formatTime = (seconds) => {
        const minutes = Math.floor(seconds / 60);
        const remainingSeconds = seconds % 60;
        return `${minutes}:${remainingSeconds < 10 ? '0' : ''}${remainingSeconds}`;
    };

    return (
        <div className=' d-flex justify-content-center'>
            <p className='mt-3 '><span className='text-danger'>Gửi lại sau:</span> {formatTime(timeLeft)}</p>
            {timeLeft === 0 ?
                <Button onClick={handleLoadClick} variant='text'><CachedIcon fontSize='small'></CachedIcon>Gửi lại</Button>
                : ''
            }
        </div>
    );
}

export default CountdownTimer;
