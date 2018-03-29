function addAuthor() {
	var form = document.getElementById('addBookForm');
	form.action = 'add/addAuthor';
	form.submit();
	return false;
}

function submitLogout() {
	var form = document.getElementById('logoutForm');
	form.submit();
	return false;
}

function changeQuantity(bookId, quantity) {
	var url = '/cart/updateQuantity?bookId=' + bookId + '&quantity=' + quantity;
	window.location.href = url;
}

function submitSetPaid(id) {
	var form = document.getElementById('setPaidLink' + id);
	form.submit();
	return false;
}

function submitSetSent(id) {
	var form = document.getElementById('setSentLink' + id);
	form.submit();
	return false;
}