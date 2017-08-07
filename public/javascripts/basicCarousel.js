var selected = 0;
var allItems = document.getElementsByClassName("listItem");
var allText = document.getElementsByClassName("imgText");

function init() {
    if (allItems.length == allText.length) {
        for (i = 0; i < allItems.length; i++) {
            if (i == 0) {
                $(allItems[i]).addClass("selected")
            } else {
                $(allItems[i]).addClass("nonSelected")
                allText[i].style.display = "none";
            }
        }
    } else {
        console.log("Each image does not have a corresponding piece of text.")
    }
}

function next() {
    console.log(selected)
    if(selected + 1 == allItems.length) {
        newShow(0, parseInt(allItems.length)-1);
    } else {
        newShow(selected+1, selected);
    }
}

function prev() {
    console.log(selected)
    if(selected == 0) {
        newShow(parseInt(allItems.length)-1, 0);
    } else {
        newShow(selected-1, selected);
    }
}

function newShow(newSelected, oldSelected) {
    $(allItems[newSelected]).removeClass("nonSelected").addClass("selected");
    allText[newSelected].style.display = "block";
    $(allItems[oldSelected]).removeClass("selected").addClass("nonSelected");
    allText[oldSelected].style.display = "none";
    selected = newSelected;
}

init();

window.setInterval(next, 12000);