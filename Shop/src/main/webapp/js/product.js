/**
 * 
 */
 
 function product(prod){
	
	let prodNo = prod.eq(0).text();
	
	$(function(){
		$('h4').empty();
		let h4 = "<h4>주문하기</h4>";
		$('h4').append(h4);
		
		$('section').empty();
		
		let table = "<table border='1'>";
			table += "<tr>";
			table += "<td>상품번호</td>";
			table += "<td><input type='text' name='prodNo' value='"+prodNo+"'></td>";
			table += "</tr>";
			table += "<tr>";
			table += "<td>수량</td>";
			table += "<td><input type='text' name='orderCount'></td>";
			table += "</tr>";
			table += "<tr>";
			table += "<td>주문자</td>";
			table += "<td><input type='text' name='uid' ></td>";
			table += "</tr>";
			table += "<tr>";
			table += "<td colspan='2' align='right'><input type='submit' id='btnOrder' value='주문하기'></td>";
			table += "</tr>";
			table += "</table>";
			
		$('section').append(table);
	});
}
