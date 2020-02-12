function fill(s) {
	document.getElementById('sel').style.background = "red";
	document.getElementById('sel').innerHTML = "wwewwe";
}

function sum(idA, idB, idC) {
	a = document.getElementById(idA).value;
	b = document.getElementById(idB).value;
	let c = Number(a) + Number(b);
	document.getElementById(idC).value = c;
}
