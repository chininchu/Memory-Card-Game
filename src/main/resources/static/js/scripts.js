"use strict";

let firstCard = null;

function cardClicked(card) {
    // const card = card.target;
    const cardId = card.getAttribute("data-card-id");

    fetch(`http://localhost:8080/toggleCard?cardId=${cardId}`, {
        method: 'POST',
    })
        .then(response => response.text())
        .then((response) => {
            console.log(response);  // Log the server response for debugging

            // Toggle the flipped class to flip the card
            card.classList.toggle('flipped');

            // Check if it's the first card being flipped
            if (!firstCard) {
                firstCard = card;  // Store the first card
            } else {
                // Check for a match with the second card
                if (firstCard.getAttribute('data-icon-name') === card.getAttribute('data-icon-name')) {
                    console.log('Match found!');
                    // (Further actions for a match can be added here)
                } else {
                    console.log('No match');
                    // (Further actions for a non-match can be added here)

                    // Flip the cards back over after a delay
                    setTimeout(() => {
                        firstCard.classList.remove('flipped');
                        card.classList.remove('flipped');
                    }, 1000);
                }

                // Reset the first card variable for the next turn
                firstCard = null;
            }
        })
        .catch((error) => {
            console.error('Error:', error);
        });
}
