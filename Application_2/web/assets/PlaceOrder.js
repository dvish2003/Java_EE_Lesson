
let orderItem_array = [];

$(document).ready(() => {
    loadCustomerIds();
    loadItemIds();
    autoGenerateNextOrderId();
    autoGenerateNextpayId();
});

/*===========================================================================================================*/
const generateOrderNextId = (orders) => {
    let maxId = 0;
    orders.forEach(order => {
        const numericPart = parseInt(order.orderId.substring(1));
        if (numericPart > maxId) {
            maxId = numericPart;
        }
    });

    const nextNumericId = maxId + 1;
    return `O${nextNumericId.toString().padStart(3, '0')}`;
};

const autoGenerateNextOrderId = () => {
    $.ajax({
        url: "http://localhost:8080/Application_1_war_exploded/orders",
        type: "GET",
        success: (response) => {
            console.log("Fetched Order:", response);

            let nextId = "O001";
            if (response.length > 0) {
                nextId = generateOrderNextId(response);
            }

            $('#orderId').val(nextId);
        },
        error: (err) => {
            console.error("Error fetching Order:", err);
        }
    });
};
/*===========================================================================================================*/
const generatePaytNextId = (payments) => {
    let maxId = 0;
    payments.forEach(payment => {
        const numericPart = parseInt(payment.paymentId.substring(1)); // Extract numeric part
        if (numericPart > maxId) {
            maxId = numericPart;
        }
    });

    const nextNumericId = maxId + 1;
    return `P${nextNumericId.toString().padStart(3, '0')}`;
};

const autoGenerateNextpayId = () => {
    $.ajax({
        url: "http://localhost:8080/Application_1_war_exploded/payment",
        type: "GET",
        success: (response) => {
            console.log("Fetched payment:", response);

            let nextId = "P001";
            if (response.length > 0) {
                nextId = generateOrderNextId(response);
            }

            $('#paymentId').val(nextId);
        },
        error: (err) => {
            console.error("Error fetching payment:", err);
        }
    });
};


/*===========================================================================================================*/

/*===========================================================================================================*/
    const loadCustomerIds = () => {
        $.ajax({
            url: "http://localhost:8080/Application_1_war_exploded/customer",
            type: "GET",
            success: (response) => {
                const customerSelect = $('#customerId');
                customerSelect.empty();
                customerSelect.append('<option selected disabled>Select Customer ID</option>');

                response.forEach(customer => {
                    customerSelect.append(`<option value="${customer.id}">${customer.id}</option>`);
                });

                customerSelect.on('change', () => {
                    const selectedCustomerId = customerSelect.val();
                    const selectedCustomer = response.find(c => c.id === selectedCustomerId);
                    if (selectedCustomer) {
                        $('#customerName').val(selectedCustomer.name);
                    }
                });
            },
                error: (err) => alert("not found customer")

        });
    };


/*===========================================================================================================*/

    const loadItemIds = () => {
        $.ajax({
            url: "http://localhost:8080/Application_1_war_exploded/item",
            type: "GET",
            success: (response) => {
                const itemSelect = $('#itemId');
                itemSelect.empty();
                itemSelect.append('<option selected disabled>Select Item ID</option>');

                response.forEach(item => {
                    itemSelect.append(`<option value="${item.item_id}">${item.item_id}</option>`);
                });

                itemSelect.on('change', () => {
                    const selectedItemId = itemSelect.val();
                    const selectedItem = response.find(i => i.item_id === selectedItemId);
                    if (selectedItem) {
                        $('#itemName').val(selectedItem.item_name);
                        $('#itemPrice').val(selectedItem.item_price);
                        $('#availableQty').val(selectedItem.item_qty);
                    }
                });
            },
                error: (err) => alert("not found item")

        });
    };

/*=========================================================  save ==================================================*/
$("#addItemButton").on('click', function() {
    const orderID = $('#orderId').val();
    const PaymentID = $('#paymentId').val();
    const CustomerID = $('#customerId').val();
    const itemID = $('#itemId').val();
    const ItemPrice = $('#itemPrice').val();
    const ItemQty = $('#availableQty').val();
    const BuyingQty = $('#buyingQty').val();
    const total = $('#total').val();

    if (BuyingQty > ItemQty){
        Swal.fire({
            icon: "error",
            title: "Oops...",
            text: "Not enough quantity!",
        });
    }else if(isNaN(ItemPrice) || ItemPrice <= 0){
        Swal.fire({
            icon: "error",
            title: "Oops...",
            text: "Invalid  Price!",
        });
    }else if(isNaN(BuyingQty) || BuyingQty <= 0){
        Swal.fire({
            icon: "error",
            title: "Oops...",
            text: "Invalid  buy Qty!",
        });
    }else if(isNaN(ItemQty) || ItemQty <= 0){
        Swal.fire({
            icon: "error",
            title: "Oops...",
            text: "Invalid  Available Qty!",
        });
    }else if(isNaN(total) || total <= 0){
        Swal.fire({
            icon: "error",
            title: "Oops...",
            text: "Invalid  total!",
        });
    }


});

