<%--
  Created by IntelliJ IDEA.
  User: MSI-PC
  Date: 2022/7/25
  Time: 19:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="page_nav">
  <c:if test="${requestScope.page.pageNo>1}">
    <a href="${requestScope.page.url}">首页</a>
    <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageNo-1}">上一页</a>
  </c:if>

  <%--页码输出的开始--%>
  <c:choose>
    <%--情况  1：如果总页码小于等于5的情况，页码的范围是：1-总页码--%>
    <c:when test="${requestScope.page.pageTotal <= 5}">
      <c:set var="begin" value="1"/>
      <c:set var="end" value="${requestScope.page.pageTotal}"/>
    </c:when>

    <%--情况2：总页码大于5的情况--%>
    <c:when test="${requestScope.page.pageTotal > 5}">
      <c:choose>
        <%--小情况 1：当前页码为前面 3 个：1，2，3 的情况，页码范围是：1-5.--%>
        <c:when test="${requestScope.page.pageNo<=3}">
          <c:set var="begin" value="1"/>
          <c:set var="end" value="5"/>
        </c:when>

        <%--小情况 2：当前页码为最后3个，8，9，10，页码范围是：总页码减 4 - 总页码--%>
        <c:when test="${requestScope.page.pageNo > requestScope.page.pageTotal-3}">
          <c:set var="begin" value="1"/>
          <c:set var="end" value="${requestScope.page.pageTotal}"/>
        </c:when>

        <%--小情况 3：页码在中间(+2和-2都在合理范围内），页码范围是：当前页码减2 - 当前页码加2--%>
        <c:otherwise >
          <c:set var="begin" value="${requestScope.page.pageNo - 2}"/>
          <c:set var="end" value="${requestScope.page.pageNo+2}"/>
        </c:otherwise>
      </c:choose>
    </c:when>
  </c:choose>
  <%--遍历输出--%>
  <c:forEach begin="${begin}" end="${end}" var="page">
    <%--如果为当前页码，不能跳转--%>
  <c:if test="${page == requestScope.page.pageNo}">
    【${page}】
  </c:if>

  <c:if test="${page != requestScope.page.pageNo}">
  <a href="${requestScope.page.url}&pageNo=${page}">${page}<a/>
    </c:if>
    </c:forEach>
    <%--页码输出的结束--%>

    <c:if test="${requestScope.page.pageNo<requestScope.page.pageTotal}">
    <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageNo+1}">下一页</a>
    <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageTotal}">末页</a>
    </c:if>

    共${requestScope.page.pageTotal}页，${requestScope.page.totalItemsCount}条记录
    到第<input value="${requestScope.page.pageNo}" name="pn" id="pn_input"/>页
    <input id="pageSkip" type="button" value="确定">
</div>
