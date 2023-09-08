"use strict";

function toggleCard(card) {

    const isFlipped = card.getAttribute("data-flipped") === "true";

    card.setAttribute("data-flipped", !isFlipped);


}