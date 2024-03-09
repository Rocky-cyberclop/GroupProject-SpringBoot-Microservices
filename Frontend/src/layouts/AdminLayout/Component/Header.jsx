import React from 'react';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faChevronDown } from '@fortawesome/free-solid-svg-icons';
import { faBell, faEnvelope } from '@fortawesome/free-regular-svg-icons';

function Header() {
    return (
        <header className="fixed top-0 w-10/12 px-6 bg-white h-16 flex place-content-between">
            <div className="flex-initial w-1/2 flex items-center">
                <label htmlFor="search" className="mb-2 text-sm font-medium text-gray-900 sr-only dark:text-white">
                    Search
                </label>
                <form className="flex items-center max-w-lg mx-auto w-full">
                    <label htmlFor="voice-search" className="sr-only">
                        Search
                    </label>
                    <div className="relative w-full">
                        <div className="absolute inset-y-0 start-0 flex items-center ps-3 pointer-events-none">
                            <svg
                                className="w-4 h-4 text-gray-500 dark:text-gray-400"
                                aria-hidden="true"
                                xmlns="http://www.w3.org/2000/svg"
                                fill="none"
                                viewBox="0 0 21 21"
                            >
                                <path
                                    stroke="currentColor"
                                    strokeLinecap="round"
                                    strokeLinejoin="round"
                                    strokeWidth={2}
                                    d="M11.15 5.6h.01m3.337 1.913h.01m-6.979 0h.01M5.541 11h.01M15 15h2.706a1.957 1.957 0 0 0 1.883-1.325A9 9 0 1 0 2.043 11.89 9.1 9.1 0 0 0 7.2 19.1a8.62 8.62 0 0 0 3.769.9A2.013 2.013 0 0 0 13 18v-.857A2.034 2.034 0 0 1 15 15Z"
                                />
                            </svg>
                        </div>
                        <input
                            type="text"
                            id="voice-search"
                            className="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full ps-10 p-2.5  dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
                            placeholder="Search..."
                            required=""
                        />
                        <button type="button" className="absolute inset-y-0 end-0 flex items-center pe-3">
                            <svg
                                className="w-4 h-4 text-gray-500 dark:text-gray-400 hover:text-gray-900 dark:hover:text-white"
                                aria-hidden="true"
                                xmlns="http://www.w3.org/2000/svg"
                                fill="none"
                                viewBox="0 0 16 20"
                            >
                                <path
                                    stroke="currentColor"
                                    strokeLinecap="round"
                                    strokeLinejoin="round"
                                    strokeWidth={2}
                                    d="M15 7v3a5.006 5.006 0 0 1-5 5H6a5.006 5.006 0 0 1-5-5V7m7 9v3m-3 0h6M7 1h2a3 3 0 0 1 3 3v5a3 3 0 0 1-3 3H7a3 3 0 0 1-3-3V4a3 3 0 0 1 3-3Z"
                                />
                            </svg>
                        </button>
                    </div>
                </form>
            </div>
            <div className="flex w-1/2 justify-end">
                <div className="flex items-center">
                    <img
                        className="w-10 h-10 rounded-full"
                        src="https://down-vn.img.susercontent.com/file/vn-11134233-7r98o-lnjefuceb33uc2_tn"
                        alt="avatar"
                    />
                    <div className="relative py-1 ml-2 w-36 group">
                        <div className="flex justify-center items-center cursor-pointer">
                            <span>pitithuong</span>
                            <FontAwesomeIcon icon={faChevronDown} className="ml-5 text-gray-400" />
                        </div>
                        <div className="absolute hidden top-8 right-px z-10 w-36 bg-white rounded divide-y divide-gray-100 shadow dark:bg-gray-700 dark:divide-gray-600 group-hover:block">
                            <ul
                                className="py-1 text-sm text-gray-700 dark:text-gray-200"
                                aria-labelledby="dropdownMenuIconHorizontalButton"
                            >
                                <li>
                                    <button className="block w-full text-left py-2 px-4 hover:bg-gray-100 dark:hover:bg-gray-600 dark:hover:text-white">
                                        Profile
                                    </button>
                                </li>
                                <li>
                                    <button className="block w-full text-left py-2 px-4 hover:bg-gray-100 dark:hover:bg-gray-600 dark:hover:text-white">
                                        Upgrade
                                    </button>
                                </li>

                                <li>
                                    <button className="block w-full text-left py-2 px-4 hover:bg-gray-100 dark:hover:bg-gray-600 dark:hover:text-white">
                                        Logout
                                    </button>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
                <div className="flex items-center w-36 place-content-evenly text-purple-500 font-light">
                    <div className="relative cursor-pointer">
                        <span class="absolute flex size-2 right-0 -top-0">
                            <span class="animate-ping absolute inline-flex h-full w-full rounded-full bg-sky-400 opacity-75"></span>
                            <span class="relative inline-flex rounded-full size-2 bg-sky-500"></span>
                        </span>
                        <FontAwesomeIcon icon={faEnvelope} className="w-6 h-6" />
                    </div>
                    <div className="cursor-pointer">
                        <FontAwesomeIcon icon={faBell} className="w-6 h-6" />
                    </div>
                </div>
            </div>
        </header>
    );
}

export default Header;
