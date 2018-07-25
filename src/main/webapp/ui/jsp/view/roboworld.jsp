<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form" %>
<%@ page session="false" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<link type="text/css" rel="stylesheet" href="<c:url value="/ui/static/css/style.css"/>"/>
<link type="text/css" rel="stylesheet" href="<c:url value="/ui/static/css/button.css"/>"/>
<link href='https://fonts.googleapis.com/css?family=Lato:300,400,700,900' rel='stylesheet' type='text/css'>
<link rel="stylesheet" href="http://fontawesome.io/assets/font-awesome/css/font-awesome.css">

<%--Page update--%>
<div id="refresh">


    <%--robot face drawing--%>
    <table class="table table-bordered">
        <tr>
            <c:forEach items="${robotList}" var="robot">

                <td>
                    <div id="container">
                            ${robot.robotName}
                        <br> Action:
                            ${robot.robotTask.type}
                        <div id="bot" class="neutral">
                            <div id="head">
                                <div id="left-ear">
                                    <div id="left-ear-inner"></div>
                                </div>
                                <div id="face">
                                    <div id="eyes">
                                        <div id="left-eye"></div>
                                        <div id="right-eye"></div>
                                    </div>
                                    <div id="mouth"></div>
                                </div>
                                <div id="right-ear">
                                    <div id="right-ear-inner"></div>
                                </div>
                            </div>
                        </div>
                        <ul id="switches">

                            <a href="<c:url value='/addDestroy/${robot.id}'/>">Destroy</a>
                            <a href="<c:url value='/addUpgrade/${robot.id}'/>">Speak</a>
                            <a href="<c:url value='/addCharge/${robot.id}'/>">Charge</a>
                            <a href="<c:url value='/addUpgrade/${robot.id}'/>">Upgrade</a>
                        </ul>
                    </div>
                </td>

            </c:forEach>
        </tr>
    </table>

    <table class="table table-bordered">
        <tr>
            <td><a href="<c:url value='/addDestroyTask'/>">ALL Destroy</a></td>
            <td><a href="<c:url value='/addSpeakTask'/>">ALL Speak</a></td>
            <td><a href="<c:url value='/addChargeTask'/>">ALL Charge</a></td>
            <td><a href="<c:url value='/addUpgradeTask'/>">ALL Upgrade</a></td>

        </tr>
    </table>

    <div style="height: 200px; overflow: auto">
        <c:forEach items="${logList}" var="log">
            <br/>
            ${log}
        </c:forEach>
    </div>
</div>

<%--Automatic page update--%>
<script type="text/javascript">
    window.setTimeout(function () {
        $('#refresh').load('${contextPath}/begin')
    }, 2000);
</script>

