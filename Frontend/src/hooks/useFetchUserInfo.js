import axios from 'axios';
import { useEffect, useState } from 'react';
import useToken from './useToken';

function useFetchUserInfo() {
    const [userInfo, setUserInfo] = useState(null);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);
    const { isTokenValid, token } = useToken();

    useEffect(() => {
        const fetchUserInfo = async () => {
            try {
                setLoading(true);

                const res = await axios.get('http://localhost:8080/api/v1/user/info', {
                    headers: { Authorization: 'Bearer ' + token },
                });
                setUserInfo(res.data);
                // console.log(data);
                setLoading(false);
            } catch (error) {
                setError('Fetch user info error!');
                console.log(error);
                console.log('Fetch user info error!');
            }
        };

        fetchUserInfo(token);
    }, []);
    return { userInfo, loading, error };
}

export default useFetchUserInfo;
