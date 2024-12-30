let select_item_row = null;

/*===========================================================================================================================*/
const generateNextIdItem = (items) => {
    let maxId = 0;
    items.forEach(item => {
        const numericPart = parseInt(item.item_id.substring(1)); // Extract numeric part
        if (numericPart > maxId) {
            maxId = numericPart;
        }
    });

    const nextNumericId = maxId + 1;
    return `I${nextNumericId.toString().padStart(3, '0')}`;
};

const autoGenerateItemNextId = () => {
    $.ajax({
        url: "http://localhost:8080/Application_1_war_exploded/item",
        type: "GET",
        success: (response) => {
            console.log("Fetched Item:", response);

            let nextId = "I001";
            if (response.length > 0) {
                nextId = generateNextIdItem(response);
            }

            $('#item_id').val(nextId);
        },
        error: (err) => {
            console.error("Error fetching item:", err);
        }
    });
};

/*======================================================= when page load run====================================================================*/
$(document).ready(function (){
    autoGenerateItemNextId();
    loadItemTable();
    loadAllItem();
})

/*==================================================== Load All Item=======================================================================*/
const loadAllItem = () => {
    $.ajax({
        url: "http://localhost:8080/Application_1_war_exploded/item",
        type: "GET",
        success: (response) => {
            console.log(response);

            const tableBody = $('#itemTableBody');
            tableBody.empty();

            response.forEach(item => {
                tableBody.append(`
               <tr>
                    <td>${item.item_id}</td>
                    <td>${item.item_name}</td>
                    <td>${item.item_price}</td>
                    <td>${item.item_qty}</td>
                </tr>
                `)
            });
        },
        error: (err) => {
            console.log(err);
        }
    });
    autoGenerateNextId();
    clearField();
}

const loadItemTable = () => {
    $('#loadAllItemsBtn').click((e) => {
        loadAllItem();
    });
}

const clearFieldItem = () => {
    $('#item_name').val("");
    $('#item_price').val("");
    $('#item_qty').val("");
    loadItemTable();
    loadAllItem();
    autoGenerateItemNextId();
}

/*============================================= Save Item==============================================================================*/
$('#addItemBtn').click(() =>{
    const id = $('#item_id').val();
    const name = $('#item_name').val();
    const price = $('#item_price').val();
    const qty = $('#item_qty').val();

    $.ajax({
        url: `http://localhost:8080/Application_1_war_exploded/item?item_id=${id}&item_name=${name}&item_price=${price}&item_qty=${qty}`,
        type: "POST",

        success: (res) => alert("Item saved successfully!"),
        error: (err) => alert("Item Saved not successfully!"),
    });
    clearFieldItem();
    loadItemTable();
    loadAllItem();
    autoGenerateItemNextId();

});

/*===========================================================================================================================*/
/*===========================================================================================================================*/
/*===========================================================================================================================*/
/*===========================================================================================================================*/
/*===========================================================================================================================*/
/*===========================================================================================================================*/
