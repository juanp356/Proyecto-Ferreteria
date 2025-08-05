function filtrarClientes() {
    const input = document.getElementById("searchInput").value.toLowerCase();
    const filas = document.querySelectorAll("#clientesTabla tr");

    filas.forEach(fila => {
        const columnas = fila.querySelectorAll("td");
        const id = columnas[0]?.textContent?.toLowerCase() || "";
        const nombre = columnas[1]?.textContent?.toLowerCase() || "";
        fila.style.display = id.includes(input) || nombre.includes(input) ? "" : "none";
    });
}