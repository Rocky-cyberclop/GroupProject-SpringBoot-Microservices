import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faUser, faBell } from '@fortawesome/free-regular-svg-icons';

export const DropdownMenuProfileData = [
    {
        name: 'account',
        menu: [
            { title: 'Your account', icon: <FontAwesomeIcon icon={faUser} />, iconColor: 'blue', value: 'profile' },
            { title: 'Profile', value: 'profile' },
            { title: 'History transaction', value: 'history transaction' },
        ],
    },
    {
        name: 'notification',
        menu: [
            {
                title: 'Notification',
                icon: <FontAwesomeIcon icon={faBell} />,
                iconColor: 'orange',
                value: 'main notification',
            },
            { title: 'Main notification', value: 'main notification' },
            { title: 'News', value: 'news' },
        ],
    },
];
