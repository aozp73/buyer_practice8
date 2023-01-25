<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

    <%@ include file="../layout/header.jsp" %>

        <h1>상품 구매목록</h1>
        <table border="1">
            <tr>
                <th>번호</th>
                <th>구매자</th>
                <th>구매품</th>
                <th>구매갯수</th>
            </tr>
            <c:forEach items="${purchaseList}" var="purchase">
                <tr>
                    <td>${purchase.id}</td>
                    <td>${purchase.username}</td>
                    <td>${purchase.name}</td>
                    <td>${purchase.count}</td>
                    <td>
                        <form action="/purchase/delete" method="post">
                            <input type="hidden" name="purchaseId" value="${purchase.id}">
                            <button type="submit">삭제</button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>

        <%@ include file="../layout/footer.jsp" %>