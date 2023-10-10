"use strict";
function updateCardFlipState() {
    const cards = document.querySelectorAll('.card');
    cards.forEach((card) => {
        const isFlipped = card.getAttribute('data-flipped') === 'true';
        const cardInner = card.querySelector('.card-inner');
        if (isFlipped) {
            cardInner.classList.add('flipped');
        } else {
            cardInner.classList.remove('flipped');
        }
    });
}

document.addEventListener("DOMContentLoaded", function() {
    updateCardFlipState();
});



