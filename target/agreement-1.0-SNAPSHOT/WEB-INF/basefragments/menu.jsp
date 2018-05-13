<%--
  Created by IntelliJ IDEA.
  User: olga
  Date: 4/30/18
  Time: 1:07 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div>

    <ul class="menu-ul" style="margin: 0; padding: 0; list-style: none;">
        <li class="menu-li">
            <a class="menu-li-item" href="${pageContext.request.contextPath}/">Strona główna</a>
        </li>
        <li class="menu-li">
            <div class="dropdown">
                <button onclick="showDropdown()" class="dropbtn">Umowy</button>
                <div id="dropdownMenu" class="dropdown-content">
                    <a href="${pageContext.request.contextPath}/agreementsActive">Aktywne</a>
                    <a href="${pageContext.request.contextPath}/agreementsNonActive">Nieaktywne</a>
                    <a href="${pageContext.request.contextPath}/agreementsAll">Wszystkie</a>
                </div>
            </div>
        </li>
        <li class="menu-li">
            <a class="menu-li-item" href="${pageContext.request.contextPath}/systemsPage">Systemy</a>
        </li>

        <li class="menu-li">
            <a class="menu-li-item" href="${pageContext.request.contextPath}/aboutApp">O aplikacji</a>
        </li>

    </ul>

    <script>
        /* When the user clicks on the button,
        toggle between hiding and showing the dropdown content */
        function showDropdown() {
            document.getElementById("dropdownMenu").classList.toggle("show");
        }

        // Close the dropdown if the user clicks outside of it
        window.onclick = function(event) {
            if (!event.target.matches('.dropbtn')) {

                var dropdowns = document.getElementsByClassName("dropdown-content");
                var i;
                for (i = 0; i < dropdowns.length; i++) {
                    var openDropdown = dropdowns[i];
                    if (openDropdown.classList.contains('show')) {
                        openDropdown.classList.remove('show');
                    }
                }
            }
        }
    </script>

</div>