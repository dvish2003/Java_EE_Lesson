let selectItemRow = null;

$(document).ready(function () {
    loadTable();
    loadAllItem();
})

const clearField = () =>{
    $('#name').val("");
    $('#price').val("");
    $('#qtyOnHand').val("");
}

/*Load All Customer*/

const loadAllItem = () =>{
    $.ajax({
        url: "http://localhost:8080/Application1_war_exploded/item",
        type: "GET",
        success : (response) => {
            console.log(response);
            const tableBody = $('#itemTableBody');
            tableBody.empty();

            response.forEach(item =>{
                tableBody.append(`
               <tr>
                    <td>${item.id}</td>
                    <td>${item.name}</td>
                    <td>${item.price}</td>
                    <td>${item.qtyOnHand}</td>
                </tr>
                `);
            });
        },
        error : (err) => {
            console.log(err);
        }
    });

    clearField();
}

const loadTable = () => {
    $('#loadAllItems').click((e) => {
        loadAllItem();
    });
}

/*save item*/
$('#saveItems').click(() => {
    const id = $('#id').val();
    const name = $('#name').val();
    const price = $('#price').val();
    const qtyOnHand = $('#qtyOnHand').val();

    $.ajax({
        url : "http://localhost:8080/Application1_war_exploded/item",
        type : "POST",
        data : {id,name,price,qtyOnHand},
        success: (res) => alert("Item saved successfully!"),
        error: (err) => alert("Item Saved not successfully!")
    });
    clearField();
    loadTable();
    loadAllItem();
});

/*Update Item*/
$('#updateItems').click(() => {
    const id = $('#itemId').val();
    const name = $('#itemName').val();
    const price = $('#price').val();
    const qtyOnHand = $('#qtyOnHand').val();

    $.ajax({
        url : `http://localhost:8080/Application1_war_exploded/item?id=${id}&name=${name}&price=${price}&qtyOnHand=${qtyOnHand}`,
        type : "PUT",
        success: (res) => alert("Item Update successfully!"),
        error: (err) => alert("Item Update not successfully!")
    });
    clearField();
    loadTable();
    loadAllItem();
})

/*Delete Item*/
$('#deleteItems').click(() => {
    const id = $('#id').val();

    $.ajax({
        url: "http://localhost:8080/Application1_war_exploded/item?id=${id}",
        type: "DELETE",
        success: (res) => alert("Item deleted successfully!"),
        error: (err) => alert("Item delete not successfully!"),

    });
    clearField();
    loadTable();
    loadAllItem();
});

$('#itemTableBody').on('click','tr',function (){
    let index = $(this).index();
    selectItemRow = index;

    let id = $(this).find('td:eq(0)').text();
    let name = $(this).find('td:eq(1)').text();
    let price = $(this).find('td:eq(2)').text();
    let qtyOnHand = $(this).find('td:eq(3)').text();

    $('#id').val(id);
    $('#name').val(name);
    $('#price').val(price);
    $('#qtyOnHand').val(qtyOnHand);
})









