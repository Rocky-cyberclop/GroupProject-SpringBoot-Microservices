import { useRef, useState, useCallback, useEffect } from 'react';
// import { useDispatch } from 'react-redux';
import { format } from 'date-fns';
// import { toast } from 'react-toastify';

import FormInputText3 from './FormInputText3';
import { useToken } from '../../hooks';
import useFetchUserInfo from '../../hooks/useFetchUserInfo';
import axios from 'axios';
// import { userService } from '~/apiServices';
// import { userActions } from '~/store/user-slice';
// import useFetchUserInfo from '~/hooks/useFetchUserInfo';

const notify = (a, b) => {};
// const notify = (message, type = 'success') => {
//     toast(message, {
//         type: type,
//         style: { fontSize: '1.4rem' },
//         position: toast.POSITION.TOP_RIGHT,
//         closeOnClick: true,
//         autoClose: 1500,
//         className: 'foo-bar',
//     });
// };

const validation = {
    phone: {
        patternRegex: /(84|0[3|5|7|8|9])+([0-9]{8})\b/,
        errorMessage: 'Invalid phone number!',
        maxLength: 10,
    },
    fullName: {
        maxLength: 30,
    },
};

function ProfileDetail() {
    // const dispatch = useDispatch();
    const { userInfo, loading, error } = useFetchUserInfo();
    const [newAvatar, setNewAvatar] = useState(null);
    const [userDetail, setUserDetail] = useState(() => ({
        fullName: '',
        lastUpdate: '',
        phone: '',
        email: '',
        fullNameFieldValid: true,
        phoneFieldValid: true,
    }));
    const { token, isTokenValid } = useToken();

    useEffect(() => {
        setUserDetail((prev) => ({
            ...prev,
            ...userInfo,
        }));
    }, [userInfo]);

    const onValueChange = useCallback((e, isValid) => {
        setUserDetail((prev) => ({
            ...prev,
            [e.target.name]: e.target.value,
            [e.target.name + 'FieldValid']: isValid,
        }));
    }, []);

    const handleSubmit = () => {
        if (!userDetail.fullNameFieldValid || !userDetail.phoneFieldValid) {
            alert('Invalid form!');
            return;
        }

        const submitData = {
            FullName: userDetail.fullName,
            Phone: userDetail.phone,
        };
        const updateUserCall = async () => {
            try {
                const res = await axios.put(
                    'http://localhost:8080/api/v1/user/edit',
                    {
                        email: 'gdsg',
                        fullName: userDetail.fullName,
                        phone: userDetail.phone,
                    },
                    {
                        headers: { Authorization: 'Bearer ' + token },
                    },
                );
                console.log(res);
                alert(res.data);
            } catch (error) {
                console.log(error);
                alert('Edit user info error!');
            }
        };

        if (isTokenValid) {
            updateUserCall();
        } else {
            alert('Token is expire!');
        }
    };

    console.log(userDetail);

    return (
        <div className="py-5 px-14">
            <div className="pb-7 border-b">
                <h2 className="text-4xl">My Profile</h2>
                <p>Manage profile information for account security</p>
            </div>
            <div className="flex flex-col-reverse lg:flex-row">
                <div className="lg:pr-10 lg:border-r lg:w-full">
                    <ul>
                        <li key={0} className="w-full flex my-7">
                            <div className="w-1/4 text-right text-gray-400">Email:</div>
                            <p className="w-3/4 ml-7">{userDetail.email}</p>
                        </li>
                        <li key={1} className="w-full flex my-7 text-gray-400">
                            <div className="w-1/4 text-right">FullName: </div>
                            <FormInputText3
                                className="w-3/4 ml-7"
                                name="fullName"
                                value={userDetail.fullName}
                                validation={validation.fullName}
                                onValueChange={onValueChange}
                            />
                        </li>
                        <li key={2} className="w-full flex my-7 text-gray-400">
                            <div className="w-1/4 text-right">Phone number: </div>
                            <FormInputText3
                                className="w-3/4 ml-7"
                                name="phone"
                                value={userDetail.phone}
                                validation={validation.phone}
                                onValueChange={onValueChange}
                            />
                        </li>
                        <li key={5} className="w-full flex my-7 text-gray-400">
                            <div className="w-1/4 text-right"></div>

                            <div className="w-3/4 ml-7">
                                <button
                                    type="button"
                                    className="text-green-700 hover:text-white border border-green-700 hover:bg-green-800 focus:ring-4 focus:outline-none focus:ring-green-300 font-medium rounded-lg text-sm px-5 py-2.5 text-center me-2 mb-2 dark:border-green-500 dark:text-green-500 dark:hover:text-white dark:hover:bg-green-600 dark:focus:ring-green-800"
                                    onClick={handleSubmit}
                                >
                                    Save
                                </button>
                            </div>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
    );
}

export default ProfileDetail;
