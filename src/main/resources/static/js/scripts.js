// let firstCard = null;
// let secondCard = null;
//
// function cardClicked(cardElement) {
//     cardElement.classList.toggle('flipped');
//
//
//     if (firstCard === null) {
//         firstCard = cardElement;
//     } else {
//         secondCard = cardElement;
//         checkForMatch();
//     }
//
//     let isFlipped = cardElement.getAttribute('data-flipped') === 'true';
//
//     cardElement.setAttribute('data-flipped', String(!isFlipped));
//
//
//     const cardId = cardElement.getAttribute('data-card-id');
//     fetch(`http://localhost:8080/toggleCard?cardId=${cardId}`, {
//
//         method: 'POST',
//
//     })
//         .then(response => response.text())
//         .then(data => console.log(data))
//         .catch(error => console.error('Error', error));
// }
//
// function checkForMatch() {
//     const firstCardId = firstCard.getAttribute('data-card-id');
//     const secondCardId = secondCard.getAttribute('data-card-id');
//
//     fetch(`http://localhost:8080/checkMatch?firstCardId=${firstCardId}&secondCardId=${secondCardId}`)
//         .then(response => response.json())
//         .then(data => {
//             if (data.match) {
//                 // Cards match, keep them flipped
//                 firstCard = null;
//                 secondCard = null;
//
//                 console.log("its a match");
//             } else {
//                 // Cards do not match, flip them back
//                 setTimeout(() => {
//                     firstCard.setAttribute('data-flipped', 'false');
//                     secondCard.setAttribute('data-flipped', false);
//                     firstCard = null;
//                     secondCard = null;
//
//                 }, 1000);
//
//                 console.log("Not a match")
//             }
//         })
//
//         .catch(error => console.error('Error', error));
// }

"use strict";


let firstCard = null;
let secondCard = null;
let timer = 300; // 5 minutes in seconds
let interval;

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
}

function cardClicked(cardElement) {
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
                // Update the score
                fetch('/updateScore', {
                    method: 'POST',
                })
                    .then(response => response.json())
                    .then(data => {
                        // Update the score on the frontend
                        console.log("Score updated");
                    });
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

// Call startGame when the page loads or when the "Start Game" button is clicked
startGame();
