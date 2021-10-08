let imgnum = [];
let win16 = [];
let win8 = [];
let win4 = [];
let win2;
let num;

function rndimg() {
    return Math.ceil(Math.random() * 16);
}

function makeImgNum() {
    for (let i = 0; i < 16; i++) {
        num = rndimg();

        imgnum[i] = num;
        for (let j = 0; j < i; j++) {
            if (imgnum[i] == imgnum[j]) {
                i--;
                break;
            }
        }

    }
    console.log(imgnum)
}

function select(num, round) {
    if (round == 16) {
        return imgnum[num];
    } else if (round == 8) {
        return win16[num];
    } else if (round == 4) {
        return win8[num];
    } else {
        return win4[num];
    }
}

function win(num, round) {
    if (round == 16) {
        win16.push(num);
        console.log(win16);
    } else if (round == 8) {
        win8.push(num);
        console.log(win8);
    } else if (round == 4) {
        win4.push(num);
        console.log(win4);
    } else {
        win2 = num;
        console.log(win2);
    }
}
