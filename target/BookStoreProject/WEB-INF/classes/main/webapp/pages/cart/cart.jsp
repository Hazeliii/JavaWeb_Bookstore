<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>购物车</title>
	<%@ include file="/pages/shared/head.jsp"%>
	<script type="text/javascript">
		$(function (){
			$("#deleteItem").click(function (){
				return confirm("确定删除《"+$(this).parent().parent().find("td:first").text()+"》吗？");
			})

			$("#clearCart").click(function (){
				return confirm("确定清空购物车吗？(oﾟωﾟo)");
			})

			//onchange事件：内容发生改变时
			$(".updateCount").change(function (){
				var name = $(this).parent().parent().find("td:first").text();
				var itemId = $(this).attr("itemId");
				var count = this.value;
				if(confirm("确定修改《"+name+"》的数量修改为"+count+"?")){
					location.href="${basePath}cartServlet?action=updateItemCount&count="+count+"&itemId="+itemId;
				}else {
					//表单项dom对象的属性，标识默认的value值属性，此处的默认值为表单中value="xx"中的值
					this.value = this.defaultValue;
					return false;
				}

			})
		})
	</script>
</head>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">购物车</span>
		<%--静态包含--%>
		<%@ include file="/pages/shared/login_seccess_menu.jsp"%>
	</div>
	
	<div id="main">
	
		<table>
			<tr>
				<td>商品名称</td>
				<td>数量</td>
				<td>单价</td>
				<td>金额</td>
				<td>操作</td>
			</tr>
			<c:if test="${empty sessionScope.cart.items}">
				<tr>
					<td colspan="5"> <a href="index.jsp">当前购物车为空！快去选购商品吧 (•̀ᴗ•́)و </a></td>
				</tr>
			</c:if>

			<c:if test="${not empty sessionScope.cart.items}">
				<c:forEach items="${sessionScope.cart.items}" var="item">
					<tr>
						<td>${item.value.name}</td>
						<td>
							<input class="updateCount" style="width: 80px;"
								   itemId="${item.value.id}"
								   type="text" value="${item.value.count}">
						</td>
						<td>${item.value.unitPrice}</td>
						<td>${item.value.totalPrice}</td>
						<td><a id="deleteItem" href="${basePath}cartServlet?action=deleteItemInCart&id=${item.value.id}">删除</a></td>
					</tr>
				</c:forEach>
			</c:if>
		</table>

		<c:if test="${not empty sessionScope.cart.items}">
			<div class="cart_info">
				<span class="cart_span">购物车中共有<span class="b_count">${sessionScope.cart.totalCount}</span>件商品</span>
				<span class="cart_span">总金额<span class="b_price">${sessionScope.cart.totalPrice}</span>元</span>
				<span class="cart_span"><a id="clearCart" href="${basePath}cartServlet?action=clearCart">清空购物车</a></span>
				<span class="cart_span"><a href="orderServlet?action=createOrder">去结账</a></span>
			</div>
		</c:if>

	
	</div>
	<%@ include file="/pages/shared/footer.jsp"%>

</body>
</html>