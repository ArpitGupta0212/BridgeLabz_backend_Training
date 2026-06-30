<c:forEach var="greet" items="${greetings}">

    <div class="card">

        <div class="card-img-wrapper">

            <c:choose>

                <c:when test="${not empty greet.imagePath}">

                    <img src="${pageContext.request.contextPath}${greet.imagePath}"
                         alt="Greeting Visual"/>

                </c:when>

                <c:otherwise>

                        <span class="card-img-placeholder">
                            No Image Available
                        </span>

                </c:otherwise>

            </c:choose>

        </div>

        <div class="card-content">

            <div>

                <div class="message">
                        ${greet.message}
                </div>

                <div class="meta">
                    Created by:
                        ${greet.createdByName != null ? greet.createdByName : 'Deleted User'}
                </div>

            </div>

            <!-- ONLY ADMIN can Edit or Delete greetings -->

            <c:if test="${currentUser.role == 'ADMIN'}">

                <div class="actions">

                    <a href="${pageContext.request.contextPath}/greetings/edit?id=${greet.id}"
                       class="btn btn-edit">
                        Edit
                    </a>

                    <a href="${pageContext.request.contextPath}/greetings/delete?id=${greet.id}"
                       class="btn btn-danger"
                       onclick="return confirm('Delete this greeting?');">
                        Delete
                    </a>

                </div>

            </c:if>

        </div>

    </div>

</c:forEach>

<c:if test="${empty greetings}">

    <div class="no-records">
        No greetings found. Go ahead and create one!
    </div>

</c:if>

</div>

</body>
</html>