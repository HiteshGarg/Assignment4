//function to display Popup
//http://jsfiddle.net/bTNV5/


//document.getElementById('upload_image').addEventListener('change', checkFile, false);
//
//function checkFileUploads(element) {
//	alert("hello");
//	element.value = "";
//}

var extensions = new Array("jpg", "jpeg", "png");
function showPopUp(id) {
	document.getElementById('abc').style.display = "block";
	document.getElementById('hidden_id').value = id;
}

function hidePopUp() {
	document.getElementById('abc').style.display = "none";
}
function validateInput() {
	var name = document.getElementById('name').value;
	var file = document.getElementById('file_edit').value;
	var submit = true;
	if (name == "" && file == "") {
		document.getElementById('error_msg').innerHTML = "Please enter atleast one field";
		submit = false;
	}
	if (file != "" && !validateFileExtension(file)) {
		document.getElementById('error_msg').innerHTML = "Please enter the image..";
		submit = false;
	}
	if (submit) {
		document.getElementById('form_edit').submit();
	}
}

function validateFileExtension(file) {
	var flag = false;
	var file_extension = file.substring(file.lastIndexOf('.') + 1);
	for (var i = 0; i < extensions.length; i++) {
		if (file_extension.toLowerCase() == extensions[i].toLowerCase()) {
			flag = true;

			break;
		}
	}
	return flag;
}