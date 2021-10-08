function rnd() {
    return Math.ceil(Math.random() * 255);
}

function rndColor() {
    color = "rgb(" + rnd() + "," + rnd() + "," + rnd() + ")";
    return color;
}