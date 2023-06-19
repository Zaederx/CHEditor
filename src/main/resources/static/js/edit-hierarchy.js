
var inputFieldId = "#name";
var switchURL = "/cheditor/api/browse";
var inputURL = "";
var address = "";
var response = "";//class data retireved from server
var toDelete;
var toDeleteList = [];
var switchState = 0;

function showCid() {
	console.log('*********showCid called*********');
	requestTreeData();
}

function requestTreeData() {
	console.log("*******REQUESTING TREE DATA*****");
	if (document.getElementById('cidSwitch').checked) {
	$.ajax({
		type:"GET",
		url:"/cheditor/api/browse/true",
		success:getData
		});
	} else {
		$.ajax({
			type:"GET",
			url:"/cheditor/api/browse",
			success:getData
			});
	}
	
}

function getTree(){
	console.log("******getTree() called******");
	$('#tree').tree('destroy');
	/* $('#cidSwitch').bootstrapToggle('off'); */
	$('#tree').tree({
		data:response,
		autoOpen: true,
		dragAndDrop:true,
		saveState:true,
		selectable: true,
	 	onCanSelectNode: function (node) {
			if (node.children.length == 0){
				return true;
			}
			return false;
		}
		
	});
}


function getData(data) {
	console.log("*******getData called****")
 	var str = JSON.stringify(data);
	console.log("JSON:"+str);
	response = parseFieldsToJQTree(str);

	console.log('Response'+response);
	getTree();
}

function parseFieldsToJQTree(data) {
	console.log("********parseFieldsToJQTree called********");
	var d = data.replace(/cid/g,'id');
	d = d.replace(/superclassOf/g,'children');
	console.log("jqTreeJSON:"+d);
	d = JSON.parse(d);
	return d;
}

function setInput(id){
	console.log("******setInput called******");
	console.log("inputId = "+ id);
	inputFieldId = "#"+id;
	address = "/cheditor/api/isValid/"+id+"/edit/";
}

function setURL(){
	inputURL = address + $(inputFieldId).val();
}

/*Called by submit button
 * 
 * Posts Form */
function editClass() {
	/* $(function(){ $('#cidSwitch').bootstrapToggle('off') }); */
	console.log("******editClass called******");
	var form = $('#classForm').serialize();
	var url = "/cheditor/api/editclass?"+form;
	$.ajax({
		type:"GET",
		url: url,
		success: function(data) {
			$('#tree').tree('destroy');
			requestTreeData();
			var str = JSON.stringify(data);
			var res = JSON.parse(str);
			if (res.ret == 'true') {
				$('#messageInfo').show();
				$('#messageInfo').text("Edited Class");
			}
			
		}
	});
	
	document.getElementById('classForm').reset();
	/* $('cidSwitch').removeCSS */
}

$(document).ready(validateForm());

function validateForm () {
	requestTreeData();
 	$('#messageInfo').hide();
	$(':input').keyup(function() {
		console.log("******validateForm called*******");
		setURL();
		$('#messageInfo').hide();
		$.ajax({
			type: "GET",
			url: inputURL,
			success: function (result) {
				var str = JSON.stringify(result);
				var res = JSON.parse(str);
				var ret = res.ret;
				if (ret == 'false') {
					$('#submit').attr("disabled", 'true');
					$('#submit').removeClass("btn-primary");
					$('#submit').addClass("btn-secondary");
					$('#messageInfo').text(res.message);
					$('#messageInfo').show();
				} else {
					$('#submit').removeAttr("disabled");
					$('#submit').removeClass("btn-secondary");
					$('#submit').addClass("btn-primary");
				 	$('#messageInfo').text("");
					$('#messageInfo').hide();
				}
			}
		});
	})
}
