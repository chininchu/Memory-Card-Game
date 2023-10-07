"use strict";

let firstCard = null;
let secondCard = null;
let timer = 300; // 5 minutes in seconds
let interval;

async function fetchGameState() {
    try {
        const response = await fetch('http://localhost:8080/game', {
            method: 'GET',
            credentials: 'include'

        });

        if (response.ok) {
            const gameState = await response.json(); // Assuming the server returns JSON
            setInitialState(gameState); // Function to update the game board based on the game state
        } else {
            console.error('Failed to fetch game state:', response.status, response.statusText);
        }
    } catch (error) {
        console.error('An error occurred while fetching the game state:', error);
    }
}

function setInitialState(gameState) {
    // Update your game board here using the game state
    // For example, you could loop through the cards in the game state and update their flipped status
    // Since gameState is not defined yet, I'm leaving this part empty
}

function startGame() {
    // Initialize the timer
    interval = setInterval(() => {
        timer--;
        document.getElementById("timer").innerText = `${Math.floor(timer / 60)}:${timer % 60}`;
        if (timer <= 0) {
            clearInterval(interval);
            // End the game
            alert("Time's up!");
        }
    }, 1000);
    fetchGameState();  // Fetch the initial state of the cards
}

function cardClicked(cardElement) {
    const iconElement = cardElement.querySelector('.material-icons');
    iconElement.classList.toggle('flipped');

    cardElement.classList.toggle('flipped');

    if (firstCard === null) {
        firstCard = cardElement;
    } else {
        secondCard = cardElement;
        checkForMatch();
    }

    let isFlipped = cardElement.getAttribute('data-flipped') === 'true';
    cardElement.setAttribute('data-flipped', String(!isFlipped));

    const cardId = cardElement.getAttribute('data-card-id');
    fetch(`http://localhost:8080/toggleCard?cardId=${cardId}`, {
        method: 'POST',
    })
        .then(response => response.text())
        .then(data => console.log(data))
        .catch(error => console.error('Error', error));
}

function checkForMatch() {
    const firstCardId = firstCard.getAttribute('data-card-id');
    const secondCardId = secondCard.getAttribute('data-card-id');

    fetch(`http://localhost:8080/checkMatch?firstCardId=${firstCardId}&secondCardId=${secondCardId}`)
        .then(response => response.json())
        .then(data => {
            if (data.match) {
                // Cards match, keep them flipped
                firstCard = null;
                secondCard = null;
            } else {
                // Cards do not match, flip them back
                setTimeout(() => {
                    firstCard.setAttribute('data-flipped', 'false');
                    secondCard.setAttribute('data-flipped', 'false');
                    firstCard = null;
                    secondCard = null;
                }, 1000);
            }
        })
        .catch(error => console.error('Error', error));
}

// Call startGame when the page loads
window.addEventListener('load', () => {
    startGame();
});
