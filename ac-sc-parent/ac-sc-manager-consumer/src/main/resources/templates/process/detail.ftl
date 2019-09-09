<#if !Request.detailMap??>
没有查询到有效数据
</#if>
<#if Request.detailMap??>
	<p>真实姓名：${Request.detailMap.realName}</p>
	<p>身份证号：${Request.detailMap.cardNum}</p>
	<p>用户昵称：${Request.detailMap.nickName}</p>
	<p>邮件地址：${Request.detailMap.emailAddr}</p>
	<p>手机号：${Request.detailMap.phoneNum}</p>
	<#if Request.detailMap.detailMapList??>
		<#list Request.detailMap.detailMapList as cert>
			<p>${cert.certName}</p>
			<p><img src="http://192.168.99.99/${cert.iconPath}"/></p>
			<br/>
		</#list>
	</#if>
</#if>