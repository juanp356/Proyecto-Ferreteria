function filterClients() {
    const input = document.getElementById("searchInput").value.toLowerCase();
    const rows = document.querySelectorAll("#clientsTable tr");

    rows.forEach(fila => {
        const columns = fila.querySelectorAll("td");
        const id = columns[0]?.textContent?.toLowerCase() || "";
        const name = columns[1]?.textContent?.toLowerCase() || "";
        fila.style.display = id.includes(input) || name.includes(input) ? "" : "none";
    });
}

function filterEmployee() {
    const input = document.getElementById("searchInput").value.toLowerCase();
    const rows = document.querySelectorAll("#employeeTable tr");

    rows.forEach(fila => {
        const columns = fila.querySelectorAll("td");
        const id = columns[0]?.textContent?.toLowerCase() || "";
        const name = columns[1]?.textContent?.toLowerCase() || "";
        fila.style.display = id.includes(input) || name.includes(input) ? "" : "none";
    });
}
