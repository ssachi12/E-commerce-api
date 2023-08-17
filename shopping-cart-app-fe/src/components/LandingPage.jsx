import React from 'react';
import { Container, Row, Col, Button,Carousel} from 'react-bootstrap';
import '../styles/LandingPage.css'
import { Link } from 'react-router-dom';
import QuoteSection from './QuoteSection';
const LandingPage = () => {
  localStorage.removeItem("merchant")
  localStorage.removeItem("user")
  localStorage.removeItem("product")
  return (
   <div className="backland">

<Container className="landing-page">
      <Row>
        <Col md={6} className="login-section">
          <h1>Welcome to Our Store!</h1>
          <p>Please choose your login type:</p>
          <Button variant="primary" size="lg" className="login-button">
            <Link to='/merchantlogin' style={{textDecoration:"none",color:"white"}}>Login as Merchant</Link>
          </Button>
          <Button variant="secondary" size="lg" className="login-button">
           <Link to='/userlogin' style={{textDecoration:"none",color:"white"}}>Login as User</Link>
          </Button>
          {/* quote section */}
          <QuoteSection></QuoteSection>
        </Col>
        {/* image section */}
        <Col md={6} className="image-section">
          <Carousel>
            <Carousel.Item>
              <img
                className="d-block w-100"
                src="https://c0.wallpaperflare.com/preview/263/921/102/business-communication-computer-concept.jpg"
                alt="First slide"
              />
            </Carousel.Item>
            <Carousel.Item>
              <img
                className="d-block w-100"
                src="https://c4.wallpaperflare.com/wallpaper/473/325/202/blue-symbol-technology-product-wallpaper-preview.jpg"
                alt="Second slide"
              />
            </Carousel.Item>
            <Carousel.Item>
              <img
                className="d-block w-100"
                src="https://c4.wallpaperflare.com/wallpaper/493/81/216/girl-laptop-shopping-white-background-wallpaper-preview.jpg"
                alt="Second slide"
              />
            </Carousel.Item>
          </Carousel>
        </Col>
      </Row>
    </Container>

   </div>
  );
};

export default LandingPage;

