
function comrsp() {
    return Math.ceil(Math.random() * 3);
}

function game(user, com) {

    if (user == com) {
        return "DRAW"

    } else if (user == 1) {
        if (com == 2) {
            return "LOSE"
        } else {
            return "WIN"
        }
    } else if (user == 2) {
        if (com == 3) {
            return "LOSE"

        } else {
            return "WIN"
        }
    } else {
        if (com == 1) {
            return "LOSE"
        } else {
            return "WIN"
        }
    }
}
