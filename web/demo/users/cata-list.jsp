<%-- 
    Document   : cata-list
    Created on : 2013-10-12, 6:14:00
    Author     : citysky
--%>

<%@page contentType="text/html" pageEncoding="GBK"%>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="gbk">
		<title>jQuery UI Draggable Demos</title>
	</head>
	<body>
		<script>
			var rows = [{'id': 3, 'cell': [3, '100912', '2010-09-29T19:05:32', '44321', '0.1', 14573.20, 'Nothing']},
				{'id': 1, 'cell': [1, '100913', '2010-09-28T21:49:21', '51234', '0.1', 21543.50, '']}];
			$().ready(function() {
				$("#list").jqGrid({
					//url: "example.php",
					datatype: "local",
					//mtype: "GET",
					colNames: ["Inv No", "Date", "Amount", "Tax", "Total", "Notes"],
					colModel: [
						{name: "invid", width: 55},
						{name: "invdate", width: 90},
						{name: "amount", width: 80, align: "right"},
						{name: "tax", width: 80, align: "right"},
						{name: "total", width: 80, align: "right"},
						{name: "note", width: 150, sortable: false}
					],
					pager: "#pager",
					rowNum: 10,
					rowList: [10, 20, 30],
					sortname: "invid",
					sortorder: "desc",
					viewrecords: true,
					gridview: true,
					//autoencode: true,
					caption: "My first grid"
				});
				for (var i = 0; i <= rows.length; i++)
					$("#list").jqGrid('addRowData', i + 1, rows[i]);
			});
		</script>
		<table id="list"><tr><td></td></tr></table> 
		<div id="pager"></div> 
	</body>
</html>

