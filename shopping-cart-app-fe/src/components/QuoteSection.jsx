import React, { useState, useEffect } from 'react';
import '../styles/LandingPage.css';
const quotes = [
  {
    quote: "E-commerce: where passion and creativity meet business and technology.",
    author: "Unknown",
  },
  {
    quote: "Innovation distinguishes between a leader and a follower.",
    author: "Steve Jobs",
  },
  {
    quote: "The best way to predict the future is to create it.",
    author: "Peter Drucker",
  },
];

const QuoteSection = () => {
  const [currentQuoteIndex, setCurrentQuoteIndex] = useState(0);

  useEffect(() => {
    // Function to update the quote index after 5 seconds
    const interval = setInterval(() => {
      setCurrentQuoteIndex((prevIndex) => (prevIndex + 1) % quotes.length);
    }, 5000); // Change quote every 5 seconds (5000 milliseconds)

    // Clean up the interval when the component unmounts
    return () => clearInterval(interval);
  }, []);

  const currentQuote = quotes[currentQuoteIndex];

  return (
    <div className="quote-section">
      <div className="quote-container">
        <p>"{currentQuote.quote}"</p>
        <p className="quote-author">- {currentQuote.author}</p>
      </div>
    </div>
  );
};

export default QuoteSection;
