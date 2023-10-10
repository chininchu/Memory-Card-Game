"use strict";

// This function is responsible for updating the visual state of card elements on a webpage based on their data-flipped attribute. Here's how it works:

async function updateCardFlipState() {
    try {
        const response = await fetch('http://localhost:8080/game');
        const cards = await response.json();

        cards.forEach((card) => {
            const cardElement = document.querySelector(`.card[data-id="${card.id}"]`);
            const cardInner = cardElement.querySelector('.card-inner');
            if (card.isFlipped) {
                cardInner.classList.add('flipped');
            } else {
                cardInner.classList.remove('flipped');
            }
        });
    } catch (error) {
        console.error('Failed to fetch initial card states:', error);
    }
}


// the following function to your existing JavaScript code to toggle the flipped class and update the data-flipped attribute when a card is clicked.

async function toggleCardFlip(event) {
    const card = event.currentTarget;
    const cardInner = card.querySelector('.card-inner');
    const isFlipped = card.getAttribute('data-flipped') === 'true';
    const cardId = card.getAttribute('data-id');

    // Update the DOM first
    if (isFlipped) {
        cardInner.classList.remove('flipped');
        card.setAttribute('data-flipped', 'false');
    } else {
        cardInner.classList.add('flipped');
        card.setAttribute('data-flipped', 'true');
    }

    // Then update the server
    try {
        const response = await fetch(`http://localhost:8080/game`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({ isFlipped: !isFlipped }),
        });

        if (!response.ok) {
            console.error('Failed to update card state on server');
        }
    } catch (error) {
        console.error('Fetch failed:', error);
    }
}



// Event Listeners

document.addEventListener("DOMContentLoaded", async function () {
    // Fetch and set the initial card states
    await updateCardFlipState();

    // Attach the toggleCardFlip function to each card
    const cards = document.querySelectorAll('.card');
    cards.forEach((card) => {
        card.addEventListener('click', toggleCardFlip);
    });
});














