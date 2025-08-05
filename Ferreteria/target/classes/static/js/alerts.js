document.addEventListener("DOMContentLoaded", function () {
    const successDiv = document.getElementById('swalSuccess');
    if (successDiv) {
        const msg = successDiv.getAttribute('data-msg');
        Swal.fire({
            title: '¡Éxito!',
            text: msg,
            icon: 'success',
            confirmButtonText: 'Aceptar',
            customClass: {
                popup: 'rounded-2xl shadow-lg',
                title: 'text-xl font-bold text-gray-800',
                confirmButton: 'bg-blue-600 text-white px-4 py-2 rounded hover:bg-blue-700 focus:outline-none'
            },
            buttonsStyling: false
        });
    }
});
