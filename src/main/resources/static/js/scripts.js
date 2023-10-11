// Fetch initial card states and update the DOM
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

// Toggle card flip state on click and update the server
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

// Attach event listeners after fetching initial card states
document.addEventListener("DOMContentLoaded", async function () {
    await updateCardFlipState();

    const cards = document.querySelectorAll('.card');
    cards.forEach((card) => {
        card.addEventListener('click', toggleCardFlip);
    });
});
