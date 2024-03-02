import Header from "../ComponentsLayout/Header/Header.jsx";
// import Nav from "../ComponentsLayout/Nav/Nav.js";
// import Grid from '@mui/material/Grid';
function DefaultLayout({ children }) {
    return (
        <div>
            <Header></Header>
            <div className="">
                {children}
            </div>

        </div>
    )
}
export default DefaultLayout;