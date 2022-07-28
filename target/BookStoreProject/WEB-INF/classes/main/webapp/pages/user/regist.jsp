<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>BookTime注册页面</title>
	<%@ include file="/pages/shared/head.jsp"%>
<style type="text/css">
	.login_form{
		height:420px;
		margin-top: 25px;
	}
</style>

	<script type="text/javascript">

		$(function (){

			/**
			 * //验证注册信息是否有误
			 * 验证用户名：必须由字母，数字下划线组成，并且长度为 5 到 12 位
			 * 验证密码：必须由字母，数字下划线组成，并且长度为 5 到 12 位
			 * 验证确认密码：和密码相同
			 * 邮箱验证：xxxxx@xxx.com
			 * 验证码：现在只需要验证用户已输入。因为还没讲到服务器。验证码生成
			 */

			$("#sub_btn").click(function (event){
				var $username = $("#username").val();
				var usernamePatt = /^\w{5,12}$/
				if(!usernamePatt.test($username)){
					$("span.errorMsg").text("用户名不合法!")
					return false
				}

				var $password = $("#password").val();
				if(!usernamePatt.test($password)){
					$("span.errorMsg").text("密码不合法!")
					return false
				}

				var $repwd = $('#repwd').val();
				if($repwd!=$password){
					$("span.errorMsg").text("两次输入密码不一致!")
					return false
				}

				var $email = $("#email").val();
				var emailPatt = /^[a-z\d]+(\.[a-z\d]+)*@([\da-z](-[\da-z])?)+(\.{1,2}[a-z]+)+$/
				if(!emailPatt.test($email)){
					$("span.errorMsg").text("输入邮箱不合法!")
					return false
				}

				var codeText = $.trim($("#code").val());
				if(codeText==null||codeText==""){
					$("span.errorMsg").text("验证码有误!")
					return false
				}

				$("span.errorMsg").text("")
				//给验证码的图片绑定单击事件，更换验证码
			})

			$("#code_img").click(function (){
				this.src = "${basePath}kaptcha.jpg?d=" + new Date()
			})

		})
	</script>
</head>


<body>
		<div id="login_header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
		</div>
		
			<div class="login_banner">
			
				<div id="l_content">
					<span class="login_word">欢迎注册</span>
				</div>
				
				<div id="content">
					<div class="login_form">
						<div class="login_box">
							<div class="tit">
								<h1>注册BookTime会员</h1>
								<span class="errorMsg">
<%--									<%=request.getAttribute("msg")==null?"":request.getAttribute("msg")%>--%>
									${ requestScope.msg }
								</span>
							</div>
							<div class="form">
								<form action="userServlet" method="post">
									<input type="hidden" name="action" value="register"/>
									<label>用户名称：</label>
									<input class="itxt" type="text" placeholder="请输入用户名"
										   value="${requestScope.username}"
										   autocomplete="off"
										   tabindex="1" name="username" id="username"/>
									<br />
									<br />
									<label>用户密码：</label>
									<input class="itxt" type="password" placeholder="请输入密码" autocomplete="off"
										   tabindex="1" name="password" id="password" />
									<br />
									<br />
									<label>确认密码：</label>
									<input class="itxt" type="password" placeholder="确认密码" autocomplete="off"
										   tabindex="1" name="repwd" id="repwd" />
									<br />
									<br />
									<label>电子邮件：</label>
									<input class="itxt" type="text" placeholder="请输入邮箱地址"
										   value="${requestScope.email}"
										   autocomplete="off"
										   tabindex="1" name="email" id="email" />
									<br />
									<br />
									<label>验证码：</label>
									<input class="itxt" type="text" style="width: 80px;" id="code" name="code"/>
									<img id="code_img" alt="" src="kaptcha.jpg" style="float: right;
									margin-right: 40px; width: 100px; height: 35px;">
									<br />
									<br />
									<input type="submit" value="注册" id="sub_btn" />
									
								</form>
							</div>
							
						</div>
					</div>
				</div>
			</div>
		<%@ include file="/pages/shared/footer.jsp"%>
</body>
</html>