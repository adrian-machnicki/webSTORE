function addAuthor() {
	form = document.getElementById('addBookForm');
	form.action = 'add/addAuthor';
	form.submit();
	return false;
}

function submitLogout() {
	form = document.getElementById('logoutForm');
	form.submit();
	return false;
}