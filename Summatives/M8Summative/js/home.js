var funds = 0;
$(document).ready(function () {
	loadInventory();
	addFunds();
	makePurchase();
	changeReturn();
});

function loadInventory() {
	var inventory = $('#inventory');
    inventory.empty();
	$.ajax({
        type: 'GET',
        url: 'http://tsg-vending.herokuapp.com/items',
        success: function(itemArray) {
			$.each(itemArray, function(index, item){
				var id = item.id;
				var name = item.name;
				var price = '$' + item.price.toFixed(2);
				var quantity = 'Quantity Left: ' + item.quantity;
				
				var box = '<div class="col-md-3 m-2 border">';
					box += '<button type="button" class="btn" onclick="updateItemField(' + id + ')">';
					box += '<p>' + id + '</p>';
					box += '<div class="text-center">';
					box += '<p>' + name + '<br>' + price + '<br><br>' + quantity + '</p>'
					box += '</div></button></div>'
				inventory.append(box);
			})
        },
        error: function() {
			$('#messageDisplay').text('Failure to connect to web service.');
        }
    })
}

function addFunds() {
	var fundsDisplay = $('#fundsDisplay');
	$('#addDollar').click(function () {
		clearFields();
		$('#changeReturn').show();
		funds += 1;
		fundsDisplay.val(funds.toFixed(2));
	});
	$('#addQuarter').click(function () {
		clearFields();
		$('#changeReturn').show();
		funds += .25;
		fundsDisplay.val(funds.toFixed(2));
	});
	$('#addDime').click(function () {
		clearFields();
		$('#changeReturn').show();
		funds += .1;
		fundsDisplay.val(funds.toFixed(2));
	});
	$('#addNickel').click(function () {		
		clearFields();
		$('#changeReturn').show();
		funds += .05;
		fundsDisplay.val(funds.toFixed(2));
	});
	
}

function makePurchase() {
	$('#makePurchaseButton').click(function(event) {
		$.ajax({
			type: 'POST',
			url: 'http://tsg-vending.herokuapp.com/money/' + $('#fundsDisplay').val() + '/item/' + $('#itemNumber').val(),
			success: function(data, status) {
				clearFields();
				$('#fundsDisplay').val('0.00');
				funds = 0;
				$('#messageDisplay').val('Thank You!');
				var change = '';
				if(data.quarters == 1) {
					change += data.quarters + ' Quarter ';
				}
				if(data.quarters > 1) {
					change += data.quarters + ' Quarters ';
				}
				if(data.dimes == 1) {
					change += data.dimes + ' Dime ';
				}
				if(data.dimes > 1) {
					change += data.dimes + ' Dimes ';
				}
				if(data.nickels == 1) {
					change += data.nickels + ' Nickel ';
				}
				if(data.nickels > 1) {
					change += data.nickels + ' Nickels ';
				}
				if(data.pennies == 1) {
					change += data.pennies + ' Penny';
				}
				if(data.pennies > 1) {
					change += data.pennies + ' Pennies';
				}
				$('#changeDisplay').val(change);
				$('#changeReturn').hide();
				loadInventory();
			},
			error: function(jqXHR, ajaxOptions, thrownError) {
				var json = JSON.parse(jqXHR.responseText);
				$('#messageDisplay').val('');
				
				$('#messageDisplay').val(json.message);
				loadInventory();
			}
		});
	});
}

function updateItemField(id){
	clearFields();
	$('#itemNumber').val(id);
}

function clearFields(){
	$('#messageDisplay').val('');
	$('#itemNumber').val('');
	$('#changeDisplay').val('');
}

function changeReturn(){
	$('#changeReturn').click(function () {
		clearFields();
		loadInventory();
		var total = $('#fundsDisplay').val();
		var change = '';
		var quarters = Math.floor(total / .25);
		total = total - (quarters * .25);
		var dimes = Math.floor(total /.1);
		total = total - (dimes * .1);
		var nickels = Math.round(total /.05);
		if(quarters == 1) {
			change += quarters + ' Quarter ';
		}
		if(quarters > 1) {
			change += quarters + ' Quarters ';
		}
		if(dimes == 1) {
			change += dimes + ' Dime ';
		}
		if(dimes > 1) {
			change += dimes + ' Dimes ';
		}
		if(nickels == 1) {
			change += nickels + ' Nickel ';
		}
		if(nickels > 1) {
			change += nickels + ' Nickels ';
		}
		$('#changeDisplay').val(change);
		$('#changeReturn').hide();
		$('#fundsDisplay').val('0.00');
		funds = 0;
		$('#messageDisplay').val('Transaction Cancelled');
	});
}