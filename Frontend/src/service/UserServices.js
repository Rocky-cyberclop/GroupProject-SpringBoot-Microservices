import API from './API';

export const register = async (email) => {
    try {
        const response = await API.post('/api/v1/user/send/register', { email: email });
        return response.data;
    } catch (error) {
        console.error('Error fetching data:', error);
    }
};

export const verifyCode = async (email, code) => {
    try {
        const response = await API.post('/api/v1/user/register', { email: email, code: code });
        return response.data;
    } catch (error) {
        console.error('Error fetching data:', error);
    }
};
