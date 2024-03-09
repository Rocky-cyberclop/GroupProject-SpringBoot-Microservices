import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import {
    faCalendarCheck,
    faChartColumn,
    faDiagramNext,
    faRightFromBracket,
    faRightToBracket,
    faUserGroup,
    faUserPlus,
} from '@fortawesome/free-solid-svg-icons';
export const SidebarAdminMenuData = [
    {
        title: 'Dashboard',
        icon: <FontAwesomeIcon icon={faChartColumn} />,
        value: 'dashboard',
        dropdownMenu: [],
    },
    {
        title: 'Table',
        icon: <FontAwesomeIcon icon={faDiagramNext} />,
        value: 'table',
        dropdownMenu: [
            {
                title: 'Air plane',
                value: 'airPlane',
            },
            {
                title: 'Air port',
                value: 'airport',
            },
            {
                title: 'Runways',
                value: 'runways',
            },
            {
                title: 'Schedule',
                value: 'schedule',
            },
        ],
    },
    {
        title: 'Todo',
        icon: <FontAwesomeIcon icon={faCalendarCheck} />,
        value: 'todo',
        dropdownMenu: [],
    },
    {
        title: 'Users',
        icon: <FontAwesomeIcon icon={faUserGroup} />,
        value: 'users',
        dropdownMenu: [],
        separate: true,
    },
    {
        title: 'SignIn',
        value: 'signIn',
        icon: <FontAwesomeIcon icon={faRightToBracket} />,
        dropdownMenu: [],
    },
    {
        title: 'SignUp',
        value: 'signUp',
        icon: <FontAwesomeIcon icon={faUserPlus} />,
        dropdownMenu: [],
    },
    {
        title: 'Logout',
        value: 'logout',
        icon: <FontAwesomeIcon icon={faRightFromBracket} />,
        dropdownMenu: [],
    },
];
