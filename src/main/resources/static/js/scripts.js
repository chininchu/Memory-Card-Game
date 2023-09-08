"use strict";

function toggleCard(card) {

    const cardId = card.getAttribute("data-card-id");

    const isFlipped = card.getAttribute("data-flipped") === "true";

    fetch(`/toggleCard?cardId=${cardId}`, {
        method: 'POST',

    })

        .then(Response => Response.text())

        .then((message) => {
            console.log(message);

            if (message === 'Card flipped') {
                card.setAttribute("data-flipped", !isFlipped);

            }


        })

        .catch((Error) => {
            console.error('Error:', Error);

        });


}