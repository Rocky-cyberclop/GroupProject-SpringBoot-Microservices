import Carousel from "../../components/Carousels";
import CartFindFlight from "../../components/CardFindFlight";
import './HomePage.styles.scss';
function HomePage() {
    return (
        <div className="homepage">
            <Carousel></Carousel>
            <div className="container cartfindflight">
                <CartFindFlight></CartFindFlight>
            </div>
        </div>
    )
}
export default HomePage;