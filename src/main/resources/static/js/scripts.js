let firstCard = null;

function cardClicked(card) {
    if (firstCard === null) {
        firstCard = card;
        return;
    }

    const firstCardId = firstCard.getAttribute("data-card-id");
    const secondCardId = card.getAttribute("data-card-id");

    fetch(`http://localhost:8080/toggleCard?firstCardId=${firstCardId}&secondCardId=${secondCardId}`)
        .then(response => response.text())
        .then(responseText => {
            console.log(responseText);
            if (responseText === 'match') {
                // The cards match, so they should remain flipped
            } else {
                // The cards do not match, flip them back over after a short delay
                setTimeout(() => {
                    firstCard.classList.remove('flipped');
                    card.classList.remove('flipped');
                }, 1000);
            }
            // Reset firstCard for the next turn
            firstCard = null;
        })
        .catch(error => {
            console.error('Error:', error);
            firstCard = null;
        });
}
