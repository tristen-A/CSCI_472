var prev_elmt_num = null;

function select_table(num) {
    var element = document.getElementById("table_selection_" + num);
    var prev_element = document.getElementById("table_selection_" + prev_elmt_num);

    if (element.dataset.reserved === "true") {
        window.alert("This table is already reserved.");
        return;
    }

    if ((element.style.display === 'none') || (num !== prev_elmt_num)) {
        if (prev_elmt_num != null) {
            prev_element.style.display = 'none';
        }
        element.style.display = 'block';
        prev_elmt_num = num;
    } else {
        element.style.display = 'none';
        prev_element.style.display = 'none';
        prev_elmt_num = null;
    }
}

function load_table_icons() {
    const images = ["Assets/Available_Table_Icon.png", "Assets/Reserved_Table_Icon.png"];
    const amount = parseInt(document.getElementById("table_container").dataset.amount);

    for (let i = 1; i <= amount + 1; i++) {
        const imageElement = document.getElementById("btn_img" + i);
        const tableSelector = document.getElementById("table_selection_" + i);
        if (tableSelector.dataset.reserved === "false") {
            imageElement.src = images[0];
        } else {
            imageElement.src = images[1];
        }
    }
}

document.addEventListener("DOMContentLoaded", function() {
    load_table_icons();
});