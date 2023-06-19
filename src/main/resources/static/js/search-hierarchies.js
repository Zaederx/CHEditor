
var inputFieldId = "#name";
var messageInfoId = "";
var inputURL = "";//url used for field validation
var address = ""; //url + inputFieldId
var response = "";//class data retrieved from server
var switchURL = "";//used to ajax request for tree data
var formId = "";//which form is curretly onfocus
var subBtn = ""; //submit Btn selection

function setMessageInfoId(id) {
	console.log("********** setMessageInfoId called **********");
	console.log(id);
	messageInfoId = '#'+id;
}
function getClassDetails() {
	console.log("********** getClassDetails**********");
	var name = "";
	if (formId == 'form-name') {
		name = 'name/';
	} 
	else {
		name = "";
	}
	var formArray = $('#'+formId).serializeArray();
	var formInput = formArray[0].value;
	$.ajax({
		type:"GET",
		url: "/cheditor/api/getclass/"+name+formInput,
		success: function (data) {
			var str = JSON.stringify(data);
			var res = JSON.parse(str);
			var ret = res.ret;
			if (ret == 'false') {
				$(messageInfoId).text(res.message);
				$(messageInfofoId).show();
			}
			else {
				displayClassDetails(data);
			 	$(messageInfoId).text("");
				$(messageInfoId).hide();
			}
		}
	});
	
}

function displayClassDetails(data) {
	console.log("*******displayClassDetails called*****");
	
	var str = JSON.stringify(data);
	var d = str.replace('abstract','_abstract');
	var classJson = JSON.parse(d);
	$('#className').text(classJson.name);
	$('#classId').text(classJson.cid);
	$('#abstract').text(classJson._abstract);
	$('#parentClassId').text(classJson.pid);
	$('#classDetails').show();
	
}
function requestTreeData() {
	console.log("*******REQUESTING TREE DATA*****");
	
	$.ajax({
		type:"GET",
		url:"/cheditor/api/browse/true",
		success:getData
		});
	
}

function setIdSwitch(state) {
	console.log('******set Switch status called******');
	if (state == 'checked') {
		if (!document.getElementById('cidSwitch').checked) {
			$('#cidSwitch').click();
			console.log('added checked');
		}
	}
	else {
		if (document.getElementById('cidSwitch').checked) {
			$('#cidSwitch').click();
			console.log('removed checked');
		}
	}
}

//Display cid on jqTree Hierarchy
function showCid() {
	console.log('*********showCid called*********');
	if (switchURL == "") {
		if (document.getElementById('cidSwitch').checked) {
			requestTreeData();
		}
		else {
			$.ajax({
				type:"GET",
				url:"/cheditor/api/browse/",
				success:getData
				});
		}
	} else {
	requestTreeDataUrl(switchURL);
	}
}

function requestTreeDataUrl(url) {
	console.log("*******REQUESTING TREE DATA*****");
	if (document.getElementById('cidSwitch').checked) {
		$.ajax({
			type:"GET",
			url:url+'&d=true',
			success:getData
			});
	} else {
		$.ajax({
			type:"GET",
			url:url+'&d=false',
			success:getData
			});
	}
	
}


function setBtn (btnId) {
	console.log("********* setBtn called **********");
	subBtn = btnId;
}

function setFormId (id) {
	console.log("********* setFormId called **********");
	console.log('formId = '+ id );
	formId = id;
}

/* function requestTreeData() {
	console.log("*******REQUESTING TREE DATA*****");
	if (document.getElementById('cidSwitch').checked) {
	
	}
	
} */

/*Calls method necessary to prepare data
 * for use with jqTree, in order to 
 crate hierarchy*/
function getData(data) {
	console.log("*******getData called****")
 	var str = JSON.stringify(data);
	console.log("JSON:"+str);
	response = parseFieldsToJQTree(str);

	console.log('Response'+response);
	getTree();
}

/* Replaces cid and superclass with text id and children
in order to match reqiured jqTree data structure */
function parseFieldsToJQTree(str) {
	console.log("********parseFieldsToJQTree called********");
	var d = str.replace(/cid/g,'id');
	d = d.replace(/superclassOf/g,'children');
	console.log("jqTreeJSON:"+d);
	d = JSON.parse(d);
	return d;
}

/*Creates jqTree*/
function getTree(){
	console.log("******getTree() called******");
	 $('#tree').tree('destroy');
	$('#tree').tree({
		data:response,
		autoOpen: true,
		dragAndDrop:true,
		saveState:false,
		selectable: true,
	 	onCanSelectNode: function (node) {
			if (node.children.length == 0){
				return true;
			}
			return false;
		}
		
	});
}




function setInput(id){
	console.log("******setInput called******");
	console.log("inputId = "+ id);
	inputFieldId = "#"+id;
	address = "/cheditor/api/isValid/search/"+id+"/";
}

function setURL(){
	inputURL = address + $(inputFieldId).val();
}

/*Called by submit button
 * 
 * Posts Form */
function searchClass() {

	console.log("******searchClass called******");
	var form = $('#'+formId).serialize();
	var url = "/cheditor/api/search?"+form;
	$.ajax({
		type:"GET",
		url: url,
		success: function(data) {
			/* $('#tree').tree('destroy'); */
			/* requestTreeData(); */
			var str = JSON.stringify(data);
			var res = JSON.parse(str);
			if (res.ret == 'false') {
				$(messageInfoId).show();
				$(messageInfoId).text(res.message);
			} else {
				console.log("*****search class else statement called*****");
				requestTreeDataUrl(url);
				switchURL = url;
			}
			
		}
	});
	
	document.getElementById(formId).reset();
}





$(document).ready(validateForm());

function validateForm () {
	/* Called once at start */
	requestTreeData();
	$('#classDetails').hide();
 	$('#messageInfo').hide();
 	$('#messageInfo-two').hide();
 	
 	/* Called repeatedly on input */
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
					$('#'+subBtn).attr("disabled", 'true');
					$('#'+subBtn).removeClass("btn-primary");
					$('#'+subBtn).addClass("btn-secondary");
					$(messageInfoId).text(res.message);
					$(messageInfoId).show();
				} else {
					$('#'+subBtn).removeAttr("disabled");
					$('#'+subBtn).removeClass("btn-secondary");
					$('#'+subBtn).addClass("btn-primary");
				 	$(messageInfoId).text("");
					$(messageInfoId).hide();
				}
			}
		});
		
	})
}
