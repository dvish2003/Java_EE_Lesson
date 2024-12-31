let select_customer_row = null;

/*================================Generate id==================================*/
const generateNextId = (customers) => {
    let maxId = 0;
    customers.forEach(customer => {
        const numericPart = parseInt(customer.id.substring(1)); // Extract numeric part
        if (numericPart > maxId) {
            maxId = numericPart;
        }
    });

    const nextNumericId = maxId + 1;
    return `C${nextNumericId.toString().padStart(3, '0')}`; // Format as C001, C002, etc.
};

const autoGenerateNextId = () => {
    $.ajax({
        url: "http://localhost:8080/Application_1_war_exploded/customer",
        type: "GET",
        success: (response) => {
            console.log("Fetched customers:", response);

            let nextId = "C001";
            if (response.length > 0) {
                nextId = generateNextId(response);
            }

            $('#id').val(nextId);
        },
        error: (err) => {
            console.error("Error fetching customers:", err);
        }
    });
};


/*==================================================================================================*/
$(document).ready(function (){
    autoGenerateNextId();
    loadTable();
    loadAllCustomers();
    autoGenerateItemNextId();
/*
    loadItemTable();
*/
/*
    loadAllItem();
*/
})

const clearField = () => {
    $('#name').val("");
    $('#address').val("");
}

/*========================================= Load All Customer ======================================*/
const loadAllCustomers = () => {
    $.ajax({
        url: "http://localhost:8080/Application_1_war_exploded/customer",
        type: "GET",
        success: (response) => {
            console.log(response);

            const tableBody = $('#customerTableBody');
            tableBody.empty();

            response.forEach(customer => {
                tableBody.append(`
                <tr>
                    <td>${customer.id}</td>
                    <td>${customer.name}</td>
                    <td>${customer.address}</td>
                </tr>
                `);
            });
        },
        error: (err) => {
            console.log(err);
        }
    });
    autoGenerateNextId();
    clearField();
}

/*========================================= Load Table Function ==================================*/
const loadTable = () => {
    $('#loadAllCustomers').click((e) => {
        loadAllCustomers();
    });
}


/*============================================save Customer==============================================*/

$('#saveCustomer').click(() => {
    const id = $('#id').val();
    const name = $('#name').val();
    const address = $('#address').val();

    $.ajax({
        url: "http://localhost:8080/Application_1_war_exploded/customer",
        type: "POST",
        data: {id, name, address},
        success: (res) => alert("Customer saved successfully!"),
        error: (err) => alert("Customer Saved not successfully!"),

    });
    autoGenerateNextId();
    clearField();
    loadTable();
    loadAllCustomers();
});

/*========================================= Update ============================================================*/
$('#updateCustomer').click(() =>{

    const id = $('#id').val();
    const name = $('#name').val();
    const address = $('#address').val();

    $.ajax({
        url: `http://localhost:8080/Application_1_war_exploded/customer?id=${id}&name=${name}&address=${address}`,
        type: "PUT",
        /*
                    data: {id, name, address},
        */
        success: (res) => alert("Customer Update successfully!"),
        error: (err) => alert("Customer Update not successfully!"),
    });
    autoGenerateNextId();
    clearField();
    loadTable();
    loadAllCustomers();
})
/*============================================delete======================================*/
$('#deleteCustomer').click(() => {
    const id = $('#id').val();

    $.ajax({
        url: `http://localhost:8080/Application_1_war_exploded/customer?id=${id}`,
        type: "DELETE",
        success: (res) => alert("Customer deleted successfully!"),
        error: (err) => alert("Customer delete not successfully!"),

    });
    autoGenerateNextId();
    clearField();
    loadTable();
    loadAllCustomers();
});
/*=============================================row select======================================*/
$('#customerTableBody').on('click','tr',function (){
    let index = $(this).index();
    select_customer_row = index;

    let id = $(this).find('td:eq(0)').text();
    let name = $(this).find('td:eq(1)').text();
    let address = $(this).find('td:eq(2)').text();



    $('#id').val(id);
    $('#name').val(name);
    $('#address').val(address);

})
/*===========================================================================================================*/
