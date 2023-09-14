let firstCard = null;
let secondCard = null;

function cardClicked(cardElement) {
    if (firstCard === null) {
        firstCard = cardElement;
    } else {
        secondCard = cardElement;
        checkForMatch();
    }

    const cardId = cardElement.getAttribute('data-card-id');
    fetch(`http://localhost:8080/toggleCard?cardId=${cardId}`)
        .then(response => response.text())
        .then(data => console.log(data));
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
                    firstCard.classList.remove('flipped');
                    secondCard.classList.remove('flipped');
                    firstCard = null;
                    secondCard = null;
                }, 1000);
            }
        });
}
