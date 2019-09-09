<!DOCTYPE html>
<html lang="zh-CN">
  <head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
	<link rel="stylesheet" href="/bootstrap/css/bootstrap.min.css">
	<link rel="stylesheet" href="/css/font-awesome.min.css">
	<link rel="stylesheet" href="/css/theme.css">
	<style>
#footer {
    padding: 15px 0;
    background: #fff;
    border-top: 1px solid #ddd;
    text-align: center;
}
	</style>
  </head>
  <body>
 <div class="navbar-wrapper">
      <div class="container">
			<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
			  <div class="container">
				<div class="navbar-header">
				  <a class="navbar-brand" href="/index.html" style="font-size:32px;">尚筹网-创意产品众筹平台</a>
				</div>
            <div id="navbar" class="navbar-collapse collapse" style="float:right;">
              <ul class="nav navbar-nav">
                <li class="dropdown">
                  <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="glyphicon glyphicon-user"></i>${Session.LOGIN_MEMBER.nickName?if_exists}<span class="caret"></span></a>
                  <ul class="dropdown-menu" role="menu">
                    <li><a href="/member.html"><i class="glyphicon glyphicon-scale"></i> 会员中心</a></li>
                    <li><a href="#"><i class="glyphicon glyphicon-comment"></i> 消息</a></li>
                    <li class="divider"></li>
                    <li><a href="/index.html"><i class="glyphicon glyphicon-off"></i> 退出系统</a></li>
                  </ul>
                </li>
              </ul>
            </div>
			  </div>
			</nav>

      </div>
    </div>

    <div class="container theme-showcase" role="main">
      <div class="page-header">
        <h1>实名认证 - 申请</h1>
      </div>

		<ul class="nav nav-tabs" role="tablist">
		  <li role="presentation" ><a href="#"><span class="badge">1</span> 基本信息</a></li>
		  <li role="presentation" ><a href="#"><span class="badge">2</span> 资质文件上传</a></li>
		  <li role="presentation" ><a href="#"><span class="badge">3</span> 邮箱确认</a></li>
		  <li role="presentation" class="active"><a href="#"><span class="badge">4</span> 申请确认</a></li>
		</ul>
        
		<form action="/consumer/process/checke/code" method="post" role="form" style="margin-top:20px;">
		<p>${Request.MESSAGE?if_exists}</p>
		  <div class="form-group">
			<label for="exampleInputEmail1">验证码</label>
			<input name="checkCode" type="text" class="form-control" placeholder="请输入你邮箱中接收到的验证码">
		  </div>
          <button id="resendCodeBtn" type="button" class="btn btn-primary">重新发送验证码</button>
		  <button type="submit" class="btn btn-success">申请完成</button>
		</form>
		<hr>
    </div> <!-- /container -->
        <div class="container" style="margin-top:20px;">
            <div class="row clearfix">
                <div class="col-md-12 column">
                    <div id="footer">
                        <div class="footerNav">
                             <a rel="nofollow" href="http://www.atguigu.com">关于我们</a> | <a rel="nofollow" href="http://www.atguigu.com">服务条款</a> | <a rel="nofollow" href="http://www.atguigu.com">免责声明</a> | <a rel="nofollow" href="http://www.atguigu.com">网站地图</a> | <a rel="nofollow" href="http://www.atguigu.com">联系我们</a>
                        </div>
                        <div class="copyRight">
                            Copyright ?2017-2017 atguigu.com 版权所有
                        </div>
                    </div>
                    
                </div>
            </div>
        </div>
        <div id="confirmModal" class="modal fade" tabindex="-1" role="dialog">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">尚筹网系统消息</h4>
				</div>
				<div id="confirmModalBody" class="modal-body" style="text-align:right;">
					<form action="/consumer/process/againcheck/code" method="post">
						<input id="newEmailInput" type="text" name="newEmail" value="${Session.LOGIN_MEMBER.emailAddr}"/>
						<button id="newEmailBtn" type="submit" class="btn btn-success">点这里重新发送验证码</button>
					</form>
				</div>
			</div>
		</div>
	</div>
    <script src="/jquery/jquery-2.1.1.min.js"></script>
    <script src="/bootstrap/js/bootstrap.min.js"></script>
	<script src="/script/docs.min.js"></script>
	<script>
   		$("#resendCodeBtn").click(function(){
			$("#confirmModal").modal("show");
		});
        $('#myTab a').click(function (e) {
          e.preventDefault()
          $(this).tab('show')
        });       
	</script>
  </body>
</html>