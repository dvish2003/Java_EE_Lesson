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
            console.log("Fetched item:", response);

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

})

/*==================================================== Load All Item=======================================================================*/
$('#loadAllItemsBtn').click((e) => {
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
    autoGenerateItemNextId();
   clearFieldItem();

})


const clearFieldItem = () => {
    $('#item_name').val("");
    $('#item_price').val("");
    $('#item_qty').val("");
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
    autoGenerateItemNextId();
    clearFieldItem();
    /*loadItemTable();
    loadAllItem();*/

});



/*===========================================================================================================================*/
/*===========================================================================================================================*/
/*========================================================= update item==================================================================*/
$('#updateItemBtn').click(() =>{

    const id = $('#item_id').val();
    const name = $('#item_name').val();
    const price = $('#item_price').val();
    const qty = $('#item_qty').val();

    $.ajax({
        url: `http://localhost:8080/Application_1_war_exploded/item?item_id=${id}&item_name=${name}&item_price=${price}&item_qty=${qty}`,
        type: "PUT",

        success: (res) => alert("Item Update successfully!"),
        error: (err) => alert("Item Update not successfully!"),
    });
    autoGenerateItemNextId();
    clearFieldItem();
})

/*======================================================= delete item====================================================================*/
$('#deleteItemBtn').click(() => {
    const id = $('#item_id').val();

    $.ajax({
        url: `http://localhost:8080/Application_1_war_exploded/item?item_id=${id}`,
        type: "DELETE",
        success: (res) => alert("item deleted successfully!"),
        error: (err) => alert("item delete not successfully!"),

    });
    autoGenerateItemNextId();
    clearFieldItem();
});
/*===========================================================================================================================*/
$('#itemTableBody').on('click','tr',function (){
    let index = $(this).index();
    select_item_row = index;

    let id = $(this).find('td:eq(0)').text();
    let name = $(this).find('td:eq(1)').text();
    let price = $(this).find('td:eq(2)').text();
    let qty = $(this).find('td:eq(3)').text();



    $('#item_id').val(id);
    $('#item_name').val(name);
    $('#item_price').val(price);
    $('#item_qty').val(qty);

})


/*===========================================================================================================================*/
