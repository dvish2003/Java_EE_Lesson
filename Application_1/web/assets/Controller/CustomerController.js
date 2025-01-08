let selectCustomerRow = null;

$(document).ready(function () {
    loadTable();
    loadAllCustomers();
})

const clearField = () =>{
    $('#name').val("");
    $('#address').val("");
}

/*Load All Customer*/

const loadAllCustomers = () =>{
    $.ajax({
        url: "http://localhost:8080/Application1_war_exploded/customer",
        type: "GET",
        success : (res) => {
            console.log(res);
            const tableBody = $('#customerTableBody');
            tableBody.empty();

            res.forEach(customer =>{
                tableBody.append(`
               <tr>
                    <td>${customer.id}</td>
                    <td>${customer.name}</td>
                    <td>${customer.address}</td>
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
    $('#loadAllCustomers').click((e) => {
        loadAllCustomers();
    });
}

/*save customer*/
$('#saveCustomer').click(() => {
    const id = $('#id').val();
    const name = $('#name').val();
    const address = $('#address').val();

    $.ajax({
        url : "http://localhost:8080/Application1_war_exploded/customer",
        type : "POST",
        data : {id,name,address},
        success: (res) => alert("Customer saved successfully!"),
        error: (err) => alert("Customer Saved not successfully!")
    });
    clearField();
    loadTable();
    loadAllCustomers();
});

/*Update Customer*/
$('#updateCustomer').click(() => {
    const id = $('#id').val();
    const name = $('#name').val();
    const address = $('#address').val();

    $.ajax({
        url :`http://localhost:8080/Application1_war_exploded/customer? id = ${id} & name = ${name} & address = ${address}`,
        type : "PUT",
        contentType : "application/json",
        /*data : JSON .stringify({
            id,
            name,
            address
        }),*/
        success: (res) => alert("Customer Update successfully!"),
        error: (err) => alert("Customer Update not successfully!")
    });
    clearField();
    loadTable();
    loadAllCustomers();
})

/*Delete Customer*/
$('#deleteCustomer').click(() => {
    const id = $('#id').val();

    $.ajax({
        url: `http://localhost:8080/Application1_war_exploded/customer ? id =${id}`,
        type: "DELETE",
        success: (res) => alert("Customer deleted successfully!"),
        error: (err) => alert("Customer delete not successfully!"),

    });
    clearField();
    loadTable();
    loadAllCustomers();
});

$('#customerTableBody').on('click','tr',function (){
    let index = $(this).index();
    selectCustomerRow = index;

    let id = $(this).find('td:eq(0)').text();
    let name = $(this).find('td:eq(1)').text();
    let address = $(this).find('td:eq(2)').text();

    $('#id').val(id);
    $('#name').val(name);
    $('#address').val(address);

})









