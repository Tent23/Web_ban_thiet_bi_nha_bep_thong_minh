<%@ page contentType="text/html; charset=UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
  <title>Quản lý Khóa Bảo Mật</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/key_management.css">
</head>
<body>
<div class="container">
  <h1>Quản lý Khóa Bảo Mật Cá Nhân</h1>

  <c:if test="${not empty errorMessage}">
    <div class="message error">${errorMessage}</div>
  </c:if>
  <c:if test="${not empty successMessage}">
    <div class="message success">${successMessage}</div>
  </c:if>

  <div class="key-info">
    <h2>Thông tin Khóa Công Khai Hiện Tại</h2>
    <c:choose>
      <c:when test="${not empty sessionScope.user.publicKey}">
        <p><strong>ID Khóa:</strong> ${sessionScope.user.publicKeyId}</p>
        <p><strong>Ngày tạo:</strong> <fmt:formatDate value="${sessionScope.user.publicKeyCreationDate}" pattern="dd/MM/yyyy HH:mm:ss"/></p>
        <p><strong>Trạng thái:</strong>
          <c:if test="${empty sessionScope.user.publicKeyRevocationDate}">
            <span class="status-active">Đang hoạt động</span>
          </c:if>
          <c:if test="${not empty sessionScope.user.publicKeyRevocationDate}">
            <span class="status-revoked">Đã thu hồi vào <fmt:formatDate value="${sessionScope.user.publicKeyRevocationDate}" pattern="dd/MM/yyyy HH:mm:ss"/></span>
          </c:if>
        </p>
        <p><strong>Khóa Công Khai (Base64):</strong></p>
        <textarea rows="5" cols="80" readonly>${sessionScope.user.publicKey}</textarea>
      </c:when>
      <c:otherwise>
        <p>Bạn chưa có khóa bảo mật nào được tạo.</p>
      </c:otherwise>
    </c:choose>
  </div>

  <div class="button-group">
    <form action="${pageContext.request.contextPath}/user/key-management" method="post" class="form-inline">
      <input type="hidden" name="action" value="generateNewKey">
      <button type="submit" class="generate">Tạo Khóa Mới</button>
    </form>

    <c:if test="${not empty sessionScope.user.publicKey && empty sessionScope.user.publicKeyRevocationDate}">
      <form action="${pageContext.request.contextPath}/user/key-management" method="post" class="form-inline" onsubmit="return confirm('Bạn có chắc chắn muốn thu hồi khóa này không? Sau khi thu hồi, khóa này sẽ không thể dùng để ký đơn hàng mới.');">
        <input type="hidden" name="action" value="revokeKey">
        <button type="submit" class="revoke">Báo Mất Khóa / Thu Hồi Khóa</button>
      </form>
    </c:if>
  </div>
</div>
</body>
</html>