const tabs = ['account_editing', 'table_editing', 'reservation_editing'];

function show_editing_menu(menu) {
    var x = document.getElementById(menu);
    if (x.style.display === 'none') {
        x.style.display = 'block';

        for (let i=0; i<tabs.length; i++) {
            if (tabs[i] !== menu) {
                let item = document.getElementById(tabs[i])
                item.style.display = 'none';
            }
        }
    }
}