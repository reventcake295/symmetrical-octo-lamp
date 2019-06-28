$(document).ready(function () {

	$(document).keyup(function (e) {
		code(e);
	}).on("swipeup", function () {
		fakeCode("U");
	}).on("swipedown", function () {
		fakeCode("D");
	}).on("swipeleft", function () {
		fakeCode("L");
	}).on("swiperight", function () {
		fakeCode("R");
	}).dblclick(function () {
		if (lastKeys.replace(/_/g, "").endsWith("UUDDLRLR")) {
			fakeCode("b");
			fakeCode("a");
		}
	});
	return;
});

function fakeCode(x, n) {
	if (n === undefined) {
		n = -1;
	}
	let e = {};
	e.key = x;
	e.keyCode = n;
	code(e);
	return;
}

function code(e) {
	let lastKey = e.key;
	switch (e.keyCode) {
		case 37:
		case 38:
		case 39:
		case 40:
			lastKey = e.key.replace("Arrow", "")[0];
			break;
	}
	lastKeys += lastKey;
	if (lastKeys.replace(/_/g, "").endsWith("UUDDLRLRba") || lastKeys.replace(/_/g, "").endsWith("wwssadadba")) {
		rickRoll();
		return;
	}
	if (lastKeys.replace(/_/g, "").endsWith("UUDDLRLRadmin")) {
		window.location.href = "admin/login";
		return;
	}

	if (lastKeys.replace(/_/g, "").endsWith("UUDDLRLRversion")) {
		alert("this website is build by Sander Tas and the version of the framework is: " + VERSION);
		return;
	}
	return;
}

let konami = {};
let lastKeys = "";
konami.running = false;

function rickRoll() {
	lastKeys = "";
	if (konami.running) {
		return;
	}
	konami.running = true;
	let audio = new Audio('media/sound/rickRoll.mp3');
	audio.play()
	audio.onended = function () {
		konami.running = false;
	};
	return;
}