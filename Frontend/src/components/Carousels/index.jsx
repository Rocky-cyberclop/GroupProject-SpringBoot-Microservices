import AwesomeSlider from 'react-awesome-slider';
import withAutoplay from 'react-awesome-slider/dist/autoplay';
import 'react-awesome-slider/dist/styles.css';
import './carousel.styles.scss';
const AutoplaySlider = withAutoplay(AwesomeSlider);

function Carousel() {
    return (
        <div className='carousel'>
        <AutoplaySlider
            play={true}
            cancelOnInteraction={false} // should stop playing on user interaction
            interval={4000}
            fillParent={true}
        >
            <div data-src="/assets/image/carousels/carousel1.jpg" />
            <div data-src="/assets/image/carousels/carousel2.jpg" />
            <div data-src="/assets/image/carousels/carousel3.jpg" />
        </AutoplaySlider>
        </div>
    )
}
export default Carousel;